package com.example.demo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demo.databinding.FragmentPersonageDetailsBinding
import com.example.demo.domain.Location
import com.example.demo.domain.Personage
import com.example.demo.ui.PersonageDetailsViewModel
import com.example.demo.ui.episode.list.EpisodeAdapter
import com.example.demo.ui.episode.list.EpisodeListFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PersonageDetailsFragment : Fragment(R.layout.fragment_personage_details) {

    private val personageDetailsViewModel by viewModels<PersonageDetailsViewModel>()
    private lateinit var goPersonageBack : GoPersonageBack
    private lateinit var goLocation : GoLocation
    private lateinit var binding: FragmentPersonageDetailsBinding

    private lateinit var episodeAdapter : EpisodeAdapter

    private lateinit var itemEpisodeSelected: EpisodeListFragment.ItemEpisodeSelected
    private lateinit var personage: Personage
    private var originId = 0
    private var locationId = 0

    companion object {
        fun newInstance(personage: Personage) = PersonageDetailsFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(PersonageListFragment.PERSONAGE_LIST_TAG, personage)
            arguments = bundle
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        goPersonageBack = context as GoPersonageBack
        goLocation = context as GoLocation
        itemEpisodeSelected = context as EpisodeListFragment.ItemEpisodeSelected


        episodeAdapter = EpisodeAdapter { i ->
            itemEpisodeSelected.onItemEpisodeSelected(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            personage = requireArguments().getParcelable(PersonageListFragment.PERSONAGE_LIST_TAG)!!

            personageDetailsViewModel.getPersonage(personage.personageId, personage.personageEpisode)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        personageDetailRecyclerView.adapter = episodeAdapter

        personageDetailsViewModel.personageLiveData.observe(viewLifecycleOwner) {
            loadingImage(it.personageImage, personageImage)
            personageName.text = it.personageName
            personageSpecies.text = it.personageSpecies
            personageStatus.text = it.personageStatus
            personageGender.text = it.personageGender
            personageOrigin.text = it.personageOrigin.name
            personageLocation.text = it.personageLocation.locationName

            //not work for all!!!
            if(it.personageOrigin.url != "" || it.personageLocation.locationUrl != ""){
                originId = getMyId(it.personageOrigin.url)
                locationId = getMyId(it.personageLocation.locationUrl)
            }


        }
        toolbar.setNavigationOnClickListener {
            goPersonageBack.onGoPersonageBack()
        }
        personageOrigin.setOnClickListener {
            goLocation.onGoLocation(originId)
        }
        personageLocation.setOnClickListener {
            goLocation.onGoLocation(locationId)
        }




        personageDetailsViewModel.episodeLiveData.observe(viewLifecycleOwner){
            episodeAdapter.episodeList = it
        }



        val decorator = DividerItemDecoration(context, GridLayoutManager.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.separator, null))
        personageDetailRecyclerView.addItemDecoration(decorator)
    }

    private fun loadingImage(string: String, imageView: ImageView) {
        Picasso.get().load(string).into(
                imageView, object : Callback {
            override fun onSuccess() {
                Log.d("OK", "OK")
            }

            override fun onError(e: Exception?) {
                Log.d("Error", "Error")
            }
        })
    }

    private fun getMyId(url : String) : Int =
            (url.substring(url.lastIndexOf("/")+1)).toInt()

    interface GoPersonageBack {
        fun onGoPersonageBack()
    }

    interface GoLocation {
        fun onGoLocation (myId : Int)
    }


}