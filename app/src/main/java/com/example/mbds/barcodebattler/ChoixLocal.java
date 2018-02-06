package com.example.mbds.barcodebattler;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Arrays;

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
    ListView equipementsView1;
    ListView equipementsView2;
    Button lancer;
    ImageView image1;
    ImageView image2;
    Creature creatureSelected1;
    Creature creatureSelected2;
    Creature[] items;

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
        equipementsView1 = (ListView) findViewById(R.id.equipements1);
        equipementsView2 = (ListView) findViewById(R.id.equipements2);
        lancer = (Button) findViewById(R.id.lancer );
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);

        // Init data
        Equipement[] equipements1 = new Equipement[]{
                new Equipement("baton", BitmapFactory.decodeResource(this.getResources(), R.mipmap.baton), 2, "Attaque"),
                new Equipement("bouclier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.bouclier), 6, "Defense")
        };
        Equipement[] equipements2 = new Equipement[]{
                new Equipement("epee", BitmapFactory.decodeResource(this.getResources(), R.mipmap.epee), 6, "Attaque"),
                new Equipement("bouclier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.bouclier), 6, "Defense")
        };
        Equipement[] equipements3 = new Equipement[]{
                new Equipement("casque", BitmapFactory.decodeResource(this.getResources(), R.mipmap.casque), 10, "Vie")
        };
        items = new Creature[]{// Data for test
                new Creature("toto", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archer_squelette), equipements1),
                new Creature("titi", 30, 8, 8, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiable), equipements2),
                new Creature("tata", 10, 17, 15, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiablotin), equipements3)
        };
        creatureSelected1 = items[0];
        creatureSelected2 = items[0];
        ArrayAdapter<Creature> adapter = new ArrayAdapter<Creature>(this, android.R.layout.simple_spinner_item, items);
        DataEquipement adapterEquipements1 = new DataEquipement(ChoixLocal.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected1.Equipements)));
        DataEquipement adapterEquipements2 = new DataEquipement(ChoixLocal.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected2.Equipements)));
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Set Widget
        equipementsView1.setAdapter(adapterEquipements1);
        equipementsView2.setAdapter(adapterEquipements2);
        choix1.setAdapter(adapter);
        choix1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                creatureSelected1 = (Creature)parentView.getItemAtPosition(position);
                nom1.setText(creatureSelected1.Nom);
                pv1.setText("PV : "+creatureSelected1.getPV());
                attaque1.setText("Attaque : "+creatureSelected1.getAttaque());
                defense1.setText("Defense : "+creatureSelected1.getDefense());
                image1.setImageBitmap(creatureSelected1.Image);
                equipementsView1.setAdapter(new DataEquipement(ChoixLocal.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected1.Equipements))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        choix2.setAdapter(adapter);
        choix2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                creatureSelected2 = (Creature)parentView.getItemAtPosition(position);
                nom2.setText(creatureSelected2.Nom);
                pv2.setText("PV : "+creatureSelected2.getPV());
                attaque2.setText("Attaque : "+creatureSelected2.getAttaque());
                defense2.setText("Defense : "+creatureSelected2.getDefense());
                image2.setImageBitmap(creatureSelected2.Image);
                equipementsView2.setAdapter(new DataEquipement(ChoixLocal.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected2.Equipements))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixLocal.this,CombatLocal.class);
                intent.putExtra("Creature1", creatureSelected1);
                intent.putExtra("Creature2", creatureSelected2);
                startActivityForResult(intent,1);
            }
        });
        title.setText("Combat Local - Choix");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }
}
