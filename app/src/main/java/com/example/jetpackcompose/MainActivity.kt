package com.example.jetpackcompose

//imports necessários para usar o framework
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

//metodo main
class MainActivity : ComponentActivity() {
    //função de criação
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //define o layout da atividade em que as funções de composição são chamadas
        setContent {
            // função de composição
            Text("Hello world")
        }
    }
}

//anotação que define uma função composta(faz mais de uma açãp)
@Composable
//função que irá colocar o texto na tela
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}

//anotação que  permite visualizar as funções de composição no Android Studio sem precisar criar e instalar o app em um emulador ou dispositivo Android
@Preview
@Composable
//função que irá permitir enxergar uma previsualização da função MessageCard
fun PreviewMessageCard() {
    MessageCard("Android")
}