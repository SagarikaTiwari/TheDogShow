package com.sagarika.features.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.sagarika.features.R
import com.sagarika.features.presentation.navigation.NavGraph
import com.sagarika.features.presentation.theme.MyApplication
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication {
                Surface(color = MaterialTheme.colors.background) {
                 val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
