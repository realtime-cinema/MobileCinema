package com.example.momocinema.AppComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.momocinema.model.Cinema
import com.example.momocinema.model.CinemaRoom
import com.example.momocinema.model.Perform
import com.example.momocinema.repository.CINEMA
import com.example.momocinema.repository.CINEMA_ROOM
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.PERFORM
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.time.Instant
import java.util.Calendar
import java.util.Date

@Composable
fun DayCard(
    cardId: Int,
    selectedCardId: Int,
    day: Int,
    dayOfWeek: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .padding(horizontal = 7.dp)
        .size(53.dp, 70.dp)
        .clickable(onClick = onClick),
    selected: Boolean = (cardId == selectedCardId),
    color: Color = if (selected) Color(0xFF234EC6) else Color.LightGray,
) {
    Column(
        modifier = modifier
            .size(53.dp, 70.dp)
            .background(color, RoundedCornerShape(9.dp))
            .border(1.dp, color, RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(26.dp)
            .background(Color.White)){
            Text(text = dayOfWeek, color = if (selected) color else Color.Black, fontSize = 13.sp, modifier = Modifier.align(
                Alignment.Center))
        }
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(42.dp)
        ){
            Text(text = day.toString(), color = if (selected) Color.White else Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(
                Alignment.Center))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun selectDate(): Date {
    val currentTime = Timestamp.from(Instant.now())  // lấy thời gian hiện tại (date + time)
    val currentDate = currentTime.date
    val currentDay = currentTime.day
    val numberOfDayOfCurrentMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH) + 1
    var selectedCardId by remember { mutableStateOf(0) }

    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .padding(start = 7.dp, end = 7.dp, bottom = 15.dp)) {
        for(cardId in 0..13) {
//          if((currentDay + i) % dayOfCurrentMonth == 0) newMonth = 1
            val day = if (currentDate + cardId <= numberOfDayOfCurrentMonth) (currentDate + cardId ) % numberOfDayOfCurrentMonth else (currentDate + cardId + 1) % numberOfDayOfCurrentMonth
            DayCard(
                cardId = cardId,
                selectedCardId = selectedCardId,
                day = if (day == 0) 1 else day,
                dayOfWeek = if(cardId == 0) "H.nay" else dayNames[(currentDay + cardId) % 7],
                onClick = {selectedCardId = cardId}     // TODO: sau khi chọn ngày ...
            )
            //CustomCard(day = (currentDay + i + newMonth) % dayOfCurrentMonth, dayNames[(currentDayOfWeek+i) % 7], isSelect, { isSelect = !isSelect})
        }
    }
    if (selectedCardId == 0) return Timestamp(currentTime.time)
    return Timestamp(currentTime.time + selectedCardId*24*60*60*1000 - currentTime.hours*60*60*1000 - currentTime.minutes*60*1000)
}


@Composable
fun LogoCinema(cinema: Cinema, color: Color = Color.Gray) {
    AsyncImage(model = cinema.logoUrl, contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier
        .size(50.dp)
        .border(width = 2.dp, color = color, RoundedCornerShape(8.dp)))
}

@Composable
//fun listCinema(listCinema: List<String>): Cinema {
fun listCinema(listCinema: List<String>): String {
    var selectedCinemaLogoId by remember { mutableStateOf(-1) }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        var listCinemaAfterFilter:MutableList<CINEMA> = mutableListOf()
        for(cinemaId in 0..listCinema.size-1) {
            val isSelected = (selectedCinemaLogoId == cinemaId)
            val selectedColor = if(isSelected) Color(0xFF234EC6) else Color.Gray
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(70.dp, 30.dp)
                    .clickable { selectedCinemaLogoId = cinemaId }
            ) {
                Text(text = listCinema[cinemaId], fontSize = 18.sp, color = selectedColor, fontWeight = if(isSelected) FontWeight.Bold else null, modifier = Modifier.padding(top = 3.dp))
            }
        }
    }
    if (selectedCinemaLogoId != -1){
        return listCinema[selectedCinemaLogoId]
    } else{
        return "ALL"
    }
}

@Composable
fun Showtime(perform: PERFORM, onClick:() -> Unit) {
    Card(border = BorderStroke(1.dp, Color.LightGray), colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 7.dp)
            .clickable(onClick = onClick)) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(132.dp, 52.dp)
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = getStringOfTime(perform.start_time), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = " ~ ${getStringOfTime(perform.end_time)}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(bottom = 2.dp))
            }
            Text(text = "139/139 Ghế", fontSize = 12.sp, color = Color.Gray)
            // TODO: "139/139 ghế" này a ko biết lấy dữ liệu sao
        }
    }
}

