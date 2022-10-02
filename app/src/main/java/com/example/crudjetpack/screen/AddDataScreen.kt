package com.example.crudjetpack.screen

import android.content.Context
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crudjetpack.util.SharedViewModel
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import com.example.crudjetpack.util.UserData

@Composable
fun AddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val focusManager= LocalFocusManager.current
    var userID:String by remember { mutableStateOf("") }
    var name:String by remember { mutableStateOf("") }
    var profession:String by remember { mutableStateOf("") }
    var age:String by remember { mutableStateOf("") }
    var ageInt:Int by remember { mutableStateOf(0) }

    val context= LocalContext.current

    //main layout
    Column(modifier = Modifier.fillMaxSize()
    ) {
        // back button
        Row (
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            IconButton(onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Unspecified
                )
            }
        }

        // data layout
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 60.dp, end = 60.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            //userID
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userID,
                onValueChange = {
                        userID = it
                                },
                label = {Text(text = "UserID")},
                singleLine=true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {focusManager.moveFocus(FocusDirection.Down)})
            )
            //name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                                },
                label = {Text(text = "Name")},
                singleLine=true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {focusManager.moveFocus(FocusDirection.Down)})
            )
            //profession
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = profession,
                onValueChange = {
                    profession = it
                                },
                label = {Text(text = "Profession")},
                singleLine=true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {focusManager.moveFocus(FocusDirection.Down)})
            )
            //age
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = age,
                onValueChange = {
                        age = it
                        ageInt=age.toInt() },
                label = {Text(text = "Age")},
                singleLine=true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {focusManager.moveFocus(FocusDirection.Down)})
            )
            //save button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val userData=UserData(
                        userID = userID,
                        name = name,
                        profession = profession,
                        age = ageInt
                    )
                    sharedViewModel.saveData(userData, context = context)
                }
            ) {
                Text(text = "Save")
            }

        }
    }

}