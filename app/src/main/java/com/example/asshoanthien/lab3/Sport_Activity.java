package com.example.asshoanthien.lab3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sport_Activity extends AppCompatActivity {
    public int a=0;
    private Button mButton;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private TextView mTvCauHoi;
    private TextView mTvNext;
    public int m=0;
    Adapter adapterSport;
    ArrayList<Sport> modelSports;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_);
        initView();

        GetQsSport getQsSport = new GetQsSport();
        getQsSport.execute("http://dotplays.com/android/lab3.json");



    }

    private void initView() {

        mTvCauHoi = findViewById(R.id.tv_as1);
        mLv = findViewById(R.id.lv);

        modelSports = new ArrayList<Sport>();
        adapterSport = new Adapter(Sport_Activity.this, modelSports);
        mLv.setAdapter(adapterSport);

    }

    public class GetQsSport extends AsyncTask<String, Long, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                String data = "";

                while (scanner.hasNext()) {
                    data = data + scanner.nextLine();
                }

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);

            try { JSONObject root = new JSONObject(s);
                JSONObject quiz = root.getJSONObject("quiz");
                final JSONArray maths = quiz.getJSONArray("sport");
                for(int j=0; j<1;j++){

                }
                for (int i=1;i<maths.length();i++) {

                    final JSONObject q1 = maths.getJSONObject(i-1);
                    final JSONObject q2 = maths.getJSONObject(i);
                    final String qs = q1.getString("question");
                    final String answer = q1.getString("answer");

                    JSONArray jsonArrayOption = q1.getJSONArray("options");
                    for (m = 0; m < jsonArrayOption.length(); m++) {
                        String as = jsonArrayOption.getString(m);
                        modelSports.add(new Sport(as));

                    }
                    mTvCauHoi.setText(qs);
                    adapterSport.notifyDataSetChanged();



                    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            String selected =((TextView)view.findViewById(R.id.tv_as21)).getText().toString();


                            modelSports.clear();
                            adapterSport.notifyDataSetChanged();
                            if (selected.equals(answer)) {
                                a = a +1;
                                JSONArray jsonArrayOption = null;
                                try {

                                    jsonArrayOption = q2.getJSONArray("options");
                                    final String answer1 = q2.getString("answer");
                                    for ( m = 0; m < jsonArrayOption.length(); m++) {
                                        String as = jsonArrayOption.getString(m);
                                        modelSports.add(new Sport(as));
                                        mTvCauHoi.setText(qs);
                                    }
                                    adapterSport.notifyDataSetChanged();
                                    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                            String selected =((TextView)view.findViewById(R.id.tv_as21)).getText().toString();

                                            if (selected.equals(answer1)) {
                                                a = a +1;
                                                Intent intent = new Intent(Sport_Activity.this, Result_Activity.class);
                                                intent.putExtra("mark",a);
                                                intent.putExtra("bien",i);
                                                startActivity(intent);
                                            } else {

                                                Intent intent = new Intent(Sport_Activity.this, Result_Activity.class);
                                                intent.putExtra("mark",a);
                                                intent.putExtra("bien",i);
                                                startActivity(intent);
                                            }

//                                               Log.e("mark" ,"" + a);
//                                               Intent intent = new Intent(Maths_Activity.this, Result_Activity.class);
//                                               intent.putExtra("mark",a);
//                                               intent.putExtra("bien",i);
//                                               startActivity(intent);

                                        }
                                    });

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {


                                try {


                                    JSONObject q2 = maths.getJSONObject(i);
                                    String qs = q2.getString("question");
                                    final String answer = q2.getString("answer");
                                    Log.e("Qs", qs);
                                    mTvCauHoi.setText(qs);
                                    JSONArray jsonArrayOption = q2.getJSONArray("options");
                                    for (int j = 0; j < jsonArrayOption.length(); j++) {
                                        String as = jsonArrayOption.getString(j);
                                        modelSports.add(new Sport(as));

                                    }
                                    adapterSport.notifyDataSetChanged();
                                    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                            String selected =((TextView)view.findViewById(R.id.tv_as21)).getText().toString();

                                            if (selected.equals(answer)) {
                                                a = a +1;
                                            }

                                            Log.e("mark" ,"" + a);
                                            Intent intent = new Intent(Sport_Activity.this, Result_Activity.class);
                                            intent.putExtra("mark",a);
                                            intent.putExtra("bien",i);
                                            startActivity(intent);

                                        }
                                    });

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }

                    });}

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }}}