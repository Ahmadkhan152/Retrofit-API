package com.example.restapi

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isInvisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

class MainActivity : AppCompatActivity(){
    lateinit var call:Call<PostModel?>
    lateinit var myRecyclerView:RecyclerView
    lateinit var API:RetrofitInterface
    var isScrolling: Boolean = false
    var currentItems: Int = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0
    var pageNo:Int=0;
    lateinit var progressBar:ProgressBar
    val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

        if (result?.resultCode == Activity.RESULT_OK)
        {
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        progressBar=findViewById(R.id.progressBar)
        //progressBar.setProgress(3,true)
        val btnAdd: Button = findViewById(R.id.btnAdd)
        myRecyclerView = findViewById(R.id.myRecyclerView)
        btnAdd.setOnClickListener {
            val shifter = Intent(this@MainActivity, AddObjectActivity::class.java)
            getContent.launch(shifter)
        }
        fetchData()
    }

    fun fetchData() {
        API = RetrofitInterface.rf.create(RetrofitInterface::class.java)
        call = API.listRepos("$pageNo")
        callEnqueue()
    }
    fun callEnqueue()
    {
        call?.enqueue(object : Callback<PostModel?> {
            override fun onResponse(call: Call<PostModel?>, response: Response<PostModel?>) {
                var postlist: PostModel = response.body() as PostModel
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                var myRecyAdapter =
                    MyRecyclerViewAdapter(this@MainActivity, postlist)
                myRecyclerView.adapter = myRecyAdapter
                progressBar.visibility = View.INVISIBLE
                myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)

                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                            isScrolling = true
                        }
                    }
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        currentItems =
                            (myRecyclerView.layoutManager as LinearLayoutManager).childCount
                        totalItems = (myRecyclerView.layoutManager as LinearLayoutManager).itemCount
                        scrollOutItems =
                            (myRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (pageNo<postlist.totalPages)
                        {
                            if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                                isScrolling=false
                                pageNo++
                                API.listRepos("$pageNo")
                                fetchData()
                                Toast.makeText(this@MainActivity,"Scrolling",Toast.LENGTH_SHORT).show()
                            }
                        }
                        else
                            Toast.makeText(this@MainActivity,"Page Is Not Avaliable",Toast.LENGTH_SHORT).show()
                    }

                })
            }

            override fun onFailure(call: Call<PostModel?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Response Is Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}