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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.BottomNavigationBar
import com.example.momocinema.AppComponent.FilmAndPerform
import com.example.momocinema.AppComponent.SelectVariantSheet
import com.example.momocinema.AppComponent.listCinema
import com.example.momocinema.AppComponent.selectDate
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.ViewModel.SelectPerformViewModel
import com.example.momocinema.data.Datasource
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.PERFORM
import com.example.momocinema.ui.theme.MomoCinemaTheme
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectCinemaTab(selectFilmViewModel: SelectFilmViewModel, selectPerformViewModel: SelectPerformViewModel, mainViewModel: MainViewModel, navigateToAnotherScreen:(screenName:String, film:FILM, perform:PERFORM)->Unit) {
    var selectedVariant by remember {
        mutableStateOf("TP.HCM")
    }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
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
                Card(colors = CardDefaults.cardColors(Color.White), modifier = Modifier.padding(bottom = 10.dp)) {
                    selectedCinema = listCinema(listCinema = Datasource().loadCinemasName())
                }
                Card(colors = CardDefaults.cardColors(Color.White) ) {
                    Column {
                        Divider(thickness = 15.dp, color = Color.White)
                        selectedDate = selectDate()
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Danh sách phim", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = { showBottomSheet = true },
                        shape = RoundedCornerShape(5.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFAACFFF))
                    ) {
                        Text(text = selectedVariant, color = Color(0xFF234EC6))
                    }
                }
            }
            if (showBottomSheet)
                selectedVariant = SelectVariantSheet(closeBottomSheet = { showBottomSheet = false }, selectedVariant)
        },
        bottomBar = { BottomNavigationBar( mainViewModel) }
    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .background(color = Color(0xFFEBEBEB))
        ) {
            Card(colors = CardDefaults.cardColors(Color.White), modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                LazyColumn() {
                    items(selectFilmViewModel.listFilmSelectState.value.listFilm) { film ->
                        FilmAndPerform(mainViewModel, film = film, listPerform = selectPerformViewModel.listPerformSelectState.value.listPerform, selectedDate = selectedDate, navigateToAnotherScreen)
//                        FilmAndPerform(mainViewModel, film = selectFilmViewModel.listFilmSelectState.value.listFilm[40], listPerform = selectPerformViewModel.listPerformSelectState.value.listPerform, selectedDate = selectedDate, navigateToAnotherScreen)

                    }

                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true, apiLevel = 33)
//@Composable
//fun SelectCinemaTabPreview() {
//    MomoCinemaTheme {
//        SelectCinemaTab()
//    }
//}