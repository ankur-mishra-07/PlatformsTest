package com.platformstest.di.modules

import com.platformstest.ui.BaseFragment
import com.platformstest.ui.FirstFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contains all fragment to be bound to application dependency graph
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): FirstFragment

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): BaseFragment

}