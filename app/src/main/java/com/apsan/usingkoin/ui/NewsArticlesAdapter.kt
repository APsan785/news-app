package com.apsan.usingkoin.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apsan.usingkoin.databinding.NewsHolderCardviewBinding
import com.apsan.usingkoin.models.Articles

class NewsArticlesAdapter(
    val articleList : ArrayList<Articles>
) : RecyclerView.Adapter<NewsArticlesAdapter.NewsHolder>() {
    class NewsHolder(val binding : NewsHolderCardviewBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindit(article: Articles){
            binding.apply {

                foldingCell.setOnClickListener {
                 foldingCell.toggle(false)
                }

                newsTitle.text = article.title
                byOrg.text = "by ${article.source?.name}"
                newsTitle1.text = article.title
                byOrg1.text = "by ${article.source?.name}"
                contentText.text = "${article.description}"
                url.text = article.url
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = NewsHolderCardviewBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article = articleList[position]
        holder.bindit(article)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

}