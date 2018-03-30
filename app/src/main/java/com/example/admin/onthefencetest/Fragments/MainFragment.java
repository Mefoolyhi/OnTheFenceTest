package com.example.admin.onthefencetest.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.onthefencetest.Activities.FiltersActivity;
import com.example.admin.onthefencetest.Adapters.ScheduleAdapter;
import com.example.admin.onthefencetest.R;
import com.example.admin.onthefencetest.Units.Performance;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class MainFragment extends Fragment {


    ImageView filters;
    Spinner type;
    ListView rv;
    ProgressBar pb;
    TextView defaultt;




    public MainFragment() {

    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_main, container, false);
        rv = v.findViewById(R.id.rv);
        filters = v.findViewById(R.id.filters);
        type = v.findViewById(R.id.spinner);
        defaultt = v.findViewById(R.id.default_text);
        pb = v.findViewById(R.id.progressBar);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setSelection(0);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choose = getResources().getStringArray(R.array.types);
                Toast toast = Toast.makeText(getActivity(),
                        "Ваш выбор: " + choose[i], Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        pb.setVisibility(View.INVISIBLE);



        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),FiltersActivity.class);
                startActivityForResult(intent,0);
            }
        });



        new MeTask().execute();
        return v;
    }
    ArrayList<Performance> news;

    class MeTask extends AsyncTask<Void, Void, Void> {
        boolean error = false;
        ArrayList<String> titles;
        ArrayList<String> places;
        ArrayList<String> datas;
        ArrayList<String> costs;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                news.clear();
                //ошибка тута
                Document doc = Jsoup.connect("http://www.aekaterinburg.com/relax/top").get();
                Log.e("News",doc.toString());
                Elements e = doc.select(".a-main-block amb-category").select(".amb-020").select(".amb-021").select("a[href]");
                titles.clear();
                for (Element contains : e){
                        titles.add(contains.text());
                }
                places.clear();
                e = doc.select(".a-main-block amb-category").select(".amb-020").select(".amb-023").select(".ta-lines-2").select("b");
                for (Element contains : e){
                    places.add(contains.text());
                }
                datas.clear();
                e = doc.select(".a-main-block amb-category").select(".amb-020").select(".amb-025").select(".ta-lines-2");
                for (Element contains : e){
                    datas.add(contains.text());
                }
                costs.clear();
                e = doc.select(".a-main-block amb-category").select(".amb-020").select("amb-024");
                for (Element contains : e){
                    costs.add(contains.text());
                }
                if (datas.size() == places.size() && places.size() == titles.size() && costs.size() == titles.size()){
                    for (int i = 0; i < titles.size(); i++){
                        Performance pr = new Performance(titles.get(i),datas.get(i),places.get(i),costs.get(i));
                        news.add(pr);
                    }
                }
                else{
                    Log.e(String.valueOf(titles.size()),titles.toString());
                    Log.e(String.valueOf(datas.size()),datas.toString());
                    Log.e(String.valueOf(places.size()),places.toString());

                }



            }
            catch (Exception e){
                error = true;
                Log.e("News",e.getMessage());
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
            if (error == true){
                defaultt.setText("Проблемы с подключением к интернету");
                defaultt.setVisibility(View.VISIBLE);
            }
            else {
                try {

                    rv.setAdapter(new ScheduleAdapter(getActivity(),news));


                } catch (Exception e) {
                    defaultt.setText("Проблемы с подключением к интернету");
                    defaultt.setVisibility(View.VISIBLE);
                }
            }
            pb.setVisibility(View.INVISIBLE);


        }



    }


}
