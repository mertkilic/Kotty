package com.mert.gettykotlin.data.rest

import com.mert.gettykotlin.data.Item
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mert Kilic on 24.10.2017.
 */

interface RestService {

    @GET("/getSomething")
    fun getSomething(@Query("page_size") pageSize: Int, @Query("page") page: Int): Observable<List<Item>>

    companion object {

        val DEFAULT_PAGE_SIZE = 20
    }
}
