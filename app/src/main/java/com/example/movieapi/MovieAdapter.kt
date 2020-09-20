package com.example.movieapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter (val context : Context, val results:List<Results>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var movieposterimage = itemView.findViewById<ImageView>(R.id.movieposterimage)
        var movietitle=itemView.findViewById<TextView>(R.id.movietitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.moive_layout,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Base_URL = "https://image.tmdb.org/t/p/original/"
        val results = results[position]
        holder.movietitle.text = results.title
        Glide.with(context).load(Base_URL+results.poster_path).into(holder.movieposterimage)
    }

    override fun getItemCount(): Int {
       return results.size
    }


}
