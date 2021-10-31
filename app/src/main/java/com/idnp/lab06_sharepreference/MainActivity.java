package com.idnp.lab06_sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ProgressBar progress;
    ProgressBar progress2;
    ImageButton guardar;
    Button veterinarias;
    Button clinicas;
    EditText etCorreo;
    EditText etContra;
    SharePreferenceHandler share;
    TextView txtVeterinarias;
    TextView txtAlbergues;

    Handler h= new Handler();
    boolean cargando=false;
    boolean cargando2=false;
    int progreso=0;
    int progreso2=0;

    String TAG ="ERROR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progress);
        progress2 = findViewById(R.id.progress2);
        guardar= findViewById(R.id.btnRegistrar);
        veterinarias= findViewById(R.id.btnVeterinarias);
        etCorreo = findViewById(R.id.etCorreo);
        etContra = findViewById(R.id.etContra);
        txtAlbergues = findViewById(R.id.txtAlbergues);
        txtVeterinarias = findViewById(R.id.txtVeterinarias);
        share = new SharePreferenceHandler(this);

        etCorreo.setText(share.getCorreo());
        etContra.setText(share.getContra());
        txtVeterinarias.setText("Cantidad de veterinarias: "+share.getVeterinarias());
        txtAlbergues.setText("Cantidad de Albergues : "+ share.getAlbergues());
    }

    public void guardar(View view){
        share.saveCorreo(etCorreo.getText().toString());
        share.saveContra(etContra.getText().toString());
    }

    public void cargarVeterinarias (View view){
        if (!cargando){
            cargando=true;
            Thread hilo = new Thread(new Runnable(){
                @Override
                public void run() {
                    while (progreso < 100){
                        try {
                            progreso += 5;
                            progress.setProgress(progreso);
                            Thread.sleep(200);
                        }
                        catch (InterruptedException e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                    if (progreso == 100){
                        share.saveVeterinarias(4);
                        progreso=0;
                        cargando=false;
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                txtVeterinarias.setText("Cantidad de veterinarias: "+share.getVeterinarias());
                            }
                        });
                    }

                }
            });
            hilo.start();

        }
    }
    public void cargarAlbergues (View view){
        if (!cargando2){
            cargando2=true;
            Thread hilo = new Thread(new Runnable(){
                @Override
                public void run() {

                    while (progreso2 < 100){
                        try {
                            progreso2 += 5;
                            progress2.setProgress(progreso2);
                            Thread.sleep(200);
                        }
                        catch (InterruptedException e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                    if (progreso2 == 100){
                        share.saveAlbergues(4);
                        progreso2=0;
                        cargando2=false;
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                txtAlbergues.setText("Cantidad de Albergues : "+ share.getAlbergues());
                            }
                        });
                    }

                }
            });
            hilo.start();
        }
    }

}