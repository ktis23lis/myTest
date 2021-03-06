package com.example.demo.ui.episode.dateil

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.demo.PersonageDetailsFragment
import com.example.demo.R
import com.example.demo.databinding.FragmentEpisodeDetailsBinding
import com.example.demo.domain.Episode
import com.example.demo.ui.episode.list.EpisodeListFragment
import kotlinx.android.synthetic.main.fragment_episode_details.*

class EpisodeDetailFragment : Fragment(R.layout.fragment_episode_details) {

    private val episodeDetailsViewModel by viewModels<EpisodeDetailsViewModel>()
    private lateinit var goEpisodeBack: GoEpisodeBack
    private lateinit var binding: FragmentEpisodeDetailsBinding
    private var myId = 0

    companion object {
        fun newInstance(id : Int) = EpisodeDetailFragment().apply {
            val bundle = Bundle()
            bundle.putInt(EpisodeListFragment.EPISODE_LIST_TAG, id)
            arguments = bundle
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        goEpisodeBack = context as GoEpisodeBack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            myId = requireArguments().getInt(EpisodeListFragment.EPISODE_LIST_TAG)
            episodeDetailsViewModel.getPersonage(myId)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodeDetailsViewModel.episodeLiveData.observe(viewLifecycleOwner) {
            episodeName.text = it.episodeName
            episodeNumber.text = it.episode
            episodeAirFate.text = it.episodeAirFate
        }
        toolbar.setNavigationOnClickListener {
            goEpisodeBack.onGoEpisodeBack()
        }
    }

    interface GoEpisodeBack {
        fun onGoEpisodeBack()
    }
}