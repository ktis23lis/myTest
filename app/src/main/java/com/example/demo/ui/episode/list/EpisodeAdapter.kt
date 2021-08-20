package com.example.demo.ui.episode.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.domain.Episode

class EpisodeAdapter(
    private val onClick : (Int) -> Unit
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    var episodeList = arrayListOf<Episode>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.episode_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodeList[position])
        holder.itemView.setOnClickListener {
            onClick(episodeList[position].episodeId)
        }
    }

    override fun getItemCount() = episodeList.size

    class EpisodeViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        private val episodeName : TextView = itemView.findViewById(R.id.episodeName)
        private val episodeNumber : TextView = itemView.findViewById(R.id.episodeNumber)
        private val episodeAirFate : TextView = itemView.findViewById(R.id.episodeAirFate)

        fun bind(episode: Episode){
            episodeName.text = episode.episodeName
            episodeNumber.text = episode.episode
            episodeAirFate.text = episode.episodeAirFate
        }
    }
}