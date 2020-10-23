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

fun isPositiveNumber(number :Int) : Boolean {
    if (number > 0) {
        return true
    }
    return false
}
