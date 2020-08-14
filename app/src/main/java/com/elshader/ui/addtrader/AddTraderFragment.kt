package com.elshader.ui.addtrader

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.elshader.R
import com.elshader.data.responses.RegionResponses
import com.elshader.databinding.FragmentAddTraderBinding
import com.elshader.utils.Loading
import com.elshader.utils.Resource
import com.elshader.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class AddTraderFragment : Fragment() {
    private lateinit var fragmentAddTradersBinding: FragmentAddTraderBinding
    private val addTradersViewModel: AddTraderViewModel by viewModels()
    lateinit var root:View
    private var codeStandardJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + codeStandardJob)
    lateinit var list:ArrayAdapter<RegionResponses.Data>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddTradersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_trader, container, false)
        fragmentAddTradersBinding.viewmodel=addTradersViewModel
        return fragmentAddTradersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root=view
        subscribeRegionsData()
        getTraders()


    }
    private fun getTraders() {
        codeStandardJob = uiScope.launch {
        addTradersViewModel.getRegions()
        }
    }

    private fun subscribeRegionsData() {
        addTradersViewModel.getRegionData.removeObservers(this)
        addTradersViewModel.getRegionData.observe(viewLifecycleOwner, Observer { resources ->
            run {
                when (resources) {
                    is Resource.Success -> {
                        getRegions(resources.data as  RegionResponses.Data)
                    }
                    is Resource.Error -> {
                        error(resources.exception.message!!)
                    }

                    is Resource.Loading -> {
                        loading()
                    }
                }

            }
        })

    }


    private fun subscribeAddTrader() {
        addTradersViewModel.addTraderData.removeObservers(this)
        addTradersViewModel.addTraderData.observe(viewLifecycleOwner, Observer { resources ->
            run {
                when (resources) {
                    is Resource.Success -> {
//                        successgetTraders()
                    }

                    is Resource.Error -> {
                        error(resources.exception.message!!)
                    }

                    is Resource.Loading -> {
                        loading()
                    }
                }

            }
        })

    }

    private fun getRegions(List:RegionResponses.Data) {
        if(Loading.Showing()){
            Loading.Disable()
        }



    }

    private fun error(message: String) {
        if(Loading.Showing()) {
            Loading.Disable()
        }
        activity?.toast(message)
    }

    private fun loading() {
        if(Loading.Showing()) {
            context?.let { Loading.Show(it) }
        }
    }

}