package com.e.tacovela.Activities.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.tacovela.Activities.Preference
import com.e.tacovela.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var pref =  Preference(this)
        //pref.setToken(token)
        val tokennn = pref.getToken()

        //var mApp = MyApplication()

        //mApp = MyApplication()
        //Toast.makeText(this@HomeActivity, mApp.token, Toast.LENGTH_SHORT).show()
    }
}
