package com.serhiiv.openweather.choosecity

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.serhiiv.openweather.choosecity.recycler.CityAdapter
import com.serhiiv.openweather.core.android.base.BaseFragment
import com.serhiiv.openweather.core.android.extention.isGone
import com.serhiiv.openweather.core.model.SelectableCity
import kotlinx.android.synthetic.main.fragment_choose_city.*
import javax.inject.Inject

class ChooseCityFragment : BaseFragment() {
    @Inject
    lateinit var factory: ChooseCityViewModel.Factory

    private val viewModel: ChooseCityViewModel by viewModels { factory }

    private val cityAdapter = CityAdapter {
        viewModel.selectCity(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler.run {
            layoutManager = LinearLayoutManager(this@ChooseCityFragment.context)
            setHasFixedSize(true)
            adapter = cityAdapter
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                ChooseCityViewModel.ChooseCityState.Loading -> showLoading()
                is ChooseCityViewModel.ChooseCityState.Success -> showCities(it.cities)
                is ChooseCityViewModel.ChooseCityState.Error -> showError(it.error)
            }
        })
    }

    private fun showLoading() {
        progress_bar.show()
        recycler isGone true
    }


    private fun showCities(cities: List<SelectableCity>) {
        progress_bar.hide()
        recycler isGone false

        cityAdapter.submitList(cities)
        recycler.scrollToPosition(0)
    }

    private fun showError(error: Exception) {
        progress_bar.hide()
        error.message?.also {
            Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.chose_city, menu)

        val searchView = menu.findItem(R.id.menu_item_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchQuery(newText)
                return true
            }

        })
    }
}
