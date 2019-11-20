package com.uhc.presentation

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.uhc.presentation.databinding.MainActivityBinding
import com.uhc.presentation.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Constancio on 2019-11-16.
 */
class MainActivity : BaseActivity<MainActivityBinding>() {
    val viewModel: MainViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    fun setupTollbar(toolbar: Toolbar, enableBackButton: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}