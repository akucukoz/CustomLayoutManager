package com.aranteknoloji.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var data = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView: TextView by lazy { itemView.findViewById(R.id.item_text) as TextView }

    fun bind(item: String) {
        textView.text = item
    }
}