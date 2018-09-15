package io.github.regres.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    protected abstract fun configureViewModel()
    open fun configureUI() {}
    open fun configureBehavior() {}
    open fun configureEvent() {}
    open fun releaseEvent() {}

    override fun onStart() {
        super.onStart()
        configureViewModel()
        configureUI()
        configureBehavior()
    }

    override fun onResume() {
        super.onResume()
        configureEvent()
    }

    override fun onPause() {
        releaseEvent()
        super.onPause()
    }
}