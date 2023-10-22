package com.example.businesscardapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAppTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    BusinessCard("name")
                }
            }
        }
    }
}
val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta, Color.Cyan)


@Composable
fun BusinessCard(name: String, modifier: Modifier = Modifier) {
    var isLogoClicked by remember { mutableStateOf(true) }
    var randomColor by remember { mutableStateOf(0) }
    val logoColor = if (isLogoClicked) {
        MaterialTheme.colorScheme.onSurface
    }
    else {
         colors[randomColor]
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scaleValue by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "logo_pulse"
    )
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .scale(scaleValue)
        ){
            Image(
                painterResource(id = R.drawable.logo),
                colorFilter = ColorFilter.tint(logoColor),
                contentDescription = "Cards icon",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        5.dp,
                        logoColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { isLogoClicked = !isLogoClicked
                    if(!isLogoClicked){
                        randomColor = Random.nextInt(colors.size)
                    }}
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Martin Goldbach",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold

            )
            Text(
                text = "Android developer",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    modifier = Modifier
                        .scale(.8f),
                    imageVector = Icons.Default.Phone,
                    contentDescription = "phone icon",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "+420 604 334 487",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
        ContactsCard()
    }

}


@Composable
fun ContactsCard(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth - 30.dp
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier
            .width(cardWidth)
            .height(200.dp),


    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            // Column of social icons
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painterResource(id = R.drawable.outlook),
                        contentDescription = "email icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Image(
                        painterResource(id = R.drawable.fb),
                        contentDescription = "fb icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Image(
                        painterResource(id = R.drawable.ig),
                        contentDescription = "ig icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Image(
                        painterResource(id = R.drawable.x),
                        contentDescription = "x icon",
                        modifier = Modifier.size(24.dp)
                    )

                }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp),

                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(
                    text = "m_goldbach@utb.cz",
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,

                    )
                Text(text = "@m_goldbach",
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,

                    )
                Text(text = "@m_goldbach",
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,

                    )
                Text(text = "@m_goldbach",
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.clickable {  }

                    )

            }

        }
    }

}

@Preview
@Composable
fun ContactsCardPrev() {
    ContactsCard()
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark mode"
)
@Composable
fun LogoPreview() {
    BusinessCardAppTheme {
        BusinessCard("Android")
    }
}