package com.veloso.convidados_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.veloso.convidados_app.model.GuestModel
import com.veloso.convidados_app.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }

}