package com.platformstest.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.platformstest.R
import com.platformstest.common.ConnectivityUtil
import com.platformstest.data.viewmodel.FetcherViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

public class BaseActivity : Base() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_base)
        callFragmentSwitcher(FirstFragment(), container = R.id.container)
    }
}