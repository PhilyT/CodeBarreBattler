package com.example.mbds.barcodebattler;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class generateRobotActivity extends AppCompatActivity {
TextView code ;
    ArrayList<Creature> creatures = new ArrayList<Creature>();
    ArrayList<Equipement> equipements1 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements2 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements3 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements4 = new ArrayList<Equipement>();
    ArrayList<Equipement> equipements = new ArrayList<Equipement>();
    TextView txtNom ,txtPv, txtAttaque, txtDefense, retour;
    ImageView image ;
    ListView mListView;
    Button ajout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_robot);
        code =(TextView) this.findViewById(R.id.code);
        ajout=(Button)this.findViewById(R.id.ajout);
        initialise();


        Intent intent = getIntent();
        code.setText(intent.getStringExtra("scanCode"));

        final Creature robotgenerate = creatures.get(ThreadLocalRandom.current().nextInt(0, creatures.size()));


        txtNom=(TextView)findViewById(R.id.nom);
        txtPv=(TextView)findViewById(R.id.pv);
        txtAttaque=(TextView) findViewById(R.id.attaque);
        txtDefense=(TextView) findViewById(R.id.defense);
        image = (ImageView)findViewById(R.id.image) ;



        txtNom.setText(robotgenerate.Nom);
        txtAttaque.setText("Attaque : "+robotgenerate.getAttaque());
        txtPv.setText("PV : "+robotgenerate.getPV());
        txtDefense.setText("Defense : "+robotgenerate.getDefense());
        image.setImageBitmap(robotgenerate.Image);

        mListView =(ListView)findViewById(R.id.equipements);
        DataEquipement dataEquiment = new DataEquipement(generateRobotActivity.this,new ArrayList<Equipement>(Arrays.asList(robotgenerate.Equipements)));

        mListView.setAdapter(dataEquiment);


        ajout.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generateRobotActivity.this,MainActivity.class);

                intent.putExtra("creature",robotgenerate);
                setResult(2,intent);
                finish();

            }
        });

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
        equipements4.add(new Equipement("baton", BitmapFactory.decodeResource(this.getResources(), R.mipmap.baton), 2, "Attaque"));


        creatures.add(new Creature("archer", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.archer_squelette), Arrays.copyOf(equipements1.toArray(), 4, Equipement[].class)));
        creatures.add(new Creature("chevalier", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.chevalier), Arrays.copyOf(equipements2.toArray(), 3, Equipement[].class)));
        creatures.add(new Creature("beast", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.beast), Arrays.copyOf(equipements3.toArray(), 1, Equipement[].class)));
        creatures.add(new Creature("diable", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.diable), Arrays.copyOf(equipements4.toArray(), 1, Equipement[].class)));




        creatures.add(new Creature("dragon_spectral", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.dragon_spectral), Arrays.copyOf(equipements1.toArray(), 4, Equipement[].class)));
        creatures.add(new Creature("assassiin", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.assassin), Arrays.copyOf(equipements2.toArray(), 3, Equipement[].class)));
        creatures.add(new Creature("dragon_rouge", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.dragon_rouge), Arrays.copyOf(equipements3.toArray(), 1, Equipement[].class)));
        creatures.add(new Creature("dragon_fury", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(), R.mipmap.dragon_fury), Arrays.copyOf(equipements4.toArray(), 1, Equipement[].class)));
    }
}
