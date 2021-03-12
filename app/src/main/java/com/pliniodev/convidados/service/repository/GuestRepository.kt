package com.pliniodev.convidados.service.repository

import android.content.Context
import com.pliniodev.convidados.service.model.GuestModel


class GuestRepository (context: Context){

    /**
     * a classe repositório deve ser feita para cada classe de entidade
     */

    //Acesso ao BD
    private val mDatabase = GuestDatabase.getDatabase(context).guestDAO()


    /**
     * buscar registro específico
     */
    fun get(id: Int): GuestModel {
        return mDatabase.get(id)
    }

    /**
     * insere convidado
     */
    fun save(guest: GuestModel): Boolean {
        return mDatabase.save(guest) > 0
    }

    /**
     * Listagem de todos
     */
    //fun nomeDaFunção(): retornaLista<Tipo_NoCaso_Model>
    fun getAll(): List<GuestModel> {
        return mDatabase.getInvited()
    }

    /**
     * Listagem dos presentes
     */
    fun getPresent(): List<GuestModel> {
        return mDatabase.getPresent()
    }

    /**
     * Listagem dos ausentes
     */
    fun getAbsent(): List<GuestModel> {
        return mDatabase.getAbsent()
    }

    fun update(guest: GuestModel): Boolean {
        return mDatabase.update(guest) > 0 // (>0) refere-se a : se atualizar mais q uma linh, faça
    }

    fun delete(guest: GuestModel){
        mDatabase.delete(guest)
    }


}