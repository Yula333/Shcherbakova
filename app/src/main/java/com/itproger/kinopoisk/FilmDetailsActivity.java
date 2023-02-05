package com.itproger.kinopoisk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

public class FilmDetailsActivity extends AppCompatActivity {
    private static String Id = "840821";
    private static String JSON_URL_filmID = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"+Id;
    private static final String API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b";

    ImageView image_details;
    TextView filmName_details;
    TextView description;
    TextView genre;
    TextView country;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);

        image_details = findViewById(R.id.image_details);
        filmName_details = findViewById(R.id.filmName_details);
        description = findViewById(R.id.description);
        genre = findViewById(R.id.genre);
        country = findViewById(R.id.country);

        FilmDetailsActivity.GetData getData = new FilmDetailsActivity.GetData();
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
                    url=new URL(JSON_URL_filmID);
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

                    FilmModel model = new FilmModel();
                    model.setId(jsonObject.getInt("kinopoiskId"));
                    model.setNameRu(jsonObject.getString("nameRu"));
                    model.setDescription(jsonObject.getString("description"));
                    model.setGenre(jsonObject.getString("genres"));
                    model.setCountry(jsonObject.getString("countries"));

                model.setImg(jsonObject.getString("posterUrlPreview"));

                    filmName_details.setText(model.getNameRu());

                //используем библиотеку Glide для отображения изображений
//                Glide.with(context)
//                        .load(model.getImg())
//                        .into(image_details);
                    country.setText(model.getCountry());
                    description.setText(model.getDescription());
                    genre.setText(model.getGenre());

                } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }
}