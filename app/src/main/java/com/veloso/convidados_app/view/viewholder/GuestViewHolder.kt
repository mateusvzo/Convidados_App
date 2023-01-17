package com.veloso.convidados_app.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.veloso.convidados_app.databinding.RowGuestBinding
import com.veloso.convidados_app.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name
    }
}