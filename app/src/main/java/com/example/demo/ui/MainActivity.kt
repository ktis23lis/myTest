package com.example.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Personage
import com.example.demo.ui.episode.dateil.EpisodeDetailFragment
import com.example.demo.ui.episode.list.EpisodeListFragment
import com.example.demo.ui.location.details.LocationDetailsFragment
import com.example.demo.ui.location.list.LocationListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),
        PersonageListFragment.ItemPersonageSelected,
        PersonageDetailsFragment.GoPersonageBack,
        PersonageDetailsFragment.GoLocation,
        LocationListFragment.ItemLocationSelected,
        LocationDetailsFragment.GoLocationBack,
        EpisodeListFragment.ItemEpisodeSelected,
        EpisodeDetailFragment.GoEpisodeBack {
    private lateinit var bottomMenu: BottomNavigationView

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomMenu = findViewById(R.id.bottomMenu)

        if (savedInstanceState == null) {
            initPersonageFragment()
        }

        bottomMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomMenuPersonage -> {
                    initPersonageFragment()
                    Toast.makeText(this, " personage", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottomMenuLocation -> {
                    initLocationFragment()
                    Toast.makeText(this, " location", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottomMenuEpisode -> {
                    initEpisodeFragment()
                    Toast.makeText(this, " episode", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    Toast.makeText(this, " hello", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    private fun initPersonageFragment() {
        supportFragmentManager.beginTransaction().run {
            val personageListFragment = PersonageListFragment.newInstance()
            replace(R.id.container, personageListFragment)
            commit()
        }
    }

    private fun initLocationFragment() {
        supportFragmentManager.beginTransaction().run {
            val locationListFragment = LocationListFragment.newInstance()
            replace(R.id.container, locationListFragment)
            commit()
        }
    }

    private fun initEpisodeFragment() {
        supportFragmentManager.beginTransaction().run {
            val episodeListFragment = EpisodeListFragment.newInstance()
            replace(R.id.container, episodeListFragment)
            commit()
        }
    }

    override fun onItemPersonageSelected(myId: Int) {
        initPersonageDetails(myId)
    }

    override fun onItemLocationSelected(myId: Int) {
        initLocationDetails(myId)
    }

    override fun onItemEpisodeSelected(myId: Int) {
        supportFragmentManager.beginTransaction().run {
            val episodeDetailsFragment = EpisodeDetailFragment.newInstance(myId)
            replace(R.id.container, episodeDetailsFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onGoPersonageBack() {
        onBackPressed()
    }

    override fun onGoLocationBack() {
        onBackPressed()
    }

    override fun onGoEpisodeBack() {
        onBackPressed()
    }

    override fun onGoLocation(myId: Int) {
        initLocationDetails(myId)
    }

    private fun initLocationDetails(myId: Int) {
        supportFragmentManager.beginTransaction().run {
            val locationDetailsFragment = LocationDetailsFragment.newInstance(myId)
            replace(R.id.container, locationDetailsFragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun initPersonageDetails(myId: Int) {
        supportFragmentManager.beginTransaction().run {
            val personageDetailsFragment = PersonageDetailsFragment.newInstance(myId)
            replace(R.id.container, personageDetailsFragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun initEpisodeDetails(myId: Int) {
        supportFragmentManager.beginTransaction().run {
            val episodeDetailsFragment = EpisodeDetailFragment.newInstance(myId)
            replace(R.id.container, episodeDetailsFragment)
            addToBackStack(null)
            commit()
        }
    }
}