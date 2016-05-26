ContactsApp Documentation - Sarah Mehmedi

Description: ContactsApp is a Java Android application that uses data from a JSON url to display their personal information in a presentable way.

Purpose: To display contacts and contact details in a different but user friend way.

How it works: Use of Volley to help parse through the JSON url to collect the necessary key and values for each person. After parsing the items are stored in a usable array that is called to display each value so the user can see it.

Problems Encountered: I encountered a few problems when I tried running and decided to use the android debugger and run through line by line to see where the problem was occurring. I found out that some people do not have a mobile key at all, so I added an exception to fix that. Another problem that I encountered was trying to figure out how to parse through another JSON url while parsing through one. I decided to use a url reader and basically do the same thing except use object instead of an array because the items were in a JSONObject. 

Future Plans:If I did more research and found out that people liked adding a map to the contacts, it would be as simple as using a Google API and using the latitude and longitude given in the JSON url. Another idea is that I could use the favorite values to set up who is displayed first.
