package com.example.faid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.faid.adapters.SymptomsRecyclerAdapter;
import com.example.faid.models.Symptom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SymptomActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private TextView symptoms;
    private RecyclerView mRecyclerView;
    private ArrayList mSymptoms = new ArrayList();
    private SymptomsRecyclerAdapter mSymptomRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        mRecyclerView = findViewById(R.id.recyclerView);
        RequestQueue queue = Volley.newRequestQueue(this);
        initRecyclerView();
        getResponse();
    }

    private void setFakeSymptoms() {
        for (int i = 0; i < 1000; i++) {
            Symptom symptom = new Symptom();
            symptom.setID(1);
            symptom.setName("Symptom " + i);
            mSymptoms.add(symptom);
        }
        mSymptomRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mSymptomRecyclerAdapter = new SymptomsRecyclerAdapter(mSymptoms);
        mRecyclerView.setAdapter(mSymptomRecyclerAdapter);
    }

    private void getResponse() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://healthservice.priaid.ch/symptoms?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImxheHlhLnBhaHVqYThAZ21haWwuY29tIiwicm9sZSI6IlVzZXIiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9zaWQiOiIyNjAyIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy92ZXJzaW9uIjoiMTA5IiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6IjEwMCIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IkJhc2ljIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9sYW5ndWFnZSI6ImVuLWdiIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9leHBpcmF0aW9uIjoiMjA5OS0xMi0zMSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcHN0YXJ0IjoiMjAxOS0wNi0xOCIsImlzcyI6Imh0dHBzOi8vYXV0aHNlcnZpY2UucHJpYWlkLmNoIiwiYXVkIjoiaHR0cHM6Ly9oZWFsdGhzZXJ2aWNlLnByaWFpZC5jaCIsImV4cCI6MTU2NTUxMDQ3OSwibmJmIjoxNTY1NTAzMjc5fQ._9VkLh2TfRbvQkSGu88wlGPeIfrh2fiSkXp4S042bEQ&format=json&language=en-gb";

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            // fetch JSONArray named users
                            // implement for loop for getting users list data
                            for (int i = 0; i < 281; i++) {
                                // create a JSONObject for fetching single user data
                                JSONObject symptom = response.getJSONObject(i);
                                // fetch email and name and store it in arraylist
                                Symptom symp = new Symptom();
                                symp.setID(symptom.getInt("ID"));
                                symp.setName(symptom.getString("Name"));
                                mSymptoms.add(symp);
                            }
                            mSymptomRecyclerAdapter.notifyDataSetChanged();
                            } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                    },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(getRequest);
    }
}
