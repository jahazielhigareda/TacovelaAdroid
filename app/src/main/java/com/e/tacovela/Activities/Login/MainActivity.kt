package com.e.tacovela.Activities.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import remote.Common.Common
import remote.Models.Login.Credentials
import remote.Models.Login.ResponseBasic
import remote.api.IApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.EditText
import com.e.tacovela.Activities.Home.HomeActivity
import com.e.tacovela.Activities.Preference
import com.google.gson.Gson
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {


    internal lateinit var mService:IApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.e.tacovela.R.layout.activity_main)

        var pref =  Preference(this@MainActivity)
        val token = pref.getToken()
        if(token?.isEmpty() == false){
            GoToHome()
        }



        val txtEmail = findViewById(com.e.tacovela.R.id.txtEmail) as EditText
        txtEmail.setText("jahaziel.higareda@gmail.com")
        val txtpassword = findViewById(com.e.tacovela.R.id.txtPassword) as EditText
        txtpassword.setText("Mex19--!#4(yxwz")

        mService = Common.api

        val btnLogin = findViewById(com.e.tacovela.R.id.btnLogin) as AppCompatButton
        btnLogin.setOnClickListener {
            val txtEmail = findViewById(com.e.tacovela.R.id.txtEmail) as EditText
            val email = txtEmail.text.toString().trim()

            val txtPassword = findViewById(com.e.tacovela.R.id.txtPassword) as EditText
            val password = txtPassword.text.toString().trim()

            if(email != null && email.isEmpty())
            {
                Toast.makeText(this@MainActivity, "Ingresar Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            if(password != null && password.isEmpty()) {
                Toast.makeText(this@MainActivity, "Ingresar Contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            authenticateUser(email, password)
        }

        val btnRegistrar = findViewById(com.e.tacovela.R.id.btnRegistrar) as AppCompatTextView
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
             startActivity(intent)
        }

        val btnForgotPw = findViewById(com.e.tacovela.R.id.btnForgotPw) as AppCompatTextView
        btnForgotPw.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }


    }

    private fun authenticateUser(email:String, password:String)
    {
        var auth = Credentials()
        auth.email = email
        auth.password = password

        mService.LoginUser(auth)
            .enqueue(object: Callback<ResponseBasic> {
                override fun onFailure(call: Call<ResponseBasic>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t!!.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseBasic>,
                    response: Response<ResponseBasic>
                ) = if (response.isSuccessful){
                    if (response!!.body()!!.Success!!){
                        var token = response.body()!!.Data!!.token.toString()

                        var pref =  Preference(this@MainActivity)
                        pref.setToken(token)

                        Toast.makeText(this@MainActivity, "Bienvenido", Toast.LENGTH_SHORT).show()
                        GoToHome()
                    }
                    else{
                        System.out.println(response.errorBody());
                        Toast.makeText(this@MainActivity, "error login", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    val gson = Gson()
                    val menssage = gson.fromJson(response.errorBody()!!.string(), ResponseBasic::class.java)

                    //val jObjError = JSONObject(response.errorBody()!!.string())
                    //System.out.println(jObjError.toString());
                    Toast.makeText(this@MainActivity, "El email y la contraseña no coinciden.", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun GoToHome(){
        //val intent = Intent(this@MainActivity, HomeActivity::class.java)
        //startActivity(intent)

        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}
