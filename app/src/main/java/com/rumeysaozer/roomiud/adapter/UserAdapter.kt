package com.rumeysaozer.roomiud.adapter

import android.nfc.tech.NfcV
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rumeysaozer.roomiud.R
import com.rumeysaozer.roomiud.model.User
import com.rumeysaozer.roomiud.view.ListFragmentDirections

class UserAdapter :RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var userList = emptyList<User>()
    class UserViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tname = view.findViewById<TextView>(R.id.name)
        val tage = view.findViewById<TextView>(R.id.age)
        fun bind(user: User){
            tage.text = user.name
            tname.text = user.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(userList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return userList.size
    }
    fun setData(userList: List<User>){
        this.userList = userList
        notifyDataSetChanged()
    }

}