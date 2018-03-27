package com.example.admin.onthefencetest;



import android.util.Log;

import com.example.admin.onthefencetest.Units.PostValue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;


/**
 * Created by admin on 18.02.2018.
 */

public class ParsingClass {
    private String URL;


    private ArrayList<PostValue> items = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    private ArrayList<String> headings = new ArrayList<>();
    private ArrayList<String> links = new ArrayList<>();
    ArrayList<String> more = new ArrayList<>();


    public ParsingClass(String URL){
        this.URL = URL;
        links.clear();
        times.clear();
        headings.clear();
        more.clear();
        items.clear();
    }

    public ArrayList<PostValue> getPostsList(){

        return items;
    }

    public void get() throws Exception {


            Document doc = Jsoup.connect(URL).get();
            Log.e("Parsing",URL);
            Elements content = doc.select(".b-subjects-list__item").select(".b-subjects-list__date");

            for (Element contains : content){
                if (!contains.parent().className().equals("b-subjects-list__item b-subjects-list__item_konkurs")) {


                    times.add(contains.text());
                }
            }
            content = doc.select(".b-subjects-list__item").select(".b-subjects-list__img").select("img");

            for (Element contains: content){
                if (!contains.parent().parent().parent().className().equals("b-subjects-list__item b-subjects-list__item_konkurs")) {


                    links.add(contains.absUrl("src"));}
            }
            content = doc.select(".b-subjects-list__item").select(".b-subjects-list__title");

            for (Element contains: content){
                if (!contains.parent().className().equals("b-subjects-list__item b-subjects-list__item_konkurs")) {


                    headings.add(contains.text());
                }            }

            content = doc.select(".b-subjects-list__item").select(".b-subjects-list__img").select("a[href]");


        for (Element contains: content){
            if (!contains.parent().parent().className().equals("b-subjects-list__item b-subjects-list__item_konkurs")) {


                more.add(contains.attr("abs:href"));
            }        }
            if(links.size() == times.size() && times.size() == headings.size() && headings.size() == more.size()){

                for (int i = 0; i < headings.size();i++){
                    PostValue pv = new PostValue(times.get(i),headings.get(i),links.get(i),more.get(i));
                    items.add(pv);
                }


                Log.e(String.valueOf(links.size()),links.toString());
                Log.e(String.valueOf(more.size()),more.toString());
                Log.e(String.valueOf(times.size()),times.toString());
                Log.e(String.valueOf(headings.size()),headings.toString());

            }
            else{
                Log.e("PARsING","Разные размеры, ё маё");
                Log.e(String.valueOf(links.size()),links.toString());
                Log.e(String.valueOf(more.size()),more.toString());
                Log.e(String.valueOf(times.size()),times.toString());
                Log.e(String.valueOf(headings.size()),headings.toString());

            }


    }


}
