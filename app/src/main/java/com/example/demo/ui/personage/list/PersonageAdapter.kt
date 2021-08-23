package com.example.demo

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.domain.model.Personage
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class PersonageAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<PersonageAdapter.PersonageViewHolder>(), Loading{

    var personageList = arrayListOf<Personage>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonageViewHolder =
        PersonageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.personage_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: PersonageViewHolder, position: Int) {

//        if ((position >= itemCount - 1 )){
//            load(holder, position)
//        }



        holder.bind(personageList[position])
        holder.itemView.setOnClickListener {
            onClick(personageList[position].personageId)
        }
    }

    override fun getItemCount(): Int = personageList.size

    class PersonageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val personageImage: ImageView = itemView.findViewById(R.id.personageImage)
        private val personageName: TextView = itemView.findViewById(R.id.personageName)
        private val personageSpecies: TextView = itemView.findViewById(R.id.personageSpecies)
        private val personageStatus: TextView = itemView.findViewById(R.id.personageStatus)
        private val personageGender: TextView = itemView.findViewById(R.id.personageGender)

        fun bind(personage: Personage) {
            loadingImage(personage.personageImage, personageImage)
            personageName.text = personage.personageName
            personageSpecies.text = personage.personageSpecies
            personageStatus.text = personage.personageStatus
            personageGender.text = personage.personageGender
        }

        private fun loadingImage(string : String, imageView : ImageView){
            Picasso.get().load(string).into(
                imageView, object : Callback{
                    override fun onSuccess() {
                       Log.d("OK", "OK")
                    }
                    override fun onError(e: Exception?) {
                        Log.d("Error", "Error")
                    }
                })
        }
    }

    override fun load(holder: PersonageAdapter.PersonageViewHolder,  position: Int) {
        holder.bind(personageList[position])
    }

}

interface Loading{
    fun load(holder: PersonageAdapter.PersonageViewHolder,  position: Int)
}