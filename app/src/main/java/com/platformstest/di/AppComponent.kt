package com.platformstest.di

import android.app.Application
import com.platformstest.AppBase
import com.platformstest.di.annotations.ApplicationScope
import com.platformstest.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Main application component that connects all the dependency providers(modules) to application
 */
@ApplicationScope
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilderModule::class, FragmentBuildersModule::class, ViewModelModule::class]
)
interface AppComponent :AndroidInjector<AppBase>{
    override fun inject(mApplication: AppBase)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}