package com.example.newsapp_v2.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp_v2.data.api.sources.Source
import com.example.newsapp_v2.databinding.NewsFragmentBinding
import com.example.newsapp_v2.ui.home.HomeActivity
import com.example.newsapp_v2.ui.ViewError
import com.example.newsapp_v2.ui.home.adapter.NewsAdapter
import com.example.newsapp_v2.ui.showDialog
import com.example.newsapp_v2.viewModel.NewsViewModel
import com.google.android.material.tabs.TabLayout

class NewsFragment : Fragment() {
    lateinit var viewBinding: NewsFragmentBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = NewsFragmentBinding.inflate(
            inflater, container, false
        )
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        intiObservers()

    }

    private fun intiObservers() {
        viewModel.shouldShowLoading
            .observe(viewLifecycleOwner, object : Observer<Boolean> {
                override fun onChanged(value: Boolean) {
                    viewBinding.progressBar.isVisible = value
                }
            })

        viewModel.sourcesLiveData
            .observe(viewLifecycleOwner) { sources ->
                bindTabs(sources)
            }

        viewModel.newsLiveData
            .observe(viewLifecycleOwner) {
                adapter.bindNews(it)
            }

        viewModel.getData().observe(viewLifecycleOwner) { data ->
            Log.e("t", data)
            viewModel.getNewsSources(data)

            viewModel.errorLiveData.observe(viewLifecycleOwner) {
                handelError(it)
            }
        }
    }

    val adapter = NewsAdapter()
    private fun initViews() {
        viewBinding.recyclerView.adapter = adapter

        adapter.onNewsClickListener = NewsAdapter.onNewsClickListenter { news ->
            viewModel.newsDetailsLiveData.value = news
            replaceFragment(NewsDescriptionFragment())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val activity = requireActivity() as HomeActivity
        activity.replaceFragment(fragment)
    }


    private fun bindTabs(sources: List<Source?>?) {

        sources?.forEach { source ->
            val tab = viewBinding.sourceTabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBinding.sourceTabLayout.addTab(tab)
        }
        viewBinding.sourceTabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val source = tab.tag as Source
                    viewModel.getNews(source.id)

                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    val source = tab.tag as Source
                    viewModel.getNews(source.id)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            }
        )

        viewBinding.sourceTabLayout.getTabAt(0)?.select()

    }


    private fun handelError(viewError: ViewError) {
        showDialog(message = viewError.message ?: viewError.t?.localizedMessage
        ?: "Something went wrong",
            posActionName = "Try again",
            posAction = { dialogInterface, i ->
                dialogInterface.dismiss()
                viewError.onClick?.onTryAgainClick()
            },
            negActionName = "Cancel",
            negAction = { dialogInterface, i ->
                dialogInterface.dismiss()
            })
    }


}