package com.memoir.home.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctech.memoir.utils.OnItemClick
import com.memoir.home.R
import com.memoir.home.databinding.ActivityHomeBinding
import com.memoir.home.viewmodel.PropertiesViewModel
import com.memoir.home.viewmodel.PropertyItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeAdapter: PropertiesAdapter

    private lateinit var viewModel: PropertiesViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[PropertiesViewModel::class.java]
        setupRecyclerView()
        bindUi()
        setupListener()
        viewModel.fetch()
    }

    private fun setupListener() {
        binding.vError.btRetry.setOnClickListener {
            viewModel.fetch()
        }
    }


    private fun setupRecyclerView() {
        val lm = LinearLayoutManager(this)
        binding.rvFeeds.adapter = homeAdapter
        binding.rvFeeds.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        homeAdapter.listener = OnItemClick{ view, item ->
            if(item is PropertyItemViewModel) {
                viewModel.onFavClickedAt(item)
            }
        }
        binding.rvFeeds.layoutManager = lm
    }

    private fun bindUi() {
        viewModel.store.observe(this) {
            when {
                it.loading -> {
                    binding.vLoading.root.visibility = View.VISIBLE
                    binding.flContent.visibility = View.GONE
                    binding.vError.root.visibility = View.GONE
                }
                it.error != null -> {
                    binding.flContent.visibility = View.GONE
                    binding.vLoading.root.visibility = View.GONE
                    binding.vError.root.visibility = View.VISIBLE
                    findViewById<TextView>(R.id.tvError).text = it.error.message
                }
                it.content != null -> {
                    binding.flContent.visibility = View.VISIBLE
                    binding.vLoading.root.visibility = View.GONE
                    binding.vError.root.visibility = View.GONE

                    homeAdapter.submitList(it.content)
                }
            }
        }
    }


}