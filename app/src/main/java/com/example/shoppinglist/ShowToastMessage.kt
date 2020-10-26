package com.example.shoppinglist

import android.content.Context
import android.widget.Toast

fun showInvalidInputTypeToast(context: Context) {
    Toast.makeText(context, "Insert the positive number", Toast.LENGTH_SHORT).show()
}
