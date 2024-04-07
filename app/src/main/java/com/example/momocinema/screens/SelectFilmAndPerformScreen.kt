package com.example.momocinema.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.FilmAndPerform
import com.example.momocinema.AppComponent.listCinema
import com.example.momocinema.AppComponent.selectDate
import com.example.momocinema.data.Datasource
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.ui.theme.MomoCinemaTheme
import java.sql.Timestamp
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectCinemaTab() {
    var selectedVariant by remember {
        mutableStateOf("TP.HCM")
    }
    var selectedDate by remember {
        mutableStateOf(Timestamp.from(Instant.now()))
    }
    //var selectedCinema: Cinema
    var selectedCinema by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            Column(modifier = Modifier
                .background(color = Color(0xFFEBEBEB))
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {
                Card(colors = CardDefaults.cardColors(Color.White)) {
                    selectedCinema = listCinema(listCinema = Datasource().loadCinemasName())
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Rạp " + selectedCinema, fontWeight = FontWeight.Bold, fontSize = 23.sp)
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(5.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFAACFFF))
                    ) {
                        Text(text = selectedVariant, color = Color(0xFF234EC6))
                    }
                }

                Card(colors = CardDefaults.cardColors(Color.White) ) {
                    Column {
                        Divider(thickness = 15.dp, color = Color.White)
                        selectedDate = selectDate()
                    }
                }
            }
        }
    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .background(color = Color(0xFFEBEBEB))
        ) {
            Text(text = "Danh sách phim", fontSize = 23.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
            Card(colors = CardDefaults.cardColors(Color.White), modifier = Modifier.padding(horizontal = 10.dp)) {
                LazyColumn() {
                    items(DatasourceCloneAPIData().loadFilms()) { film ->
                        FilmAndPerform(film = film, listPerform = DatasourceCloneAPIData().loadPerforms(), selectedDate = selectedDate)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, apiLevel = 33)
@Composable
fun GreetingPreview() {
    MomoCinemaTheme {
        SelectCinemaTab()
    }
}