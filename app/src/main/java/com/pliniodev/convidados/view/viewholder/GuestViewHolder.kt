package com.pliniodev.convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pliniodev.convidados.R
import com.pliniodev.convidados.service.model.GuestModel
import com.pliniodev.convidados.view.listener.GuestListener

class GuestViewHolder(itemView: View, val listener: GuestListener): RecyclerView.ViewHolder(itemView) {

    /**
     * GuestListener é uma interface que está esperando receber um id(Int)
     */


    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener {
            listener.onCLick(guest.id)
        }
    }

}