package ru.itis.cooking.core.ui.utils

import org.jsoup.Jsoup

fun String.toCleanString(): String {
    val jsoup = Jsoup.parse(this)
    return jsoup.text()
}
