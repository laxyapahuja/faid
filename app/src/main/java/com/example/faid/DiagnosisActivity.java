package com.example.faid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.faid.models.Symptom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DiagnosisActivity extends AppCompatActivity {

    private ArrayList mDiagnosis = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        getResponse();
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
                            for (int i = 0; i < 281; i++) {
                                // create a JSONObject for fetching single user data
                                JSONObject symptom = response.getJSONObject(i);
                                // fetch email and name and store it in arraylist
                                Symptom symp = new Symptom();
                                symp.setID(symptom.getString("ID"));
                                symp.setName(symptom.getString("Name"));
                                symp.setSelect(false);
                                mDiagnosis.add(symp);
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
