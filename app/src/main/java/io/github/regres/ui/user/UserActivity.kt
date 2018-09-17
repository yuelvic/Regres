package io.github.regres.ui.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.gson.Gson
import io.github.regres.R
import io.github.regres.data.entities.User
import io.github.regres.databinding.ActivityUserBinding
import io.github.regres.ui.base.BaseActivity
import io.github.regres.utils.ViewModelFactory
import io.github.regres.utils.extensions.DataState
import io.github.regres.utils.extensions.getAppInjector
import io.github.regres.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class UserActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityUserBinding
    private lateinit var userViewModel: UserViewModel

    private lateinit var data: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)
    }

    override fun configureViewModel() {
        getAppInjector().inject(this)
        this.userViewModel = getViewModel(this.viewModelFactory)
    }

    override fun configureUI() {
        this.data = Gson().fromJson(intent.getStringExtra("USER_INTENT"), User::class.java)
        binding.apply {
            user = this@UserActivity.data
        }

        setSupportActionBar(this.userToolbar)
        title = data.firstName + " " + data.lastName

        supportActionBar.apply {
            this!!.setDisplayHomeAsUpEnabled(true)
        }
        this.userToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun configureBehavior() {
        this.btnDelete.onClick {
            this@UserActivity.userViewModel.deleteUser(data.id).apply {
                observe(this@UserActivity, Observer {
                    binding.data = it
                    if (it.dataState == DataState.SUCCESS) {
                        finish()
                    }
                })
            }
        }
    }

}
