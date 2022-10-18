package com.example.android.country.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.country.databinding.CardViewItemBinding
import com.example.android.country.data.Country


/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */

class CountryListAdapter : ListAdapter<Country, CountryListAdapter.CountryViewHolder>(DiffCallback){

    /**
     * The CountryViewHolder constructor takes the binding variable from the associated
     * CardViewItem, which nicely gives it access to the full [Country] information.
     */
    class CountryViewHolder(private var binding: CardViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(country: Country){
            binding.country = country
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Country]
     * has been updated.
     */
    companion object DiffCallback :  DiffUtil.ItemCallback<Country>(){
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.capital == oldItem.capital
        }

    }


    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
       return CountryViewHolder(CardViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       val country = getItem(position)
       holder.bind(country)
    }

}