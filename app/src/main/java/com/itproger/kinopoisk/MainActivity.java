package com.itproger.kinopoisk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

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
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS";
    private static final String API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b";

    private RecyclerView recyclerView;
    List<FilmModel> filmsList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmsList = new ArrayList<>();
        recyclerView = findViewById(R.id.top_films_recyclerView);


        GetData getData = new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... strings) {

            String current = "";
            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try{
                    url=new URL(JSON_URL);
                    urlConnection=(HttpsURLConnection) url.openConnection();
                    urlConnection.setRequestProperty("X-API-KEY", API_KEY);
                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);

                    int data=isr.read();
                    while(data!=-1){
                        current+=(char) data;
                        data = isr.read();
                    }

                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(urlConnection!=null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("films");

                for (int i = 0 ; i< jsonArray.length() ; i++){

                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    FilmModel model = new FilmModel();
                    model.setId(jsonObj.getInt("filmId"));
                    model.setNameRu(jsonObj.getString("nameRu"));
                    model.setYear(jsonObj.getString("year"));
                    model.setImg(jsonObj.getString("posterUrlPreview"));

                    filmsList.add(model);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(filmsList);
        }
    }

    private void PutDataIntoRecyclerView(List<FilmModel> movieList){

        FilmsAdapter filmsAdapter = new FilmsAdapter(this, filmsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(filmsAdapter);
    }
}