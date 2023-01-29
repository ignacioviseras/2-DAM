package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.Nullable;
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

    ListView listViewTareas;
    List<String> listaTareas = new ArrayList<>();
    List<String> listaIdTareas = new ArrayList<>();
    ArrayAdapter<String> mAdapterTarea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //variable intermedia para acceder a los datos
        emailUsuario = mAuth.getCurrentUser().getEmail();
        listViewTareas = findViewById(R.id.ListView);

        //actualizamos la ui para mostrar las tareas del usuaio
        actualizarUI();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                //activar dialogo para añadir tarea
                final EditText taskEditText = new EditText(this);//es la caja de texto

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Nuevo tarea")
                        .setMessage("¿Que quieres?")
                        .setView(taskEditText)
                            .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //añadir tarea a la base de datos

                                    //variable intermedia
                                    String miTarea = taskEditText.getText().toString();

                                    //mapa de datos
                                    Map<String, Object> tarea = new HashMap<>();
                                    tarea.put("nombreTarea", miTarea);//nombre de tarea a lo insertado en la caja de texto
                                    tarea.put("emailUsuario", emailUsuario);//correponde a este usuario

                                    //añadimos la tarea a la base de datos
                                    db.collection("Tareas").add(tarea);
                                    Toast.makeText(MainActivity.this, "Tarea añadida", Toast.LENGTH_LONG).show();
                                    /*no se puede poner esto aqui ya que estamos fuera del contexto
                                    * la mejor solucion es llamar a un metodo que tenga el toast
                                    *
                                    * Toast.makeText(this, "Tarea añadida", Toast.LENGTH_LONG).show();
                                    */
                                }
                            })
                            .setNegativeButton("Cancelar", null)
                            .create();
                //para mostrar el dialogo hacemos esto
                dialog.show();

                //Toast.makeText(MainActivity.this, "Tarea añadida", Toast.LENGTH_LONG).show();
                return true;
            case R.id.logout:
                //cierre de session firebase
                mAuth.signOut();//desloguea
                onBackPressed();//vuelve a la pestaña anterior
                finish();


                //startActivity(new Intent(MainActivity.this, Login.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    public void borrarTarea(View view){//el view hace referencia al boton q ha sido pulsado
        View parent = (View) view.getParent();//pillamos del boton el padre y necesitamos castearlo
        TextView tareaTextView = parent.findViewById(R.id.nombreTarea);//ahora a raiz del padre podemos pillar el textView usando el findByid
        String tarea = tareaTextView.getText().toString();//texto de la tarea
        int posicion = listaTareas.indexOf(tarea);//posicion de la tarea en la lista
        db.collection("Tareas").document(listaIdTareas.get(posicion)).delete();//pillamos de la bd Tareas, y buscamos el documento con el identificador del id q pasamos para asi poder borrarlo
        Toast.makeText(this, "Tarea finalizada", Toast.LENGTH_LONG).show();
    }


    public void actualizarTarea (View view){
        EditText taskEditText= new EditText(this);
        View parent = (View) view.getParent();
        TextView tareaTextView = parent.findViewById(R.id.nombreTarea);
        String tarea2 = tareaTextView.getText().toString();
        taskEditText.setText(tarea2);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Modificar tarea")
                .setMessage("La nueva tarea será")
                .setView(taskEditText)
                .setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
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


    public void actualizarUI(){
        db.collection("Tareas")
                .whereEqualTo("emailUsuario", emailUsuario)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {

                            return;
                        }

                        //limpiamos las listas
                        listaTareas.clear();
                        listaIdTareas.clear();

                        for (QueryDocumentSnapshot doc : value) {
                            listaIdTareas.add(doc.getId());
                            listaTareas.add(doc.getString("nombreTarea"));
                        }
                        //si la lista esta vacia
                        if (listaTareas.size() == 0){
                            listViewTareas.setAdapter(null);//el usuario no tiene docs
                        }else{//tiene datos la lista
                            mAdapterTarea = new ArrayAdapter<String>(MainActivity.this, R.layout.item_tarea, R.id.nombreTarea, listaTareas);
                            listViewTareas.setAdapter(mAdapterTarea);
                        }

                    }
                });
    }
}