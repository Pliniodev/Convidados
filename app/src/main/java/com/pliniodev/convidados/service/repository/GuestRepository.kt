package com.pliniodev.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.pliniodev.convidados.service.constants.DataBaseConstants
import com.pliniodev.convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context){

    //Padrão Singleton para banco de dados utiliza apenas uma instancia da classe de repositorio
    //por isso é necessário o (private constructor())
    //cria-se uma variável de classe para o GuestDataBaseHelper
    //depois um método estático que retorna a instância desta classe
    //Neste padrão somente o método estático é responsável por te dar a instância da classe GuestRepository

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository//essa variável guarda a instância da classe

        fun getInstance(context: Context) : GuestRepository {
            if (!::repository.isInitialized) {//verifica se essa variavel já foi inicializada
                repository = GuestRepository(context)
            }
            return repository
        }
    }


    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val cv = ContentValues()
            cv.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            cv.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, cv)
            true
        }catch (e: Exception) {
            false
        }
    }

    //fun nomeDaFunção(): retornaLista<Tipo_NoCaso_Model>
    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase



            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }
            cursor?.close()
            list
        }catch (e: Exception) {
            list
        }
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                    DataBaseConstants.GUEST.COLUMNS.ID,
                    DataBaseConstants.GUEST.COLUMNS.NAME,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }
            cursor?.close()
            list
        }catch (e: Exception) {
            list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }
            cursor?.close()
            list
        }catch (e: Exception) {
            list
        }
    }

    //buscar registro específico
    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                    DataBaseConstants.GUEST.COLUMNS.NAME,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"//whereClause
            val args = arrayOf(id.toString())//whereArgs

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
                    projection,
                    selection,
                    args,
                    null,
                    null,
                    null
            )
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)
            }
            cursor?.close()
            guest
        }catch (e: Exception) {
            guest
        }

    }

    //CRUD



    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val cv = ContentValues()
            cv.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            cv.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            //Critério de atualização
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"//whereClause
            val args = arrayOf(guest.id.toString())//whereArgs

            db.update(DataBaseConstants.GUEST.TABLE_NAME, cv, selection, args)
            true
        }catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase


            //Critério de atualização
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"//whereClause
            val args = arrayOf(id.toString())//whereArgs

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        }catch (e: Exception) {
            false
        }
    }


}