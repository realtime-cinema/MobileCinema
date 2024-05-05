package com.example.momocinema.ViewModel

import android.icu.util.Calendar
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.APIService
import com.example.momocinema.APIService.SimpleApi
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import kotlinx.coroutines.launch
import retrofit2.create

class SelectFilmViewModel:ViewModel() {
    private val _listFilmSelectStateFake = mutableStateOf(FilmSelectState())
    val listFilmSelectStateFake = _listFilmSelectStateFake

    private var _listFilmSelectState = mutableStateOf(FilmSelectState())
    var listFilmSelectState = _listFilmSelectState

    var currentTime:Long = 0
    fun convertStringDayToLong(day:String):Long{
        // later
        return 0
    }
    private fun filterListPerforming(list:List<FILM>): List<FILM> {
//        currentTime = System.currentTimeMillis()
//        var listPerforming:MutableList<FILM> = mutableListOf()
//        for (item in list){
//            if (convertStringDayToLong(item.release_date.toString())<=currentTime){
//                listPerforming.add(item)
//            }
//        }
//        return listPerforming
        return list
    }
    private fun filterListHaventPerformed(list:List<FILM>): List<FILM> {
//        currentTime = System.currentTimeMillis()
//        var listHaventPerformed:MutableList<FILM> = mutableListOf()
//        for (item in list){
//            if (convertStringDayToLong(item.release_date.toString())>currentTime){
//                listHaventPerformed.add(item)
//            }
//        }
//        return listHaventPerformed
        return list
    }
    private fun filterListOutstanding(list:List<FILM>): List<FILM> {
//        var listOutstanding:MutableList<FILM> = mutableListOf()
//        for (item in list){
//            var totalStar = 0;
//            for (rank in _listFilmSelectState.value.listRanking){
//                if (rank.id == item.id){
//                    totalStar += rank.ranking.toInt();
//                }
//            }
//            if (totalStar>=4.0){
//                listOutstanding.add(item)
//            }
//        }
//        return listOutstanding
        return list
    }
    fun averageRankOfFilm(listRank:List<RANKING>, film:FILM):Float{
        var average:Float = 0.0f
        var total = 0;
        for (item in listRank){
            if (item.dest_id == film.id){
                if(item.ranking!=null){
                    total++;
                    average = average+item.ranking!!
                }

            }
        }
        average = if (total == 0) 0.0f else average/total
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

                val retrofit = SimpleApi.retrofit.create(APIService::class.java)
                val fetchFilmRespone1 = retrofit.getListFilm()
                Log.d("SUCCESS", fetchFilmRespone1.body().toString())
                val fetchFilmRespone = fetchFilmRespone1.body()

                val fetchRankingRespone = DatasourceCloneAPIData().loadRanking()  //backlog
                val fetchTagRespone = DatasourceCloneAPIData().loadTags()  //backlog
                fetchFilmRespone?.let {
                    _listFilmSelectState.value = _listFilmSelectState.value.copy(
                        loading = false,
                        error  =false,
                        listFilm = fetchFilmRespone.data,
                        listRanking = fetchRankingRespone,  //backlog
                        listFilmOutstanding = filterListOutstanding(fetchFilmRespone.data),
                        listFilmPerforming = filterListPerforming(fetchFilmRespone.data),
                        listTag = fetchTagRespone,//backlog
                        listFilmHaventPerformed = filterListHaventPerformed(fetchFilmRespone.data),
                    )
                }

            }catch (e:Exception){
                Log.d("ERROR", e.toString())
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
        val listTag:List<List<TAG>> = listOf(),
        val listFilmHaventPerformed:List<FILM> = listOf(),
    )
}

