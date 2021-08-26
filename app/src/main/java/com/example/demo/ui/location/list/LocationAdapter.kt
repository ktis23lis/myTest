package com.example.demo.ui.location.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.domain.model.Location

class LocationAdapter(
    private val onClick :(Int) ->Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    var locationList = arrayListOf<Location>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.location_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locationList[position])
        holder.itemView.setOnClickListener {
            onClick(locationList[position].locationId)
        }
    }

    override fun getItemCount() = locationList.size

    class LocationViewHolder (itemView :View) : RecyclerView.ViewHolder(itemView){
        private val locationName : TextView= itemView.findViewById(R.id.locationName)
        private val locationType : TextView= itemView.findViewById(R.id.locationType)
        private val locationDimension : TextView= itemView.findViewById(R.id.locationDimension)

        fun bind(location: Location){
            locationName.text = location.locationName
            locationType.text = location.locationType
            locationDimension.text = location.locationDimension
        }

    }
}