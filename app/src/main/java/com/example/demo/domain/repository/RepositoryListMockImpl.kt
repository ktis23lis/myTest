package com.example.demo.domain

import android.os.Handler
import android.os.Looper
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.SuccessList
import java.util.concurrent.Executor


class RepositoryListMockImpl : RepositoryList {

    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun getPersonageArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Personage>>) -> Unit
    ) {
        executor.execute {
            Thread.sleep(3000)
//            val result = ArrayList<Personage>().apply {
//                add(Personage(R.drawable.test, "A", "a", "a", "a"))
//                add(Personage(R.drawable.test, "q", "q", "q", "q"))
//                add(Personage(R.drawable.test, "w", "w", "w", "w"))
//                add(Personage(R.drawable.test, "e", "e", "e", "e"))
//                add(Personage(R.drawable.test, "r", "r", "r", "r"))
//                add(Personage(R.drawable.test, "t", "t", "t", "t"))
//                add(Personage(R.drawable.test, "y", "y", "y", "y"))
//                add(Personage(R.drawable.test, "u", "u", "u", "u"))
//                add(Personage(R.drawable.test, "i", "i", "i", "i"))
//            }
//            mainThreadHandler.post {
//                callback(Success(result))
//            }
        }
    }

    override fun getLocationArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Location>>) -> Unit
    ) {
        executor.execute {
            Thread.sleep(2000)
//            val result = ArrayList<Location>().apply {
//                add(Location("a", "a", "a"))
//                add(Location("q", "q", "q"))
//                add(Location("w", "w", "w"))
//                add(Location("e", "e", "e"))
//                add(Location("r", "r", "r"))
//                add(Location("t", "t", "t"))
//                add(Location("y", "y", "y"))
//                add(Location("u", "u", "u"))
//                add(Location("i", "i", "i"))
//                add(Location("o", "o", "o"))
//                add(Location("p", "p", "p"))
//            }
//            mainThreadHandler.post {
//                callback(Success(result))
//            }
        }
    }

    override fun getEpisodeArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Episode>>) -> Unit
    ) {
        executor.execute {
            Thread.sleep(2000)
//            val result = ArrayList<Episode>().apply {
//                add(Episode("a", 1, 11 - 11 - 1111))
//                add(Episode("q", 2, 22 - 22 - 2222))
//                add(Episode("w", 3, 33 - 33 - 3333))
//                add(Episode("e", 4, 44 - 44 - 4444))
//                add(Episode("r", 5, 55 - 55 - 5555))
//                add(Episode("t", 6, 66 - 66 - 6666))
//                add(Episode("y", 7, 77 - 77 - 7777))
//                add(Episode("u", 8, 88 - 88 - 8888))
//                add(Episode("i", 9, 99 - 99 - 9999))
//            }
//            mainThreadHandler.post {
//                callback(Success(result))
//            }
        }
    }
}

//data class SuccessList<T>(val value: T)



