package com.apsan.usingkoin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apsan.usingkoin.R
import com.apsan.usingkoin.databinding.FragHomeBinding
import com.apsan.usingkoin.models.Articles
import com.apsan.usingkoin.utils.LoadingState
import com.apsan.usingkoin.viewmodel.NewsViewmodel
import com.hoc081098.viewbindingdelegate.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.frag_home) {

    private val binding by viewBinding(FragHomeBinding::bind)
    private val MyViewModel by viewModel<NewsViewmodel>()
    val TAG = "tag"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root
        Log.d(TAG, "onViewCreated: starting frag")

        initRecyclerView()

        binding.newsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch(Dispatchers.IO) {
                    MyViewModel.getTopNewsFromRepo(newText)
                }
                return true
            }

        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        val adapter = NewsArticlesAdapter(MyViewModel.data.value as ArrayList<Articles>)

        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.setHasFixedSize(true)
        binding.newsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        MyViewModel.apply {
            // api response array list observe
            this.data.observe(viewLifecycleOwner) {
                if (binding.newsRecyclerView.adapter != null) {
                    adapter.articleList.clear()
                    adapter.articleList.addAll(it)
                    adapter.notifyDataSetChanged()
                    Log.d(TAG, "initRecyclerView: notifydataset")
                }
                Log.d(TAG, "initRecyclerView: ${it.size}")
            }
            // loading state response observe
            this.loadingState.observe(viewLifecycleOwner){
                when (it) {
                    LoadingState.LOADING -> { binding.progressWheel.visibility = View.VISIBLE }
                    else -> { binding.progressWheel.visibility = View.INVISIBLE }
                }
            }
        }
    }


}