package com.veloso.convidados_app.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.veloso.convidados_app.constants.DataBaseConstants
import com.veloso.convidados_app.databinding.FragmentAbsentBinding
import com.veloso.convidados_app.view.adapter.GuestsAdapter
import com.veloso.convidados_app.view.listener.OnGuestListener
import com.veloso.convidados_app.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private val adapter = GuestsAdapter()
    private lateinit var viewModel: GuestsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        //Layout - atribui um layout que diz como a recyclerview se comporta
        binding.recyclerAbsentGuests.layoutManager = LinearLayoutManager(context)

        //Adapter - faz a ligacao do recyclerview com a listagem de itens
        binding.recyclerAbsentGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                viewModel.deleteGuest(id)
                viewModel.getAbsent()
            }

        }

        observe()

        adapter.attachListener(listener)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }
    }
}