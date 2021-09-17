package pl.tkadziolka.highlights.internal.locator

import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.internal.SyntaxTokens
import pl.tkadziolka.highlights.internal.indicesOf

internal object TokenLocator {
    fun locate(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        code.split(*SyntaxTokens.TOKEN_DELIMITERS.toTypedArray()) // Separate words
            .asSequence() // Manipulate on given word separately
            .filter { it.isNotEmpty() } // Filter spaces and others
            .forEach { token ->
                code.indicesOf(token).forEach { startIndex ->
                    locations.add(PhraseLocation(startIndex, startIndex + token.length))
                }
            }

        return locations
    }
}