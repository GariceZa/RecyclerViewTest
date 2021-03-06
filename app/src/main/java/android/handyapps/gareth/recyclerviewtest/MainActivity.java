package android.handyapps.gareth.recyclerviewtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //--------------------------------------------------

        // Setting up the swipe refresh layout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new LoadAlerts().execute();
            }
        });
        //---------------------------------------------------

        // Executing async task to retrieve data from api
        new LoadAlerts().execute();
        //--------------------------------------------------
    }

    private class LoadAlerts extends AsyncTask<Void, Void, String> {

        ArrayList<String> time = new ArrayList<>();
        ArrayList<String> msg = new ArrayList<>();

        @Override
        protected String doInBackground(Void... params) {
            String resp = null;
            String url = "http://animalalert.garethprice.co.za/selectAlerts.php";
            HttpEntity httpEntity;

            try {
                // Retrieve data from API
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                httpEntity = httpResponse.getEntity();
                //--------------------------------------------------

                if (httpEntity != null) {
                    resp = EntityUtils.toString(httpEntity);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.v("Response", s);

            try {
                // Create JSON Array from response
                JSONArray jsonArray = new JSONArray(s);

                for (int i = 0; i < jsonArray.length(); i++) {

                    try {
                        // Add data to array lists
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        time.add(jsonObject.getString("AlertTime"));
                        msg.add(jsonObject.getString("AlertDescription"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Specify an adapter
            mAdapter = new MyAdapter(time, msg);
            // Set the adapter
            mRecyclerView.setAdapter(mAdapter);
            // Set swipe refresh to false if swiped
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
