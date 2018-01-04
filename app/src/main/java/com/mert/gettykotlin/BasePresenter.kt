package com.mert.gettykotlin

/**
 * Created by Mert Kilic on 19.10.2017.
 */

interface BasePresenter<in V : BaseView> {
    fun takeView(view: V)

    fun dropView()

    fun startPresentation()

    fun stopPresentation()
}
