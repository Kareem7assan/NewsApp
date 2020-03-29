package com.example.newstask.features.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.newstask.R
import com.example.newstask.network.model.news_model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_order_details.*
import openUrl
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        title = "Link Development"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val item = intent.getSerializableExtra("article") as (Article)
        if (!item.urlToImage.isNullOrBlank()) Picasso.get().load(item.urlToImage).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_article)
        tv_title.text=item.title ?:""
        tv_author.text=item.author ?:""
        tv_desc.text=item.description ?:""
        tv_date.text= item.publishedAt?.let { convertDate(it) } ?:""

        action_btn.onClick {
            item.url?.let { it1 -> openUrl(it1) }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDate(date:String):String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormat = SimpleDateFormat("MMM dd, yyyy")
        val date = inputFormat.parse(date)
        return outputFormat.format(date)

    }

    //main menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
