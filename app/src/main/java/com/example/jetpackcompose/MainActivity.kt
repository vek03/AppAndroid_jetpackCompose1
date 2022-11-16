package com.example.jetpackcompose

//imports necessários para usar o framework
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//metodo main
class MainActivity : ComponentActivity() {
    //função de criação
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //define o layout da atividade em que as funções de composição são chamadas
        setContent {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
    }
}

//declarando a classe mensagem para passar como parametro
data class Message(val author: String, val body: String)

//função que irá formatar e adicionar as mensagens passadas
@Composable
fun MessageCard(msg: Message) {
    // formatando padding e etc da mensagem na linha
    Row(modifier = Modifier.padding(all = 8.dp)) {
        //formatando a imagem
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )

        // adicionando espaço entre a coluna e o resto
        Spacer(modifier = Modifier.width(8.dp))

        //adicionando coluna com o texto da mensagem
        Column {
            // adicionando o autor da mensagem passada
            Text(text = msg.author)
            // adicionando um espaço vertical entre o autor e a mensagem
            Spacer(modifier = Modifier.height(4.dp))
            //adicionando a mensagem passada
            Text(text = msg.body)
        }
    }
}

//declarando o preview com a mensagem a seguir
@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
    )
}