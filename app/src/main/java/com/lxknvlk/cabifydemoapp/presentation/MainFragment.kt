package com.lxknvlk.cabifydemoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxknvlk.cabifydemoapp.R
import com.lxknvlk.cabifydemoapp.domain.ProductEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerview = view.findViewById(R.id.rvProducts)
        recyclerview.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.productsLiveData.observe(viewLifecycleOwner) { products: List<ProductEntity>? ->
            products?.let {
                val adapter = ProductAdapter(it)
                recyclerview.adapter = adapter
            }
        }

        viewModel.getProducts()
    }

}