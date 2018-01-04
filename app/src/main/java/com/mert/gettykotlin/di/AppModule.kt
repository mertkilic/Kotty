package com.mert.gettykotlin.di

import android.app.Application
import android.content.Context

import dagger.Binds
import dagger.Module

/**
 * Created by Mert Kilic on 19.10.2017.
 */

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}
