package com.example.newsapp.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var adapter: HomeAdapter? = null
    private val viewModel by viewModels<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        adapter = HomeAdapter()
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.newsListLiveData.observe(viewLifecycleOwner) { newsList ->
            if (newsList.isNullOrEmpty()) {
                // show empty view
            } else {
                adapter?.submitList(newsList)
            }

        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }
}