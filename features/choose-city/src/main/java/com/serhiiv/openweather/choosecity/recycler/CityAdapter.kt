package com.serhiiv.openweather.choosecity.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serhiiv.openweather.choosecity.R
import com.serhiiv.openweather.core.android.extention.inflate
import com.serhiiv.openweather.core.model.SelectableCity
import com.serhiiv.openweather.core.tools.Logger
import kotlinx.android.synthetic.main.list_item_city.view.*

class CityAdapter constructor(
    private val selectListener: (SelectableCity) -> Unit
) : ListAdapter<SelectableCity, CityAdapter.CityViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(parent.inflate(R.layout.list_item_city)).apply {
            itemView.setOnClickListener {
                val item = getItem(adapterPosition)
                selectListener(item)
            }
        }
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SelectableCity) = with(itemView) {
            name.text = "%s, %s".format(item.name, item.country)
        }
    }

    override fun submitList(list: List<SelectableCity>?) {
        Logger.d(list.toString())
        super.submitList(list)
    }

    companion object {
        @JvmStatic
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SelectableCity>() {
            override fun areItemsTheSame(
                oldItem: SelectableCity,
                newItem: SelectableCity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SelectableCity,
                newItem: SelectableCity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
