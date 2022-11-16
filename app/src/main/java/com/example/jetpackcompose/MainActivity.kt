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
import android.content.res.Configuration

//metodo main
class MainActivity : ComponentActivity() {
    //função de criação
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //define o layout da atividade em que as funções de composição são chamadas
        setContent {
            JetpackComposeTheme() {
                    Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }
        }
    }
}

//declarando classe mensagem para passagem do texto
data class Message(val author: String, val body: String)

//função que exibirá e formatará a mensagem
@Composable
fun MessageCard(msg: Message) {
    //declarando a linha com formatação
    Row(modifier = Modifier.padding(all = 8.dp)) {
        //formatando e adicionando a imagem
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        //adicionando um espaço entre as colunas
        Spacer(modifier = Modifier.width(8.dp))

        //adiconando a coluna onde conterá a mensagem e o autor
        Column {
            //mostrando e formatando o autor
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            //adicionando um espaço entre o autor e a mensagem
            Spacer(modifier = Modifier.height(4.dp))

            //adicionando elevação de sombra ao texto da mensagem
            Surface(shadowElevation = 1.dp) {
                //adicionando e formatando a mensagem
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


//adicionando os dois previews
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

//passando a mensagem e o autor da mensagem
@Composable
fun PreviewMessageCard() {
    JetpackComposeTheme() {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }
}