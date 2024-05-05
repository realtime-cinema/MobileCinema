package com.example.momocinema.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.momocinema.ViewModel.LoginViewModel
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.data.Datasource
import com.example.momocinema.model.Cinema
import com.example.momocinema.model.Film
import com.example.momocinema.repository.CINEMA
import com.example.momocinema.repository.CINEMA_ROOM
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.PERFORM
import com.example.momocinema.ui.theme.MomoCinemaTheme
import java.sql.Date
import java.sql.Timestamp
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun SelectPerformScreen(mainViewModel: MainViewModel, film: FILM, listPerform:List<PERFORM>, listCinemaRoom:List<CINEMA_ROOM>, listCinemaName:List<String>, listCINEMA: List<CINEMA>, navigateToAnotherScreen:(ScreenName:String, film:FILM, perform:PERFORM)->Unit) {
    var selectedDate by remember{ mutableStateOf(Timestamp.from(Instant.now())) }
    //var selectedCinema: Cinema
    var selectedCinema: String by remember {mutableStateOf("ALL")}
    var listCinemaAfterFilterBaseOnCinemaName:MutableList<CINEMA> by remember {
        mutableStateOf(listCINEMA.toMutableList())
    }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(text = film.title.toString(), onClick = { navigateToAnotherScreen(ScreenName.FilmInfoScreen.route, film, PERFORM())})
                Divider(thickness = 10.dp, color = Color.White)
                selectedDate = selectDate()
                // trả về Timestamp cho bộ lọc, nếu khác ngày hiện tại sẽ set thời gian 00:00
            }
        }
    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
        ) {
            Divider(thickness = 10.dp, color = Color.LightGray, modifier = Modifier.padding( bottom = 10.dp))


            selectedCinema = listCinema(listCinema = listCinemaName)         // TODO: truyền listCinema thích hợp
            if (selectedCinema =="ALL"){
                listCinemaAfterFilterBaseOnCinemaName = listCINEMA.toMutableList()
            } else{
                listCinemaAfterFilterBaseOnCinemaName = listCINEMA.filter { cinema->
                    cinema.name == selectedCinema
                }.toMutableList()
            }
            Divider(thickness = 10.dp, color = Color.LightGray)

            var expandedCinemaId by remember { mutableStateOf(-1) }
            val listCinemas: List<Cinema> = Datasource().loadCinemas() // TODO: này dựa trên bộ lọc sau khi chọn ngày và loại cinema

            Column {
//              Chổ này phải lọc listcinema theo tên đã focus trên thanh lọc name cinema
//              Mặc định sẽ là all, sau khi chọn sẽ filter theo cinema name
                for (cinemaId in 0..listCinemaAfterFilterBaseOnCinemaName.size-1) {
                    detailCinema(
                        mainViewModel,
                        listPerform = listPerform,
                        listCinemaRoom = listCinemaRoom,
                        listCINEMA = listCINEMA,
                        cinema = listCinemaAfterFilterBaseOnCinemaName[cinemaId],
                        isExpanded = (cinemaId == expandedCinemaId),
                        onExpandedButtonClick = {if (expandedCinemaId == cinemaId)expandedCinemaId = -1 else expandedCinemaId = cinemaId },
                        modifier = Modifier,
                        selectedDate, // để kiểm tra các Perform có start_time sau thời gian CHỌN, nếu h.nay lấy các Perform sau giờ hiện tại, còn lại là sau 00:00
                        navigateToAnotherScreen
                    )
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