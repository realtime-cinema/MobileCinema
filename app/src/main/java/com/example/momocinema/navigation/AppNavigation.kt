package com.example.momocinema.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.momocinema.ViewModel.FilmInfoViewModel
import com.example.momocinema.ViewModel.LoginViewModel
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.RegisterViewModel
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.ViewModel.SelectPerformViewModel
import com.example.momocinema.ViewModel.SelectSeetViewModel
import com.example.momocinema.ViewModelFactory.SelectFilmViewModelFactory
import com.example.momocinema.data.Datasource
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.CINEMA
import com.example.momocinema.repository.CINEMA_ROOM
import com.example.momocinema.repository.COMMENT
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.PERFORM
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import com.example.momocinema.repository.USER
import com.example.momocinema.screens.FilmInfo
import com.example.momocinema.screens.LoginScreen
import com.example.momocinema.screens.RegisterScreen
import com.example.momocinema.screens.ReviewsScreen
import com.example.momocinema.screens.SelectCinemaTab
import com.example.momocinema.screens.SelectFilmScreen
import com.example.momocinema.screens.SelectPerformScreen
import com.example.momocinema.screens.SelectSeatScreen
import com.example.momocinema.screens.UserScreen
import java.sql.Timestamp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CinemaTicketApp(
    navControler:NavHostController,
    mainViewModel: MainViewModel,
    loginViewModel:LoginViewModel,
    registerViewModel: RegisterViewModel,
    selectFilmViewModel:SelectFilmViewModel,
    filmInfoViewModel: FilmInfoViewModel,
    selectPerformViewModel: SelectPerformViewModel,
    selectSeetViewModel: SelectSeetViewModel,
    ){
    NavHost(navController = navControler, startDestination = if(mainViewModel.applicationState.value.indexScreen == 0)ScreenName.SelectFilmScreen.route else if(mainViewModel.applicationState.value.indexScreen==1)ScreenName.SelectFilmAndPerformScreen.route else ScreenName.UserScreen.route){
        composable(ScreenName.SelectFilmScreen.route){
//            Initial SelectFilmViewModel
            SelectFilmScreen(
                mainViewModel,
                selectFilmViewModel,
                navigateToAnotherScreen = {
                filmInfoViewModel.fetchFilmInfo()
                navControler
                    .currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("film", it)
                navControler.navigate(ScreenName.FilmInfoScreen.route)

            })
        }
        composable(ScreenName.FilmInfoScreen.route){
            val filmSelected = navControler.previousBackStackEntry
                ?.savedStateHandle
                ?.get<FILM>("film")
                ?:FILM()
//            Filter tag
            var filmTag = /*selectFilmViewModel.listFilmSelectState.value.listTag.find { tag ->
                tag[0].id == filmSelected.id
            }?: TAG(0, "")*/ listOf(TAG(name = "Hành động"), TAG(name = "Phiêu lưu"))

//            Filter list comment and user
            var listCommentOfFilmSelected = /*filmInfoViewModel.listFilmInfoState.value.listComment.filter { comment->
                comment.dest_id == filmSelected.id
            }*/ listOf<COMMENT>()

            var listUser = filmInfoViewModel.listFilmInfoState.value.listUser
//            Get list rank
            var listRank = selectFilmViewModel.listFilmSelectState.value.listRanking

            FilmInfo(filmSelected, filmTag, listCommentOfFilmSelected, listRank, listUser, navigateToAnotherScreen = {stringName,averageRank, amountRank, listTypeRank->

                navControler.currentBackStackEntry?.savedStateHandle?.set("film", filmSelected)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_comment", listCommentOfFilmSelected)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_rank", listRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_user", listUser)
                navControler.currentBackStackEntry?.savedStateHandle?.set("average_rank", averageRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("amount_rank", amountRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_type_rank", listTypeRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("tag", filmTag)
                navControler.navigate(stringName)
            })
        }
        composable(ScreenName.DetailReviewScreen.route){
            val film = navControler.previousBackStackEntry?.savedStateHandle?.get<FILM>("film")?:FILM()
            val listComment = navControler.previousBackStackEntry?.savedStateHandle?.get<List<COMMENT>>("list_comment")?: listOf()
            val listRank = navControler.previousBackStackEntry?.savedStateHandle?.get<List<RANKING>>("list_rank")?: listOf()
            val listUser = navControler.previousBackStackEntry?.savedStateHandle?.get<List<USER>>("list_user")?: listOf()
            val averageRank = navControler.previousBackStackEntry?.savedStateHandle?.get<Float>("average_rank")?: 0.0f
            val amountRank = navControler.previousBackStackEntry?.savedStateHandle?.get<Int>("amount_rank")?: 0
            val listTypeRank = navControler.previousBackStackEntry?.savedStateHandle?.get<MutableList<Int>>("list_type_rank")?: mutableListOf(0,0,0,0,0)
            val filmTag = navControler.previousBackStackEntry?.savedStateHandle?.get<List<TAG>>("tag")?: listOf()
            navControler.currentBackStackEntry?.savedStateHandle?.set("film", film)
            ReviewsScreen(film, listRank, listComment, listUser, averageRank, amountRank, listTypeRank,filmTag, {screen, film->
                navControler.currentBackStackEntry?.savedStateHandle?.set("film", film)
                navControler.navigate(screen)
            })
        }
        composable(ScreenName.SelectPerformScreen.route){
            val film = navControler.previousBackStackEntry?.savedStateHandle?.get<FILM>("film")?:FILM()

            var listCinemaRoomOfFilm:MutableList<CINEMA_ROOM> = mutableListOf()
            var listCinemaOfFilm:MutableList<CINEMA> = mutableListOf()

            val listPerform = selectPerformViewModel.listPerformSelectState.value.listPerform.filter { perform->
                if(perform.film!=null) perform.film.id == film.id else false
            }
            var setNameCinema:MutableSet<String> = mutableSetOf()
            listPerform.forEach {perform->
                if(perform.cinema_room!=null) listCinemaRoomOfFilm.add(perform.cinema_room)
            }

            listCinemaRoomOfFilm.forEach { cinemaRoom ->
                if(cinemaRoom.cinema!=null) listCinemaOfFilm.add(cinemaRoom.cinema)
                setNameCinema.add(listCinemaOfFilm[listCinemaOfFilm.size-1].name.toString())
            }
            SelectPerformScreen(mainViewModel, film = film, listPerform, listCinemaRoomOfFilm.toList(), setNameCinema.toList(), listCinemaOfFilm.toList(), {screenName, film, perform->
                navControler.currentBackStackEntry?.savedStateHandle?.set("film", film)
                navControler.currentBackStackEntry?.savedStateHandle?.set("perform", perform)
                navControler.navigate(screenName)
            })
        }
        composable(ScreenName.UserScreen.route){
            UserScreen(mainViewModel,user = if(mainViewModel.applicationState.value.user!=null) mainViewModel.applicationState.value.user!! else USER(), {screenName ->
                navControler.navigate(screenName)
            })
        }
        composable(ScreenName.LoginScreen.route){
            LoginScreen(mainViewModel, loginViewModel, {screenName, user->
                navControler.navigate(screenName)
            })
        }
        composable(ScreenName.RegisterScreen.route){
            RegisterScreen(mainViewModel, registerViewModel,{screenName, user ->
                navControler.navigate(screenName)
            })
        }
        composable(ScreenName.SelectFilmAndPerformScreen.route){
            SelectCinemaTab(selectFilmViewModel, selectPerformViewModel, mainViewModel, {screenName, film, perform ->
                selectSeetViewModel.fetchSeet(perform.id.toString())

                navControler.currentBackStackEntry?.savedStateHandle?.set("perform", perform)
                navControler.navigate(screenName)
            })
        }
        composable(ScreenName.SelectSeatScreen.route){
            var perform = navControler.previousBackStackEntry?.savedStateHandle?.get<PERFORM>("perform")
            SelectSeatScreen(selectSeetViewModel,perform = perform!!)
        }
    }
}