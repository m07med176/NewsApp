package iti.workshop.newApp.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import iti.workshop.data.source.dto.Article
import iti.workshop.newApp.databinding.FragmentDetailsBinding
import iti.workshop.newApp.di.AppDependency

class DetailsFragment : Fragment() {

    lateinit var binding:FragmentDetailsBinding

    lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity(),DetailsViewModelFactory(
            delete = AppDependency.deleteFavoriteUseCase,
            insert = AppDependency.insetFavorite,
            checkIsExist = AppDependency.checkFavoriteExistUseCase
        ))[DetailsViewModel::class.java]

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        // TODO get data from bundle
        val article = Article(5, title = "Moahemd Arfa", description = "My name is mohamed arfa", author = "mohamed")
        binding.article = article

        viewModel.checkIfFavoriteExist(title = article.title)

        lifecycleScope.launchWhenCreated {
            viewModel.isExist.collect{
                binding.tbSaveFavorite.isChecked = it
            }
        }



        binding.tbSaveFavorite.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked){
                viewModel.removeArticle(article)
            }else{
                viewModel.insertArticle(article)
            }
        }
        return binding.root
    }

}