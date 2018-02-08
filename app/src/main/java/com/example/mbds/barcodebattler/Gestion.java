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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Gestion extends AppCompatActivity {
    ListView mListView;
    ArrayList<Creature> creatures = new ArrayList<Creature>();
    ArrayList<Equipement> equipements1 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements2 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements3 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements4 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements = new ArrayList<Equipement>();
    int potion=30;
    TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);
        initialise();
        mListView =(ListView)findViewById(R.id.listView) ;
        points=  (TextView) findViewById(R.id.points);
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
                    final DataCreature dataCreature = new DataCreature(Gestion.this,creatures);

                    mListView.setAdapter(dataCreature);
                    mListView.setVisibility(View.VISIBLE);
                    points.setVisibility(View.INVISIBLE);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {
                            Creature creature =  dataCreature.creatures.get(position);
                            Intent intent = new Intent(Gestion.this,DetailCreature.class);
                            intent.putExtra("creature",creature);
                            startActivityForResult(intent,1);
                        }
                    });



                }
                else if(item.equalsIgnoreCase("Equipements")){
                    DataEquipement dataEquiment = new DataEquipement(Gestion.this,equipements);

                    mListView.setAdapter(dataEquiment);
                    mListView.setVisibility(View.VISIBLE);
                    points.setVisibility(View.INVISIBLE);

                }
                else if(item.equalsIgnoreCase("Potions")){

                    points.setText(" Reserve de potion"+potion);
                    points.setVisibility(View.VISIBLE);
                    mListView.setVisibility(View.INVISIBLE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//recuperaton de la creature generée par le scan
        Intent intent = getIntent();
        Creature creature =  intent.getParcelableExtra("creature");
        creatures.add(creature);

    }
    public void initialise(){
        equipements.add(new Equipement("baton", BitmapFactory.decodeResource(this.getResources(), R.mipmap.baton), 2, "Attaque"));
        equipements.add(new Equipement("epee", BitmapFactory.decodeResource(this.getResources(), R.mipmap.epee), 6, "Attaque"));
        equipements.add(new Equipement("bouclier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.bouclier), 6, "Defense"));
        equipements.add(new Equipement("casque", BitmapFactory.decodeResource(this.getResources(), R.mipmap.casque), 10, "Vie"));

        equipements1.add(new Equipement("baton", BitmapFactory.decodeResource(this.getResources(), R.mipmap.baton), 2, "Attaque"));
        equipements1.add(new Equipement("epee", BitmapFactory.decodeResource(this.getResources(), R.mipmap.epee), 6, "Attaque"));
        equipements1.add(new Equipement("bouclier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.bouclier), 6, "Defense"));
        equipements1.add(new Equipement("casque", BitmapFactory.decodeResource(this.getResources(), R.mipmap.casque), 10, "Vie"));

        equipements2.add(new Equipement("baton", BitmapFactory.decodeResource(this.getResources(), R.mipmap.baton), 2, "Attaque"));
        equipements2.add(new Equipement("epee", BitmapFactory.decodeResource(this.getResources(), R.mipmap.epee), 6, "Attaque"));
        equipements2.add(new Equipement("bouclier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.bouclier), 6, "Defense"));

        equipements3.add(new Equipement("baton", BitmapFactory.decodeResource(this.getResources(), R.mipmap.baton), 2, "Attaque"));


        creatures.add(new Creature("archer", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.archer_squelette), Arrays.copyOf(equipements1.toArray(), 4, Equipement[].class)));
        creatures.add(new Creature("chevalier", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.chevalier), Arrays.copyOf(equipements2.toArray(), 3, Equipement[].class)));
        creatures.add(new Creature("beast", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.beast), Arrays.copyOf(equipements3.toArray(), 1, Equipement[].class)));
        creatures.add(new Creature("diable", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.diable), Arrays.copyOf(equipements4.toArray(), 0, Equipement[].class)));

    }

}
