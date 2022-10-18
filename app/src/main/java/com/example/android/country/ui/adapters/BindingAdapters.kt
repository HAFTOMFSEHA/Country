package com.example.android.country.ui.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.country.data.Country

@BindingAdapter("listData")
fun bindRecyclerview(recyclerView: RecyclerView, data: List<Country>?){
    val adapter = recyclerView.adapter as CountryListAdapter
    adapter.submitList(data)
}