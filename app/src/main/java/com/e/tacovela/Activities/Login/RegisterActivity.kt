package com.e.tacovela.Activities.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.e.tacovela.R
import remote.Common.Common
import remote.Models.Login.UserRegister
import remote.Models.Login.UserRegisterResponse
import remote.api.IApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    internal lateinit var mService: IApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mService = Common.api

        val btnRegister = findViewById(R.id.btnRegistrar2) as AppCompatButton
        btnRegister.setOnClickListener {
            val txtFirstName = findViewById(R.id.txtFirstName) as EditText
            val firstName = txtFirstName.text.toString().trim()

            val txtLastName = findViewById(R.id.txtLastName) as EditText
            val lastName = txtLastName.text.toString().trim()

            val txtEmail = findViewById(R.id.txtEmail2) as EditText
            val email = txtEmail.text.toString().trim()

            val txtPassword = findViewById(R.id.txtPassword2) as EditText
            val password = txtPassword.text.toString().trim()

            val txtRePassword = findViewById(R.id.txtRePassword) as EditText
            val rePassword = txtRePassword.text.toString().trim()

            if(firstName != null && firstName.isEmpty())
            {
                Toast.makeText(this@RegisterActivity, "Ingresar Nombre (s)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            if(lastName != null && lastName.isEmpty())
            {
                Toast.makeText(this@RegisterActivity, "Ingresar Apellidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            if(email != null && email.isEmpty())
            {
                Toast.makeText(this@RegisterActivity, "Ingresar Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            if(password != null && password.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "Ingresar Contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            if(rePassword != null && rePassword.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "Ingresar Re-Contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            var register = UserRegister()
            register.firstName = firstName
            register.lastName = lastName
            register.email = email
            register.password = password
            register.confirmPassword = rePassword
            register.type = 1

            registerUser(register)
        }

        val btnLogin = findViewById(R.id.btnLogin2) as AppCompatTextView
        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun registerUser(user:UserRegister)
    {
        mService.RegisterUser(user)
            .enqueue(object: Callback<UserRegisterResponse> {
                override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, t!!.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<UserRegisterResponse>,
                    response: Response<UserRegisterResponse>
                ) {
                     if (response.isSuccessful){
                        if (response!!.body()!!.success!!){
                            var token = response.body()!!.data!!.token.toString()

                            Toast.makeText(this@RegisterActivity, "Registro con exito!", Toast.LENGTH_SHORT).show()

                            GoToLogin()
                        }
                        else{
                            Toast.makeText(this@RegisterActivity, "error login", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Toast.makeText(this@RegisterActivity, "El email y la contraseña no coinciden.", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun GoToLogin(){
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
