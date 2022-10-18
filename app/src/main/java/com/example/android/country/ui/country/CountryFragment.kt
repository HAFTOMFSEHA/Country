package com.example.android.country.ui.country

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android.country.databinding.FragmentCountryBinding
import com.example.android.country.ui.adapters.CountryListAdapter

/**
 * This fragment shows the the status of the Country web services transaction.
 */
class CountryFragment : Fragment() {

    /**
     * Lazily initialize our [CountryViewModel].
     */
    private val viewModel: CountryViewModel by lazy {
        ViewModelProvider(this).get(CountryViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the CountryFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentCountryBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the CountryViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.countryList.adapter = CountryListAdapter()
        binding.countryList.addItemDecoration( DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        return binding.root
    }
}
