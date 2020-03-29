package com.example.newstask.features.home

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstask.R
import com.example.newstask.network.model.news_model.Article
import com.example.newstask.repositories.newsRepository
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import subscribeWithLoading


class MainActivity : AppCompatActivity() {

    val mAdapter = ArticlesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        title = "Link development"
        setupToggle()
        setRecycler()
        //combine 2 requests
        sendRequests()
    }

    private fun setRecycler() {
        main_rec.layoutManager=LinearLayoutManager(this)
        main_rec.adapter=mAdapter
    }


    private fun sendRequests() {
        Observable.merge(newsRepository.getArticlesByNextWeb(), newsRepository.getArticlesByAssociated())
            .subscribeWithLoading({showShimmer()},{ handleData(it.articles)},{handleError(it.message)},{})
    }

    private fun showShimmer() {
        shimmer_lay.visibility= View.VISIBLE
    }

    private fun handleError(error: String?) {
        toast(error!!)
        Log.e("error",error)
    }

    private fun handleData(articles: List<Article?>?) {
        shimmer_lay.visibility= View.GONE
        mAdapter.swapData(articles as List<Article>)
    }


    private fun setupToggle() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
            }
            return@setNavigationItemSelectedListener true
        }

        toggle.syncState()
    }

    //main menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
