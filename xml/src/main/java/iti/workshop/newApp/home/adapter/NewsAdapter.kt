package iti.workshop.newApp.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import iti.workshop.data.source.dto.Article
import iti.workshop.newApp.databinding.ListviewitemBinding

class NewsAdapter(var context: Context,
                      //var fav: FavouriteInterface
                      ) :
        ListAdapter<Article, NewsViewHolder>(NewsItemDiffUtil()) {
        lateinit var binding: ListviewitemBinding
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val inflater: LayoutInflater =
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = ListviewitemBinding.inflate(inflater, parent, false)
            return NewsViewHolder(binding)
        }
        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val current = getItem(position)
            Glide.with(context).load(current.urlToImage).into(holder.binding.newsIV)
            holder.binding.bodyTV.text = current.content
            holder.binding.titleTV.text = current.title
            holder.binding.categoryTV.text = current.author
        }
    }
