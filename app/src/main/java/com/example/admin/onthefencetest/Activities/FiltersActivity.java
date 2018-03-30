package com.example.admin.onthefencetest.Activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.admin.onthefencetest.R;

import java.util.Calendar;

public class FiltersActivity extends AppCompatActivity {


    String[] theatres = {"Екатеринбургский государственный академический театр оперы и балета","Народный театр драмы ДКЖ им. Г. Е. Гецова (ДК железнодорожников)",
            "Екатеринбургский муниципальный театр юного зрителя","Свердловский государственный академический театр драмы","Екатеринбургский муниципальный театр кукол",
            "Свердловский государственный академический театр музыкальной комедии",
            "Уральская государственная консерватория им. М. П. Мусоргского",
            "Свердловская государственная академическая филармония"	,
            "Народный студенческий театр «Старый дом» (УрФУ, бывший УГТУ-УПИ)"	,
    "Театр балета «Сказка» (ЦК «Урал»)",
    "Свердловская государственная детская филармония",
    "Екатеринбургский государственный цирк им. В. И. Филатова",
    "Учебный театр Екатеринбургского государственного театрального института (ЕГТИ)",
    "Екатеринбургский драматический театр «Волхонка»"	,
    "Малый драматический театр «Театрон»"	,
    "Дом актёра",
    "Муниципальный театр балета «Щелкунчик»",
    "Балетная труппа «ТанцТеатр»"	,
    "Театр «Провинциальные танцы» (МБУ «Екатеринбургский театр современной хореографии»)",
    "Екатеринбургский цыганский театр «Сердца Ромэн» Любови Жемчужной",
    "Театр романса Музыкального общества Свердловской области",
    "Детско-подростковый театр «Зеркало»",
    "Уральский государственный театр эстрады",
    "Студенческий театр «Жест» (бывш. «Камерная Сцена») (УИУ РАНХиГС)",
    "Экспериментальный драматический Театр номер три (Театр № 3)",
    "Камерный театр Объединённого музея писателей Урала",
    "Православный театр «Лаборатория драматического искусства им. М. А. Чехова» (Театр Чехова)",
    "Театр-студия современного актёра «Афалина»	",
    "Театр для детей «Сказка»",
    "Коляда-театр",
    "Лингвистический театр «Лингва-Т» (УрФУ)",
    "Молодёжный театр-студия «Галёрка»",
    "Музыкально-драматический театр инвалидов «ТВИН»",
    "Открытый студийный театр (О. С. Т.)",
    "Театр «Шарманка»",
    "Экспериментальный театр «Бждын» (ЦК «Орджоникидзевский»)",
    "Молодёжный народный театр «Игра» (ЦК «Эльмаш»)",
    "Детский театр «Алиса",
    "Проектное бюро «ТанцТрест» (Екатеринбургский центр современного искусства)",
    "Театр оживающих кукол",
    "Екатеринбургский детский ледовый театр-студия (КРК «Уралец»)",
    "Молодёжный театр «АПрель»",
    "Екатеринбургский Центр современной драматургии",
    "Детский театр эстрады",
    "Музыкальный театр-студия «МаSка» (ЦК «Орджоникидзевский»)",
    "Театр кукол «ФиМ»",
    "Передвижной камерный музыкальный театр «Живой театр» (Студия Александра Пантыкина)",
    "Новый художественный театр «ТургеневЪ»",
    "Театр «Фабрика»",
    "Екатеринбургский музыкально-драматический театр «Сцена»",
    "Детский театр «Кукольный электронно-механический театр» (ТГ «Дирижабль»)",
    "Театр спонтанной импровизации «НА ВЕСУ» Михаила Пайкина",
    "Учебный театр кукол Православной школы «Сирин»",
    "Театр «Жёлтый квадрат»"	,
    "Театр «Bambini»",
    "Творческая мастерская «Тут Театр»"
    ,"Студия современной хореографии (МБУ «Екатеринбургский театр современной хореографии»)"};
    Button done, back;
    AutoCompleteTextView theare;
    TextView first_date, second_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        done = findViewById(R.id.ready);
        theare = findViewById(R.id.autoCompleteTextView);
        first_date = findViewById(R.id.first_date);
        second_date = findViewById(R.id.second_date);


        first_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getFragmentManager(), "first");

            }
        });
        second_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getFragmentManager(), "second");
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        theare.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,theatres));
    }


    @SuppressLint("ValidFragment")
    class DatePicker extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // определяем текущую дату
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            Dialog picker = new DatePickerDialog(getActivity(), this,
                    year, month, day);


            return picker;
        }
        int id = 0;

        @Override
        public void show(FragmentManager manager, String tag) {
            super.show(manager, tag);
            if (tag.equals("first")){
                id = 1;
            }
            else{
                id = 2;
            }

        }

        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
            month++;

            String d,m;
            if (day <= 9){
                d = '0'+ String.valueOf(day);
            }
            else
                d = String.valueOf(day);
            if (month <= 9){
                m = '0' + String.valueOf(month);
            }
            else
                m = String.valueOf(month);
            if (id == 1) {
                first_date.setText(d + "." + m + "." + year);
                first_date.setTextSize(20);
                first_date.setTextColor(Color.rgb(0,0,0));
            }
            else {
                second_date.setText(d + "." + m + "." + year);
                second_date.setTextSize(20);
                second_date.setTextColor(Color.rgb(0,0,0));
            }
        }
    }
}
