package com.example.rickandmorty

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.model.Results

class RecyclerViewAdapter(private var context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var myList = emptyList<Results>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val secondFragment = DetailsFragment()
        val args = Bundle()
        val tvPersonName = itemView.findViewById<TextView>(R.id.tvPersonName)!!
        val tvGender = itemView.findViewById<TextView>(R.id.tvGender)!!
        val tvSpecies = itemView.findViewById<TextView>(R.id.tvSpecies)!!
        val imageView = itemView.findViewById<ImageView>(R.id.personImg)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvPersonName.text = myList[position].name
        holder.tvGender.text = myList[position].gender
        holder.tvSpecies.text = myList[position].species
        Glide.with(context)
            .load(myList[position].image)
            .into(holder.imageView)
        holder.itemView.setOnClickListener {
            holder.args.putString("location_name", myList[position].location.locationName)
            holder.args.putString("episode", myList[position].episode.toString())
            holder.args.putString("person_name", myList[position].name)
            holder.args.putString("species", myList[position].species)
            holder.args.putString("gender", myList[position].gender)
            holder.args.putString("status", myList[position].status)
            holder.args.putString("image", myList[position].image)
            holder.secondFragment.arguments = holder.args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, holder.secondFragment).addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<Results>) {
        myList = newList
        notifyDataSetChanged()
    }

}