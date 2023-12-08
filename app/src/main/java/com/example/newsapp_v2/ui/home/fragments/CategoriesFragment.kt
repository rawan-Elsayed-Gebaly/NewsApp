package com.example.newsapp_v2.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp_v2.databinding.FragmentCategoriesBinding
import com.example.newsapp_v2.ui.home.HomeActivity
import com.example.newsapp_v2.viewModel.NewsViewModel

class CategoriesFragment:Fragment() {

    lateinit var viewBinding:FragmentCategoriesBinding
    lateinit var viewModel:NewsViewModel
    val bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(requireActivity())[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoriesBinding.inflate(inflater
            , container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCategoryClick()
    }

    fun switchToFragmentB() {
        val activity = requireActivity() as HomeActivity
        activity.replaceFragment(NewsFragment())
    }


    fun onCategoryClick(){
        viewBinding.healthCat.setOnClickListener {
            viewModel.setData(viewBinding.healthCat.tag.toString())
            switchToFragmentB()

        }
        viewBinding.sportsCat.setOnClickListener {
            viewModel.setData(viewBinding.sportsCat.tag.toString())
            switchToFragmentB()

        }
        viewBinding.environmentCat.setOnClickListener {
            viewModel.setData(viewBinding.environmentCat.tag.toString())
            switchToFragmentB()

        }
        viewBinding.scienceCat.setOnClickListener {
            viewModel.setData(viewBinding.scienceCat.tag.toString())
            switchToFragmentB()

        }
        viewBinding.politicsCat.setOnClickListener {
            viewModel.setData(viewBinding.politicsCat.tag.toString())
            switchToFragmentB()

        }
        viewBinding.businessCat.setOnClickListener {
            viewModel.setData(viewBinding.businessCat.tag.toString())
            switchToFragmentB()

        }
    }
}