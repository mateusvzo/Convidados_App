package com.veloso.convidados_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.veloso.convidados_app.R
import com.veloso.convidados_app.constants.DataBaseConstants
import com.veloso.convidados_app.databinding.ActivityGuestFormBinding
import com.veloso.convidados_app.model.GuestModel
import com.veloso.convidados_app.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(guestId, name, presence)
            viewModel.save(model)
        }
    }

    private fun observe() {
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if(it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer {

        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if(bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}