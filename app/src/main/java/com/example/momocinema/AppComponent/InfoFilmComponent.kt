package com.example.momocinema.AppComponent

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.momocinema.R
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.model.Cast
import com.example.momocinema.model.Comment
import com.example.momocinema.model.Film
import com.example.momocinema.model.Ranking
import com.example.momocinema.model.User
import com.example.momocinema.repository.COMMENT
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import com.example.momocinema.repository.USER
val focusRequester = FocusRequester()

@Composable
fun firstInfo(film: FILM, tag: TAG) {
    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
//        Image(painter = painterResource(id = film.picture), contentDescription = null, modifier = Modifier
//            .width(120.dp)
//            .height(160.dp)
//            .clip(shape = RoundedCornerShape(8.dp)))
        AsyncImage(model = film.picture_url, contentDescription = null, modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .clip(shape = RoundedCornerShape(8.dp)))
        Column(modifier = Modifier.padding(start = 5.dp, end = 10.dp)) {
            Text(
                text = film.title,
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 3.dp, start = 5.dp)
            )
            Text(
                text = tag.tag,
                fontWeight = FontWeight(450),
                fontSize = 13.sp,
                color = Color(0xFF797979),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.25.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
            )
            Row {
                restrictAgeTag(restrictAge = film.restrict_age)
                Text(
                    text = stringResource(
                        id = when (film.restrict_age) {
                            18 -> R.string.over_18
                            16 -> R.string.over_16
                            else -> R.string.all_age
                        }
                    ),
                    fontWeight = FontWeight(450), fontSize = 13.sp, color = Color(0xFF797979), lineHeight = 16.25.sp,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 5.dp, start = 5.dp)
                )
            }
        }

    }
}

@Composable
fun secondInfo(title: Int, detail: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight(450),
            fontSize = 13.sp,
            color = Color(0xFF797979),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.25.sp,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
        )
        Text(
            text = detail,
            fontWeight = FontWeight(600),
            fontSize = 15.sp,
            color = Color.Black,
            maxLines = 2,
            lineHeight = 17.sp,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 3.dp, start = 5.dp)
                .width(88.dp)
        )
    }
}

@Composable
fun determinateProgress(progress: Float, star: Int) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 5.dp)) {
        Text(text = "$star-${star+1}", modifier = Modifier.width(35.dp))
        Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.LightGray, modifier = Modifier
            .padding(end = 10.dp)
            .size(17.dp))
        LinearProgressIndicator(
            progress = progress,
            color = Color(0xFFFE8E1D),
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .size(145.dp, 7.dp)
                .clip(shape = RoundedCornerShape(100.dp))
        )
    }
}

@Composable
fun detailRating(averageRanking: Float, amountRanking:Int, listTypeRank:List<Int>) {
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 17.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Absolute.Center) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFE8E1D), modifier = Modifier.size(30.dp))
                    Text(text = averageRanking.toString(), fontSize = 30.sp, fontWeight = FontWeight.Bold, lineHeight = 30.sp)
                    Text(
                        text = "/10",
                        fontWeight = FontWeight(450),
                        fontSize = 15.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 16.25.sp,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 5.dp, bottom = 5.dp)
                            .align(Alignment.Bottom)
                    )
                }
                numberOfReviews(amount = amountRanking, style = MaterialTheme.typography.labelLarge)
            }
            Column {
                determinateProgress(progress = (listTypeRank[4].toFloat() / amountRanking), star = 9)
                determinateProgress(progress = (listTypeRank[3].toFloat() / amountRanking), star = 7)
                determinateProgress(progress = (listTypeRank[2].toFloat() / amountRanking), star = 5)
                determinateProgress(progress = (listTypeRank[1].toFloat() / amountRanking), star = 3)
                determinateProgress(progress = (listTypeRank[0].toFloat() / amountRanking), star = 1)
            }
        }
    }
}

@Composable
fun expandableText(text: String, isExpanded: Boolean, onClick:() -> Unit, modifier: Modifier = Modifier.fillMaxWidth()) {
    Column(modifier = modifier.padding(top = 7.dp)) {
        if (isExpanded)
            Text(text = text, fontSize = 13.sp, lineHeight = 15.sp)
        else
            Text(text = text, lineHeight = 15.sp, fontSize = 13.sp, maxLines = 3, overflow = TextOverflow.Ellipsis)
        Text(text = if (isExpanded) "thu gọn" else "xem thêm", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF234EC6), modifier = Modifier.clickable(onClick = onClick))
    }
}

