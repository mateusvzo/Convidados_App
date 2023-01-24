package com.veloso.convidados_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.veloso.convidados_app.model.GuestModel
import com.veloso.convidados_app.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
        listAllGuests.value = repository.getAll()
    }

    fun deleteGuest(id: Int) {
        repository.delete(id)
    }

    fun getPresent() {
        listAllGuests.value = repository.getPresent()
    }

    fun getAbsent() {
        listAllGuests.value = repository.getAbsent()
    }
}