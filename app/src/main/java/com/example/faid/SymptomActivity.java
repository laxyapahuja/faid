package com.example.faid;

import android.content.Intent;
import android.media.MediaDataSource;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faid.adapters.SymptomsRecyclerAdapter;
import com.example.faid.models.AccessToken;
import com.example.faid.models.Symptom;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SymptomActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private TextView symptoms;
    private MaterialButton btnGetSelected;
    private RecyclerView mRecyclerView;
    private ArrayList mSymptoms = new ArrayList();
    private SymptomsRecyclerAdapter mSymptomRecyclerAdapter;
    public CheckBox mchk;
    public ArrayList mSelectedSymptoms = new ArrayList();
    public String diagnosisURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        mRecyclerView = findViewById(R.id.recyclerView);
        btnGetSelected = findViewById(R.id.btnGetSelected);
        RequestQueue queue = Volley.newRequestQueue(this);
        initRecyclerView();
        getResponse();

        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSymptomRecyclerAdapter.getSelected().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < mSymptomRecyclerAdapter.getSelected().size(); i++) {
                        stringBuilder.append(mSymptomRecyclerAdapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");
                        mSelectedSymptoms.add(mSymptomRecyclerAdapter.getSelected().get(i).getID());
                    }
                    Bundle bun = getIntent().getExtras();
                    String token = bun.getString("token");
                    String mtoken = token.replaceAll("\\s+","");
                    String selectedsymptoms = mSelectedSymptoms.toString().replaceAll("\\s+","");
                    diagnosisURL = "https://healthservice.priaid.ch/diagnosis?symptoms="+selectedsymptoms+"&gender=male&year_of_birth=2002&token="+mtoken+"&format=json&language=en-gb";
                    System.out.println(mSelectedSymptoms);
                    Toast.makeText(SymptomActivity.this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                    goToDiagnosis();
                } else {
                    Toast.makeText(SymptomActivity.this, "No selection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initRecyclerView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(linearLayoutManager);
    mSymptomRecyclerAdapter = new SymptomsRecyclerAdapter(mSymptoms);
    mRecyclerView.setAdapter(mSymptomRecyclerAdapter);
    }

    private void getResponse() {
        Bundle bun = getIntent().getExtras();
        String token = bun.getString("token");
        String mtoken = token.replaceAll("\\s+","");
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://healthservice.priaid.ch/symptoms?token="+mtoken+"&format=json&language=en-gb";

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
                                symp.setID(symptom.getString("ID"));
                                symp.setName(symptom.getString("Name"));
                                symp.setSelect(false);
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

    public void goToDiagnosis() {
        startActivity(new Intent(SymptomActivity.this,DiagnosisActivity.class));
        System.out.println(diagnosisURL);
        Intent intent = new Intent(this, DiagnosisActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", diagnosisURL);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
