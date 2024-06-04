package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Select") }
    var outputUnit by remember { mutableStateOf("Select") }
    var iconverterRate by remember { mutableDoubleStateOf(1.00) }
    var oconverterRate by remember { mutableDoubleStateOf(1.00) }
    var dropDownState1 by remember { mutableStateOf(false) }
    var dropDownState2 by remember { mutableStateOf(false) }
    fun convertunits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * iconverterRate * 100.0 / oconverterRate).roundToInt() / 100.0
        outputValue = result.toString()
    }
    0
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //ui 요소들을 하나씩 추가할 부분
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text(text = "value를 입력하세요") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { dropDownState1 = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = dropDownState1,
                    onDismissRequest = { dropDownState1 = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        dropDownState1 = false;
                        inputUnit = "Centimeters";
                        iconverterRate = 0.01
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        dropDownState1 = false;

                        inputUnit = "Centimeters";
                        iconverterRate = 1.0
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        dropDownState1 = false;

                        inputUnit = "Centimeters";
                        iconverterRate = 0.3048
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        dropDownState1 = false;

                        inputUnit = "Centimeters";
                        iconverterRate = 0.001
                        convertunits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { dropDownState2 = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = dropDownState2,
                    onDismissRequest = { dropDownState2 = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        dropDownState2 = false;

                        outputUnit = "Centimeters";
                        oconverterRate = 0.01
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        dropDownState2 = false;

                        outputUnit = "Meters";
                        oconverterRate = 1.0
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        dropDownState2 = false;

                        outputUnit = "Feet";

                        oconverterRate = 0.3048
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        dropDownState2 = false;
                        outputUnit = "Milimeters";
                        oconverterRate = 0.001
                        convertunits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:${outputValue}")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}
