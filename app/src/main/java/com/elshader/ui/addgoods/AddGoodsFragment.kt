package com.elshader.ui.addgoods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.elshader.R
import com.elshader.databinding.FragmentAddGoodsBinding
import com.elshader.utils.*
import kotlinx.android.synthetic.main.fragment_add_goods.view.*


class AddGoodsFragment : Fragment() {
    private lateinit var fragmentAddGoodsBinding: FragmentAddGoodsBinding
    private val addGoodsViewModel: AddGoodsViewModel by viewModels()
    lateinit var root:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddGoodsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_goods, container, false)
        fragmentAddGoodsBinding.viewmodel=addGoodsViewModel
        return fragmentAddGoodsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root=view
         subscribeAuthData()
    }

    private fun subscribeAuthData() {
        addGoodsViewModel.authData.removeObservers(this)
        addGoodsViewModel.authData.observe(viewLifecycleOwner, Observer { resources ->
            run {
                when (resources) {
                    is Resource.Success -> {
                        success()
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

    private fun success() {
        Loading.Disable()
        root.E_TraderName.text=null
        root.E_BoxesCount.text=null
        activity?.toast(resources.getString(R.string.addgood_succes))

    }

    private fun error(message: String) {
        Loading.Disable()
        activity?.toast(message)
    }

    private fun loading() {
        context?.let { Loading.Show(it) }
    }

}




























//private fun letsMakeAPICallUsingCoroutine() {
//    codeStandardJob = uiScope.launch {
//        val result = requestService.getCodeStandards()
//        when (result) {
//            is Result.Success -> {
//                codeAdapter.updateData(result.data.data)
//            } // Do Something }
//            is Result.Error -> {
//                Log.e("MainActivity", result.exception.toString())
//            } // Log Error
//        }
//    }
//}