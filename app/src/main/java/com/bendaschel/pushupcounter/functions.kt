package com.bendaschel.pushupcounter

import android.graphics.Color

fun square(a: Int) = a * a

fun complementaryColor(rgb: Int): Int {
    val hsvValues = FloatArray(3)
    Color.colorToHSV(rgb, hsvValues)
    hsvValues[0] = (hsvValues[0] + 180) % 360
    hsvValues[2] = contrastingLValue(50f, hsvValues[2])
    return Color.HSVToColor(hsvValues)
}

fun contrastingLValue(ratio: Float, lightness: Float): Float{
    return ratio * (lightness + 0.05f) - 0.05f
}