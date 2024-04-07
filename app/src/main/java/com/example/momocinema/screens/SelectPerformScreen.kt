package com.example.momocinema.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momocinema.AppComponent.CustomTopAppBar
import com.example.momocinema.AppComponent.detailCinema
import com.example.momocinema.AppComponent.listCinema
import com.example.momocinema.AppComponent.selectDate
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.data.Datasource
import com.example.momocinema.model.Cinema
import com.example.momocinema.model.Film
import com.example.momocinema.repository.CINEMA
import com.example.momocinema.repository.CINEMA_ROOM
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.PERFORM
import com.example.momocinema.ui.theme.MomoCinemaTheme
import java.sql.Timestamp
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectPerformScreen(film: FILM, listPerform:List<PERFORM>, listCinemaRoom:List<CINEMA_ROOM>, listCinemaName:List<String>, listCINEMA: List<CINEMA>, navigateToAnotherScreen:(ScreenName:String, film:FILM)->Unit) {
    val currentTime = Timestamp.from(Instant.now())    // lấy thời gian hiện tại (date + time)
    val selectFilterDay by remember {
        mutableStateOf(Timestamp(0))
    }
    val selectFilterCinema by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(text = film.title, onClick = { navigateToAnotherScreen(ScreenName.FilmInfoScreen.route, film)})
                Divider(thickness = 10.dp, color = Color.White)
                selectDate(currentTime = currentTime)
            }
        }
    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
        ) {
            Divider(thickness = 10.dp, color = Color.LightGray, modifier = Modifier.padding( bottom = 10.dp))

            listCinema(listCinema = listCinemaName)         // TODO: truyền listCinema thích hợp
                                                                   // bởi vì film có thể dc hãng này chiếu nhưng hãng kia ko chiếu
            Divider(thickness = 10.dp, color = Color.LightGray)

            var expandedCinemaId by remember { mutableStateOf(0) }
            val listCinemas: List<Cinema> = Datasource().loadCinemas() // TODO: này dựa trên bộ lọc sau khi chọn ngày và loại cinema

            Column {
                for (cinemaId in 0..listCINEMA.size-1) {
                    detailCinema(listPerform = listPerform, cinema = listCINEMA[cinemaId], isExpanded = (cinemaId == expandedCinemaId),  onExpandedButtonClick = {expandedCinemaId = cinemaId})
                    // TODO: listPerform sẽ từ List<Perform> của Film
                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true, apiLevel = 33)
//@Composable
//fun SelectPerformPreview() {
//    MomoCinemaTheme {
//        SelectPerformScreen(film = Datasource().loadFilms()[0])
//
//    }
//}