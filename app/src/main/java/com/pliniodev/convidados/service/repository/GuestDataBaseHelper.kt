package com.pliniodev.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.pliniodev.convidados.service.constants.DataBaseConstants

class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {//caso não haja um banco de dados ele cria
        db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {//caso haja ele faz o upgrade

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Convidados.db"

        private const val CREATE_TABLE_GUEST =
                ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                        + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                        + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
                        + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }
}