package com.serhiiv.openweather.weather.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.serhiiv.openweather.core.android.extention.inflate
import com.serhiiv.openweather.weather.R
import com.serhiiv.openweather.weather.WeatherUiState
import kotlinx.android.synthetic.main.list_item_weather_day.view.*

class WeatherDailyAdapter :
    ListAdapter<WeatherUiState.WeatherDailyUiState, WeatherDailyAdapter.WeatherDayViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayViewHolder {
        return WeatherDayViewHolder(parent.inflate(R.layout.list_item_weather_day))
    }

    override fun onBindViewHolder(holder: WeatherDayViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class WeatherDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: WeatherUiState.WeatherDailyUiState) = with(itemView) {
            day_name.text = item.day
            temp.text = item.temp
            icon.load(item.iconUrl)
        }
    }

    companion object {
        @JvmStatic
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<WeatherUiState.WeatherDailyUiState>() {
            override fun areContentsTheSame(
                oldItem: WeatherUiState.WeatherDailyUiState,
                newItem: WeatherUiState.WeatherDailyUiState
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: WeatherUiState.WeatherDailyUiState,
                newItem: WeatherUiState.WeatherDailyUiState
            ): Boolean {
                return oldItem.day == newItem.day
            }
        }
    }
}
