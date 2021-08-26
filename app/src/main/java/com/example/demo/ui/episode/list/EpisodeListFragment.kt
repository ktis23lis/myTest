package com.example.demo.ui.episode.list

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
import com.example.demo.databinding.FragmentEpisodeListBinding
import com.example.demo.domain.model.Episode

class EpisodeListFragment : Fragment(R.layout.fragment_episode_list) {

    private val episodeViewModel by viewModels<EpisodeListViewModel>()
    private lateinit var itemEpisodeSelected: ItemEpisodeSelected
    private lateinit var binding: FragmentEpisodeListBinding
    private lateinit var episodeAdapter : EpisodeAdapter

    companion object{
        fun newInstance() = EpisodeListFragment()
        const val EPISODE_LIST_TAG = "EPISODE_LIST_TAG"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemEpisodeSelected = context as ItemEpisodeSelected
        episodeAdapter = EpisodeAdapter{ pos ->
            itemEpisodeSelected.onItemEpisodeSelected(pos)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null){
            episodeAdapter.episodeList
            episodeViewModel.getEpisode()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        super.onViewCreated(view, savedInstanceState)
        episodeRecyclerView.adapter = episodeAdapter
        episodeViewModel.episodeLiveData.observe(viewLifecycleOwner){
            episodeAdapter.episodeList = it
        }
        episodeViewModel.loadingLiveData.observe(viewLifecycleOwner){
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        val decorator = DividerItemDecoration(context, GridLayoutManager.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.separator, null))
        episodeRecyclerView.addItemDecoration(decorator)
    }

    interface ItemEpisodeSelected {
        fun onItemEpisodeSelected(id : Int)
    }
}