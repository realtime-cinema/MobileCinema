package com.example.momocinema.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momocinema.AppComponent.CustomButton
import com.example.momocinema.AppComponent.CustomTopAppBar
import com.example.momocinema.AppComponent.InfoPerform
import com.example.momocinema.AppComponent.Seat
import com.example.momocinema.AppComponent.SeatStatus
import com.example.momocinema.AppComponent.displayTotalPrice
import com.example.momocinema.R
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.ViewModel.SelectSeetViewModel
import com.example.momocinema.data.Datasource
import com.example.momocinema.model.Perform
import com.example.momocinema.repository.PERFORM
import com.example.momocinema.ui.theme.MomoCinemaTheme
import java.sql.Timestamp

@Composable
fun SelectSeatScreen(selectSeetViewModel: SelectSeetViewModel,perform: PERFORM, navigateToAnotherScreen:(screenName:String)->Unit) {
    var listSeat = mutableStateOf(selectSeetViewModel.selectSeetState.value.data)
    var listPickSeat = mutableStateOf(selectSeetViewModel.selectSeetState.value.listPickSeat)
    var cinemaLayoutMaxX = 0;
    var cinemaLayoutMaxY = 0;
    if(perform!=null){
        cinemaLayoutMaxX = perform!!.cinema_room!!.cinema_layout!!.x_index!!
        cinemaLayoutMaxY = perform!!.cinema_room!!.cinema_layout!!.y_index!!
    }
//    // //TODO: truyền maxY cho thích hợp
    val totalCost = mutableStateOf(0)
    var isSelected = MutableList(cinemaLayoutMaxX) {
        MutableList(cinemaLayoutMaxY){
            mutableStateOf(false)
        }
    }
    var isOrdered = MutableList(cinemaLayoutMaxX) {
        MutableList(cinemaLayoutMaxY){
            mutableStateOf(false)
        }
    }
    listPickSeat.value.forEach { pickSeat ->
        isOrdered[pickSeat.x!!-1][pickSeat.y!!-1].value = true
    }
//    TODO:Update state sau khi fetch list pickseat

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    text = perform.cinema_room!!.cinema!!.name + " " + perform.cinema_room.cinema!!.variant,
                    onClick = { navigateToAnotherScreen(ScreenName.SelectFilmAndPerformScreen.route) })
            } },
        bottomBar = {
            Column {
                Divider(thickness = 1.dp, modifier = Modifier.padding(top = 7.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                        .fillMaxWidth()
                ) {
                    SeatStatus(text = R.string.selectedSeat, color = Color.LightGray)
                    SeatStatus(text = R.string.selectingSeat, color = Color(0xFF234EC6))
                    SeatStatus(text = R.string.normalSeat, color = Color(0xFFEFDBFE))
                    SeatStatus(text = R.string.VIPSeat, color = Color(0xFFFFCBC3))
                }
                Divider(thickness = 10.dp, color = Color.LightGray, modifier = Modifier.padding(bottom = 12.dp))
                InfoPerform(perform = perform, navigateToAnotherScreen)
                displayTotalPrice(totalPrice = totalCost.value)      // TODO: tính toán tiền xong đưa vào
                CustomButton(actionText =  R.string.continue_button, onClick = { navigateToAnotherScreen(ScreenName.PaymentScreen.route) })
            }
        }

    ) {it->
        Column(modifier = Modifier
            .padding(it)
        ) {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp

                // đúng vs trường hop x > 9
            Column(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState(initial = (cinemaLayoutMaxX / 2) * 20))
                    .verticalScroll(rememberScrollState())
            ) {
                Image(painter = painterResource(id = R.drawable.screen_in_cinema), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier
                    .width(screenWidth.dp)
                    .height(53.dp)
                    .align(Alignment.CenterHorizontally))
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(cinemaLayoutMaxY),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.SpaceBetween,
                    userScrollEnabled = false,
                    modifier = Modifier.size(
                        width = (cinemaLayoutMaxX * 36).dp,
                        height = (cinemaLayoutMaxY * 36).dp
                    )
                ) {
                    items(listSeat.value) { seat ->
                        Seat(seat = seat,
                            availableSeat = !isOrdered[seat.x!!-1][seat.y!!-1].value,   // ghế đã có ng đặt hay chưa
                            selectingSeat = isSelected[seat.x-1][seat.y-1].value,
                            onClick = {     //TODO hàm khi chọn ghế, chỉ cho phép chọn tối đa 8 ghế
                                isSelected[seat.x-1][seat.y-1].value =
                                    !isSelected[seat.x-1][seat.y-1].value
                                totalCost.value+=listSeat.value.find { seatPrice ->
                                    (seat.x == seatPrice.x)&&(seat.y == seatPrice.y)
                                }!!.price!!
                            },
                            onClickUnavaiableSeat = {}
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, apiLevel = 33)
//@Composable
//fun SelectSeatPreview() {
//    MomoCinemaTheme {
//        SelectSeatScreen(
//            perform = Perform(Datasource().loadSeats(), listFilm[0], startTime = Timestamp.valueOf("2024-03-23 09:00:00.0"), cinemaRoom = Datasource().loadCinemaRooms()[0]),
//           )
//
//    }
//}