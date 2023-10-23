package iti.workshop.newApp.home

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import iti.workshop.newApp.databinding.FragmentNewsListBinding
import iti.workshop.newApp.home.adapter.NewsAdapter
import iti.workshop.newApp.states.NewsState
import kotlinx.coroutines.launch


class NewsListFragment : Fragment() {
    private val viewModel: NewsListViewModel by viewModels { NewsListViewModel.Factory }

    lateinit var binding: FragmentNewsListBinding
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            viewModel.updateNews()
           // val container = findViewById(R.id.shimmer_view_container) as ShimmerFrameLayout
           // container.startShimmer()
            viewModel.newsStatus.collect {
                when (it) {
                    is NewsState.Success -> {

                       // binding.header.text= it.data[0].title

                        newsAdapter = NewsAdapter(requireContext())
                        newsAdapter.submitList(it.data)
                        binding.NewsRec.apply {
                            adapter=newsAdapter
                            layoutManager= LinearLayoutManager(context).apply {
                                orientation= RecyclerView.VERTICAL
                            }
                        }

                        Log.i("TAG2", "Success")
                    }

                    is NewsState.Loading -> {

                        Log.i("TAG2", "LOADING: ")

                    }

                    is NewsState.Failure -> {
                        Log.i("TAG2", it.error.toString())

                    }

                    else -> {
                        println("TEEEE")
                    }
                }
            }

        }
    }
}