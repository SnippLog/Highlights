package pl.tkadziolka.highlights.extension

import android.graphics.Color

internal val maxAlpha get() = 255

internal val Int.red get() = Color.red(this)

internal val Int.green get() = Color.green(this)

internal val Int.blue get() = Color.blue(this)

internal val Int.rgb get() = Color.rgb(red, green, blue)

internal val Int.opaque: Int get() = Color.argb(maxAlpha, red, green, blue)