package com.experionglobal.kotlinmvvmlearning.ui.activities

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.experionglobal.kotlinmvvmlearning.R
import com.experionglobal.kotlinmvvmlearning.navigation.FragmentManager.Companion.addFragment


import com.experionglobal.kotlinmvvmlearning.ui.fragments.ButtonPageFragment

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addFragment(ButtonPageFragment(),R.id.mainContainer)

    }


}
