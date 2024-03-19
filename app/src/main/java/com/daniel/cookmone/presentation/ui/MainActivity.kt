package com.daniel.cookmone.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daniel.cookmone.presentation.utils.MAIN
import com.daniel.cookmone.presentation.ui.recipe.RecipeFragment
import com.daniel.cookmone.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MAIN = this

        supportFragmentManager.beginTransaction()
            .add(R.id.container, RecipeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}