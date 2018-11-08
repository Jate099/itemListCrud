package com.example.quizuno.itemlistcrud;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TareasList extends ArrayAdapter<Item>{

    private Activity context;
    private List<Item> itemList;



    public TareasList(Activity context, List<Item> itemList){
        super(context, R.layout.list_layout, itemList);
        this.context = context;
        this.itemList = itemList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null, true);

        TextView tv_titulo;
        TextView tv_descrip;

        tv_titulo = (TextView) listViewItem.findViewById(R.id. tv_titulo);
        tv_descrip = (TextView) listViewItem.findViewById(R.id. tv_descrip);

        Item item = itemList.get(position);

        tv_titulo.setText(item.getTitulo());
        tv_descrip.setText(item.getDescrip());

        return listViewItem;
    }
}
