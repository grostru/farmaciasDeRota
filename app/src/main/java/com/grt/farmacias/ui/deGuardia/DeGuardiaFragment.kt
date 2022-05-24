package com.grt.farmacias.ui.deGuardia

import BaseFragment
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grt.farmacias.databinding.FragmentDeGuardiaBinding
import com.grt.farmacias.model.FarmaciasModel
import com.grt.farmacias.ui.dialog.DialogFragment
import com.grt.farmacias.ui.listadoFarmacias.FarmaciasFragment
import com.grt.farmacias.utils.UtilsFarmacias
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.grt.farmacias.R

class DeGuardiaFragment : BaseFragment<FragmentDeGuardiaBinding, DeGuardiaViewModel>() {

    private val dialogMessage by lazy { DialogFragment.newInstance() }

    override val vm: DeGuardiaViewModel by sharedViewModel()

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDeGuardiaBinding {
        return FragmentDeGuardiaBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comprobarConnectionInternet()
    }

    // Método privado para comprobar la conexión a internet.
    // Si el usuario no tiene conexión, mostramos un mensaje de aviso indicándolo
    // y no se podrá descargar de nuevo la lista de Farmacias de internet
    private fun comprobarConnectionInternet(){
        if (!UtilsFarmacias.isNetworkAvailable(requireContext())){
            dialogMessage.show(parentFragmentManager, FarmaciasFragment::class.java.name, "Revise su conexión a Internet"){
                dialogMessage.dismiss(parentFragmentManager)
            }
        } else {
            vm.onInitialization()

            setupBinding()

        }
    }

    private fun setupBinding() {
        observeData(vm.obsListDeGuardia,::onObserveList)
    }

    private fun onObserveList(list: List<FarmaciasModel>) {
        val farmaciaDeGuardia = list[0]

        with(binding){
            tvName.setText(farmaciaDeGuardia.name)
            tvAddress.setText(farmaciaDeGuardia.address)
            tvPhone.setText(farmaciaDeGuardia.tlfno)

            tvAddress.setOnClickListener(View.OnClickListener {
                val uriBegin = "geo:${farmaciaDeGuardia.lat},${farmaciaDeGuardia.lon}"
                val query = "${farmaciaDeGuardia.lat},${farmaciaDeGuardia.lon}(${farmaciaDeGuardia.name})"
                val encodedQuery = Uri.encode(query)
                val uriString = "$uriBegin?q=$encodedQuery&z=16"
                val uri = Uri.parse(uriString)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            })

            tvPhone.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Farmacia de Guardia")
                builder.setMessage("¿Desea llamar por teléfono a esta Farmacia?")
                builder.setPositiveButton(R.string.accept) { dialog, which ->
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:${farmaciaDeGuardia.tlfno}")
                    startActivity(dialIntent)
                }
                builder.setNegativeButton(R.string.cancel) { dialog, which ->
                }
                builder.show()
            })
        }
    }
}