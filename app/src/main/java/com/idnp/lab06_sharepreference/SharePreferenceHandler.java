package com.idnp.lab06_sharepreference;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceHandler {
    private Context context;
    private SharedPreferences share;

    public SharePreferenceHandler (Context context){
        this.context = context;
        share = context.getSharedPreferences("data", Activity.MODE_PRIVATE);
    }

    public void saveCorreo(String email){
        SharedPreferences.Editor editor = share.edit();
        editor.putString("correo",email);
        editor.commit();
    }
    public void saveContra(String password){
        SharedPreferences.Editor editor = share.edit();
        editor.putString("contraseña",password);
        editor.commit();
    }
    public void saveVeterinarias(int veterinarias){
        SharedPreferences.Editor editor = share.edit();
        editor.putInt("veterinarias",veterinarias);
        editor.commit();
    }
    public void saveAlbergues(int albergues){
        SharedPreferences.Editor editor = share.edit();
        editor.putInt("albergues",albergues);
        editor.commit();
    }
    public String getContra(){
        String password = share.getString("contraseña","");
        return password;
    }
    public String getCorreo(){
        String email = share.getString("correo","");
        return email;
    }
    public int getVeterinarias(){
        int veterinarias = share.getInt("veterinarias",0);
        return veterinarias;
    }
    public int getAlbergues(){
        int albergues = share.getInt("albergues",0);
        return albergues;
    }
}
