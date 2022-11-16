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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*

//metodo main
class MainActivity : ComponentActivity() {
    //função de criação
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //define o layout da atividade em que as funções de composição são chamadas
        //definindo o conteúdo da tela
        setContent {
            //chamando a função que desenhará as conversas no container do app
            JetpackComposeTheme() {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}


//declarando a classe mensagem
data class Message(val author: String, val body: String)

//primeiro composable que irá rodar
@Composable
//função que desenha as mensagens com as mensagens como parametro
fun MessageCard(msg: Message) {
    //declarando a linha de cada conversa e o design
    Row(modifier = Modifier.padding(all = 8.dp)) {
        //puxando a img e formatando ela
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        //adicionando espaço entre a imagem e a coluna
        Spacer(modifier = Modifier.width(8.dp))

        // definindo se a mensagem será expansivel(para textos grandes)
        // variavel
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor irá atualizar as cores caso seja expansivel
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        // declarando coluna. ao clicar na coluna, define se é expansivel
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            //mostrando o autor da mensagem e formatando o campo
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            // adicionando espaço entre o autor e o texto
            Spacer(modifier = Modifier.height(4.dp))

            //declarando o container surface para formatar as mensagens
            Surface(
                //declarando a elevação da sombra
                shadowElevation = 1.dp,
                // surfaceColor irá mudar a cor gradualmente de primária para surface
                color = surfaceColor,
                // animateContentSize mudará o tamanho do surface gradualmente
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                //formatando o texto que irá mostrar a mensagem
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // Se a mensagem for expandida, exibimos todo o seu conteúdo
                    // caso contrário, exibimos apenas a primeira linha
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

//criando método que irá receber as mensagens e mandá-las para o método MessageCard(que irá formatar)
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

//método que recebe as mensagens da arquivo SampleData e trás para o MainActivity, mostrando no Preview
@Preview
@Composable
fun PreviewConversation() {
    JetpackComposeTheme() {
        Conversation(SampleData.conversationSample)
    }
}