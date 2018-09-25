package com.experionglobal.kotlinmvvmlearning.navigation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.experionglobal.kotlinmvvmlearning.viewmodel.ViewModelFactory

class FragmentManager {

    companion object {

        fun AppCompatActivity.addFragment(fragment: Fragment,frameId: Int){

            supportFragmentManager.inTransaction {
                add(frameId,fragment,fragment::class.java.simpleName)
            }
        }

        fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int){

            supportFragmentManager.inTransaction{
                replace(frameId,fragment,fragment::class.java.simpleName)
            }

        }

        fun AppCompatActivity.replaceFragmentWithBackStack(fragment: Fragment, frameId: Int){

            supportFragmentManager.inTransaction {

                replace(frameId,fragment,fragment::class.java.simpleName).addToBackStack(fragment::class.java.simpleName)
            }
        }

        inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
            beginTransaction().func().commit()
        }

        fun <viewModel : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<viewModel>) =
                ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)


    }
}