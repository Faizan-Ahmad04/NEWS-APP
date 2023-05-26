package com.example.allnewshub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsCategoriesActivity extends AppCompatActivity {

    EditText userName, userPassword, confirmPassword;
   public static Button buttonLogin;
   public static Button buttonSignup;

   TextView newsText;
    public void onLoginClicked(View view) {
        // Build an Intent to open another activity
        Intent loginIntent = new Intent(this,LogInPage.class);
        startActivity(loginIntent);
    }


    public void onSignupClicked(View view) {
        // Build an Intent to open another activity
        Intent signupIntent = new Intent(this,SignUpPage.class);
        startActivity(signupIntent);
    }


    public void onsportsClicked(View view) {
        // Build an Intent to open another activity
        Intent signupIntent = new Intent(this,NewsList.class);
        startActivity(signupIntent);
    }


    public class DownloadNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                while(data != -1) {
                    char ch = (char) data;
                    result += ch;
                    data = inputStreamReader.read();
                }
                return  result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return  null;
            } catch (IOException e) {
                e.printStackTrace();
                return  null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Log.i("result",s);

            // JSON parsing code
            try {
                JSONObject jsonObject = new JSONObject(s);
                String status = jsonObject.getString("status");
                String totalResults = jsonObject.getString("totalResults");
                String results = jsonObject.getString("results");

                Log.i("result status",status);
                Log.i("total result",totalResults);
                // Log.i("result results",results);

                JSONArray resultArray = new JSONArray(results);
                for(int i = 0; i<resultArray.length(); i++) {
                    JSONObject jsonPart = resultArray.getJSONObject(i);
                    titlesList.add(jsonPart.getString("title"));
                    linkList.add(jsonPart.getString("link"));
                    Log.i("result title", jsonPart.getString("title"));
                    Log.i("result image", jsonPart.getString("image_url"));
                }

                callListActivity();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void callListActivity() {
        Intent intent = new Intent(NewsCategoriesActivity.this,NewsList.class);
        intent.putExtra("data",titlesList);
        intent.putExtra("link",linkList);
        startActivity(intent);
    }

    ArrayList<String> titlesList;
    ArrayList<String> linkList;
    // Get News Title
    public void onWordWideClicked(View view) {
        DownloadNewsTask downloadNewsTask = new DownloadNewsTask();

        downloadNewsTask.execute("https://newsdata.io/api/1/news?apikey=pub_147277cc393e219ef18a46943dba3f2c55229&language=fr,en");
    }

    Toolbar toolbarNewsCategories;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_categories);

        // all id
        buttonLogin = findViewById(R.id.buttonLoginHome);
        buttonSignup = findViewById(R.id.buttonSignupHome);

        Intent intent = getIntent();

        toolbarNewsCategories = findViewById(R.id.myToolbarNewsCategories);

        // for AppCompact toolbar
        setSupportActionBar(toolbarNewsCategories);

        getSupportActionBar().setTitle("Home");

        titlesList = new ArrayList<>();
        linkList = new ArrayList<>();


        // Animation News Text
//        newsText = findViewById(R.id.newsTextView);
//        Animation myTextAnimation = AnimationUtils.loadAnimation(NewsCategoriesActivity.this,R.anim.news_text_animation);
//        newsText.startAnimation(myTextAnimation);



        if(LogInPage.hide == -1) {
            buttonLogin.setVisibility(View.INVISIBLE);
            buttonSignup.setVisibility(View.INVISIBLE);
            newsText = findViewById(R.id.newsTextView);

            Animation myTextAnimation = AnimationUtils.loadAnimation(NewsCategoriesActivity.this,R.anim.news_text_animation);
            myTextAnimation.setRepeatMode(myTextAnimation.INFINITE);
            newsText.startAnimation(myTextAnimation);



        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.news_categories_menu,menu);
        return true;
    }
}