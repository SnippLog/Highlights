package pl.tkadziolka.highlights

import pl.tkadziolka.highlights.internal.CodeAnalyzer
import pl.tkadziolka.highlights.model.*

// TODO Handle logger (lambda)
class Highlights private constructor(
    private var code: String,
    private val language: SyntaxLanguage,
    private val theme: SyntaxTheme, // Default
    private var emphasisLocations: List<PhraseLocation>
) {

    companion object {
        fun fromBuilder(builder: Builder) = builder.build()
    }

    data class Builder(
        var code: String = "",
        var language: SyntaxLanguage = SyntaxLanguage.DEFAULT,
        var theme: SyntaxTheme = SyntaxThemes.default,
        var emphasisLocations: List<PhraseLocation> = emptyList(),
    ) {
        fun code(code: String) = apply { this.code = code }
        fun language(language: SyntaxLanguage) = apply { this.language = language }
        fun theme(theme: SyntaxTheme) = apply { this.theme = theme }
        fun emphasis(vararg locations: PhraseLocation) =
            apply { this.emphasisLocations = locations.toList() }

        fun build() = Highlights(code, language, theme, emphasisLocations)
    }

    fun setCode(code: String) {
        this.code = code
    }

    fun setEmphasis(vararg locations: PhraseLocation) {
        this.emphasisLocations = locations.toList()
    }

    fun getCodeStructure(): CodeStructure = CodeAnalyzer.analyze(code, language)

    fun getHighlights(): List<CodeHighlight> {
        val highlights = mutableListOf<CodeHighlight>()
        val structure = getCodeStructure()
        with(structure) {
            tokens.forEach { highlights.add(Color(it, theme.code)) }
            marks.forEach { highlights.add(Color(it, theme.mark)) }
            punctuations.forEach { highlights.add(Color(it, theme.punctuation)) }
            keywords.forEach { highlights.add(Color(it, theme.keyword)) }
            strings.forEach { highlights.add(Color(it, theme.string)) }
            literals.forEach { highlights.add(Color(it, theme.literal)) }
            comments.forEach { highlights.add(Color(it, theme.comment)) }
            multilineComments.forEach { highlights.add(Color(it, theme.multilineComment)) }
            annotations.forEach { highlights.add(Color(it, theme.metadata)) }
        }

        emphasisLocations.forEach { highlights.add(Bold(it)) }

        return highlights
    }

    fun getBuilder() = Builder(code, language, theme, emphasisLocations)

    fun getCode() = code

    fun getLanguage() = language

    fun getTheme() = theme

    fun getEmphasis() = emphasisLocations
}