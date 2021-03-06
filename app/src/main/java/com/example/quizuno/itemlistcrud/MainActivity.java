package com.example.quizuno.itemlistcrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_titulo;
    EditText et_descrip;
    Spinner sp_spinner;
    Button btn_crear;
    ListView lv_lista;

    DatabaseReference itemsDatabase;

    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsDatabase = FirebaseDatabase.getInstance().getReference();

        et_titulo = findViewById(R.id.et_titlo);
        et_descrip = findViewById(R.id.et_descrip);
        sp_spinner = findViewById(R.id.sp_spinner);
        btn_crear = findViewById(R.id.btn_crear);
        lv_lista = findViewById(R.id.lv_lista);

        itemList = new ArrayList<>();

        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItems();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        itemsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList.clear();

                for(DataSnapshot  itemSnapshot : dataSnapshot.getChildren()){
                    Item item = itemSnapshot.getValue(Item.class);

                    itemList.add(item);


                }

                TareasList adapter = new TareasList(MainActivity.this, itemList);

                lv_lista.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addItems(){
        String titulo = et_titulo.getText().toString().trim();
        String descripcion = et_descrip.getText().toString().trim();
        String itemClass = sp_spinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(titulo)){

            String id = itemsDatabase.push().getKey();

            Item item = new Item(id, titulo, descripcion);

            itemsDatabase.child(id).setValue(item);

            Toast.makeText(this,"Item añadido", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Ingresa un titulo, poh faboh", Toast.LENGTH_LONG).show();
        }

    }
}