@Composable
fun detailCinema(listPerform: List<PERFORM>,listCinemaRoom: List<CINEMA_ROOM>, listCINEMA: List<CINEMA>, cinema: CINEMA, isExpanded: Boolean, onExpandedButtonClick:() -> Unit, modifier: Modifier = Modifier, seletedDate: Date) {
    val extraPadding by animateDpAsState(               // cho phần mở rộng, thu hẹp Showtime
        targetValue = if (isExpanded) 20.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Column (modifier = Modifier
        .padding(horizontal = 10.dp)
        .fillMaxWidth()){
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 3.dp)
            .fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = "${cinema.name} ${cinema.variant}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = cinema.variant, fontSize = 14.sp, color = Color.Gray)
                }
            }
            IconButton(onClick = onExpandedButtonClick) {
                Icon(imageVector = if (!isExpanded) Icons.Sharp.KeyboardArrowDown else Icons.Sharp.KeyboardArrowUp, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        }
        if (isExpanded) Column(Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
            Text(text = "2D Phụ đề" /* TODO: truyền viewType, translateType */, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp, start = 3.dp))
//          avaiablePerform phải là list đã lọc theo tên rạp, thời gian chiếu
            var availablePerform = listPerform.filter { perform->
                var cinemaRoomOfPerform = listCinemaRoom. find { cinemaRoom ->
                    perform.dest_id == cinemaRoom.id
                }?: CINEMA_ROOM(-1, -1, -1, -1, "")
                var CinemaOfPerform = listCINEMA.find {cinema->
                    cinema.id ==cinemaRoomOfPerform.cinema_id
                }?:CINEMA(-1, "", "")
                cinema.name == CinemaOfPerform.name
            }
            availablePerform = availablePerform.filter { perform -> perform.start_time.after(seletedDate)}
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 60.dp, max = 127.dp)
            ) {
                items(availablePerform) {item   ->
                    Showtime(perform = item, onClick = { /* TODO: chuyển sang trang chọn ghế */})
                }
            }
        }
    }
}

@Composable
fun FilmAndPerform(film: FILM, listPerform: List<PERFORM>, selectedDate: java.util.Date) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = film.title,
                fontWeight = FontWeight(600),
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(0.75f)
            )
            Column(modifier = Modifier.weight(0.25f), horizontalAlignment = Alignment.End) {
                Text(text = "Chi tiết", fontSize = 14.sp, fontWeight = FontWeight(500), color = Color(0xFF234EC6), modifier = Modifier
                    .clickable { /* TODO: Thiện*/}
                )
                restrictAgeTag(restrictAge = film.restrict_age)
            }

        }
        Divider(thickness = 10.dp, color = Color.White)
        Row {
            AsyncImage(model = film.picture_url, contentDescription = null, modifier = Modifier
                .width(120.dp)
                .height(160.dp)
                .clip(shape = RoundedCornerShape(8.dp)))
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(text = "2D Phụ đề")
                var availablePerform = listPerform.filter { perform -> perform.start_time.after(selectedDate) }
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 60.dp, max = 127.dp)
                    // tính tón height theo số lượng Perform
                ) {
                    items(availablePerform) {item   ->
                        Showtime(perform = item, onClick = { /* TODO: chuyển sang trang chọn ghế */})
                    }
                }
            }
        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(top = 10.dp) )
    }
}
val variants = listOf(
    "TP.HCM", "Hà Nội", "Đà Nẵng", "An Giang", "Vũng Tàu", "Bạc Liêu", "Bắc Giang", "Bắc Kạn", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Huế", "Tiền Giang", "Tuyên Quang", "Vĩnh Long", "Yên Bái"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectVariantSheet(closeBottomSheet:() -> Unit, selectedVariant: String): String {
    var selectedVariant by remember {
        mutableStateOf(selectedVariant)
    }
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = closeBottomSheet,
        sheetState = sheetState
    ) {
        LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)) {
            items(variants) {variant ->
                Divider(thickness = 1.dp)
                Row(modifier = Modifier
                    .clickable {
                        selectedVariant = variant
                    }
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null, tint = Color(0xFF234EC6))
                        Text(text = variant, fontWeight = FontWeight(500))
                    }
                    if (selectedVariant == variant)
                        Icon(imageVector = Icons.Filled.Check, contentDescription = null, tint = Color(0xFF234EC6))
                }
            }
        }
    }
    return selectedVariant
}