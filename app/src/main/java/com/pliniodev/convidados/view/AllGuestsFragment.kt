package com.pliniodev.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pliniodev.convidados.R
import com.pliniodev.convidados.view.adapter.GuestAdapter
import com.pliniodev.convidados.viewmodel.AllGuestsViewModel
import kotlinx.android.synthetic.main.fragment_all.*

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //RecyclerView
        //1º Obter a recyclerView
        //obs root que armazena a criação do layout
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        //2º Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)

        //3º - Definir um adapter
        recycler.adapter = mAdapter

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()
    }

    //para o contexto foi utilizado o viewLifeCycleOwner que faz o mesmo papel do contexto
    //isso é necessário devido a utilização do fragment
    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}