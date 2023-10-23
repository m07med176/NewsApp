package iti.workshop.newApp.favorite

import androidx.recyclerview.widget.DiffUtil
import iti.workshop.data.source.dto.Article

class FavDiffUtils: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
return oldItem == newItem
    }

}