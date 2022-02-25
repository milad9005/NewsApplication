package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragNewsBinding
import com.example.newsapp.ui.holders.RecHolderNews
import com.example.newsapp.ui.utils.GenericPagingDataAdapter
import com.example.newsapp.ui.utils.GenericRecyclerAdapter
import com.example.newsapp.ui.utils.Holder
import com.example.newsapp.ui.utils.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragNews : Fragment() {

    private lateinit var binding: FragNewsBinding
    private val viewModel: VMNewsList by viewModels()
    private lateinit var pagingAdapter: GenericPagingDataAdapter<RecArticle, Holder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()


        viewModel.items.observe(viewLifecycleOwner, Observer {
            pagingAdapter.submitData(lifecycle, it)
        })

//        viewModel.state.observe(viewLifecycleOwner){
//
//
//        }




    }

    private fun initRecycler() {
        pagingAdapter = GenericPagingDataAdapter(RecHolderNews.diffCallback) { parent, viewType ->
            RecHolderNews.from(parent, viewType)
        }

        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.newsRecycler.apply {
            layoutManager = linearLayoutManager
            adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                LoadStateAdapter {
                    pagingAdapter.retry()
                },
                LoadStateAdapter {
                    pagingAdapter.retry()
                },
            )
        }
    }

}