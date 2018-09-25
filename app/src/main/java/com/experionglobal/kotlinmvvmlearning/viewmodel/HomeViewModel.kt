package com.experionglobal.kotlinmvvmlearning.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.experionglobal.kotlinmvvmlearning.helper.DbHelper
import com.experionglobal.kotlinmvvmlearning.model.User
import com.experionglobal.kotlinmvvmlearning.model.UserResults
import com.experionglobal.kotlinmvvmlearning.services.DataEntryService

class HomeViewModel(context: Application): AndroidViewModel(context) {

    var dataResponse : MutableLiveData<UserResults> = MutableLiveData()
    val dataEntryService : DataEntryService = DataEntryService()
    var userResults: UserResults = UserResults(userInsertResult = false,userSearchData = ArrayList<User>())

    fun sendDetailsToDb(dbHelper: DbHelper, name: String, age: Int, sex: String) {

        var userDetails : User = User(userName = name,userAge = age,userSex = sex)

        dataEntryService.sendData(userDetails,dataResponse,dbHelper,userResults)

    }

    fun searchFromDb(dbHelper: DbHelper) {


      dataEntryService.searchUser(dbHelper,userResults,dataResponse)

    }

    fun searchFromDb(dbHelper: DbHelper,value: String) {


        dataEntryService.searchUser(dbHelper,userResults,dataResponse,value)

    }


}