package com.example.akshay.demohttp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends ListActivity {

    private static String url = "your_url";
    // JSON Node names
    private static final String TAG_All_USER = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE = "contact_no";
    private static final String TAG_USERGROUP = "usergroup";
    private static final String TAG_USERGROUP_ID = "id";
    private static final String TAG_USERGROUP_TITLE = "group_title";
    ListView listView1;
    ArrayList<User> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getalluser = (Button) findViewById(R.id.getall);
        listView1 = (ListView) findViewById(android.R.id.list);

        getalluser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new NetworkOperation().execute();
            }
        });
    }

    private class NetworkOperation extends AsyncTask<Void, Void, Void> {

        int UserParsedData;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
            proDialog = new ProgressDialog(MainActivity.this);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            WebRequest webreq = new WebRequest();

            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GETRequest);
            Log.d("Response: ", "> " + jsonStr);
            ParseJSON(jsonStr);
            return null;
        }

        @Override
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            if (proDialog.isShowing())
                proDialog.dismiss();

            UserAdapter adapter = new UserAdapter(getApplicationContext(), +++++++++++);
            listView1.setAdapter(adapter);

        }
    }

    private int ParseJSON(String json) {
        if (json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);
                JSONArray jsonarray = jsonObj.getJSONArray(TAG_All_USER);

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject c = jsonarray.getJSONObject(i);
                    JSONObject usergroup = c.getJSONObject(TAG_USERGROUP);
                    User userObj = new User();
                    userObj.setIcon(R.drawable.tu);
                    userObj.setName(c.getString(TAG_NAME));
                    userObj.setEmail(c.getString(TAG_EMAIL));
                    userObj.setUsergroup_title(usergroup.getString(TAG_USERGROUP_TITLE));

                    usersList.add(userObj);
                }
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            Log.e("ServiceHandler", "No data received from HTTP request");
            return 0;
        }
    }
}
