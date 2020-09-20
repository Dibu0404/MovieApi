package com.example.movieapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MovieAdapter
    private val results = mutableListOf<Results>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MovieAdapter(this@MainActivity, results)
        recyclerview.adapter = adapter

        val LayoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        LayoutManager.setPagerMode(true)
        LayoutManager.setPagerFlingVelocity(3000)
        recyclerview.layoutManager = LayoutManager

        getMovie()
    }

    private fun getMovie() {
        val movies: Call<Movies> = MovieService.newInstances.getTitle("en", 1)
        movies.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val movie: Movies? = response.body()
                if (movie != null) {
                    Log.d("Dibu", movie.toString())
                    results.addAll(movie.results)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("Dibu", "Error in Fetching News", t)
            }
        })

    }
}