package com.experionglobal.kotlinmvvmlearning.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val application: Application):ViewModelProvider.NewInstanceFactory() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(application)
                            .also { INSTANCE = it }
                }
    }

    override fun <viewModel : ViewModel?> create(modelClass: Class<viewModel>) = with(modelClass){

        when{

            isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(application)

            else->
                throw IllegalArgumentException("Unknown View model Class")
    }

        }as viewModel
}