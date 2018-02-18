package com.example.mbds.barcodebattler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.mbds.barcodebattler.R.id.spinner;

public class DetailCreature extends AppCompatActivity {
    TextView txtNom ,txtPv, txtAttaque, txtDefense, retour;
    ImageView image ;
    ListView mListView;
MonHelper database = new MonHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_creature);
        initialise();
        retour =  (TextView) findViewById(R.id.retour);
        txtNom=(TextView)findViewById(R.id.nom);
        txtPv=(TextView)findViewById(R.id.pv);
        txtAttaque=(TextView) findViewById(R.id.attaque);
        txtDefense=(TextView) findViewById(R.id.defense);
        image = (ImageView)findViewById(R.id.image) ;

        Intent intent = getIntent();
        Creature creature =  intent.getParcelableExtra("creature");


        mListView =(ListView)findViewById(R.id.equipements);
        ArrayList<Equipement>equipementsCreat = database.equipementsCreature(creature);

        if(equipementsCreat!=null && !equipementsCreat.isEmpty()){
            creature.Equipements = new  Equipement[equipementsCreat.size()];
            for(int i=0 ; i< equipementsCreat.size();i++) {
                creature.Equipements[i] = equipementsCreat.get(i);
            }
        }
        System.out.print(creature.Equipements);
        txtNom.setText(creature.Nom);
        txtAttaque.setText("Attaque : "+creature.getAttaque());
        txtPv.setText("PV : "+creature.getPV());
        txtDefense.setText("Defense : "+creature.getDefense());
        image.setImageBitmap(creature.Image);
        DataEquipement dataEquiment = new DataEquipement(DetailCreature.this,equipementsCreat);
        System.out.print("la taille de m"+database.equipementsCreature(creature).size());
        mListView.setAdapter(dataEquiment);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[] { "Equipement1", "Equipement2", "Equipements3"  };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Log.v("item", (String) parent.getItemAtPosition(i));
                String item = (String) parent.getItemAtPosition(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initialise(){
    }
}
