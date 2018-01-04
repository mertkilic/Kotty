package com.mert.gettykotlin.di

import android.app.Application
import com.mert.gettykotlin.data.rest.NetworkModule
import com.mert.gettykotlin.ui.ActivityBuilder

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

/**
 * Created by Mert Kilic on 19.10.2017.
 */

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class, ActivityBuilder::class))
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