@Composable
fun filmComment(comment: COMMENT, ranking: RANKING, user: USER) { //comment, user, ranking
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val exclamation = when (ranking.ranking) {
        1,2 -> R.string.star12
        3,4 -> R.string.star34
        5,6 -> R.string.star56
        7,8 -> R.string.star78
        else -> R.string.star910
    }
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)) {
            AsyncImage(
                model = "https://i.pinimg.com/736x/f1/0f/f7/f10ff70a7155e5ab666bcdd1b45b726d.jpg",
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .padding(start = 5.dp)
                .fillMaxSize()
            ) {
                Text(text = user.name, fontWeight = FontWeight(500), fontSize = 13.sp)
                Row {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color(0xFFF08715), modifier = Modifier
                        .padding(end = 2.dp)
                        .size(20.dp))
                    Text(text = ranking.ranking.toString() + "/10 - " + stringResource(exclamation), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
        expandableText(
            text = comment.body,
            isExpanded = isExpanded,
            onClick = { isExpanded = !isExpanded})
        Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
    }
}

@Composable
fun createCommentTextButton(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight(500),
        color = Color(0xFF234EC6),
        modifier = Modifier.clickable { focusRequester.requestFocus() }
    )
}

@Composable
fun listCommentOfFilm(averageRanking: Float,
                      amountRanking: Int,
                      listTypeRank:MutableList<Int>,
                      listComment: List<COMMENT>,
                      listRanking:List<RANKING>,
                      listUser:List<USER>,
                      film:FILM,
                      navigateToAnotherScreen:(nameScreen:String, averageRank:Float, amountRank:Int, listTypeRank:MutableList<Int>)->Unit) { //list comment, list ranking, list user, averageRank, amountRank,
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(text = "Cộng đồng Momo nghĩ gì?", fontWeight = FontWeight(500), modifier = Modifier.padding(top = 10.dp, bottom = 3.dp))
        Row(modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFE8E1D),
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .size(30.dp)
                )
                Text(
                    text = averageRanking.toString() + "/10",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp
                )
                numberOfReviews(
                    amount = amountRanking,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }
            createCommentTextButton(text = "Viết đánh giá")
        }
        Card(
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Column {
                Divider(thickness = 10.dp, color = Color.White)
                for(comment in listComment) {
                    val user = listUser.find { user->
                        user.id == comment.user_id
                    }?:USER(0, "", "", 0)

// TODO    xu ly loc ranking nap vao
                    val ranking = listRanking.find { ranking->
                        ranking.from_id == user.id && ranking.dest_id == film.id
                    }?:RANKING(0, 0, 0, 0, "")
// TODO    xu ly loc user nap vao

                    filmComment(comment = comment, ranking, user)
                }
                Row(modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .clickable { navigateToAnotherScreen(ScreenName.DetailReviewScreen.route, averageRanking, amountRanking, listTypeRank) }, horizontalArrangement = Arrangement.Center) {
                    Text(text = "Xem tất cả ${amountRanking} bài viết",
                        color = Color(0xFF234EC6),
                        fontWeight = FontWeight(500),)
                    Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = Color(0xFF234EC6))
                }
            }
        }
    }
}

@Composable
fun castInfo(cast: Cast) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(120.dp)) {
        AsyncImage(
            model = cast.pictureUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(120.dp, 170.dp)
                .clip(RoundedCornerShape(7.dp))
        )
        Text(text = cast.name, textAlign = TextAlign.Center, lineHeight = 17.sp)
        Text(text = cast.characterName, textAlign = TextAlign.Center, lineHeight = 17.sp, color = Color(0xFF949494), modifier = Modifier.padding(bottom = 17.dp))
    }
}

@Composable
fun createNewComment(modifier: Modifier = Modifier) {
    var commentBody by remember {
        mutableStateOf("")
    }
    var selectedStarId by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        val exclamation =  when (selectedStarId) {
            1,2 -> R.string.star12
            3,4 -> R.string.star34
            5,6 -> R.string.star56
            7,8 -> R.string.star78
            else -> R.string.star910
        }
        OutlinedTextField(
            value = commentBody,
            onValueChange = {commentBody = it},
            placeholder = { Text(text = "Giờ là lúc ngôn từ lên ngôi ✍️")},
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            minLines = 5,
            shape = RoundedCornerShape(10.dp),
        )
        Divider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.padding(vertical = 10.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            if (selectedStarId != 0) { Text(text = selectedStarId.toString() + "/10 - " + stringResource(id = exclamation), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.fillMaxWidth()) }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            for (starID in 1..10) {
                IconButton(onClick = {selectedStarId = starID}, modifier = Modifier.size(35.dp)) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null,
                        tint = if (starID <= selectedStarId) Color(0xFFF08715) else Color.LightGray, modifier = Modifier.size(35.dp))
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Cảm nhận về bộ phim", fontSize = 18.sp, fontWeight = FontWeight(500))
            IconButton(
                onClick = {
                    // hoàn tất comment
                    val newComment = Comment(
                        User(name = "tên người dùng hiện tại", username = "username hiện tại" ),
                        body = commentBody, ranking = selectedStarId)
                    // TODO: đưa User vào Comment
                    commentBody = ""
                    selectedStarId = 0
                },
                enabled = (selectedStarId > 0 && commentBody != "")
            ) {
                Icon(imageVector = Icons.Outlined.Send, contentDescription = null, tint = if (selectedStarId > 0 && commentBody != "") Color(0xFF234EC6) else Color.Gray, modifier = Modifier.size(27.dp))
            }
        }
    }
}