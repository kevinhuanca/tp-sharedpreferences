package com.ulp.sharedpreferences.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.sharedpreferences.model.Usuario;
import com.ulp.sharedpreferences.request.ApiClient;
import com.ulp.sharedpreferences.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Usuario> mUsuario;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }

    public LiveData<Usuario> getMUsuario() {
        if (mUsuario == null) {
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void guardarDatos(String dni, String apellido, String nombre, String mail, String password) {
        if (dni.isEmpty() || apellido.isEmpty() || nombre.isEmpty() || mail.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiClient.guardar(context, new Usuario(Long.parseLong(dni), apellido, nombre, mail, password));
        Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show();
    }

    public void cargarDatos(Intent intent) {
        boolean nuevo = intent.getBooleanExtra("nuevo", true);
        if (!nuevo) {
            Usuario usuario = ApiClient.leer(context);
            if (usuario != null) {
                mUsuario.setValue(new Usuario(
                        usuario.getDni(),
                        usuario.getApellido(),
                        usuario.getNombre(),
                        usuario.getMail(),
                        usuario.getPassword()
                ));
            }
        }

    }

}
