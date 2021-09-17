package pl.tkadziolka.highlights

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.model.SyntaxLanguage
import pl.tkadziolka.highlights.model.SyntaxTheme

class CodeTextView : TextView {
    private var highlights: Highlights

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        highlights = Highlights.Builder().build()
    }

    fun init(language: SyntaxLanguage, theme: SyntaxTheme) {
        highlights = Highlights.Builder(language = language, theme = theme).build()
    }

    fun setCode(code: String) {
        highlights.setCode(code)
        updateText()
    }

    fun setLanguage(language: SyntaxLanguage) {
        highlights = Highlights.fromBuilder(highlights.getBuilder().copy(language = language))
        updateText()
    }

    fun setTheme(theme: SyntaxTheme) {
        highlights = Highlights.fromBuilder(highlights.getBuilder().copy(theme = theme))
        updateText()
    }

    fun setEmphasis(vararg locations: PhraseLocation) {
        highlights.setEmphasis(*locations)
        updateText()
    }

    private fun updateText() {
        text = SpannableHighlights.getHighlighted(highlights)
    }
}