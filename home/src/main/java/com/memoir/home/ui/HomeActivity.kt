package com.memoir.home.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctech.common.view.afterTextChange
import com.memoir.home.R
import com.memoir.home.databinding.ActivityHomeBinding
import com.memoir.home.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeAdapter: WeatherAdapter

    companion object {
        const val DEBOUNCE_TEXT = 450L
    }


    private lateinit var mergeAdapter: ConcatAdapter


    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        setupRecyclerView()
        bindUi()
        setupListener()

    }

    private fun setupListener() {
        binding.btLoad.setOnClickListener {
            val city = binding.etCity.text.toString()
            viewModel.fetch(city)
        }
        lifecycleScope.launchWhenCreated {
            binding.etCity.afterTextChange()
                .drop(1)
                .debounce(DEBOUNCE_TEXT)
                .filter {
                    it.length >= 3
                }
                .collect {
                    viewModel.fetch(it)
                }
        }
    }


    private fun setupRecyclerView() {
        val lm = LinearLayoutManager(this)
        mergeAdapter = ConcatAdapter(homeAdapter)
        binding.rvFeeds.adapter = mergeAdapter
        binding.rvFeeds.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
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