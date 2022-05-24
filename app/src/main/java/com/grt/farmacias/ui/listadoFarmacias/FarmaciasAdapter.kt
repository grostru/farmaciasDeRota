package com.grt.farmacias.ui.listadoFarmacias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grt.farmacias.R
import com.grt.farmacias.databinding.ItemFarmaciasBinding
import com.grt.farmacias.model.FarmaciasModel
import com.squareup.picasso.Picasso
import java.io.File

/**
 * Created por Gema Rosas Trujillo
 * 16/02/2022
 *
 * Clase manejadora que se encarga de adaptar cada farmacia a la lista
 */
class FarmaciasAdapter(private val farmaciaList:List<FarmaciasModel> = emptyList(),
                       private val listener: (FarmaciasModel) -> Unit)
    : RecyclerView.Adapter<FarmaciasAdapter.HomeFarmaciasViewHolder>() {

    private var mutableFarmaciaList : MutableList<FarmaciasModel> = mutableListOf(*farmaciaList.toTypedArray())

    inner class HomeFarmaciasViewHolder(val binding : ItemFarmaciasBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(farmaciaModel: FarmaciasModel){
            val context = itemView.context
            with(binding){
                farmaciaModel.also {
                    tvName.text = it.name
                    tvAdress.text = it.address
                    when (it.id.toInt()){
                        1,4,5,6,7,10 ->
                            // Imagen Farmacia
                            binding.sivFarmacia.setImageResource(R.drawable.ic_image_green)

                        else ->
                            binding.sivFarmacia.setImageResource(R.drawable.ic_image_red)

                    }
                }
            }
            itemView.setOnClickListener {
                listener(farmaciaModel)
            }
        }
    }

    fun updateList(list: List<FarmaciasModel>){
        mutableFarmaciaList.clear()
        mutableFarmaciaList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFarmaciasViewHolder {
        val binding = ItemFarmaciasBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeFarmaciasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFarmaciasViewHolder, position: Int) {
        holder.bind(mutableFarmaciaList[position])
    }

    override fun getItemCount(): Int {
        return mutableFarmaciaList.size
    }
}