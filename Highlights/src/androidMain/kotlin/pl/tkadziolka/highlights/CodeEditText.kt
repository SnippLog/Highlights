package pl.tkadziolka.highlights

import android.content.Context
import android.text.SpannableString
import android.util.AttributeSet
import android.util.Log
import android.widget.EditText
import kotlinx.coroutines.*
import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.model.SyntaxLanguage
import pl.tkadziolka.highlights.model.SyntaxTheme

class CodeEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = android.R.attr.editTextStyle
) : EditText(context, attrs, defStyle) {
    private val job: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private var currentText: SpannableString? = null
    private var highlights: Highlights

    init {
        highlights = Highlights.Builder().build()
        highlights.setCode(text.toString()) // From xml
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

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        when {
            // highlights is null during initialization
            highlights == null -> super.onTextChanged(text, start, lengthBefore, lengthAfter)
            text.toString() == currentText.toString() -> super.onTextChanged(
                text,
                start,
                lengthBefore,
                lengthAfter
            )
            else -> {
                setCurrentCode(text)
                super.onTextChanged(text, start, lengthBefore, lengthAfter)
            }
        }
    }

    private fun setCurrentCode(text: CharSequence?) {
        text?.let {
            currentText = SpannableString(it)
            setCode(it.toString())
        }
    }

    private fun updateText() {
        throttleFirst(coroutineScope = uiScope) {
            var highlighted: SpannableString

            withContext(Dispatchers.Default) {
                highlighted = SpannableHighlights.getHighlightedAsync(highlights)
            }

            setTextKeepState(highlighted)
        }
    }

    // Debounce
    private fun throttleFirst(
        skipMs: Long = 1000L, // 1 sec
        coroutineScope: CoroutineScope,
        destinationFunction: suspend () -> Unit
    ) {
        var throttleJob: Job? = null
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                destinationFunction()
                delay(skipMs)
            }
        }
    }
}