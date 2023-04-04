package com.tamayo.christophertamayo_achronyms.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tamayo.christophertamayo_achronyms.R
import com.tamayo.christophertamayo_achronyms.databinding.FragmentSearchBinding
import com.tamayo.christophertamayo_achronyms.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener  {


    private val myViewModel by lazy {
        ViewModelProvider(requireActivity())[MyViewModel::class.java]
    }

    private val binding by lazy{
        FragmentSearchBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.searchView.setOnQueryTextListener(this)



        // Inflate the layout for this fragment
        return binding.root
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
    }


    @SuppressLint("ResourceAsColor")
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            myViewModel.tag = query
            hideKeyboard()
            findNavController().navigate(R.id.action_search_to_acronym)
        }


        return true
    }


    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}