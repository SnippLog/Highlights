package pl.tkadziolka.highlights.internal.locator

import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.internal.SyntaxTokens.COMMENT_DELIMITERS
import pl.tkadziolka.highlights.internal.indicesOf
import pl.tkadziolka.highlights.internal.lengthToEOF

internal object CommentLocator {

    fun locate(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        val indices = mutableListOf<Int>()
        COMMENT_DELIMITERS.forEach { delimiter ->
            indices.addAll(code.indicesOf(delimiter))
        }

        indices.forEach { start ->
            val end = start + code.lengthToEOF(start)
            locations.add(PhraseLocation(start, end))
        }
        return locations
    }
}