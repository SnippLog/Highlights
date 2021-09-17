package pl.tkadziolka.highlights.model

sealed class CodeHighlight(open val location: PhraseLocation)
data class Bold(override val location: PhraseLocation) : CodeHighlight(location)
data class Color(override val location: PhraseLocation, val rgb: Int) : CodeHighlight(location)
