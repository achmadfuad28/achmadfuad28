package com.android.achmadfuadagustian.presentation

import android.os.Bundle
import com.android.achmadfuadagustian.R
import com.android.achmadfuadagustian.databinding.ActivitySplashScreenBinding
import com.android.achmadfuadagustian.presentation.view.SplashScreenView
import com.android.achmadfuadagustian.presentation.viewmodel.SplashScreenViewModel
import com.framework.base.BaseActivity
import com.framework.owner.ViewDataBindingOwner
import com.framework.owner.ViewModelOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : BaseActivity(),
    SplashScreenView,
    ViewModelOwner<SplashScreenViewModel>,
    ViewDataBindingOwner<ActivitySplashScreenBinding> {

    override lateinit var binding: ActivitySplashScreenBinding

    companion object {
        private const val SPLASH_SCREEN_DELAY = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            delay(SPLASH_SCREEN_DELAY)
            if (!isFinishing) {
                navigateTo()
            }
        }
    }

    override val viewModel: SplashScreenViewModel by viewModel()

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_splash_screen
    }

    private fun navigateTo() {
        MainActivity.startThisActivity(this@SplashScreenActivity)
        finish()
    }

}
