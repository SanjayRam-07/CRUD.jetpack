package com.example.crudjetpack.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.crudjetpack.util.SharedViewModel
import com.example.crudjetpack.util.UserData

@Composable
fun GetDataScreen(
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

        //add data layout
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 60.dp, end = 60.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                //userID
                OutlinedTextField(
                    modifier = Modifier,
                    value = userID,
                    onValueChange = {
                        userID = it
                                    },
                    label = { Text(text = "UserID") },
                    singleLine=true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {focusManager.moveFocus(FocusDirection.Down)})
                )
                // get user data button
                Button(modifier = Modifier,
                    onClick = {
                        sharedViewModel.retrieveData(
                            userID = userID,
                            context = context
                        ){data ->
                            name=data.name
                            profession=data.profession
                            age=data.age.toString()
                        }
                }
                ) {
                    Text(text = "Get Data")
                }
            }
            //name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                        name = it
                                },
                label = { Text(text = "Name") },
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
                label = { Text(text = "Profession") },
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
                        ageInt=age.toInt()
                },
                label = { Text(text = "Age") },
                singleLine=true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()})
                )
            //save button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val userData= UserData(
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
            //delete button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val userData= UserData(
                        userID = userID,
                        name = name,
                        profession = profession,
                        age = ageInt
                    )
                    sharedViewModel.deleteData(userID = userID, context = context, navController = navController)
                }
            ) {
                Text(text = "Delete")
            }

        }
    }
}