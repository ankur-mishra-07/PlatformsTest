package com.platformstest.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.platformstest.common.BaseViewModel
import com.platformstest.di.modules.Injectable
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * Base Fragment to be extended by all fragment
 */

open class BaseFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: BaseViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        activity?.let {
            viewModel = ViewModelProviders.of(it, viewModelFactory)
                .get(BaseViewModel::class.java)
        }
        super.onAttach(context)
    }

}