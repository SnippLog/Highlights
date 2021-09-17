package pl.tkadziolka.highlights.internal.locator

import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.internal.SyntaxTokens.TOKEN_DELIMITERS
import pl.tkadziolka.highlights.internal.indicesOf

internal object AnnotationLocator {

    fun locate(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        code.split(*TOKEN_DELIMITERS.toTypedArray()) // Separate words
            .asSequence() // Manipulate on given word separately
            .filter { it.isNotEmpty() } // Filter spaces and others
            .filter { it.first() == '@' } // Find start of literals
            .forEach { annotation ->
                // For given annotation find all occurrences
                code.indicesOf(annotation).forEach { startIndex ->
                    locations.add(PhraseLocation(startIndex, startIndex + annotation.length))
                }
            }

        return locations
    }
}