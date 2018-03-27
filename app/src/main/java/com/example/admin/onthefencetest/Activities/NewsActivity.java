package com.example.admin.onthefencetest.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.onthefencetest.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class NewsActivity extends AppCompatActivity {

    ProgressBar pb;
    SimpleDraweeView photo;
    TextView title, main, doptitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_);

        link = getIntent().getStringExtra("News");
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        main = findViewById(R.id.main);
        title = findViewById(R.id.title);
        doptitle = findViewById(R.id.dop_title);
        photo = findViewById(R.id.photo);



        new MeTask().execute();

    }

    String link,title1,main1,photo1,doptitle1;
    int go;


    class MeTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc = Jsoup.connect(link).get();
                Element tit = doc.selectFirst(".l-col31_1__in-inner").selectFirst(".b-content-header");
                title1 = tit.text();
                tit = doc.selectFirst(".b-content-wrapper").selectFirst(".b-news__dop-title");
                doptitle1=tit.text();
                Element e = doc.selectFirst(".b-content-wrapper").selectFirst(".content");

                main1 = e.text();

                tit = doc.selectFirst(".b-content-wrapper").selectFirst(".b-lead-image").selectFirst("img");
                photo1=tit.absUrl("src");


            } catch (Exception e) {
                Log.e("News", e.getMessage());
                title1 = "Что-то пошло не так, попробуйте снова чуть позже";

            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            photo.setImageURI(photo1);
            title.setText(title1);
            main.setText(main1);
            doptitle.setText(doptitle1);

            pb.setVisibility(View.INVISIBLE);

        }

    }
}
