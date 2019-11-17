package com.uhc.presentation

import androidx.appcompat.widget.Toolbar
import com.uhc.presentation.databinding.MainActivityBinding
import com.uhc.presentation.ui.base.BaseActivity

/**
 * Created by Constancio on 2019-11-16.
 */
class MainActivity : BaseActivity<MainActivityBinding>() {
    override fun getLayoutRes(): Int = R.layout.main_activity

    fun setupTollbar(toolbar: Toolbar, enableBackButton: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}