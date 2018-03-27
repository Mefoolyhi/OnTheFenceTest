package com.example.admin.onthefencetest;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainFragment extends Fragment {


    ImageView filters;
    Spinner type;
    RecyclerView rv;


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



        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),FiltersActivity.class);
                startActivityForResult(intent,0);
            }
        });


        return v;
    }



}
