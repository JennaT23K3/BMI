package com.example.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi.ui.theme.BMITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMITheme {
            //Surface container
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) { Bmi() }
            }
        }
    }
}

@Composable
fun Bmi() {
    var heightInput: String by remember{mutableStateOf("")}
    var weightInput: String by remember{mutableStateOf("")}
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi = if (weight > 0 && height > 0) weight / (height * height) else 0.0

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
    Text(
        text = "Body mass index",
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp,bottom=16.dp)
    )
    OutlinedTextField(
       value = heightInput,
       onValueChange = {heightInput = it.replace( ',', '.')},
       label = {Text("Height")},
       singleLine = true,
       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
       modifier = Modifier.fillMaxWidth()
    )
   OutlinedTextField(
       value = weightInput,
       onValueChange = {weightInput = it.replace( ',', '.')},
       label = {Text("Weight")},
       singleLine = true,
       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
       modifier = Modifier.fillMaxWidth()
       )
        Text(text = stringResource(R.string.result,String.format("%.2f",bmi).replace(',','.')))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMITheme {
        Bmi()
    }
}