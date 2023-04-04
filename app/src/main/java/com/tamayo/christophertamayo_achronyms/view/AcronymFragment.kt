package com.tamayo.christophertamayo_achronyms.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tamayo.christophertamayo_achronyms.adapter.MyAdapter
import com.tamayo.christophertamayo_achronyms.data.model.AcronymsItem
import com.tamayo.christophertamayo_achronyms.data.model.Lfs
import com.tamayo.christophertamayo_achronyms.databinding.FragmentAcronymBinding
import com.tamayo.christophertamayo_achronyms.utils.UIState
import com.tamayo.christophertamayo_achronyms.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AcronymFragment : Fragment() {

    private val acronymAdapter by lazy {
        MyAdapter {
        }
    }

    private val myViewModel by lazy {
        ViewModelProvider(requireActivity())[MyViewModel::class.java]
    }

    private val binding by lazy {
        FragmentAcronymBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding.recyclerAcronyms.apply {

            //RecyclerView
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = acronymAdapter

        }

        myViewModel.getAcronym(myViewModel.tag)
        getAcronymByTag()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getAcronymByTag() {
        print("Holaaa")
        //ViewModel here
        myViewModel.acronym.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    println("Here is my Fragment LOADING")
                }

                is UIState.SUCCESS<List<AcronymsItem>> -> {

                    state.response.forEach {
                        println("loloolo -> $it")
                        binding.result.text = "About ${it.lfs?.count().toString()} results for ${myViewModel.tag}"
                        acronymAdapter.updateItems(it.lfs ?: emptyList())
                    }

                }
                is UIState.ERROR -> {
                    println("Here is my Fragment ERROR")
                    state.error.localizedMessage?.let {
                        showError(it) {

                        }
                    }
                }
            }
        }

    }

    private fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setPositiveButton("RETRY") { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

}