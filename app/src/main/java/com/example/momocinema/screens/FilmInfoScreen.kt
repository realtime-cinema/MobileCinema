package com.example.momocinema.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.momocinema.AppComponent.CustomButton
import com.example.momocinema.AppComponent.CustomTopAppBar
//import com.example.momocinema.AppComponent.TrailerFromUrl
import com.example.momocinema.AppComponent.castInfo
import com.example.momocinema.AppComponent.createNewComment
import com.example.momocinema.AppComponent.detailRating
import com.example.momocinema.AppComponent.expandableText
import com.example.momocinema.AppComponent.firstInfo
import com.example.momocinema.AppComponent.getStringOfDate
import com.example.momocinema.AppComponent.listCommentOfFilm
import com.example.momocinema.AppComponent.secondInfo
import com.example.momocinema.R
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.data.Datasource
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.model.Comment
import com.example.momocinema.model.Film
import com.example.momocinema.repository.COMMENT
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.FILM_CAST
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import com.example.momocinema.repository.USER
import com.example.momocinema.ui.theme.MomoCinemaTheme
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar

import java.util.TimeZone


@Composable
fun FilmInfo(
    film: FILM,
    tag:List<TAG>,
    listComment:List<COMMENT>,
    listRank:List<RANKING>,
    listUser:List<USER>,
    navigateToAnotherScreen:(
        nameScreen:String,
        averageRank:Float,
        amountRank:Int,
        listTypeRank:MutableList<Int>)->Unit)
{
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var averageRank = 0f
    var amountRank = listComment.size
    var listTypeRank = mutableListOf(0,0,0,0,0)
    Scaffold(
        topBar = { CustomTopAppBar(text = film.title.toString(), onClick = {navigateToAnotherScreen(ScreenName.SelectFilmScreen.route, averageRank, amountRank, listTypeRank)}) },
        bottomBar = { CustomButton(actionText = R.string.buy_button, onClick = {navigateToAnotherScreen(ScreenName.SelectPerformScreen.route, averageRank, amountRank, listTypeRank)}) }

    ) {it ->
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = film.trailer_url.toString(),
                contentDescription = "${film.title} poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(222.dp)
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
//TODO: Sẽ mở ra sau khi update database
//            TrailerFromUrl(videoUrl = film.trailer_url.toString(), modifier = Modifier.height(222.dp).fillMaxWidth().padding(bottom = 10.dp))


            // thông tin quan trọng của film
            firstInfo(film = film, tag)

            // ngày khởi chiếu | Thời lượng | Ngôn ngữ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp) ,horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                var timeCalendar: java.util.Date? = null
                if (film.release_date!=null){
                    timeCalendar = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(film.release_date.toString())
                }
                var time = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                time.time = timeCalendar

                secondInfo(title = R.string.release_date, detail =if(time ==null)"null" else "${time.get(Calendar.DAY_OF_MONTH)}/${time.get(Calendar.MONTH)+1}/${time.get(Calendar.YEAR)}")
                Divider(thickness = 1.dp, color = Color(0xFFC8C8C8), modifier = Modifier
                    .height(48.dp)
                    .width(1.dp)
                    .offset(y = 5.dp))
                secondInfo(title = R.string.duration, detail = "${film.duration} min")
                Divider(thickness = 1.dp, color = Color(0xFFC8C8C8), modifier = Modifier
                    .padding(start = 5.dp)
                    .height(48.dp)
                    .width(1.dp)
                    .offset(y = 5.dp))
                secondInfo(title = R.string.language, detail = film.country.toString())
            }
//            Count rank và Tính tb rank


            listRank.forEach { ranking->
                if (ranking.dest_id == film.id){
                    averageRank = ( if(ranking.ranking!=null)averageRank+ranking.ranking else averageRank)
                    if (ranking.ranking==1 || ranking.ranking == 2) listTypeRank[0]++
                    if (ranking.ranking==3 || ranking.ranking == 4) listTypeRank[1]++
                    if (ranking.ranking==5 || ranking.ranking == 6) listTypeRank[2]++
                    if (ranking.ranking==7 || ranking.ranking == 8) listTypeRank[3]++
                    if (ranking.ranking==9 || ranking.ranking == 10) listTypeRank[4]++
                }

            }
            if (amountRank!=0){
                averageRank = averageRank/amountRank
            }
            detailRating(averageRank, amountRank, listTypeRank)
            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))

            // description
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(vertical = 17.dp, horizontal = 10.dp)) {

                Text(text = "Nội dung phim", fontWeight = FontWeight(600), fontSize = 19.sp,)
                expandableText(text = film.description.toString(), isExpanded = isExpanded, onClick = { isExpanded = !isExpanded })

            }
            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))

            // cast & Crew
            // phần clickable (hiện các phim mà cast đã tham gia) chưa cần thiết, qua giai đoạn 2 làm sau
            // Phần này cast chưa được nối với db nên k theer load cast, tạm thời fix trống
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 15.dp)) {

                Text(text = "Đạo diễn & Diễn viên", fontWeight = FontWeight(600), fontSize = 19.sp, modifier = Modifier.padding(bottom = 10.dp))
//                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                    items(listOf<FILM_CAST>()) { cast ->
////                        castInfo(cast = cast)             //TODO: Phần này sau nối db sẽ mở ra update sau(GIÁP)
//                    }
//                }
                Text(text = film.director.toString(), fontWeight = FontWeight(400), fontSize = 12.sp, modifier = Modifier.padding(bottom = 10.dp))

            }
            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))
            // comments
//            TODO: Loc list rank cua id
            listCommentOfFilm(averageRank, amountRank, listTypeRank, listComment, listRank, listUser, film, navigateToAnotherScreen)
            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))

            createNewComment()
            Divider(thickness = 10.dp, color = Color(0xFFE6E6E6))
        }
    }
}

//@Preview(showBackground = true, apiLevel = 33)
//@Composable
//fun FilmInfoPreview() {
//    MomoCinemaTheme {
//        FilmInfo(Datasource().loadFilms()[0])
//    }
//}