package com.pliniodev.convidados.service.repository

import androidx.room.*
import com.pliniodev.convidados.service.model.GuestModel

/**
 * Métodos de execução no database
 * CRUD, Queries e etc
 */
@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel) : Long

    @Update
    fun update(guest: GuestModel) : Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM Guest")//Retorna todos os convidados
    fun getInvited(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")//Retorna os convidados presents
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")//Retorna os convidados ausentes
    fun getAbsent(): List<GuestModel>

}