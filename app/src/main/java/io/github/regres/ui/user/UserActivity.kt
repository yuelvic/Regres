package io.github.regres.ui.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import io.github.regres.R
import io.github.regres.data.entities.User
import io.github.regres.databinding.ActivityUserBinding
import io.github.regres.ui.base.BaseActivity

class UserActivity : BaseActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)
    }

    override fun configureViewModel() {

    }

    override fun configureUI() {
        val data = Gson().fromJson(intent.getStringExtra("USER_INTENT"), User::class.java)
        binding.apply {
            user = data
        }
    }

}
