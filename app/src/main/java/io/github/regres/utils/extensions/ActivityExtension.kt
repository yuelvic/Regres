package io.github.regres.utils.extensions

import android.app.Activity
import io.github.regres.App
import io.github.regres.di.Injector

val Activity.app: App get() = application as App

fun Activity.getAppInjector(): Injector = (app).injector