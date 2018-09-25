package com.experionglobal.kotlinmvvmlearning.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.experionglobal.kotlinmvvmlearning.R
import com.experionglobal.kotlinmvvmlearning.navigation.FragmentManager.Companion.replaceFragmentWithBackStack
import com.experionglobal.kotlinmvvmlearning.ui.activities.HomeActivity
import kotlinx.android.synthetic.main.fragment_button_page.*

class ButtonPageFragment: BaseFragment() {

    lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        homeActivity = context as HomeActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_button_page,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterData.setOnClickListener{

            homeActivity.replaceFragmentWithBackStack(WriteDataFragment(),R.id.mainContainer)

        }

        getData.setOnClickListener(View.OnClickListener {

            homeActivity.replaceFragmentWithBackStack(ShowDataFragment(),R.id.mainContainer)
        })

        searchData.setOnClickListener(View.OnClickListener {

            homeActivity.replaceFragmentWithBackStack(SearchUserFragment(),R.id.mainContainer)

        })
    }

}