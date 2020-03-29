package com.example.newstask.features.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newstask.R
import com.example.newstask.network.model.news_model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_articles.view.*
import java.text.SimpleDateFormat
import java.util.*


class ArticlesAdapter(val activity: Activity) : RecyclerView.Adapter<ArticlesAdapter.ArticlesVH>() {

    private var data: List<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesVH {
        return ArticlesVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_articles, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ArticlesVH, position: Int) = holder.bind(data[position],activity)

    fun swapData(data: List<Article>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ArticlesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: Article,
            activity: Activity
        ) = with(itemView) {

            if (!item.urlToImage.isNullOrBlank()) Picasso.get().load(item.urlToImage).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_article)
            tv_title.text=item.title ?:""
            tv_author.text=item.author ?:""

            tv_date.text= item.publishedAt?.let { convertDate(it) } ?:""
            val options = ActivityOptions
                .makeSceneTransitionAnimation(activity, iv_article, "img")
            setOnClickListener {
                context.startActivity(Intent(context,NewsDetailsActivity::class.java).apply {
                    putExtra("article",item)
                },options.toBundle())
            }
        }
        @SuppressLint("SimpleDateFormat")
        fun convertDate(date:String):String{
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("MMM dd, yyyy")
            val date = inputFormat.parse(date)
            return outputFormat.format(date)

        }


    }

}