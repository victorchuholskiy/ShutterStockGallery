package com.gmail.victorchuholskiy.shutterstockgallery.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.victorchuholskiy.shutterstockgallery.R
import com.gmail.victorchuholskiy.shutterstockgallery.data.models.ImageModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class ImagesAdapter(private var items: MutableList<ImageModel>, private val listener: (ImageModel) -> Unit) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

	var page: Int = 0

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))

	override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

	override fun getItemCount() = items.size

	fun setData(wallets: List<ImageModel>) {
		items.clear()
		page = 1
		addData(wallets)
	}

	fun addData(wallets: List<ImageModel>) {
		items.addAll(wallets)
		page++
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: ImageModel, listener: (ImageModel) -> Unit) = with(itemView) {
			Picasso.get()
					.load(item.url)
					.into(itemView.iv_image)
			setOnClickListener { listener(item) }
		}
	}
}