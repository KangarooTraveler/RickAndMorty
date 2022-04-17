package com.example.rickandmorty

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.databinding.FragmentMainBinding
import com.example.rickandmorty.model.CharacterModel
import com.example.rickandmorty.presenter.CharacterPresenter
import com.example.rickandmorty.view.CharacterView
import retrofit2.Response

class MainFragment : Fragment(), CharacterView {

    private lateinit var binding: FragmentMainBinding
    private var characterPresenter = CharacterPresenter(this)
    private val myAdapter by lazy { RecyclerViewAdapter(context as MainActivity) }
    var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Проверка на подключение пользователя к интернету
        if (isNetworkConnected(context as MainActivity)) initView()
        else Toast.makeText(requireContext(), "Check your internet connection", Toast.LENGTH_SHORT)
            .show()
        return binding.root
    }


    private fun initView() {
        characterPresenter.getCharacterFromApi(page)
        showLoading()
        binding.previousButton.isVisible = page > 1
        binding.nextButton.setOnClickListener {
            page++
            showLoading()
            characterPresenter.getCharacterFromApi(page)
            binding.previousButton.isVisible = page > 1
        }
        binding.previousButton.setOnClickListener {
            page--
            showLoading()
            characterPresenter.getCharacterFromApi(page)
            binding.previousButton.isVisible = page > 1
        }
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun dataFromApi(model: Response<CharacterModel>) {
        if (model.isSuccessful) {
            hideLoading()
            binding.nextButton.isVisible = true
            myAdapter.setData(model.body()!!.results)
        } else showLoading()
    }

    override fun errorFromApi(throwable: Throwable) {}

    @SuppressLint("MissingPermission")
    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

}