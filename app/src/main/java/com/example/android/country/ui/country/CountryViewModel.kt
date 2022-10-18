package com.example.android.country.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.country.data.Country
import com.example.android.country.network.CountryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * The [ViewModel] that is attached to the [CountryFragment].
 */

class CountryViewModel : ViewModel() {

    // Internally, we use a MutableLiveData, because we will be updating the List of Country
    // with new values
    private val _countries = MutableLiveData<List<Country>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val countries : LiveData<List<Country>>
    get() = _countries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var viewModelJob = Job()
    private var  coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call  getCountries() on init so we can display status immediately.
     */
    init {
        getCountries()
    }

    /**
     * Gets Country information from the API Retrofit service and
     * updates the [Country] [List] and [_isLoading] [LiveData]. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     */
    private fun getCountries() {
        _isLoading.value = true
        coroutineScope.launch {
            val getCountriesDeferred = CountryApi.retrofitService.getCountries()
            try {
                val listResult = getCountriesDeferred.await()
                _countries.value = listResult
            }catch (e: Exception){
                _countries.value = ArrayList()
            }
            _isLoading.value = false
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
