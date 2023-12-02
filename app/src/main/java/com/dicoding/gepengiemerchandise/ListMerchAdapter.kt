package com.dicoding.gepengiemerchandise

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class ListMerchAdapter(private val listMerch: ArrayList<Merch>) :
    RecyclerView.Adapter<ListMerchAdapter.ListViewHolder>() {

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        onItemClickListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_merch, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listMerch[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MerchDetail::class.java)
            intent.putExtra("merchName", name)
            intent.putExtra("merchDescription", description)
            intent.putExtra("merchPhoto", photo)
            val extraText =
                holder.itemView.context.resources.getStringArray(R.array.merchExtraDescription)[position]
            intent.putExtra("merchExtraDescription", extraText)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listMerch.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}