package com.experionglobal.kotlinmvvmlearning.adapters
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.experionglobal.kotlinmvvmlearning.R
import com.experionglobal.kotlinmvvmlearning.model.User
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserAdapter(val users: ArrayList<User>,val context: Context): RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var userDetail : User


        userDetail = users.get(position)

        holder.user_name.setText(userDetail.userName)
        holder.user_age.setText(userDetail.userAge.toString())
        holder.user_sex.setText(userDetail.userSex)

    }



    override fun getItemCount(): Int {
        return users.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val user_name = view.userName
    val user_age = view.userAge
    val user_sex = view.userSex

}
