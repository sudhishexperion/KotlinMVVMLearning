package com.experionglobal.kotlinmvvmlearning.helper

import android.provider.BaseColumns

object DbDetails {


    class UserDetails : BaseColumns {

        companion object {

            val TABLE_NAME = "userdetails"
            val COLUMN_NAME = "name"
            val COLUMN_AGE = "age"
            val COLUMN_SEX = "sex"
        }
    }
}