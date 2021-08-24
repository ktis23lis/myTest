package com.example.demo

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
import com.example.demo.databinding.FragmentPersonageListBinding
import com.example.demo.domain.model.Personage
import com.example.demo.ui.PersonageListViewModel

class PersonageListFragment : Fragment(R.layout.fragment_personage_list) {

    private val personageListViewModel by viewModels<PersonageListViewModel>()
    private lateinit var binding: FragmentPersonageListBinding
    private lateinit var personageAdapter: PersonageAdapter
    private lateinit var itemPersonageSelected: ItemPersonageSelected

    companion object {
        fun newInstance() = PersonageListFragment()
        const val PERSONAGE_LIST_TAG = "PERSONAGE_LIST_TAG"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemPersonageSelected = context as ItemPersonageSelected
        personageAdapter = PersonageAdapter { i ->
            itemPersonageSelected.onItemPersonageSelected(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            personageAdapter.personageList
            personageListViewModel.getPersonage()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        personageRecyclerView.adapter = personageAdapter
        personageListViewModel.personageLiveData.observe(viewLifecycleOwner) {
            personageAdapter.personageList = it
        }
        personageListViewModel.loadingLiveData.observe(viewLifecycleOwner){
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        val decorator = DividerItemDecoration(context, GridLayoutManager.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.separator, null))
        personageRecyclerView.addItemDecoration(decorator)
    }

    interface ItemPersonageSelected {
        fun onItemPersonageSelected(id: Int)
    }
}
