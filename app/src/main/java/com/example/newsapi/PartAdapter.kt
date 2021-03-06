package com.example.newsapi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class PartAdapter(var items: List<PartData>, val callback: Callback) : RecyclerView.Adapter<PartAdapter.MainHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder
    = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.part_list_item, parent, false))

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.item_title)
    private val content = itemView.findViewById<TextView>(R.id.item_content)

    fun bind(item: PartData) {
        title.text = item.title
        content.text = item.content
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
        }
    }
}

interface Callback {
    fun onItemClicked(item: PartData)
}
}