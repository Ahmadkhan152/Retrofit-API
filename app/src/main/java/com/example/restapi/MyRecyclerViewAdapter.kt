package com.example.restapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.net.IDN

class MyRecyclerViewAdapter(var context:Context,var obj:PostModel):RecyclerView.Adapter<MyRecyclerViewAdapter.viewHolder>() {

    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val tvID:TextView=itemView.findViewById(R.id.tvID)
        val tvFirstName:TextView=itemView.findViewById(R.id.tvFirstName)
        val tvLastName:TextView=itemView.findViewById(R.id.tvLastName)
        val tvEmail:TextView=itemView.findViewById(R.id.tvEmail)
        val tvAvatar:ImageView=itemView.findViewById(R.id.tvAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        var myView=LayoutInflater.from(context).inflate(R.layout.recycler_view,parent,false)
        return viewHolder(myView)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.tvID.setText(" ID:"+obj.data.get(position).id.toString())
        holder.tvFirstName.setText(" First Name: "+obj.data.get(position).firstName)
        holder.tvLastName.setText(" Last Name: "+obj.data.get(position).lastName)
        holder.tvEmail.setText(""+obj.data.get(position).email)
        Glide.with(context).load(obj.data.get(position).avatar).into(holder.tvAvatar)
    }

    override fun getItemCount(): Int {
        return obj.data.size
    }
}