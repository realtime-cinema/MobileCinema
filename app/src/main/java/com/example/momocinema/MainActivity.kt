package com.example.momocinema

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.momocinema.ViewModel.FilmInfoViewModel
import com.example.momocinema.ViewModel.LoginViewModel
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.RegisterViewModel
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.ViewModel.SelectPerformViewModel
import com.example.momocinema.ViewModel.SelectSeetViewModel
import com.example.momocinema.ViewModelFactory.FilmInfoViewModelFactory
import com.example.momocinema.ViewModelFactory.LoginViewModelFactory
import com.example.momocinema.ViewModelFactory.MainViewModelFactory
import com.example.momocinema.ViewModelFactory.RegisterViewModelFactory
import com.example.momocinema.ViewModelFactory.SelectFilmViewModelFactory
import com.example.momocinema.ViewModelFactory.SelectPerformViewModelFactory
import com.example.momocinema.ViewModelFactory.SelectSeetViewModelFactory
import com.example.momocinema.navigation.CinemaTicketApp
import com.example.momocinema.ui.theme.MomoCinemaTheme
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.transports.WebSocket
import java.net.URISyntaxException


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
        selectPerformViewModel.fetchListPerform()

        val selectSeetViewModelFactory  =SelectSeetViewModelFactory()
        val selectSeetViewModel = ViewModelProvider(this, selectSeetViewModelFactory)[SelectSeetViewModel::class.java]
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
                        selectPerformViewModel,
                        selectSeetViewModel,
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