package com.example.faid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.faid.adapters.DiagnosisRecyclerAdapter;
import com.example.faid.adapters.SymptomsRecyclerAdapter;
import com.example.faid.models.Diagnosis;
import com.example.faid.models.Symptom;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DiagnosisActivity extends AppCompatActivity {

    private ArrayList mDiagnosis = new ArrayList();
    private DiagnosisRecyclerAdapter mDiagnosisRecyclerAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        initRecyclerView();
        getResponse();
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.diagnosisRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mDiagnosisRecyclerAdapter = new DiagnosisRecyclerAdapter(mDiagnosis);
        mRecyclerView.setAdapter(mDiagnosisRecyclerAdapter);
    }


    private void getResponse() {
        Bundle bundle = getIntent().getExtras();
        String diagnosisURL = bundle.getString("url");
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, diagnosisURL, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            // fetch JSONArray named users
                            // implement for loop for getting users list data
                            for (int i = 0; i < response.length(); i++) {
                                // create a JSONObject for fetching single user data
                                JSONObject diag = response.getJSONObject(i);
                                JSONObject issue = diag.getJSONObject("Issue");
                                // fetch email and name and store it in arraylist
                                Diagnosis diagnosis = new Diagnosis();
                                diagnosis.setAccuracy(issue.getString("Accuracy"));
                                diagnosis.setDiagnosis(issue.getString("Name"));
                                diagnosis.setProfName(issue.getString("ProfName"));
                                mDiagnosis.add(diagnosis);
                            }
                            mDiagnosisRecyclerAdapter.notifyDataSetChanged();
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
