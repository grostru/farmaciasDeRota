package com.grt.farmacias.ui.deInteres

import BaseFragment
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.grt.farmacias.R
import com.grt.farmacias.databinding.FragmentDeInteresBinding
import com.grt.farmacias.databinding.FragmentDetailBinding
import com.grt.farmacias.model.FarmaciasModel
import com.grt.farmacias.utils.UtilsFarmacias
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeInteresFragment : BaseFragment<FragmentDeInteresBinding, DeInteresViewModel>() {

    override val vm: DeInteresViewModel by viewModel()

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDeInteresBinding {
        return FragmentDeInteresBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Iniciamos el fragmento ocultando el botón de descarga
        vm.onInit()

        setupBinding()
    }

    // Establecemos los datos de Detalle la Farmacia Seleccionado en la lista
    private fun setupBinding() {
        with(binding){

            tvAddressCentroSalud.setOnClickListener(View.OnClickListener {
                val uriBegin = "geo:${UtilsFarmacias.latCentroSalud},${UtilsFarmacias.lonCentroSalud}"
                val query = "${UtilsFarmacias.latCentroSalud},${UtilsFarmacias.lonCentroSalud}(Centro de Salud de Rota)"
                val encodedQuery = Uri.encode(query)
                val uriString = "$uriBegin?q=$encodedQuery&z=16"
                val uri = Uri.parse(uriString)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            })

            tvPhoneSaludResponde.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Salud Responde")
                builder.setMessage("¿Desea llamar por teléfono a Salud Responde?")
                builder.setPositiveButton(R.string.accept) { dialog, which ->
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:${UtilsFarmacias.tlfnoSaludResponde}")
                    startActivity(dialIntent)
                }
                builder.setNegativeButton(R.string.cancel) { dialog, which ->
                }
                builder.show()
            })

            tvClicSalud.movementMethod = LinkMovementMethod.getInstance()
            tvAppSaludResponde.movementMethod = LinkMovementMethod.getInstance()


        }
    }

}