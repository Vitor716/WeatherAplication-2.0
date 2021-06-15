package com.example.weatheraplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBase db;

    Button btnPesquisar, btnListar, btnSalvar, btnGeo;
    EditText editWord;
    String url;
    ListView list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBase(this);

        btnListar = findViewById(R.id.btnListar);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnGeo = findViewById(R.id.btnGeo);
        editWord = findViewById(R.id.editTextWord);
        list = findViewById(R.id.list);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model cm = new Model();
                cm.setTitle(editWord.getText().toString());

                db.addData(cm);

                Toast.makeText(getBaseContext(), "Resultado salvo com sucesso no Banco de Dados", Toast.LENGTH_SHORT).show();
            }
        });


        btnGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Geolocation();
            }
        });


        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase dataBaseHelper = new DataBase(MainActivity.this);
                List<Model> everyone = dataBaseHelper.getEveryone();

                ArrayAdapter customArray = new ArrayAdapter<Model>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                list.setAdapter(customArray);

            }
        });
    }


        public void requestApiButtonClick (View v){
            url = dictionaryEntries();
            Request dictionaryRequest = new Request(this);
            dictionaryRequest.execute(url);
        }


        private String dictionaryEntries () {

            final String word = editWord.getText().toString();
            final String word_id = word.toLowerCase();

            return "https://www.metaweather.com/api/location/search/?query=" + word_id + "";
        }

        public void Geolocation() {
            Intent intent = new Intent(this, Geolocation.class);
            startActivity(intent);
        }
    }
