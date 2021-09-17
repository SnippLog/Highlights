package pl.tkadziolka.highlights.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import pl.tkadziolka.highlights.CodeEditText
import pl.tkadziolka.highlights.model.SyntaxLanguage
import pl.tkadziolka.highlights.model.SyntaxThemes

class EditTextActivity : AppCompatActivity() {
    private lateinit var codeEditText: CodeEditText
    private lateinit var themeDropdown: Spinner
    private lateinit var languageDropdown: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        setupEditText()
        setupThemeDropdown()
        setupLanguageDropdown()
    }

    private fun setupEditText() {
        codeEditText = findViewById(R.id.edit_code_text)
    }

    private fun setupThemeDropdown() {
        themeDropdown = findViewById(R.id.edit_theme)
        themeDropdown.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            SyntaxThemes.themes.keys.toTypedArray()
        )
        themeDropdown.onItemSelectedListener = themeSelectedListener
        themeDropdown.setSelection(SyntaxThemes.themes.values.indexOf(SyntaxThemes.notepad))
    }

    private fun setupLanguageDropdown() {
        languageDropdown = findViewById(R.id.edit_language)
        languageDropdown.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            SyntaxLanguage.values().map { it.name }
        )
        languageDropdown.onItemSelectedListener = languageSelectedListener
        languageDropdown.setSelection(SyntaxLanguage.values().indexOf(SyntaxLanguage.KOTLIN))
    }

    private val themeSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            val themes = SyntaxThemes.themes.values.toList()
            themes.getOrNull(position)?.let { codeEditText.setTheme(it) }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    }

    private val languageSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            val languages = SyntaxLanguage.values()
            languages.getOrNull(position)?.let { codeEditText.setLanguage(it) }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    }
}