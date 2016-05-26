package com.solsticemobile.sarahmehmedi.ContactsApp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.solsticemobile.sarahmehmedi.ContactsApp.R;
import com.solsticemobile.sarahmehmedi.ContactsApp.model.ContactModel;


public class ContactDetailActivity extends Activity {
    ContactModel contactDetail;
    TextView contactName;
    TextView largeImage;
    TextView smallImage;
    TextView contactCompany;
    TextView contactBirthdate;
    TextView contactEmployeeId;
    TextView workPhone;
    TextView homePhone;
    TextView mobilePhone;

    TextView contactFavorite; //can be used later if other items want to be implemented
    TextView contactEmail;
    TextView contactWebsite;
    TextView contactAddress;
    TextView contactCoordinates; //can be used if other items want to be implemented



    private ProgressDialog progressDialog;
    ImageView ivContactImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail_layout);
        getAllWidgets();
        contactDetail = getContactDetail();

        setContactDetail();
    }

    private void getAllWidgets() {

        contactAddress = (TextView) findViewById(R.id.tvContactDetailAddress);
        contactName = (TextView) findViewById(R.id.tvContactDetailName);
        contactEmployeeId = (TextView) findViewById(R.id.tvContactDetailEmployeeId);
        contactCompany = (TextView) findViewById(R.id.tvContactDetailCompany);
        homePhone = (TextView) findViewById(R.id.tvContactDetailHome);
        mobilePhone = (TextView) findViewById(R.id.tvContactDetailMobile);
        workPhone = (TextView) findViewById(R.id.tvContactDetailWork);
        ivContactImage = (ImageView) findViewById(R.id.ivContactImage);
        contactBirthdate = (TextView) findViewById(R.id.tvContactDetailBirthdate);
        contactEmail = (TextView) findViewById(R.id.tvContactDetailEmail);
        contactWebsite = (TextView) findViewById(R.id.tvContactDetailWebsite);

    }

    private ContactModel getContactDetail() {
        ContactModel contactDetails = (ContactModel) getIntent().getSerializableExtra("ContactDetail");
        return contactDetails;
    }
//sets the contact details to the textview
    private void setContactDetail() {
        contactName.setText(contactDetail.getName());
        mobilePhone.setText(contactDetail.getMobilePhone());
        workPhone.setText(contactDetail.getWorkPhone());
        homePhone.setText(contactDetail.getHomePhone());
        contactEmployeeId.setText("" + contactDetail.getEmployeeId());
        contactCompany.setText(contactDetail.getCompany());
        contactEmail.setText(contactDetail.getEmail());
        contactWebsite.setText(contactDetail.getWebsite());
        contactBirthdate.setText(contactDetail.getBirthdate());
        contactAddress.setText(contactDetail.getStreet() + ", " + contactDetail.getCity() + ", "
                + contactDetail.getState() + " " + contactDetail.getZip());


        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(contactDetail.getLargeImageURL(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivContactImage.setImageBitmap(bitmap);
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        Volley.newRequestQueue(this).add(request);

    }
}
