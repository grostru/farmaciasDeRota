package com.grt.farmacias.ui.listadoFarmacias

import BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grt.farmacias.R
import com.grt.farmacias.common.NavData
import com.grt.farmacias.databinding.FragmentFarmaciasBinding
import com.grt.farmacias.model.FarmaciasModel
import com.grt.farmacias.ui.dialog.DialogFragment
import com.grt.farmacias.utils.UtilsFarmacias
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FarmaciasFragment : BaseFragment<FragmentFarmaciasBinding, FarmaciasViewModel>() {

    private val dialogMessage by lazy { DialogFragment.newInstance() }

    override val vm: FarmaciasViewModel by sharedViewModel()

    private val farmaciaAdapter by lazy {
        FarmaciasAdapter(){
            // Capturamos la acción de pulsar en un elemento de la lista
            vm.onActionFarmaciasClicked(it)
        }
    }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFarmaciasBinding {
        return FragmentFarmaciasBinding.inflate(inflater,container,false)
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

            // Observamos la lista obtenida
            setupBinding()

            // Iniciamos la ReciclyerView
            setupRecycler()
        }
    }

    private fun setupBinding() {
        observeData(vm.obsListFarmacias,::onObserveList)
    }

    private fun onObserveList(list: List<FarmaciasModel>) {

        farmaciaAdapter.updateList(list)
    }

    private fun setupRecycler() {
        with(binding) {
            rvHome.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvHome.adapter = farmaciaAdapter

            // Añadimos al recycler una decoración divisoria
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            requireContext().getDrawable(R.drawable.line_divider)
                ?.let { dividerItemDecoration.setDrawable(it) }
            rvHome.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onNavigate(navData: NavData) {
        when(navData.id){
            FarmaciasViewModel.NAV_DETAIL ->{

                val farmacia = navData.data as FarmaciasModel

                findNavController().navigate(FarmaciasFragmentDirections.actionNavigationListadoFarmaciasToDetailFragment(farmacia))
            }
        }
    }
}