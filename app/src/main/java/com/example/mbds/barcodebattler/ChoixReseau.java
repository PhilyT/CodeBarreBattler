package com.example.mbds.barcodebattler;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class ChoixReseau extends AppCompatActivity {

    int REQUEST_ENABLE_BT = 2;
    TextView title;
    TextView nom;
    TextView pv;
    TextView attaque;
    TextView defense;
    TextView retour;
    Spinner choix;
    ListView equipements;
    Spinner joueur;
    ArrayList<BluetoothDevice> joueurs;
    Button lancer;
    Button bluetooth;
    ImageView image;
    Creature creatureSelected;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice joueurSelected;
    Boolean sendCreature;
    Creature creatureAdverse;
    AcceptThread server;
    ConnectedThread client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_reseau);

        // Init widgets
        title =  (TextView) findViewById(R.id.title);
        nom =  (TextView) findViewById(R.id.nom);
        pv =  (TextView) findViewById(R.id.pv);
        attaque =  (TextView) findViewById(R.id.attaque);
        defense =  (TextView) findViewById(R.id.defense);
        retour =  (TextView) findViewById(R.id.retour);
        choix = (Spinner) findViewById(R.id.choix);
        joueur = (Spinner) findViewById(R.id.joueur);
        equipements = (ListView) findViewById(R.id.equipements);
        lancer = (Button) findViewById(R.id.lancer );
        image = (ImageView) findViewById(R.id.image);
        bluetooth = (Button) findViewById(R.id.bluetooth);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Init data
        sendCreature = false;
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
        Creature[] items = new Creature[]{// Data for test
                new Creature("toto", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archer_squelette),equipements1),
                new Creature("titi", 30, 8, 8, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiable),equipements2),
                new Creature("tata", 10, 17, 15, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiablotin), equipements3)
        };
        creatureSelected = items[0];
        ArrayAdapter<Creature> adapter = new ArrayAdapter<Creature>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        DataEquipement adapterEquipements1 = new DataEquipement(ChoixReseau.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected.Equipements)));

        // bluetooth
        if (mBluetoothAdapter == null) {
            // si l’adapter est null, le téléphone ne supporte pas le bluetooth
            AlertDialog.Builder builder = new AlertDialog.Builder(ChoixReseau.this);

            builder.setMessage("La fonctionnalité bluetooth est indisponible sur ce device")
                    .setTitle("Info");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    joueur.setEnabled(false);
                    lancer.setEnabled(false);
                    bluetooth.setEnabled(false);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            if (!mBluetoothAdapter.isEnabled()) {
                activeBluetooth();
            }else {
                bluetooth.setEnabled(false);
                initializeDevices();
            }
        }

        // Set Widgets
        equipements.setAdapter(adapterEquipements1);
        choix.setAdapter(adapter);
        choix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                creatureSelected = (Creature)parentView.getItemAtPosition(position);
                nom.setText(creatureSelected.Nom);
                pv.setText("PV : "+creatureSelected.getPV());
                attaque.setText("Attaque : "+creatureSelected.getAttaque());
                defense.setText("Defense : "+creatureSelected.getDefense());
                image.setImageBitmap(creatureSelected.Image);
                equipements.setAdapter(new DataEquipement(ChoixReseau.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected.Equipements))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        joueur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                joueurSelected = joueurs.get(position);
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
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBluetooth();
            }
        });
        lancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCreature = true;
                choix.setEnabled(false);
                joueur.setEnabled(false);
                if (creatureAdverse != null){
                    Intent intent = new Intent(ChoixReseau.this,CombatReseau.class);
                    intent.putExtra("Creature1", creatureSelected);
                    intent.putExtra("Creature2", creatureAdverse);
                    intent.putExtra("DeviceAdverse", joueurSelected);
                    startActivityForResult(intent,1);
                }else{
                    lancer.setText("Attente du joueur adverse");
                }
            }
        });
        title.setText("Combat Reseau - Choix");
    }

    private void initializeDevices(){
        joueurs = new ArrayList<BluetoothDevice>(mBluetoothAdapter.getBondedDevices());
        String[] joueursInfo = new String[joueurs.size()];
        for(int i = 0; i<joueurs.size(); i++){
            joueursInfo[i]=joueurs.get(i).getName() + "\n" + joueurs.get(i).getAddress();
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ChoixReseau.this, android.R.layout.simple_spinner_item, joueursInfo);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        joueur.setAdapter(adapter2);
    }

    private void activeBluetooth(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        server = new AcceptThread("", new UUID(Long.MAX_VALUE, Long.MIN_VALUE), mBluetoothAdapter){
            @Override
            public void onReception(Intent data){
                if (sendCreature){
                    
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ENABLE_BT){
            if(resultCode == -1){
                initializeDevices();
            }else{
                joueur.setEnabled(false);
                lancer.setEnabled(false);
            }
        }
    }
}
