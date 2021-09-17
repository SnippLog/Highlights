package pl.tkadziolka.highlights.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import pl.tkadziolka.highlights.CodeTextView
import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.model.SyntaxLanguage
import pl.tkadziolka.highlights.model.SyntaxTheme
import pl.tkadziolka.highlights.model.SyntaxThemes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_action_textview).setOnClickListener {
            startActivity(Intent(this, TextViewActivity::class.java))
        }

        findViewById<Button>(R.id.main_action_edittext).setOnClickListener {
            startActivity(Intent(this, EditTextActivity::class.java))
        }
    }
}
