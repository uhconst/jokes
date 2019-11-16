package com.uhc.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.uhc.presentation.MainActivity
import com.uhc.presentation.R
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Constancio on 2019-05-04.
 */
abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B

    protected val compositeDisposable = CompositeDisposable()

    protected val mainActivity: MainActivity get() = (activity as MainActivity)

    protected val navController: NavController? get() = activity?.findNavController(R.id.navHostMain)

    abstract fun getLayoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return binding.root
    }

    protected fun setupToobar(toolbar: Toolbar, enableBackButton: Boolean = true) {
        mainActivity.setupTollbar(toolbar, enableBackButton)
    }

    protected fun onBackPressed() {
        mainActivity.onBackPressed()
    }
}