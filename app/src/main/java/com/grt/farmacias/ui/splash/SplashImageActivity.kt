package com.grt.farmacias.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.grt.farmacias.common.BaseActivity
import com.grt.farmacias.databinding.ActivitySplashImageBinding
import com.grt.farmacias.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created por Gema Rosas Trujillo
 * 28/01/2022
 *
 * Activity creado para cargar inicialmente la app
 */
class SplashImageActivity : BaseActivity<ActivitySplashImageBinding>(ActivitySplashImageBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashImageActivity, MainActivity::class.java))
            finish()
        }
    }
}