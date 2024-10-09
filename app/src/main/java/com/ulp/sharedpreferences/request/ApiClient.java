package com.ulp.sharedpreferences.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.ulp.sharedpreferences.model.Usuario;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences conectar (Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario) {
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("mail", usuario.getMail());
        editor.putString("password", usuario.getPassword());
        editor.commit();
    }

    public static Usuario leer(Context context) {
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", null);
        String nombre = sp.getString("nombre", null);
        String mail = sp.getString("mail", null);
        String password = sp.getString("password", null);

        Usuario usuario = new Usuario(dni, apellido, nombre, mail, password);
        return usuario;
    }

    public static Usuario login(Context context, String mail, String password) {
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", null);
        String nombre = sp.getString("nombre", null);
        String email = sp.getString("mail", null);
        String pass = sp.getString("password", null);

        if (mail.equals(email) && password.equals(pass)) {
            usuario = new Usuario(dni, apellido, nombre, email, pass);
        }
        return usuario;
    }

}
