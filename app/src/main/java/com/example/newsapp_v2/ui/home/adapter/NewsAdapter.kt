package com.example.newsapp_v2.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp_v2.R
import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.databinding.ItemNewsBinding

class NewsAdapter(var newsList :List<News?>?=null)
    :RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding
    :ItemNewsBinding):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding
            .inflate(LayoutInflater.from(parent.context)
                , parent , false)
        return ViewHolder(viewBinding)
    }
    override fun getItemCount(): Int = newsList?.size?:0

    var onNewsClickListener: onNewsClickListenter?=null
    fun interface onNewsClickListenter{
        fun onItemClick(news: News)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList!![position]
       holder.itemBinding.newSource.text = news?.author
        holder.itemBinding.newsDescription.text = news?.title.toString()
        Glide.with(holder.itemView)
            .load(news?.urlToImage )
            .placeholder(R.drawable.splash)
            .into(holder.itemBinding.newsImage)

          if(onNewsClickListener!= null){
                holder.itemBinding.newsLayout.setOnClickListener{
                    onNewsClickListener!!.onItemClick(newsList!![position]!!)
                }
            }

    }

    fun bindNews(articles: List<News?>?) {
        newsList = articles
        notifyDataSetChanged()
    }
}