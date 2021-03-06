package com.pliniodev.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pliniodev.convidados.service.model.GuestModel
import com.pliniodev.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    //
    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel(id, name, presence) // criou uma variavel com o model e recebeu os parametros

        //se id == 0 salve o convidado
        //senão faça o update
        if (id == 0) {
            mSaveGuest.value = mGuestRepository.save(guest)//envia o objeto guest para o repository
        } else {
            mSaveGuest.value = mGuestRepository.update(guest)
        }
    }

    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }
}