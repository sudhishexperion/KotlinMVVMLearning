package com.experionglobal.kotlinmvvmlearning.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.experionglobal.kotlinmvvmlearning.R
import com.experionglobal.kotlinmvvmlearning.adapters.UserAdapter
import com.experionglobal.kotlinmvvmlearning.helper.DbHelper
import com.experionglobal.kotlinmvvmlearning.ui.activities.HomeActivity
import com.experionglobal.kotlinmvvmlearning.viewmodel.HomeViewModel
import com.experionglobal.kotlinmvvmlearning.navigation.FragmentManager.Companion.obtainViewModel
import kotlinx.android.synthetic.main.fragment_write_data.*
import com.experionglobal.kotlinmvvmlearning.navigation.FragmentManager.Companion.replaceFragment
import com.experionglobal.kotlinmvvmlearning.navigation.FragmentManager.Companion.replaceFragmentWithBackStack
import kotlinx.android.synthetic.main.fragment_button_page.*
import kotlinx.android.synthetic.main.fragment_showdata.*


class WriteDataFragment: BaseFragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var dbHelper: DbHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_write_data,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterDetails.setOnClickListener(View.OnClickListener {

            getViewModel().sendDetailsToDb(dbHelper,name.text.toString(),Integer.parseInt(age.text.toString()),sex.text.toString())



        })

        getViewModel().dataResponse.observe(this, Observer {

            if(it!!.userInsertResult){

                Toast.makeText(homeActivity,"Data Entered",Toast.LENGTH_LONG).show()
                name.setText("")
                age.setText("")
                sex.setText("")
                it!!.userInsertResult = false

            }


        })


    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)
        homeActivity = context as HomeActivity
        dbHelper = DbHelper(homeActivity)
    }

    private fun getViewModel(): HomeViewModel = (activity as HomeActivity)
            .obtainViewModel(HomeViewModel::class.java)
}