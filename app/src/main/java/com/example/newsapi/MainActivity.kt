package com.example.newsapi

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var API_KEY = "297f800c8ccb4b34982f0402646e8147"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContent()
        search_button.setOnClickListener{ serchButtonHandler(search_button) }
    }

    fun serchButtonHandler(view: View?) {
        if(view != null) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        Repo.partList.clear()
        recycler_list.removeAllViews()

        val searchString = search_line.text.toString().replace(" ", "+")
        var url = "https://newsapi.org/v2/everything?q=$searchString&sortBy=date&apiKey=$API_KEY"

        getJson(url)
    }


    fun getJson(url: String) {
       Ion.with(this)
           .load(url)
           .asString()
           .setCallback { _, obj ->

               var jsonArr = JSONArray()
               try {
                   jsonArr = JSONObject(obj).getJSONArray("articles")
               } catch (ex: Exception) { }

               if(jsonArr.length() > 0) {
                   for(i in 0..(jsonArr.length() - 1)) {
                       val title = jsonArr.getJSONObject(i).getString("title")
                       val content = jsonArr.getJSONObject(i).getString("content")
                       Log.e("Crafter", content)
                       Repo.partList.add(PartData(title, content))
                   }
               }
               loadContent()
           }
    }

    fun loadContent() {

        recycler_list.layoutManager = LinearLayoutManager(this)

        val adapter = PartAdapter(Repo.partList, object : PartAdapter.Callback {
            override fun onItemClicked(item: PartData) {
                val intent = Intent(baseContext, DetailsActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("content", item.content)
                startActivity(intent)
            }
        })
        recycler_list.adapter = adapter
    }
}
