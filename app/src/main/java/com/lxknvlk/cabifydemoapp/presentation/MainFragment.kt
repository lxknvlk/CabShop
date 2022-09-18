package com.lxknvlk.cabifydemoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.lxknvlk.cabifydemoapp.R
import com.lxknvlk.cabifydemoapp.domain.ProductEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var btnCheckout: Button
    private lateinit var piLoader: LinearProgressIndicator

    private var productAdapter: ProductAdapter? = null

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
        piLoader = view.findViewById(R.id.piLoader)
        recyclerview.layoutManager = LinearLayoutManager(context)

        btnCheckout = view.findViewById(R.id.btnCheckout)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnCheckout.setOnClickListener {
            //take amounts and codes from list
            //and show dialogue with receipt
        }

        viewModel.productsLiveData.observe(viewLifecycleOwner) { products: List<ProductEntity>? ->
            if (products == null) {
                Toast.makeText(context, "Error getting products", Toast.LENGTH_SHORT).show()
            } else {
                productAdapter = ProductAdapter(products)
                recyclerview.adapter = productAdapter
                piLoader.visibility = View.INVISIBLE
                btnCheckout.visibility = View.VISIBLE
            }
        }

        viewModel.getProducts()
    }

}