package com.platformstest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.platformstest.common.MyObserver
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class Base : AppCompatActivity(), HasSupportFragmentInjector, LifecycleObserver {
    override fun supportFragmentInjector() = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyObserver())
    }

    protected fun callFragmentSwitcher(
        currentFragment: Fragment,
        container: Int
    ) {
        supportFragmentManager.beginTransaction()
            .replace(container, currentFragment!!)
            .commitNow()
    }

}