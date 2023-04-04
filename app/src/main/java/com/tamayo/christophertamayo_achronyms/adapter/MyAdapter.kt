package com.tamayo.christophertamayo_achronyms.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamayo.christophertamayo_achronyms.data.model.Lfs
import com.tamayo.christophertamayo_achronyms.databinding.AcronymItemBinding

class MyAdapter(
    private val itemSet: MutableList<Lfs> = mutableListOf(),
    private val onItemClick: (Lfs) -> Unit
) : RecyclerView.Adapter<AcronymViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<Lfs>) {
        println("I am here in update")
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)
            notifyDataSetChanged()


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder =
        AcronymViewHolder(
            AcronymItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = itemSet.size

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)
}

class AcronymViewHolder(private val binding: AcronymItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    //Binding the view in the cardView
    @SuppressLint("SetTextI18n")
    fun bind(item: Lfs, onItemClick: (Lfs) -> Unit) {

        binding.textViewMeaning.text = item.lf.toString()
        binding.textViewYear.text = "Since: ${item.since.toString()}"
        binding.textViewFrequency.text = "Frequency: ${item.freq.toString()}"

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}

