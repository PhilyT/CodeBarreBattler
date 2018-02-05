package com.example.mbds.barcodebattler;
import android.content.Context;
import android.database.DataSetObserver;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by deptinfo on 13/01/2018.
 */

public class DataEquipement implements ListAdapter {
    Context context;
    ArrayList<Equipement> equipements;
    public DataEquipement(Context context , ArrayList<Equipement> equipements ) {
        this.context=context;
        this.equipements=equipements;
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
        return equipements.size();
    }

    @Override
    public Object getItem(int i) {
        return equipements.get(i);
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
        TextView txtNom ;
        ImageView image ;
        TextView fab;
        View returnView ;
        if(view==null){
            returnView= View.inflate(this.context,R.layout.layoutequipement,null);
            txtNom=(TextView) returnView.findViewById(R.id.nom);
            txtNom.setText(equipements.get(i).Nom);
            fab=(TextView) returnView.findViewById(R.id.delete);

            if(equipements.get(i).Image != null)
            {
                image=(ImageView) returnView.findViewById(R.id.image);
                image.setImageBitmap(equipements.get(i).Image);
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
        return equipements.isEmpty();
    }
}
