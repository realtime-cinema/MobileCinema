package com.example.momocinema

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.momocinema.AppComponent.restrictAgeTag
import com.example.momocinema.AppComponent.selectDate
import com.example.momocinema.ViewModel.FilmInfoViewModel
import com.example.momocinema.ViewModel.LoginViewModel
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.RegisterViewModel
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.ViewModel.SelectPerformViewModel
import com.example.momocinema.ViewModelFactory.FilmInfoViewModelFactory
import com.example.momocinema.ViewModelFactory.LoginViewModelFactory
import com.example.momocinema.ViewModelFactory.MainViewModelFactory
import com.example.momocinema.ViewModelFactory.RegisterViewModelFactory
import com.example.momocinema.ViewModelFactory.SelectFilmViewModelFactory
import com.example.momocinema.ViewModelFactory.SelectPerformViewModelFactory
import com.example.momocinema.data.Datasource
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.navigation.CinemaTicketApp
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.PERFORM
import com.example.momocinema.screens.FilmInfo
import com.example.momocinema.screens.SelectFilmScreen
import com.example.momocinema.screens.SelectSeatScreen
import com.example.momocinema.ui.theme.MomoCinemaTheme

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModelFactory = MainViewModelFactory()
        val mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        val loginViewModelFactory = LoginViewModelFactory()
        val loginViewModel = ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]

        val registerViewModelFactory = RegisterViewModelFactory()
        val registerViewModel = ViewModelProvider(this, registerViewModelFactory)[RegisterViewModel::class.java]

        val selectFilmViewModelFactory = SelectFilmViewModelFactory()
        val selectFilmViewModel = ViewModelProvider(this, selectFilmViewModelFactory)[SelectFilmViewModel::class.java]
        selectFilmViewModel.fetchListFilm()

        val filmInfoViewModelFactory = FilmInfoViewModelFactory()
        val filmInfoViewModel = ViewModelProvider(this, filmInfoViewModelFactory)[FilmInfoViewModel::class.java]

        val selectPerformViewModelFactory = SelectPerformViewModelFactory()
        val selectPerformViewModel = ViewModelProvider(this, selectPerformViewModelFactory)[SelectPerformViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            MomoCinemaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CinemaTicketApp(
                        navControler = navController,
                        mainViewModel,
                        loginViewModel,
                        registerViewModel,
                        selectFilmViewModel,
                        filmInfoViewModel,
                        selectPerformViewModel
                    )
                    }
                }
            }////////////////
        }
    }










//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true, apiLevel = 33)
//@Composable
//fun GreetingPreview() {
//    MomoCinemaTheme {
//        //LogoCinema(Cinema("Cinestar", "Đồng Nai", "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170453-637584230934981809.png"))
//        // selectDay()
//        //listCinema(Datasource().loadCinemas())
//        //detailCinema(cinema = Cinema("Cinestar", "Đồng Nai", "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170453-637584230934981809.png"), isExpanded = isExpanded, {isExpanded = !isExpanded})
//        //Showtime(perform = Perform(listFilm[0], startTime = "17:20") )
//        //SelectSeatScreen(perform = Perform(Datasource().loadSeats(),listFilm[0], startTime = Timestamp.valueOf("2024-03-23 09:00:00.0")),cinema = Cinema(cinemaRooms = listOf<CinemaRoom>(CinemaRoom(1, "ROOM6", CinemaLayout(13,9))) ,"Cinestar", "Đồng Nai", "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170453-637584230934981809.png"))
//        //InfoPerform( perform = Perform(listFilm[0], startTime = Timestamp.valueOf("2024-03-23 09:00:00.0")))
//        //Seat(seat = SeatPrice(13,9, 100000, "DEFAULT"), availableSeat = true, )
//        //PaymentScreen(perform = Datasource().loadPerforms()[0])
//        //filmComment(Datasource().loadComments()[0])
//        //listCommentOfFilm(ranking = Ranking(averageRating = 9.3f, amount = 2200, star12 = 100, star34 = 100, star56 = 500, star78 = 600, star910 = 900), listComment = Datasource().loadComments())
//        //ReviewsScreen(filmTitle = "Godzilla x Kong: Đế chế mới", ranking = Ranking(averageRating = 9.3f, amount = 2200, star12 = 100, star34 = 100, star56 = 500, star78 = 600, star910 = 900), listComment = Datasource().loadComments())
//        //createNewComment()
//    }
//}