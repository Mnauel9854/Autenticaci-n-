package com.manuelrosado.autenticacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewPropertyAnimatorListener
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CrearCuenta : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val txtnombre_nuevo: TextView = findViewById(R.id.edtNombre)
        val txtemail_nuevo : TextView =findViewById(R.id.edtEmailNuevo)
        val txtpassword1 : TextView =findViewById(R.id.edtContraseñaNueva)
        val txtpassword2 : TextView =findViewById(R.id.edtContraseñaNueva1)
        val btnCrear : Button =findViewById(R.id.edtCrearCuenta)
        val btnRegresar : Button = findViewById(R.id.edtRegresar)

        btnCrear.setOnClickListener()
        {
            var pass1 = txtpassword1.text.toString()
            var pass2 = txtpassword2.text.toString()
           if (pass1.equals(pass2))
           {
               createAccount(txtemail_nuevo.text.toString(),txtpassword1.text.toString())
           }
            else
           {
               Toast.makeText(baseContext,"Error: las Contraseñas no son iguales", Toast.LENGTH_SHORT).show()
               txtpassword1.requestFocus()

           }
        }
        btnRegresar.setOnClickListener()
        {
                val i = Intent(this,MainActivity::class.java)
                startActivity(i)

        }




        firebaseAuth= Firebase.auth
    }

    private fun createAccount(email: String, password: String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this ){  task ->
            if (task.isSuccessful)
            {
                Toast.makeText(baseContext, "Cuenta Creada Correctamente", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(baseContext,"Hubo un error, Error " + task.exception, Toast.LENGTH_SHORT).show()
            }
        }
    }
}