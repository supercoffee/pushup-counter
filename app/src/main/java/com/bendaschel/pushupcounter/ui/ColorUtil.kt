package com.bendaschel.pushupcounter.ui
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun genRandomColors(): Pair<Color, Color> {
    val red = Random.nextInt(255)
    val green = Random.nextInt(255)
    val blue = Random.nextInt(255)
    val bgColor = Color(red, green, blue)
//    val textColor = complementaryColor(bgColor)
    return Pair(bgColor, Color.White);
}

//fun complementaryColor(rgb: Int): Int {
//    val hsvValues = FloatArray(3)
//    Color.colorToHSV(rgb, hsvValues)
//    hsvValues[0] = (hsvValues[0] + 180) % 360
//    hsvValues[2] = contrastingLValue(50f, hsvValues[2])
//    return Color.HSVToColor(hsvValues)
//}

fun contrastingLValue(ratio: Float, lightness: Float): Float{
    return ratio * (lightness + 0.05f) - 0.05f
}