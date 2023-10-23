package iti.workshop.newApp.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import iti.workshop.data.source.dto.Article
import iti.workshop.newApp.R
import iti.workshop.newApp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(),FavAdapter.ListItemClickListener,FavAdapter.ListItemClickListenerDelete {
    lateinit var binding: FragmentFavoriteBinding
    var favAdapter = FavAdapter(this@FavoriteFragment,this@FavoriteFragment)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root    }

    override fun onClickFavDelete(article: Article) {
        TODO("Not yet implemented")
    }

    override fun onClickFav(article: Article) {
        TODO("Not yet implemented")

       val action : FavoriteFragmentDirections.ActionFavoriteFragmentToDetailsFragment =
        FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(article)
        findNavController(requireView()).navigate(action)

    }

}