package com.lxknvlk.cabifydemoapp.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.lxknvlk.cabifydemoapp.R
import com.lxknvlk.cabifydemoapp.domain.entity.Product
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

        productAdapter = ProductAdapter(mutableListOf(), this::onItemAdded, this::onItemRemoved)
        recyclerview.adapter = productAdapter

        btnCheckout.setOnClickListener {
            viewModel.checkout()
        }

        viewModel.productsLiveData.observe(viewLifecycleOwner) { products: List<Product>? ->
            piLoader.visibility = View.INVISIBLE

            if (products == null) {
                Toast.makeText(context, "Error getting products", Toast.LENGTH_SHORT).show()
            } else {
                productAdapter?.addItems(products)
                btnCheckout.visibility = View.VISIBLE
            }
        }

        viewModel.receiptLiveData.observe(viewLifecycleOwner) { receipt ->
            //show receipt in popup
            val mAlertDialog = context?.let { AlertDialog.Builder(it) }
            mAlertDialog?.setMessage(receipt)
            mAlertDialog?.setTitle("Your receipt")
            mAlertDialog?.setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            mAlertDialog?.show()
        }

        viewModel.getProducts()
    }



    private fun onItemAdded(product: Product) {
        viewModel.addProduct(product)
    }

    private fun onItemRemoved(product: Product) {
        viewModel.removeProduct(product)
    }
}