package io.github.regres.ui.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import io.github.regres.R
import io.github.regres.data.entities.User
import io.github.regres.databinding.ActivityAddBinding
import io.github.regres.ui.base.BaseActivity
import io.github.regres.utils.ViewModelFactory
import io.github.regres.utils.extensions.DataState
import io.github.regres.utils.extensions.getAppInjector
import io.github.regres.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class AddActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityAddBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_add)
    }

    override fun configureViewModel() {
        getAppInjector().inject(this)
        this.userViewModel = getViewModel(this.viewModelFactory)
    }

    override fun configureUI() {
        setSupportActionBar(this.addToolbar)
        supportActionBar.apply {
            this!!.setDisplayHomeAsUpEnabled(true)
        }
        this.addToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun configureBehavior() {
        btnAdd.onClick {
            val user = User()
            user.name = this@AddActivity.etName.text.toString()
            user.job = this@AddActivity.etJob.text.toString()
            this@AddActivity.userViewModel.addUser(user).apply {
                observe(this@AddActivity, Observer {
                    if (it.dataState == DataState.SUCCESS) {
                        finish()
                    }
                })
            }
        }
    }
}
