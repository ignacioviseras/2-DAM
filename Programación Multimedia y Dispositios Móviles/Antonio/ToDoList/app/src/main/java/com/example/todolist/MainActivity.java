package com.example.todolist;

import static android.content.ContentValues.TAG;

import static com.example.todolist.R.id.select;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String emailUsuario;
    String nombreTarea;
    ListView listViewTareas;
    List<String> listaTareas = new ArrayList<>();
    List<String> listaIdTareas= new ArrayList<>();
    ArrayAdapter<String> mAdapterTareas;

    //
    Spinner select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         db = FirebaseFirestore.getInstance();
         mAuth= FirebaseAuth.getInstance();
         emailUsuario = mAuth.getCurrentUser().getEmail();
         listViewTareas=findViewById(R.id.listView);

         //actualizar la interfaz de usuario con sus propias tareas.
        actualizarUI();

    }

    private void actualizarUI(){
        db.collection("Tareas")
                .whereEqualTo("emailUsuario", emailUsuario)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {

                            return;
                        }
                            listaTareas.clear();
                            listaIdTareas.clear();
                            for (QueryDocumentSnapshot doc : value) {
                                listaIdTareas.add(doc.getId());
                                listaTareas.add(doc.getString("nombreTarea"));
                            if (listaTareas.size() == 0) {
                                listViewTareas.setAdapter(null);
                            }else {
                                mAdapterTareas= new ArrayAdapter<String>(MainActivity.this, R.layout.item_tarea, R.id.nota, listaTareas);
                                listViewTareas.setAdapter(mAdapterTareas);
                            }
                        }

                    }
                });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
                case R.id.mas:

                    final EditText taskEditText= new EditText(this);
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setTitle("Nueva Tarea")
                            .setMessage("Escribe la nueva tarea")
                            .setView(taskEditText)
                            .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    String miTarea = taskEditText.getText().toString();


                                    Map<String, Object> tarea = new HashMap<>();
                                    tarea.put("nombreTarea", miTarea);
                                    tarea.put("emailUsuario", emailUsuario);

                                    db.collection("Tareas").add(tarea);

                                }
                            })

                            .setNegativeButton("Cancelar", null)
                            .create();
                    dialog.show();

                    return true;
                case R.id.logout:
                    // cierre de sesion por firebase
                   mAuth.signOut();
                   onBackPressed();
                   finish();
                    return true;
            default:return super.onOptionsItemSelected(item);
        }


    }

    public void borrarTarea (View view){
        View parent = (View) view.getParent();
        TextView tareaTextView = parent.findViewById(R.id.nota);
        String tarea = tareaTextView.getText().toString();
        int posicion = listaTareas.indexOf(tarea);

        db.collection("Tareas").document(listaIdTareas.get(posicion)).delete();

    }


    public void vistaSelect(){
        select = findViewById(R.id.select);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.itemselect));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(myadapter);
    }


    public void actualizarTarea (View view){


         EditText taskEditText= new EditText(this);
        View parent = (View) view.getParent();
        TextView tareaTextView = parent.findViewById(R.id.nota);
        String tarea2 = tareaTextView.getText().toString();
        taskEditText.setText(tarea2);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Modificar tarea")
                .setMessage("Escribe de nuevo ls tarea")
                .setView(taskEditText)
                .setPositiveButton("actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String miTarea = taskEditText.getText().toString();
                        int posicion = listaTareas.indexOf(tarea2);

                        Map<String, Object> tarea = new HashMap<>();
                        tarea.put("nombreTarea", miTarea);
                        tarea.put("emailUsuario", emailUsuario);

                        db.collection("Tareas").document(listaIdTareas.get(posicion)).set(tarea);

                    }
                })

                .setNegativeButton("Cancelar", null)
                .create();
        dialog.show();



    }
}