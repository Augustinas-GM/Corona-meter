package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    public static final String COVID_API = "https://covid19-api.weedmark.systems/api/v1/stats";
    private ArrayList<Corona> coronaList = new ArrayList<Corona>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AsyncFetch asyncFetch = new AsyncFetch();
        asyncFetch.execute();
    }

    private class AsyncFetch extends AsyncTask<String, String, JSONObject> {
        ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getResources().getString(R.string.search_loading_data));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                JSONObject jsonObject = JSON.readJsonFromUrl(COVID_API);
                return jsonObject;
            } catch (IOException e) {
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data) + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            } catch (JSONException e) {
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data) + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            progressDialog.dismiss();

            int statusCode = 0;
            try {
                statusCode = jsonObject.getInt("statusCode");
            } catch (JSONException e) {
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data) + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
            if(statusCode == HttpURLConnection.HTTP_OK) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = JSON.getJSONArray(jsonObject);
                    coronaList = JSON.getList(jsonArray);
                    System.out.println("Italy covidStats:" + JSON.getCoronaListByCountry(coronaList, "Italy"));
                } catch (JSONException e) {
                    System.out.println(getResources().getString(R.string.search_error_reading_data) + e.getMessage());
                }

            } else {
              String message = null;
                try {
                    message = jsonObject.getString("message");
                } catch (JSONException e) {
                    Toast.makeText(
                            SearchActivity.this,
                            getResources().getString(R.string.search_error_reading_data) + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data) + message,
                        Toast.LENGTH_LONG
                ).show();
            }
        }
    }



}