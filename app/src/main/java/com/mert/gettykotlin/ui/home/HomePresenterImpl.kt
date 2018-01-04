package com.mert.gettykotlin.ui.home

import com.mert.gettykotlin.data.Item
import com.mert.gettykotlin.data.rest.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Mert Kilic on 24.10.2017.
 */

class HomePresenterImpl @Inject
constructor(private val service: RestService) : HomeContract.Presenter {
    private var view: HomeContract.View? = null
    private val disposables = CompositeDisposable()

    private fun getObserver()  : DisposableObserver<List<Item>>{
        return object  : DisposableObserver<List<Item>>(){
            override fun onNext(value: List<Item>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }



    override fun fetchItems(page: Int, pageSize: Int) {
        view!!.showProgress()
        service.getSomething(pageSize, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    private fun processData(items: List<Item>?) {
        view!!.onItemsLoaded(items!!)
    }

    private fun catchError(e: Throwable) {
        view!!.onError(e)
    }

    override fun takeView(view: HomeContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun startPresentation() {
        fetchItems(1, RestService.DEFAULT_PAGE_SIZE)
    }

    override fun stopPresentation() {
        disposables.clear()
    }
}
