package com.example.momocinema.ViewModel

import android.icu.util.Calendar
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.recipeService
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import kotlinx.coroutines.launch

class SelectFilmViewModel:ViewModel() {
    private val _listFilmSelectStateFake = mutableStateOf(FilmSelectState())
    val listFilmSelectStateFake = _listFilmSelectStateFake




    // fake data here----begin
    fun fakeFetchListFilm(){
        _listFilmSelectStateFake.value = _listFilmSelectStateFake.value.copy(
            error = false,
            loading = false,
            listRanking = DatasourceCloneAPIData().loadRanking(),
            listFilm = DatasourceCloneAPIData().loadFilms(),
            listFilmHaventPerformed = DatasourceCloneAPIData().loadFilms(),
            listFilmOutstanding = DatasourceCloneAPIData().loadFilms(),
            listTag = DatasourceCloneAPIData().loadTags(),
            listFilmPerforming = DatasourceCloneAPIData().loadFilms(),
        )
    }

    // fake data here----end

    init {
        /*fetchListFilm()*/
        fakeFetchListFilm()
    }

    private var _listFilmSelectState = mutableStateOf(FilmSelectState())
    var listFilmSelectState = _listFilmSelectState

    var currentTime:Long = 0
    fun convertStringDayToLong(day:String):Long{
        // later
        return 0
    }
    private fun filterListPerforming(list:List<FILM>): List<FILM> {
        currentTime = System.currentTimeMillis()
        var listPerforming:MutableList<FILM> = mutableListOf()
        for (item in list){
            if (convertStringDayToLong(item.release_date.toString())<=currentTime){
                listPerforming.add(item)
            }
        }
        return listPerforming
    }
    private fun filterListHaventPerformed(list:List<FILM>): List<FILM> {
        currentTime = System.currentTimeMillis()
        var listHaventPerformed:MutableList<FILM> = mutableListOf()
        for (item in list){
            if (convertStringDayToLong(item.release_date.toString())>currentTime){
                listHaventPerformed.add(item)
            }
        }
        return listHaventPerformed
    }
    private fun filterListOutstanding(list:List<FILM>): List<FILM> {
        var listOutstanding:MutableList<FILM> = mutableListOf()
        for (item in list){
            var totalStar = 0;
            for (rank in _listFilmSelectState.value.listRanking){
                if (rank.id == item.id){
                    totalStar += rank.ranking.toInt();
                }
            }
            if (totalStar>=4.0){
                listOutstanding.add(item)
            }
        }
        return listOutstanding
    }
    fun averageRankOfFilm(listRank:List<RANKING>, film:FILM):Float{
        var average:Float = 0.0f
        for (item in listRank){
            if (item.id == film.id){
                average = (average+item.ranking.toInt())/2
            }
        }
        return average
    }
    fun totalRankOfFilm(listRank:List<RANKING>, film:FILM):Int{
        var total:Int = 0
        for (item in listRank){
            if (item.id == film.id){
                total +=1
            }
        }
        return total
    }
    fun fetchListFilm(){
        viewModelScope.launch {
            try {
                val fetchFilmRespone = recipeService.getListFilm()
                val fetchRankingRespone = recipeService.getRanking()
                val fetchTagRespone = recipeService.getTag()
                _listFilmSelectState.value = _listFilmSelectState.value.copy(
                    loading = false,
                    error  =false,
                    listFilm = fetchFilmRespone.FilmList,
                    listRanking = fetchRankingRespone.RankingList,
                    listFilmOutstanding = filterListOutstanding(fetchFilmRespone.FilmList),
                    listFilmPerforming = filterListPerforming(fetchFilmRespone.FilmList),
                    listTag = fetchTagRespone.TagList,
                    listFilmHaventPerformed = filterListHaventPerformed(fetchFilmRespone.FilmList),
                )
            }catch (e:Exception){
                _listFilmSelectState.value = _listFilmSelectState.value.copy(
                    loading = false,
                    error = true
                )
            }
        }
    }
    data class FilmSelectState(
        val loading:Boolean = false,
        val error:Boolean = false,
        val listFilm:List<FILM> = listOf(),
        val listRanking:List<RANKING> = listOf(),
        val listFilmOutstanding:List<FILM> = listOf(),
        val listFilmPerforming:List<FILM> = listOf(),
        val listTag:List<TAG> = listOf(),
        val listFilmHaventPerformed:List<FILM> = listOf(),
    )
}

