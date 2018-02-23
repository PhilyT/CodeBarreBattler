package com.example.mbds.barcodebattler;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.mbds.barcodebattler.R.color.blue;
import static java.security.AccessController.getContext;

/**
 * Created by deptinfo on 13/01/2018.
 */

public class DataCreature  implements ListAdapter {
    Context context;
    ArrayList<Creature> creatures;
    public DataCreature(Context context , ArrayList<Creature> creatures ) {
        this.context=context;
        this.creatures=creatures;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return creatures.size();
    }

    @Override
    public Object getItem(int i) {
        return creatures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txtNom ,txtPv, txtAttaque, txtDefense;
        ImageView image ;
        View returnView ;
        if(view==null){
        returnView= View.inflate(this.context,R.layout.layoutcreature,null);
        txtNom=(TextView) returnView.findViewById(R.id.nom);
            txtNom.setText(creatures.get(i).Nom);
        txtPv=(TextView) returnView.findViewById(R.id.pv);
            txtPv.setText("Points de vie:"+creatures.get(i).PV);
        txtAttaque=(TextView) returnView.findViewById(R.id.attaque);
            txtAttaque.setText("Points Attaque:"+creatures.get(i).Attaque);
        txtDefense=(TextView) returnView.findViewById(R.id.defense);
            txtDefense.setText("Points Defense:"+creatures.get(i).Defense);
        if(creatures.get(i).Image != null)
        {
            image=(ImageView) returnView.findViewById(R.id.image);

            image.setImageBitmap(creatures.get(i).Image);
        }
        return returnView;
    }
        else
                return view;

}

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return creatures.isEmpty();
    }
}
