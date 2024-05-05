package com.example.momocinema.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.PasswordTextField
import com.example.momocinema.AppComponent.TextFieldCustom
import com.example.momocinema.R
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.RegisterViewModel
import com.example.momocinema.ViewModel.ScreenName
import com.example.momocinema.repository.USER
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable
fun RegisterScreen(mainViewModel: MainViewModel, registerViewModel: RegisterViewModel, navigateToAnotherScreen:(screenName:String, user:USER)->Unit,modifier: Modifier = Modifier.padding(horizontal = 20.dp)) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var validConfirmPassword = if (password == confirmPassword) 0 else 3
    var validPassword = if (password == "" || password.length >= 6) 0 else 2

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier) {
        Divider(thickness = 20.dp, color = Color.White)
        Text(text = stringResource(id = R.string.register), fontSize = 30.sp, fontWeight = FontWeight(800), color = Color(0xFF234EC6))
        Divider(thickness = 2.dp, color = Color.White)
        Text(text = stringResource(id = R.string.create_a_new_account), style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight(400))
        Divider(thickness = 30.dp, color = Color.White)

        TextFieldCustom(
            label = R.string.full_name_label,
            leadingIcon = Icons.Outlined.Person,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = firstName,
            onValueChange = { firstName = it },
            modifier = Modifier.size(width = 310.dp, height = 65.dp)
        )
        Divider(thickness = 22.dp, color = Color.White)
        TextFieldCustom(
            label = R.string.last_name_label,
            leadingIcon = Icons.Outlined.Person,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = lastName,
            onValueChange = { lastName = it },
            modifier = Modifier.size(width = 310.dp, height = 65.dp)
        )
        Divider(thickness = 22.dp, color = Color.White)

        TextFieldCustom(
            label = R.string.email_label,
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.size(width = 310.dp, height = 65.dp)
        )
        Divider(thickness = 22.dp, color = Color.White)

        PasswordTextField(
            label = R.string.password_label,
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            value = password,
            onValueChange = { password = it },
            idError = validPassword
        )
        Divider(thickness = 2.dp, color = Color.White)

        PasswordTextField(
            label = R.string.confirm_label,
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            idError = validConfirmPassword
        )
        Divider(thickness = 15.dp, color = Color.White)

        Button(             // button: CREATE ACCOUNT
            onClick = { registerViewModel.register(mainViewModel, firstName, lastName, email, password, navigateToAnotherScreen) },
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF234EC6), contentColor = Color.White),
            shape = RoundedCornerShape(5.dp),
            enabled = (firstName != "" && lastName != "" &&email != "" && validPassword == 0 && password == confirmPassword)
            // chỉ cho tạo tk khi điền đủ thông tin
        ) {
            Text(text = stringResource(id = R.string.create_account), fontWeight = FontWeight(500), fontSize = 16.sp)
        }
        Divider(thickness = 12.dp, color = Color.White)

        Row {
            Text(text = stringResource(id = R.string.already_have_account), modifier = Modifier.padding(end = 4.dp))
            Text(text = stringResource(id = R.string.login), fontWeight = FontWeight.Bold, color = Color(0xFF234EC6),
                modifier = Modifier.clickable { navigateToAnotherScreen(ScreenName.LoginScreen.route, USER()) })
            // TODO: chuyển qua LoginScreen
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun RegisterPreview() {
//    MomoCinemaTheme {
//        RegisterScreen()
//    }
//}