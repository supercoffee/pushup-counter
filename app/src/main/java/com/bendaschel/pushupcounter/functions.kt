package com.bendaschel.pushupcounter

import android.graphics.Color

fun square(a: Int) = a * a

fun toHSV(rgb: Int): Int {
    val hsvValues = FloatArray(3)
    Color.colorToHSV(rgb, hsvValues)
    hsvValues[0] = (hsvValues[0] + 180) % 360
    return Color.HSVToColor(hsvValues)
}