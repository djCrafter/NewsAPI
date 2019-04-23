package com.example.newsapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        var intent = getIntent()

        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")

        title_label.text = title
        content_label.text = content
    }
}
