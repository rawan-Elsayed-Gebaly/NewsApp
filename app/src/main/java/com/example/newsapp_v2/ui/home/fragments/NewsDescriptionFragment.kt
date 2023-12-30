package com.example.newsapp_v2.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsapp_v2.R
import com.example.newsapp_v2.data.api.news.News
import com.example.newsapp_v2.databinding.NewsTitleFragmentBinding
import com.example.newsapp_v2.viewModel.NewsViewModel

class NewsDescriptionFragment : Fragment() {
    lateinit var viewBinding: NewsTitleFragmentBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = NewsTitleFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        initObservers()
    }

    private fun initObservers() {
        viewModel.newsDetailsLiveData.observe(viewLifecycleOwner) { news ->
            getNewsDetails(news)
        }
    }

    private fun getNewsDetails(news: News?) {
        Glide.with(this)
            .load(news?.urlToImage)
            .placeholder(R.drawable.splash)
            .into(viewBinding.newsImage)
        viewBinding.newsDescription.text = news?.description
        viewBinding.newsSource.text = news?.source?.name
        viewBinding.newsTime.text = news?.publishedAt
        viewBinding.newsContent.text = news?.content
        viewBinding.newsContent.setOnClickListener {

        }
    }
}
