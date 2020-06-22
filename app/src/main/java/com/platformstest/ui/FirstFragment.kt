package com.platformstest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.platformstest.R
import com.platformstest.common.*
import com.platformstest.data.adapter.PostAdapter
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.data.viewmodel.FetcherViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment]
 */
class FirstFragment : BaseFragment() {

    private lateinit var mFetcherModel: FetcherViewModel
    private lateinit var mAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemBinding = inflate<ViewDataBinding>(
            layoutInflater,
            R.layout.fragment_first,
            container,
            false
        )
        itemBinding.lifecycleOwner = viewLifecycleOwner
        return itemBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        peopleDataRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        peopleDataRecyclerView.setHasFixedSize(true)
        mAdapter = PostAdapter()
        peopleDataRecyclerView.adapter = mAdapter
        if (ConnectivityUtil.isConnected(requireContext())) {
            mFetcherModel.getServerPost()
        }
        mFetcherModel.getPostLiveData().observe(requireActivity(), Observer { onDataReceived(it) })
        fab.setOnClickListener {
            if (ConnectivityUtil.isConnected(requireContext())) {
                mFetcherModel.getServerPost()
            }
        }
    }

    private fun onDataReceived(viewState: ViewState<FetcherModelItem>?) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
                mAdapter.setPeopleList(viewState.mData.rows)
            }
        }
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        mFetcherModel =
            ViewModelProviders.of(this, viewModelFactory).get(FetcherViewModel::class.java)
    }

}