package com.example.demo.ui.location.list

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
import com.example.demo.R
import com.example.demo.databinding.FragmentLocationListBinding

class LocationListFragment : Fragment(R.layout.fragment_location_list) {

    private val locationListViewModel by viewModels<LocationListViewModel>()
    private lateinit var binding: FragmentLocationListBinding
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var itemLocationSelected: ItemLocationSelected

    companion object {
        fun newInstance() = LocationListFragment()
        const val LOCATION_LIST_TAG = "LOCATION_LIST_TAG"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemLocationSelected = context as ItemLocationSelected
        locationAdapter = LocationAdapter { i ->
            itemLocationSelected.onItemLocationSelected(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null){
            locationAdapter.locationList
            locationListViewModel.getLocation()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        locationRecyclerView.adapter = locationAdapter
        locationListViewModel.locationLiveData.observe(viewLifecycleOwner) {
            locationAdapter.locationList = it
        }
        locationListViewModel.loadingLiveData.observe(viewLifecycleOwner){
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        val decorator = DividerItemDecoration(context, GridLayoutManager.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.separator, null))
        locationRecyclerView.addItemDecoration(decorator)
    }

    interface ItemLocationSelected {
        fun onItemLocationSelected(myId: Int)
    }
}