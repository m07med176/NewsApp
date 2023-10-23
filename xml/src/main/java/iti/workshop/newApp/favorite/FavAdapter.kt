package iti.workshop.newApp.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iti.workshop.data.source.dto.Article
import iti.workshop.newApp.databinding.RvFavBinding

class FavAdapter (var deleteClickListener: FavAdapter.ListItemClickListenerDelete,var mClickListener: FavAdapter.ListItemClickListener) : ListAdapter<Article, FavAdapter.FavViewHolder>(FavDiffUtils()) {

    lateinit var binding: RvFavBinding
    interface ListItemClickListenerDelete {
        fun onClickFavDelete(article: Article)
    }
    interface ListItemClickListener {
        fun onClickFav(article: Article)
    }
    class FavViewHolder(var binding: RvFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        //    var inflate=(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))as LayoutInflater
        var inflate = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = RvFavBinding.inflate(inflate, parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int)  {
        holder.binding.tvNewsFav.text = getItem(position).title
        holder.binding.articleModel = getItem(position)
        holder.binding.actionDelete = deleteClickListener
        holder.binding.action = mClickListener
    }
}

