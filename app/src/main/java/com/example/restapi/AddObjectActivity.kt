package com.example.restapi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_object)


        val etName:TextInputEditText=findViewById(R.id.etName)
        val etJob:TextInputEditText=findViewById(R.id.etJob)
        val btnConfirm:Button=findViewById(R.id.btnConfirm)
        val name=etName.text.toString()
        val job=etJob.text.toString()
        btnConfirm.setOnClickListener {
            /*var API=RetrofitInterface.rf.create(RetrofitInterface::class.java)
            val userData:UserData=UserData(name,job)
            val call=API.sendUserData(userData)
            call?.enqueue(object:Callback<UserData?>{
                override fun onResponse(call: Call<UserData?>, response: Response<UserData?>) {
                    setResult(Activity.RESULT_OK)
                    finish()
                    Toast.makeText(this@AddObjectActivity,response.code().toString(),Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<UserData?>, t: Throwable) {
                    Toast.makeText(this@AddObjectActivity,t.message,Toast.LENGTH_SHORT).show()
                }

            })*/
        }

    }
}