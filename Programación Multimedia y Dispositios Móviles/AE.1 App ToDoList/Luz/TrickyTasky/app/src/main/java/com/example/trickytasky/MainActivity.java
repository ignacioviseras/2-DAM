package com.example.trickytasky;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
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

    ListView listViewTareas;
    List<String> listaTareas = new ArrayList<>();
    List<String> listaIdTareas = new ArrayList<>();
    ArrayAdapter<String> mAdapterTareas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        emailUsuario = mAuth.getCurrentUser().getEmail();
        listViewTareas = findViewById(R.id.ListView);

        //actualizar la interfaz del usuario con las propias tareas del usuario logado

        actualizarUI();

    }

    private void actualizarUI() {
        db.collection("Tareas")
                .whereEqualTo("emailUsuario", emailUsuario)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            return;
                        }
                        listaTareas.clear();
                        listaIdTareas.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            listaIdTareas.add(doc.getId());
                            listaTareas.add(doc.getString("nombreTarea"));
                        }
                        if (listaTareas.size() == 0) {
                            listViewTareas.setAdapter(null);
                        } else {
                            mAdapterTareas = new ArrayAdapter<>(MainActivity.this, R.layout.item_tarea, R.id.nombreTarea, listaTareas);
                            listViewTareas.setAdapter(mAdapterTareas);
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
        switch (item.getItemId()) {
            case R.id.add:
                //activar el cuadro de Dialogo para añadir tarea
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("New Task")
                        .setMessage("Which is your Tricky Tasky?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Añadir la tarea a la base de datos

                                String myTask = taskEditText.getText().toString();

                                Map<String, Object> task = new HashMap<>();
                                task.put("nombreTarea", myTask);
                                task.put("emailUsuario", emailUsuario);

                                db.collection("Tareas").add(task);

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                //Toast.makeText(this, "Tarea añadida", Toast.LENGTH_SHORT).show();
                dialog.show();
                return true;

            case R.id.logout:
                //cierre de sesion de Firebase
                mAuth.signOut();
                onBackPressed();
                finish();
                startActivity(new Intent(MainActivity.this, Login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void borrarTarea(View view) {
        View parent = (View) view.getParent();
        TextView tareaTextView = parent.findViewById(R.id.nombreTarea);
        String tarea = tareaTextView.getText().toString();
        int posicion = listaTareas.indexOf(tarea);

        db.collection("Tareas").document(listaIdTareas.get(posicion)).delete();

        Toast.makeText(MainActivity.this, "Task done!",
                Toast.LENGTH_SHORT).show();

    }

    public void editarTarea (View view){

        EditText taskEditText= new EditText(this);
        View parent = (View) view.getParent();
        TextView tareaTextView = parent.findViewById(R.id.nombreTarea);
        String tarea2 = tareaTextView.getText().toString();
        taskEditText.setText(tarea2);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit Task")
                .setMessage("Write down your changes")
                .setView(taskEditText)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
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
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();

    }

}