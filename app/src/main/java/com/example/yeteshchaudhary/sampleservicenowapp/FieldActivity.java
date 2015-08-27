package com.example.yeteshchaudhary.sampleservicenowapp;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;

import android.view.View;
import android.widget.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import java.util.List;

public class FieldActivity extends Activity {

    String mTableName="";
    String jsonString="";
    Context mContext =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        mTableName = getIntent().getStringExtra("table");
        mContext=getApplicationContext();

        final Button loadButton = (Button) findViewById(R.id.button2);
        loadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner1);
                String fieldString = spinner.getSelectedItem().toString();

                Intent i = new Intent(FieldActivity.this, CustomListActivity.class);
                i.putExtra("json",jsonString);
                i.putExtra("field", fieldString);
                startActivity(i);

            }
        });


        new HttpGetTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_field, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class HttpGetTask extends AsyncTask<Void, Void,String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                return new GetAction().getOtherAction();

            }
            catch(Exception e){System.out.println("Error !!!");}
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            jsonString = result;
            List<String> keysList = new JsonParser().getKeys(result);

            Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
            String[] items = keysList.toArray(new String[keysList.size()]);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, items);
            dropdown.setAdapter(adapter);
        }
    }


    public class GetAction {


        public String getOtherAction()
        {

            String username = <username>;
            String host = <instance>;
            String password = <password>;


            String urlBasePath = "<instance>/now/table/"+ mTableName +"?sysparm_limit=10";
            String urlApiCall_FindAllRepositories = urlBasePath;

            try {
                HttpClient client = new DefaultHttpClient();

                AuthScope as = new AuthScope(host, 443);
                UsernamePasswordCredentials upc = new UsernamePasswordCredentials(
                        username, password);

                ((AbstractHttpClient) client).getCredentialsProvider()
                        .setCredentials(as, upc);

                BasicHttpContext localContext = new BasicHttpContext();

                BasicScheme basicAuth = new BasicScheme();
                localContext.setAttribute("preemptive-auth", basicAuth);

                HttpHost targetHost = new HttpHost(host, 443, "https");

                HttpGet httpget = new HttpGet(urlApiCall_FindAllRepositories);
                httpget.setHeader("Content-Type", "application/xml");

                HttpResponse response = client.execute(targetHost, httpget,
                        localContext);

                HttpEntity entity = response.getEntity();
                Object content = EntityUtils.toString(entity);

                return content.toString();

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Yetesh Chaudhary", "Error: " + e.getMessage());
            }
            return null;
        }

    }




}




