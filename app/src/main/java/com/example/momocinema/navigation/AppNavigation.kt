package com.example.momocinema.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.momocinema.ViewModel.FilmInfoViewModel
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.data.Datasource
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.TAG
import com.example.momocinema.repository.USER
import com.example.momocinema.screens.FilmInfo
import com.example.momocinema.screens.SelectFilmScreen
import java.sql.Timestamp

@Composable
fun CinemaTicketApp(navControler:NavHostController){
    val selectFilmViewModel = SelectFilmViewModel()
    NavHost(navController = navControler, startDestination = "select_film"){
        composable(ScreenName.SelectFilmScreen.route){
            SelectFilmScreen(selectFilmViewModel,navigateToAnotherScreen = {
                navControler.currentBackStackEntry?.savedStateHandle?.set("film", it)
                navControler.navigate(ScreenName.FilmInfoScreen.route)
            })
        }
        composable(ScreenName.FilmInfoScreen.route){
            val filmInfoViewModel = FilmInfoViewModel()

        // TODO: Filter film, tag, comment and list user of each comment. list ranking is original and isn't filtered
            val filmSelected = navControler.previousBackStackEntry
                ?.savedStateHandle
                ?.get<FILM>("film")
                ?:FILM(0, "", "", "", "", "", Timestamp(0), "", 0, 0)
//            Filter tag
            var filmTag = selectFilmViewModel.listFilmSelectStateFake.value.listTag.find { tag ->
                tag.id == filmSelected.id
            }?: TAG(0, "")

//            Filter list comment and user
            var listCommentOfFilmSelected = filmInfoViewModel.listFilmInfoStateFake.value.listComment.filter { comment->
                comment.dest_id == filmSelected.id
            }
            var listUser = filmInfoViewModel.listFilmInfoStateFake.value.listUser
//            Get list rank
            var listRank = selectFilmViewModel.listFilmSelectStateFake.value.listRanking
            FilmInfo(filmSelected, filmTag, listCommentOfFilmSelected, listRank, listUser){
                navControler.navigate(ScreenName.SelectFilmScreen.route)
            }
        }
    }
}