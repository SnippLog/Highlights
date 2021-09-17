package pl.tkadziolka.highlights.internal.locator

import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.internal.SyntaxTokens.PUNCTUATION_CHARACTERS
import pl.tkadziolka.highlights.internal.indicesOf

internal object PunctuationLocator {
    fun locate(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        code.asSequence()
            .toSet()
            .filter { it.toString() in PUNCTUATION_CHARACTERS }
            .forEach {
                code.indicesOf(it.toString()).forEach { index ->
                    locations.add(PhraseLocation(index, index + 1))
                }
            }
        return locations
    }
}