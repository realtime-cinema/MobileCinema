package com.example.momocinema.AppComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.R

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