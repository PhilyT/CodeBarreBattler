package com.example.mbds.barcodebattler;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ChoixLocal extends AppCompatActivity {

    TextView title;
    TextView nom1;
    TextView pv1;
    TextView attaque1;
    TextView defense1;
    TextView nom2;
    TextView pv2;
    TextView attaque2;
    TextView defense2;
    TextView retour;
    Spinner choix1;
    Spinner choix2;
    ListView equipements1;
    ListView equipements2;
    Button lancer;
    ImageView image1;
    ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_local);

        // Init widget
        title =  (TextView) findViewById(R.id.title);
        nom1 =  (TextView) findViewById(R.id.nom1);
        pv1 =  (TextView) findViewById(R.id.pv1);
        attaque1 =  (TextView) findViewById(R.id.attaque1);
        defense1 =  (TextView) findViewById(R.id.defense1);
        nom2 =  (TextView) findViewById(R.id.nom2);
        pv2 =  (TextView) findViewById(R.id.pv2);
        attaque2 =  (TextView) findViewById(R.id.attaque2);
        defense2 =  (TextView) findViewById(R.id.defense2);
        retour =  (TextView) findViewById(R.id.retour);
        choix1 = (Spinner) findViewById(R.id.choix1);
        choix2 = (Spinner) findViewById(R.id.choix2);
        equipements1 = (ListView) findViewById(R.id.equipements1);
        equipements2 = (ListView) findViewById(R.id.equipements2);
        lancer = (Button) findViewById(R.id.lancer );
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);

        // Init data
        Creature[] items = new Creature[]{// Data for test
                new Creature("toto", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archer_squelette)),
                new Creature("titi", 30, 8, 8, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiable)),
                new Creature("tata", 10, 20, 15, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiablotin))
        };
        ArrayAdapter<Creature> adapter = new ArrayAdapter<Creature>(this, android.R.layout.simple_spinner_item, items);

        // Set Widget
        choix1.setAdapter(adapter);
        choix1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Creature creatureSelected = (Creature)parentView.getItemAtPosition(position);
                nom1.setText(creatureSelected.Nom);
                pv1.setText("PV : "+creatureSelected.PV);
                attaque1.setText("Attaque : "+creatureSelected.Attaque);
                defense1.setText("Defense : "+creatureSelected.Defense);
                image1.setImageBitmap(creatureSelected.Image);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        choix2.setAdapter(adapter);
        choix2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Creature creatureSelected = (Creature)parentView.getItemAtPosition(position);
                nom2.setText(creatureSelected.Nom);
                pv2.setText("PV : "+creatureSelected.PV);
                attaque2.setText("Attaque : "+creatureSelected.Attaque);
                defense2.setText("Defense : "+creatureSelected.Defense);
                image2.setImageBitmap(creatureSelected.Image);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        title.setText("Combat Local - Choix");
    }
}
