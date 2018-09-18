package io.github.regres.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.regres.R
import io.github.regres.adapter.MainAdapter
import io.github.regres.databinding.ActivityMainBinding
import io.github.regres.ui.base.BaseActivity
import io.github.regres.utils.ViewModelFactory
import io.github.regres.utils.extensions.DataState
import io.github.regres.utils.extensions.getAppInjector
import io.github.regres.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun configureViewModel() {
        getAppInjector().inject(this)
        this.mainViewModel = getViewModel(this.viewModelFactory)
    }

    override fun configureUI() {
        setSupportActionBar(this.toolbar)
        title = "Reqres"

        this.mainAdapter = MainAdapter(this)
        this.recyclerView.adapter = this.mainAdapter

        val layoutManager = LinearLayoutManager(this)
        this.recyclerView.layoutManager = layoutManager
    }

    override fun configureBehavior() {
        this.mainViewModel.get().observe(this, Observer {
            Timber.d(it.data.toString())
            this.binding.data = it
            this.refreshLayout.isRefreshing = it.dataState == DataState.LOADING
        })

        this.refreshLayout.setOnRefreshListener {
            this.mainAdapter.clear()
            this.mainViewModel.get()
        }
    }

}
