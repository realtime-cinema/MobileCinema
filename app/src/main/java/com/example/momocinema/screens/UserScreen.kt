package com.example.momocinema.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.BottomNavigationBar
import com.example.momocinema.AppComponent.DefaultBody
import com.example.momocinema.AppComponent.PurchasedFilms
import com.example.momocinema.AppComponent.UserMetric
import com.example.momocinema.R
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.USER
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable
fun UserScreen(user: USER) {
    val listPurchasedFilm: List<FILM> = listOf()

    Scaffold(
        topBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(Color(0xFF267AFF))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                        )
                        .align(Alignment.BottomCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(thickness = 42.dp, color = Color.White, modifier = Modifier.padding(top = 10.dp))
                    Text(text = user.name.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Row(
                        modifier = Modifier
                            .padding(top = 17.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        UserMetric(image = R.drawable.ticketinuserscreen, color = Color(0xFF234EC6), backgroundColor = Color(0xFFE8F6FF) , amount = 13, label = "Vé đã mua")
                        UserMetric(image = R.drawable.view, color = Color(0xFF6EB430), backgroundColor = Color(0xFFE9FFC7), amount = 7, label = "Phim đã xem")
                        UserMetric(image = R.drawable.comment, color = Color(0xFFF1A93A), backgroundColor = Color(0xFFFFFFBF), amount = 5, label = "Bình luận")
                    }
                }
                Image(painter = painterResource(id = R.drawable.popcorn), contentScale = ContentScale.Fit, contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 70.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.Center)
                        .border(1.dp, color = Color.LightGray, CircleShape))
            }
        },
        bottomBar = { BottomNavigationBar() }
    ) {it ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(color = Color(0xFFEBEBEB))
        ) {
            Card(modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                if (listPurchasedFilm.size == 0) {
                    DefaultBody()
                }
                else {
                    PurchasedFilms(purchasedFilms = listPurchasedFilm)
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun UserPreview() {
    MomoCinemaTheme {
        UserScreen(DatasourceCloneAPIData().loadUser()[0])
    }
}