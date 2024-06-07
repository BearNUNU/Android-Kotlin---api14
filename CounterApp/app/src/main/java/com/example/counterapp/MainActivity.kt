package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheCounterApp()
                }
            }
        }
    }
}


@Composable
fun TheCounterApp() {
    val count = remember { mutableStateOf(0) }

    fun increment() {
        count.value++
    }

    fun decrement() {
        count.value--
    }

    Column(modifier = Modifier.fillMaxSize(), Arrangement.Center , Alignment.CenterHorizontally) {
        Text(text = count.value.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Row {
            IconButton(onClick = { increment() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription ="" )
            }
            IconButton(onClick = { decrement() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription ="" )
            }
        }
    }
}