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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.mbds.barcodebattler.R.id.spinner;

public class DetailCreature extends AppCompatActivity {
    TextView txtNom ,txtPv, txtAttaque, txtDefense;
    ImageView image ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_creature);

        txtNom=(TextView)findViewById(R.id.nom);
        txtPv=(TextView)findViewById(R.id.pv);
        txtAttaque=(TextView) findViewById(R.id.attaque);
        txtDefense=(TextView) findViewById(R.id.defense);
        image = (ImageView)findViewById(R.id.image) ;

        Intent intent = getIntent();

        txtNom.setText(intent.getStringExtra("nom"));
        txtAttaque.setText(intent.getStringExtra("attaque"));
        txtPv.setText(intent.getStringExtra("pv"));
        txtDefense.setText(intent.getStringExtra("defense"));
        image.setImageBitmap((Bitmap) intent.getParcelableExtra("image"));

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
    }
}
