package br.com.alexf.thirtyDaysOfCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import br.com.alexf.thirtyDaysOfCompose.model.Author
import br.com.alexf.thirtyDaysOfCompose.model.Message
import br.com.alexf.thirtyDaysOfCompose.ui.theme.ThirtyDaysOfComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                Conversation(
                    listOf(
                        Message(
                            author = Author(
                                name = "Alex"
                            ),
                            body = LoremIpsum(20).values.first()
                        ),
                        Message(
                            author = Author(
                                name = "Alex"
                            ),
                            body = LoremIpsum(30).values.first()
                        ),
                    )
                )
            }
        }
    }

}

@Composable
private fun App(content: @Composable () -> Unit = {}) {
    ThirtyDaysOfComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    val author = message.author
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(
                id = author.avatar
            ),
            contentDescription = "Avatar photo",
            Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(16.dp)
                .clip(CircleShape)
        )
        Column {
            Text(
                text = author.name,
                modifier = Modifier.padding(top = 8.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, end = 8.dp)
                    .animateContentSize()
                    .animateContentSize()
                    .padding(1.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    },
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
            ) {
                Text(
                    text = message.body,
                    Modifier
                        .padding(8.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    MessageCard(
        Message(
            author = Author(
                name = "Alex"
            ),
            body = LoremIpsum(20).values.first()
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun ConversationPreview() {
    Conversation(
        listOf(
            Message(
                author = Author(
                    name = "Alex"
                ),
                body = LoremIpsum(20).values.first()
            ),
            Message(
                author = Author(
                    name = "Felipe"
                ),
                body = LoremIpsum(20).values.first()
            ),
        )
    )
}
