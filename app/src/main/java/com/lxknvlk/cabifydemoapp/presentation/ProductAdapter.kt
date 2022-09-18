package com.lxknvlk.cabifydemoapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lxknvlk.cabifydemoapp.R
import com.lxknvlk.cabifydemoapp.domain.ProductEntity

class ProductAdapter(
    private val productList: MutableList<ProductEntity>,
    private val addProductCallback: (ProductEntity) -> Unit,
    private val removeProductCallback: (ProductEntity) -> Unit,
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAmount: TextView
        val tvName: TextView
        val tvPrice: TextView
        val btnPlus: Button
        val btnMinus: Button

        init {
            tvAmount = view.findViewById(R.id.tvAmount)
            tvName = view.findViewById(R.id.tvName)
            tvPrice = view.findViewById(R.id.tvPrice)
            btnPlus = view.findViewById(R.id.btnPlus)
            btnMinus = view.findViewById(R.id.btnMinus)
        }
    }

    fun addItems(newProducts: List<ProductEntity>){
        productList.clear()
        productList.addAll(newProducts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product =  productList[position]
        viewHolder.tvName.text = product.name
        viewHolder.tvPrice.text = "€${product.price}"


        viewHolder.btnPlus.setOnClickListener {
            val amount = viewHolder.tvAmount.text.toString().toInt() + 1
            viewHolder.tvAmount.text = amount.toString()
            addProductCallback(product)
        }

        viewHolder.btnMinus.setOnClickListener {
            var amount = viewHolder.tvAmount.text.toString().toInt() - 1
            if (amount < 0) amount = 0
            viewHolder.tvAmount.text = amount.toString()
            removeProductCallback(product)
        }
    }

    override fun getItemCount() = productList.size
}