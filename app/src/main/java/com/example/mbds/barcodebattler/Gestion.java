package com.example.mbds.barcodebattler;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Gestion extends AppCompatActivity {
    ListView mListView;
    ArrayList<Creature> creatures = new ArrayList<Creature>();
    ArrayList<Equipement> equipements = new ArrayList<Equipement>();
    int potion=30;
    TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);
        initialise();
        mListView =(ListView)findViewById(R.id.listView) ;
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[] { "Creatures", "Equipements", "Potions"  };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                Log.v("item", (String) parent.getItemAtPosition(position));
                String item = (String) parent.getItemAtPosition(position);

                if(item.equalsIgnoreCase("Creatures")){
                    DataCreature dataCreature = new DataCreature(Gestion.this,creatures);

                    mListView.setAdapter(dataCreature);

                }
                else if(item.equalsIgnoreCase("Equipements")){
                    DataEquipement dataEquiment = new DataEquipement(Gestion.this,equipements);

                    mListView.setAdapter(dataEquiment);
                }
                else if(item.equalsIgnoreCase("Potions")){
                  points=  (TextView) findViewById(R.id.points);
                    points.setText(" Reserve de potion"+potion);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void initialise(){
        creatures.add(new Creature("archer", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.archer_squelette)));
        creatures.add(new Creature("chevalier", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.chevalier)));
        creatures.add(new Creature("best", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.best)));
        creatures.add(new Creature("diable", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.diable)));


        equipements.add(new Equipement("archer", BitmapFactory.decodeResource(this.getResources(), R.mipmap.archer_squelette)));
        equipements.add(new Equipement("feticheur", BitmapFactory.decodeResource(this.getResources(), R.mipmap.feticheur)));
        equipements.add(new Equipement("chevalier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.chevalier)));
        equipements.add(new Equipement("best", BitmapFactory.decodeResource(this.getResources(), R.mipmap.best)));

    }

}
