package com.example.newsapp_v2.ui.home
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp_v2.databinding.SearchActivityBinding
import com.example.newsapp_v2.ui.home.adapter.NewsAdapter
import com.example.newsapp_v2.viewModel.NewsViewModel


class SearchActivity:AppCompatActivity() {
    lateinit var viewBinding:SearchActivityBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initObservers()

    }

    val adapter =NewsAdapter()

    private fun initObservers() {
        viewBinding.recyclerView.adapter=adapter
        viewModel.shouldShowLoading.observe(this){ value->
            viewBinding.progressBar.isVisible = value
        }
        viewModel.newsLiveData.observe(this){
            adapter.bindNews(it)
        }



    }

    private fun initViews() {
        viewBinding = SearchActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel
    }

}