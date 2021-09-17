package pl.tkadziolka.highlights

import android.text.SpannableString
import kotlinx.coroutines.coroutineScope
import pl.tkadziolka.highlights.extension.*
import pl.tkadziolka.highlights.model.*

class SpannableHighlights {

    companion object {
        fun getHighlighted(highlights: Highlights): SpannableString {
            val finalString = SpannableString(highlights.getCode())

            highlights.getHighlights().forEach { highlight ->
                val start = highlight.location.start
                val end = highlight.location.end
                when (highlight) {
                    is Bold -> bold(finalString, start, end)
                    // Color is just RGB in hex and there is a need for alpha channel (opaque)
                    is Color -> color(highlight.rgb.opaque, finalString, start, end)
                }
            }

            return finalString
        }

        suspend fun getHighlightedAsync(highlights: Highlights) = coroutineScope {
            return@coroutineScope getHighlighted(highlights)
        }
    }
}