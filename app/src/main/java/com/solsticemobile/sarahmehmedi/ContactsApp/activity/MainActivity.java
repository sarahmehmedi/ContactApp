package com.solsticemobile.sarahmehmedi.ContactsApp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.solsticemobile.sarahmehmedi.ContactsApp.R;
import com.solsticemobile.sarahmehmedi.ContactsApp.constant.Constants;
import com.solsticemobile.sarahmehmedi.ContactsApp.model.ContactModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog progressDialog;
    android.widget.LinearLayout parentLayout;

    private Handler handler = new Handler(new Handler.Callback() {
        //checks for errors and displayscontactlist
        @Override
        public boolean handleMessage(Message msg) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();

            if (msg.what == 1) {
                displayContactList();
            } else if (msg.what == 2) {
                Toast.makeText(getApplicationContext(), "JSON has Error", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(getApplicationContext(), "Contains Null Pointer Exception", Toast.LENGTH_LONG);
            }
            return false;
        }
    });
    ArrayList<ContactModel> jsonContacts = new ArrayList<ContactModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        callContactAPI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void callContactAPI() {
        //shows loading icon when app opens
        progressDialog = ProgressDialog
                .show(this, "", getString(R.string.loading));

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_CONTACTS, new Response.Listener<String>()

        {
            //goes through each array in json and adds to model, including the JSON url in "detailsURL"
            @Override
            public void onResponse(final String responseString) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray contactList = new JSONArray(responseString);
                            for(int i =0; i<contactList.length(); i++) {
                                final JSONObject contact = (JSONObject) contactList.getJSONObject(i);
                                ContactModel contactModel = new ContactModel();
                                contactModel.setName(contact.getString(Constants.TAG_NAME));
                                contactModel.setEmployeeId(contact.getInt(Constants.TAG_EMPLOYEEID));
                                contactModel.setCompany(contact.getString(Constants.TAG_COMPANY));

                                String detailsURL = contact.getString(Constants.TAG_DETAILSURL);
                                try{
                                    JSONObject details = new JSONObject(readUrl(detailsURL));

                                    int employeeId = (int) details.get(Constants.TAG_EMPLOYEEID);
                                    contactModel.setEmployeeId(employeeId);

                                    String largeImageURL = (String) details.get(Constants.TAG_LARGEIMAGEURL);
                                    contactModel.setLargeImageURL(largeImageURL);

                                    String email = (String) details.get(Constants.TAG_EMAIL);
                                    contactModel.setEmail(email);

                                    String website = (String) details.get(Constants.TAG_WEBSITE);
                                    contactModel.setWebsite(website);

                                    JSONObject address = details.getJSONObject(Constants.TAG_ADDRESS);

                                    String street = (String) address.get(Constants.TAG_STREET);
                                    contactModel.setStreet(street);

                                    String city = (String) address.get(Constants.TAG_CITY);
                                    contactModel.setCity(city);

                                    String state = (String) address.get(Constants.TAG_STATE);
                                    contactModel.setState(state);

                                    String country = (String) address.get(Constants.TAG_COUNTRY);
                                    contactModel.setCountry(country);

                                    String zip = (String) address.get(Constants.TAG_ZIP);
                                    contactModel.setZip(zip);

                                    double latitude = (double) address.get(Constants.TAG_LAT);
                                    contactModel.setLatitude(latitude);

                                    double longitude = (double) address.get(Constants.TAG_LONG);
                                    contactModel.setLongitude(longitude);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                contactModel.setDetailsURL(contact.getString(Constants.TAG_DETAILSURL));


                                contactModel.setSmallImageURL(contact.getString(Constants.TAG_SMALLIMAGEURL));
                                contactModel.setBirthdate(contact.getString(Constants.TAG_BIRTHDATE));

                                JSONObject phone = contact.getJSONObject(Constants.TAG_PHONE);
                                contactModel.setWorkPhone(phone.getString(Constants.TAG_WORKPHONE));
                                contactModel.setHomePhone(phone.getString(Constants.TAG_HOMEPHONE));
                                // exception for areas without "mobile" field
                                if(phone.has(Constants.TAG_MOBILEPHONE))
                                    contactModel.setMobilePhone(phone.getString(Constants.TAG_MOBILEPHONE));
                                else
                                    contactModel.setMobilePhone("");

                                jsonContacts.add(contactModel);

                            } //catches exceptions to help aid in debugging
                            handler.sendEmptyMessage(1);
                        } catch (JSONException e) {
                            handler.sendEmptyMessage(2);
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            handler.sendEmptyMessage(3);
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.TIMEOUT_IN_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(this).add(stringRequest);
    }
    //reads URL for detailsURL
    private static String readUrl(String urlString) throws Exception {
         BufferedReader reader = null;
         try {
             URL url = new URL(urlString);
             reader = new BufferedReader(new InputStreamReader(url.openStream()));
             StringBuffer buffer = new StringBuffer();
             int read;
             char[] chars = new char[1024];
             while ((read = reader.read(chars)) != -1)
                 buffer.append(chars, 0, read);

             return buffer.toString();
         } finally {
             if (reader != null)
                reader.close();
         }
}
    //displays contacts using library to help with design/user experience
    private void displayContactList() {
        for (int i = 0; i < jsonContacts.size(); i++) {
            final ContactModel contactModel = jsonContacts.get(i);
            Holder holder = new Holder();
            View view = LayoutInflater.from(this).inflate(R.layout.inflate_contact, null);
            final com.rey.material.widget.LinearLayout inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            view.setTag(i);
            holder.tvName.setText(contactModel.getName());
            inflateParentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                    intent.putExtra("ContactDetail", contactModel);
                    startActivity(intent);

                }
            });
            parentLayout.addView(view);
        }
    }

    private class Holder {
        TextView tvName;
    }
}
