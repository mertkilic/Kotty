package com.mert.gettykotlin

import dagger.android.support.DaggerAppCompatActivity


/**
 * Created by Mert Kilic on 25.10.2017.
 */

abstract class BaseActivity : DaggerAppCompatActivity()
/*

        <VM : ViewModel> : DaggerAppCompatActivity(){
@Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

protected lateinit var viewModel: VM

abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
            }
        }*/
