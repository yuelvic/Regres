package io.github.regres.ui.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import io.github.regres.R
import io.github.regres.databinding.ActivityAddBinding
import io.github.regres.ui.base.BaseActivity
import io.github.regres.utils.ViewModelFactory
import io.github.regres.utils.extensions.getAppInjector
import io.github.regres.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_add.*
import javax.inject.Inject

class AddActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAddBinding>(this, R.layout.activity_add)
    }

    override fun configureViewModel() {
        getAppInjector().inject(this)
        this.userViewModel = getViewModel(viewModelFactory)
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
}
