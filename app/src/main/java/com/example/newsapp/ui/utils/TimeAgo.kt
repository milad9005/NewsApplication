package com.example.newsapp.ui.utils

import java.text.SimpleDateFormat
import java.util.*


fun String.toTimeAgo(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): String {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    val cal = Calendar.getInstance()
    cal.time = parser.parse(this)

    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24
    val weekInMili = daysInMilli * 7
    val monthInMili = weekInMili*4
    val yearInMili = monthInMili * 12

    var diff = Calendar.getInstance().timeInMillis - cal.timeInMillis
    when (diff) {
        in 0..secondsInMilli -> {return "Just now"}
        in secondsInMilli..minutesInMilli -> {return "${(diff/secondsInMilli).toInt()} Sec ago"}
        in minutesInMilli..hoursInMilli -> {return "${(diff/minutesInMilli).toInt()} Min ago"}
        in hoursInMilli..daysInMilli -> {return "${(diff/hoursInMilli).toInt()} Hour ago"}
        in daysInMilli..weekInMili->{return "${(diff/daysInMilli).toInt()} Day ago"}
        in weekInMili..monthInMili->{return "${(diff/weekInMili).toInt()} Week ago"}
        in monthInMili..yearInMili->{return "${(diff/monthInMili).toInt()} Month ago"}
        else ->{return "${(diff/yearInMili).toInt()} Year ago"}
    }
}