package com.example.admin.onthefencetest.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.onthefencetest.Activities.NewsActivity;
import com.example.admin.onthefencetest.R;
import com.example.admin.onthefencetest.Units.PostValue;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


/**
 * Created by admin on 08.03.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;

private ArrayList<PostValue> data;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_tem, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PostValue pv = data.get(position);
        holder.time.setText(pv.getTime());
        holder.heading.setText(pv.getHeading());
        holder.picture.setImageURI(pv.getLink());
        holder.cvListener.setRecord(pv,position);




    }

    public NewsAdapter(ArrayList<PostValue> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView time,heading;
        SimpleDraweeView picture;
        ClickListener cvListener = new ClickListener();

        public ViewHolder(final View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.pic_news);
            cv = itemView.findViewById(R.id.cv);
            time = itemView.findViewById(R.id.time);
            heading = itemView.findViewById(R.id.heading);
            cv.setOnClickListener(cvListener);

        }}
        class ClickListener implements View.OnClickListener{

            PostValue pv;
            int pos;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("News",pv.getMore());

                context.startActivity(intent);

            }

            void setRecord(PostValue pv,int pos){
                this.pv = pv;
                this.pos = pos;
            }

        }


    }

