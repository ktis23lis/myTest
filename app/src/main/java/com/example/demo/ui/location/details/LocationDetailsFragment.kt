package com.example.demo.ui.location.details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demo.PersonageAdapter
import com.example.demo.PersonageListFragment
import com.example.demo.R
import com.example.demo.databinding.FragmentLocationDetailsBinding
import com.example.demo.ui.location.list.LocationListFragment

class LocationDetailsFragment : Fragment(R.layout.fragment_location_details) {

    private val locationDetailsViewModel by viewModels<LocationDetailsViewModel>()
    private lateinit var goLocationBack: GoLocationBack
    private lateinit var personageAdapter : PersonageAdapter
    private lateinit var itemPersonageSelected: PersonageListFragment.ItemPersonageSelected
    private lateinit var binding: FragmentLocationDetailsBinding
    private var myId = 0

    companion object {

        fun newInstance(id : Int )= LocationDetailsFragment().apply {
            val bundle = Bundle()
            bundle.putInt(LocationListFragment.LOCATION_LIST_TAG, id)
            arguments = bundle
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        goLocationBack = context as GoLocationBack
        itemPersonageSelected = context as PersonageListFragment.ItemPersonageSelected

        personageAdapter = PersonageAdapter { i ->
            itemPersonageSelected.onItemPersonageSelected(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            myId = requireArguments().getInt(LocationListFragment.LOCATION_LIST_TAG)
            locationDetailsViewModel.getLocation(myId)
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View{
        binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        locationDetailRecyclerView.adapter = personageAdapter

        locationDetailsViewModel.locationLiveData.observe(viewLifecycleOwner) {
            locationName.text = it.locationName
            locationType.text = it.locationType
            locationDimension.text = it.locationDimension
        }

        toolbar.setNavigationOnClickListener {
            goLocationBack.onGoLocationBack()
        }


        locationDetailsViewModel.personageLiveData.observe(viewLifecycleOwner){
            personageAdapter.personageList = it
        }

        val decorator = DividerItemDecoration(context, GridLayoutManager.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.separator, null))
        locationDetailRecyclerView.addItemDecoration(decorator)
    }

    interface GoLocationBack {
        fun onGoLocationBack()
    }
}