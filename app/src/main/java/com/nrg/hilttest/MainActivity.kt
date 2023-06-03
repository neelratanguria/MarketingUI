package com.nrg.hilttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.ArrowDropDown
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
                        imageVector = Icons.Filled.Home,
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
                items(processList.size) {
                    ProcessItem(processList[it])
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Flows List",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowDropDown,
                            contentDescription = "Drop down menu",
                            tint = Color.Black
                        )
                    }
                }

                Text(
                    text = "See all",
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 20.dp),
                    fontSize = 20.sp
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                itemsIndexed(flows) {index, flow ->
                    FlowItem(flow)

                    if (index < flows.lastIndex ) {
                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(top = 20.dp),
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FlowItem(flow: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = flow.capitalizeWords(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text(
                text = "3 mintues ago",
                color = Color.Gray,
                fontSize = 15.sp
            )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(CircleShape)
                .background(GatoradeGreen)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add button",
                modifier = Modifier.padding(7.dp)
            )
        }
    }
}

@Composable
fun ProcessItem(process: String) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightWheat),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Review",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(
                modifier = Modifier
                    .height(29.dp)
            )
            
            Text(
                text = process,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
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
                    style = Stroke(
                        width = 2f,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(10f, 10f),
                            0f
                        )
                    ),
                    cornerRadius = CornerRadius(30.dp.toPx()),
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