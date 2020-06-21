package com.platformstest.di.modules

import com.platformstest.di.annotations.ActivityScope
import com.platformstest.ui.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contains all activities to be bound to application dependency graph
 */
@Module
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseActivity

}