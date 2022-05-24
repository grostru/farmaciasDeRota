package com.grt.farmacias.ui.detail

import BaseFragment
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.grt.farmacias.R
import com.grt.farmacias.databinding.FragmentDetailBinding
import com.grt.farmacias.model.FarmaciasModel
import com.grt.pokemon.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created por Gema Rosas Trujillo
 * 19/02/2022
 *
 * Fragmento Detalle de la lista de Farmacias.
 * Se muestra la información detallada de cada farmacia
 */
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val vm: DetailViewModel by viewModel()

    val args: DetailFragmentArgs by navArgs()

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Iniciamos el fragmento ocultando el botón de descarga
        vm.onInit()

        // Obtenemos la Farmcia que hemos clickeado en la lista para mostrarlo,
        // ya que lo hemos pasado como argumento en el Navigation
        val pokemon = args.farmacia
        vm.onAttachFarmacia(pokemon)
        observeData(vm.obsFarmacia,::onObserveFarmacia)

    }

    // Establecemos los datos de Detalle del Pokemon Seleccionado en la lista
    private fun onObserveFarmacia(farmaciaModel: FarmaciasModel) {
        with(binding){
            tvDetailName.text = farmaciaModel.name.uppercase()
            tvAddress.text = farmaciaModel.address
            tvPhone.text = farmaciaModel.tlfno

            tvAddress.setOnClickListener(View.OnClickListener {
                val uriBegin = "geo:${farmaciaModel.lat},${farmaciaModel.lon}"
                val query = "${farmaciaModel.lat},${farmaciaModel.lon}(${farmaciaModel.name})"
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
                    dialIntent.data = Uri.parse("tel:${farmaciaModel.tlfno}")
                    startActivity(dialIntent)
                }
                builder.setNegativeButton(R.string.cancel) { dialog, which ->
                }
                builder.show()
            })
        }
    }


}
