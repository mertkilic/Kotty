package com.mert.gettykotlin.ui.home


import com.mert.gettykotlin.data.rest.RestService
import dagger.Module
import dagger.Provides

/**
 * Created by Mert Kilic on 24.10.2017.
 */
@Module
class HomeModule {

    @Provides
    fun providePresenter(service: RestService): HomeContract.Presenter {
        return HomePresenterImpl(service)
    }

}
