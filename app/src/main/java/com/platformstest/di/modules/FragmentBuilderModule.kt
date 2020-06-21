package com.platformstest.di.modules

import com.platformstest.ui.BaseFragment
import com.platformstest.ui.FirstFragment
import com.platformstest.ui.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): FirstFragment

    @ContributesAndroidInjector
    abstract fun contributeSecondFragment(): SecondFragment

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): BaseFragment

}