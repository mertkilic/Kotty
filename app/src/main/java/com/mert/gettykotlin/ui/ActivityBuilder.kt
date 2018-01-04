package com.mert.gettykotlin.ui

import com.mert.gettykotlin.di.scope.PerActivity
import com.mert.gettykotlin.ui.home.HomeActivity
import com.mert.gettykotlin.ui.home.HomeModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Mert Kilic on 24.10.2017.
 */
@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    abstract fun bindHomeActivity(): HomeActivity

}
