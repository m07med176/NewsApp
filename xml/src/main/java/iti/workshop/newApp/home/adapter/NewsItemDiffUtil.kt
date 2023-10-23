package iti.workshop.newApp.home.adapter

import androidx.recyclerview.widget.DiffUtil
import iti.workshop.data.source.dto.Article

class NewsItemDiffUtil: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title==newItem.title
    }


    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem==newItem
    }
}