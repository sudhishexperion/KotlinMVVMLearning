package com.experionglobal.kotlinmvvmlearning.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.experionglobal.kotlinmvvmlearning.R
import com.experionglobal.kotlinmvvmlearning.adapters.SearchAdapter
import com.experionglobal.kotlinmvvmlearning.adapters.UserAdapter
import com.experionglobal.kotlinmvvmlearning.helper.DbHelper
import com.experionglobal.kotlinmvvmlearning.navigation.FragmentManager.Companion.obtainViewModel
import com.experionglobal.kotlinmvvmlearning.ui.activities.HomeActivity
import com.experionglobal.kotlinmvvmlearning.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_button_page.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_showdata.*

class SearchUserFragment : BaseFragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var dbHelper: DbHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton.setOnClickListener(View.OnClickListener {

            getViewModel().searchFromDb(dbHelper,searchTextBox.text.toString())
        })

        searchBox.layoutManager = LinearLayoutManager(homeActivity)
        getViewModel().dataResponse.observe(this, Observer {

            searchBox.adapter = SearchAdapter(it!!.userSearchData, homeActivity)

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