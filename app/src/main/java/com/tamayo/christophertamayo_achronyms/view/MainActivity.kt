package com.tamayo.christophertamayo_achronyms.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tamayo.christophertamayo_achronyms.R
import com.tamayo.christophertamayo_achronyms.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //Navigation
        val navHost =
            supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment

        binding.bottomNavigationView.setupWithNavController(navHost.navController)

    }


}