package com.example.shoppinglistapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    var id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
) {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun shoppingLsitApp() {
    var isShowDialog by remember {
        mutableStateOf(false)
    }
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var itemName by remember {
        mutableStateOf("")
    }
    var itemQuantity by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { isShowDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "추가")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) { item ->
                if (item.isEditing) {
                    ShoppingItemEditor(item = item, onEditComplete = { editName, editQuantity ->
                        sItems = sItems.map { it.copy(isEditing = false) }
                        val editedItem = sItems.find { it.id == item.id }
                        editedItem?.let {
                            it.name = editName
                            it.quantity = editQuantity
                        }
                    })
                } else {
                    ShoppingListItem(item, onEditClick = {
                        sItems = sItems.map { it.copy(isEditing = it.id == item.id) }

                    }, onDeleteClick = {
                        sItems = sItems - item
                    })
                }
            }
        }
    }
    if (isShowDialog) {
        AlertDialog(onDismissRequest = { isShowDialog = false },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(onClick = {
                        if (!itemName.isBlank()) {
                            val newItem = ShoppingItem(
                                id = sItems.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt(),
                                isEditing = false
                            )
                            sItems = sItems + newItem
                            itemName = "";
                            itemQuantity = ""
                        }
                        isShowDialog = false;
                    }) {
                        Text(text = "확인")
                    }
                    Button(
                        onClick = {
                            isShowDialog = false; itemName = ""; itemQuantity = "";
                        },
                    ) {
                        Text(text = "취소")
                    }
                }
            },
            title = {
                Text(
                    text = "Add shopping Item"
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }

            })
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RectangleShape)
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty: ${item.quantity.toString()}", modifier = Modifier.padding(8.dp))
        IconButton(onClick = { onEditClick() }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
        }
        IconButton(onClick = { onDeleteClick() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        }
    }

}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit) {
    var editName by remember { mutableStateOf(item.name) }
    var editQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            OutlinedTextField(
                value = editName,
                onValueChange = { editName = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = editQuantity,
                onValueChange = { editQuantity = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )

        }
        IconButton(onClick = {
            isEditing = false
            onEditComplete(editName, editQuantity.toIntOrNull() ?: 0)
        }) {
            Icon(imageVector = Icons.Default.Build, contentDescription = "")
        }
    }
}


