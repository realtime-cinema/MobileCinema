package com.example.momocinema.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momocinema.AppComponent.BottomNavigationBar
import com.example.momocinema.AppComponent.ListTrendingNow
import com.example.momocinema.AppComponent.briefFilmList
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.data.Datasource
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable

fun SelectFilmScreen(
    mainViewModel: MainViewModel,
    selectFilmViewModel: SelectFilmViewModel,
    navigateToAnotherScreen:(film:FILM)->Unit,
    modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { BottomNavigationBar(mainViewModel) }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            ListTrendingNow(
                selectFilmViewModel,
                purposeTitle = "Phim nổi bật",
                navigateToAnotherScreen)   //               // TODO: truyền listFilm phù hợp vào

            Divider(thickness = 10.dp, color = Color(0xFFEEEEEE))

            briefFilmList(selectFilmViewModel, purposeTitle = "Phim hay đang chiếu", navigateToAnotherScreen)               // TODO: truyền listFilm phù hợp vào

            Divider(thickness = 10.dp, color = Color(0xFFEEEEEE))

            briefFilmList(selectFilmViewModel, purposeTitle = "Phim sắp chiếu", navigateToAnotherScreen)                // TODO: truyền listFilm phù hợp vào
        }
    }


}

//@Preview(showBackground = true)
//@Composable
//fun SelectFilmPreview() {
//    MomoCinemaTheme {
//        SelectFilmScreen()
//    }
//}