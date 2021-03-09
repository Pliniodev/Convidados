package com.pliniodev.convidados.view

import android.content.Intent
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
import com.pliniodev.convidados.service.constants.GuestConstants
import com.pliniodev.convidados.view.adapter.GuestAdapter
import com.pliniodev.convidados.view.listener.GuestListener
import com.pliniodev.convidados.viewmodel.GuestsViewModel

class PresentFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_present, container, false)

        //RecyclerView
        //1º Obter a recyclerView
        //obs root que armazena a criação do layout
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_presents)

        //2º Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)

        //3º - Definir um adapter
        recycler.adapter = mAdapter

        //implementação do listener
        mListener = object : GuestListener{
            override fun onCLick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                //utilizando o Bundle() é possível a passagem de parâmetros entre activities
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)
            }
        }

        mAdapter.attachListener(mListener)

        observer()


        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENT)
    }


    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}