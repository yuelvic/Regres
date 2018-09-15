package io.github.regres.utils.extensions

import androidx.fragment.app.Fragment
import io.github.regres.App
import io.github.regres.di.Injector

val Fragment.app: App get() = activity!!.application as App

fun Fragment.getAppInjector(): Injector = (app).injector