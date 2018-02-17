package com.example.mbds.barcodebattler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    //ImageView image;
    private Button button ,gestion ,local,reseau;
    TextView code;
    MonHelper  dataBase = new MonHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // image = (ImageView)findViewById(R.id.image);
        //image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.assassin));
        button = (Button) this.findViewById(R.id.button);
        code =(TextView)this.findViewById(R.id.code);
        final Activity activity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        gestion= (Button) findViewById(R.id.gestionId);
        gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Gestion.class);
                startActivityForResult(intent,2);
            }
        });
        local= (Button) findViewById(R.id.localId);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChoixLocal.class);
                startActivityForResult(intent,2);
            }
        });
        reseau= (Button) findViewById(R.id.reseauId);
        reseau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChoixReseau.class);
                startActivityForResult(intent,2);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Log.d("MainActivity", "Scanned");
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,generateRobotActivity.class);
                intent.putExtra("scanCode",result.getContents());
                startActivityForResult(intent,1);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
        if(resultCode==2){
            Creature creature =  data.getParcelableExtra("creature");
            Intent intent = new Intent(MainActivity.this,Gestion.class);

            //intent.putExtra("creature",creature);
            creature.Id= dataBase.addCreature(creature.Nom , creature.PV,creature.Defense , creature.Attaque , creature.Image);
            System.out.print(creature.Id);
            if(creature.Equipements!=null){
                for(Equipement e : creature.Equipements){
                    e.CreatureID=creature.Id ;
                    System.out.print(e);
                    dataBase.addEquipement(e.Nom , e.Point,e.Image,e.Attribut,e.CreatureID);
                }
            }
            startActivityForResult(intent,2);
        }
    }

}
