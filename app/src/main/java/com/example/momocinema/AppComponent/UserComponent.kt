package com.example.momocinema.AppComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.momocinema.R
import com.example.momocinema.repository.FILM

@Composable
fun UserMetric(image: Int, color: Color, backgroundColor: Color, amount: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilledIconButton(enabled = false, onClick = {/* ko cần làm gì cả*/}, colors = IconButtonDefaults.filledIconButtonColors(disabledContainerColor =  backgroundColor)) {
            Icon(painter = painterResource(id = image), contentDescription = null, tint = color, modifier = Modifier
                .size(25.dp)
            )
        }
        Text(text = "$amount", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(vertical = 5.dp))
        Text(text = label, color = Color.Gray, fontSize = 15.sp)
    }
}

@Composable
fun DefaultBody() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(thickness = 40.dp, color = Color.White)
        Image(painter = painterResource(id = R.drawable.backgrounduserscreen), contentDescription = null)
        Divider(thickness = 15.dp, color = Color.White)
        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Color(0xFF234EC6))) {
            Text(text = "Chọn phim", fontSize = 16.sp)
        }
    }
}

@Composable
fun PurchasedFilmInfo(film: FILM) {
    Row(modifier = Modifier.padding(top = 10.dp)) {
        Box(modifier = Modifier
            .size(100.dp, 150.dp)
            .clip(RoundedCornerShape(8.dp))) {
            AsyncImage(model = film.picture_url, contentDescription = null, modifier = Modifier
                .padding(end = 10.dp)
                .size(100.dp, 150.dp)
                .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop)
            restrictAgeTag(restrictAge = if(film.restrict_age!=null)film.restrict_age else 18)
        }
        Column {
            Text(text = film.title.toString(), fontWeight = FontWeight(500), fontSize = 16.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,)
            Text(text = "TODO: List<TAG>", fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text(text = "Thời lượng: ${film.duration} phút", fontSize = 13.sp)
            // tạm thời là vậy, nếu e muốn show thêm gì thì kêu a
        }
    }
}

@Composable
fun PurchasedFilms(purchasedFilms: List<FILM>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)) {
        items(purchasedFilms) { film ->
            PurchasedFilmInfo(film = film)
            Divider(thickness = 1.dp, modifier = Modifier.padding(top = 10.dp))
        }
        item {
            CustomButton(actionText = R.string.cross_sell, onClick = { /*TODO*/ })
        }

    }
}