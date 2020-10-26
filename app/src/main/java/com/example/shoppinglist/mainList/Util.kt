package com.example.shoppinglist.mainList


fun isNumericString(str : String) : Boolean {
    var strToInt : Int

    try {
        strToInt = str.toInt()
    } catch (e: NumberFormatException) {
        return false
    }
    return true
}
