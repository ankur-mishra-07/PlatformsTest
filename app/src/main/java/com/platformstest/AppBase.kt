package com.platformstest

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.platformstest.common.SharedPreference
import com.platformstest.di.AppComponent
import com.platformstest.di.DaggerAppComponent
import com.platformstest.di.modules.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

val sPreference: SharedPreference by lazy {
    AppBase.sharedPreference!!
}

open class AppBase : MultiDexApplication(), HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> = this.dispatchingAndroidInjector!!


    @set:Inject
    internal var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
    private lateinit var appComponent: AppComponent

    companion object {
        var sharedPreference: SharedPreference? = null
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreference = SharedPreference(context = this)
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        AppInjector.init(this)
    }
}