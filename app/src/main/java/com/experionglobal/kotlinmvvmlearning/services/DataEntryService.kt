package com.experionglobal.kotlinmvvmlearning.services

import android.arch.lifecycle.MutableLiveData
import com.experionglobal.kotlinmvvmlearning.helper.DbHelper
import com.experionglobal.kotlinmvvmlearning.model.User
import com.experionglobal.kotlinmvvmlearning.model.UserResults


class DataEntryService {



    fun sendData(userDetails: User, dataResponse: MutableLiveData<UserResults>,dbHelper : DbHelper,userResults: UserResults) {


        var result = dbHelper.insertUserDetails(userDetails)
        userResults.userInsertResult = true
        dataResponse.value = userResults

    }

    fun searchUser(dbHelper: DbHelper,userResults: UserResults,dataResponse: MutableLiveData<UserResults>) {

        var users: ArrayList<User> = dbHelper.searchFromDb()
        userResults.userSearchData = users
        dataResponse.value = userResults
    }

    fun searchUser(dbHelper: DbHelper,userResults: UserResults,dataResponse: MutableLiveData<UserResults>,value: String) {

        var users: ArrayList<User> = dbHelper.searchFromDb(value)
        userResults.userSearchData = users
        dataResponse.value = userResults
    }


}