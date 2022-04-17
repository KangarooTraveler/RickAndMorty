package com.example.rickandmorty

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var mainFragment = MainFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.tvPersonDetailedName.text = arguments?.getString("person_name").toString()
        binding.tvSpeciesDetailed.text = arguments?.getString("species").toString()
        binding.tvGenderDetailed.text = arguments?.getString("gender").toString()
        binding.tvStatusDetailed.text = arguments?.getString("status").toString()
        binding.tvLocationDetailed.text = arguments?.getString("location_name").toString()
        binding.tvEpisodesDetailed.text =
            countWords(arguments?.getString("episode").toString()).toString()
        binding.btnBack.setOnClickListener {
            (context as MainActivity)
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, mainFragment)
                .addToBackStack(null).commit()
        }
        Glide.with(requireContext())
            .load(arguments?.getString("image"))
            .into(binding.personImgDetailed)
    }

    private fun countWords(inputString: String): Int {
        val strArray = inputString.split(" ".toRegex()).toTypedArray()
        var count = 0
        for (s in strArray) if (s != "") count++
        return count
    }

}