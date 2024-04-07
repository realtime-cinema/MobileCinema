package com.example.momocinema.navigation

import android.util.Log
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
import com.example.momocinema.repository.COMMENT
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import com.example.momocinema.repository.USER
import com.example.momocinema.screens.FilmInfo
import com.example.momocinema.screens.ReviewsScreen
import com.example.momocinema.screens.SelectFilmScreen
import java.sql.Timestamp

@Composable
fun CinemaTicketApp(navControler:NavHostController){
    val selectFilmViewModel = SelectFilmViewModel()
    val initialFilmObject = FILM(0, "", "", "", "", "", Timestamp(0), "", 0, 0)
    NavHost(navController = navControler, startDestination = "select_film"){
        composable(ScreenName.SelectFilmScreen.route){
            SelectFilmScreen(selectFilmViewModel,navigateToAnotherScreen = {
                navControler.currentBackStackEntry?.savedStateHandle?.set("film", it)
                navControler.navigate(ScreenName.FilmInfoScreen.route)
            })
        }
        composable(ScreenName.FilmInfoScreen.route){
            val filmInfoViewModel = FilmInfoViewModel()
            val filmSelected = navControler.previousBackStackEntry
                ?.savedStateHandle
                ?.get<FILM>("film")
                ?:initialFilmObject
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
            FilmInfo(filmSelected, filmTag, listCommentOfFilmSelected, listRank, listUser, navigateToAnotherScreen = {stringName,averageRank, amountRank, listTypeRank->
                navControler.currentBackStackEntry?.savedStateHandle?.set("film", filmSelected)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_comment", listCommentOfFilmSelected)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_rank", listRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_user", listUser)
                navControler.currentBackStackEntry?.savedStateHandle?.set("average_rank", averageRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("amount_rank", amountRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("list_type_rank", listTypeRank)
                navControler.currentBackStackEntry?.savedStateHandle?.set("tag", filmTag)
                Log.d("AppNavigate", listTypeRank.toString())
                navControler.navigate(stringName)
            })
        }
        composable(ScreenName.DetailReviewScreen.route){
            val film = navControler.previousBackStackEntry?.savedStateHandle?.get<FILM>("film")?:initialFilmObject
            val listComment = navControler.previousBackStackEntry?.savedStateHandle?.get<List<COMMENT>>("list_comment")?: listOf()
            val listRank = navControler.previousBackStackEntry?.savedStateHandle?.get<List<RANKING>>("list_rank")?: listOf()
            val listUser = navControler.previousBackStackEntry?.savedStateHandle?.get<List<USER>>("list_user")?: listOf()
            val averageRank = navControler.previousBackStackEntry?.savedStateHandle?.get<Float>("average_rank")?: 0.0f
            val amountRank = navControler.previousBackStackEntry?.savedStateHandle?.get<Int>("amount_rank")?: 0
            val listTypeRank = navControler.previousBackStackEntry?.savedStateHandle?.get<MutableList<Int>>("list_type_rank")?: mutableListOf(0,0,0,0,0)
            val filmTag = navControler.previousBackStackEntry?.savedStateHandle?.get<TAG>("tag")?:TAG(0, "")
            ReviewsScreen(film, listRank, listComment, listUser, averageRank, amountRank, listTypeRank,filmTag, {screen, film->
                navControler.currentBackStackEntry?.savedStateHandle?.set("film", film)
                navControler.navigate(screen)
            })
        }
    }
}