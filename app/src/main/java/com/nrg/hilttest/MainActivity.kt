package com.nrg.hilttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nrg.hilttest.ui.theme.GatoradeGreen
import com.nrg.hilttest.ui.theme.LightWheat
import com.nrg.hilttest.ui.theme.MarketDashboardTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketDashboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    val processList = listOf(
        "Verification process with team",
        "Launch process with colleagues"
    )
    val flows = listOf("document verification", "newbie on-boarding")
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Welcome back",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(text = "Carolina Terner", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }


                Box {
                    Image(
                        painter = painterResource(id = R.drawable.profile_picture),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )

                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .background(Color.Black, shape = CircleShape)
                            .align(alignment = Alignment.BottomStart)
                            .aspectRatio(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "2",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(3.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = LightWheat
            ) {
                BottomNavigationItem(selected = true, onClick = { /*TODO*/ }, icon =  {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Home icon"
                    )
                })

                BottomNavigationItem(selected = true, onClick = { /*TODO*/ }, icon =  {
                    Icon(
                        imageVector = Icons.Outlined.AccountBox,
                        contentDescription = "Home icon"
                    )
                })

                BottomNavigationItem(selected = true, onClick = { /*TODO*/ }, icon =  {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Home icon"
                    )
                })
            }
        },
        backgroundColor = LightWheat
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 16.dp)
        ) {
            SearchBar()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Task-based \nExplanation Process",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 20.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    LeadingRowItem()
                }
            }
        }
    }
}

@Composable
fun LeadingRowItem() {
    Box(
        modifier = Modifier
            .width(190.dp)
            .padding(start = 16.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Gray,
                    cornerRadius = CornerRadius(8.dp.toPx()),
                    style = Stroke(
                        width = 2f,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(10f, 10f),
                            0f
                        )
                    )
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Button(onClick = { }, colors = ButtonDefaults.textButtonColors(
                backgroundColor = GatoradeGreen
            ),
                modifier = Modifier.clip(RoundedCornerShape(15.dp))
            ){
                Text(
                    text = "Add task",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Creative for branding", fontSize = 18.sp, color = Color.Black )
        }
    }
}

@Composable
fun SearchBar() {
    val textState = remember {
        mutableStateOf(TextFieldValue())
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp)),
        value = textState.value,
        onValueChange = {textState.value = it},
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Try to find...",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarketDashboardTheme {
        Content()
    }
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { word ->
    word.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}