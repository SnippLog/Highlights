package pl.tkadziolka.highlights.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import pl.tkadziolka.highlights.CodeTextView
import pl.tkadziolka.highlights.model.PhraseLocation
import pl.tkadziolka.highlights.model.SyntaxLanguage
import pl.tkadziolka.highlights.model.SyntaxThemes

class TextViewActivity : AppCompatActivity() {
    private lateinit var codeView: CodeTextView
    private lateinit var dropdown: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)

        initCodeView()
        initDropdown()
    }

    private fun initCodeView() {
        codeView = findViewById(R.id.text_view)
        codeView.apply {
            movementMethod = ScrollingMovementMethod()
            setCode(code)
            setEmphasis(getFirstLine())
        }
    }

    private fun initDropdown() {
        dropdown = findViewById(R.id.options)

        dropdown.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            SyntaxThemes.themes.keys.toTypedArray()
        )
        dropdown.onItemSelectedListener = itemSelectedListener
    }

    private val itemSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            val themes = SyntaxThemes.themes.values.toList()
            themes.getOrNull(position)?.let { codeView.setTheme(it) }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    }

    private fun getFirstLine(): PhraseLocation =
        code.lines().first().let {
            return@let PhraseLocation(0, it.length)
        }

    private val code =
        """
        /* Block comment, this is emphasised */
        import java.util.Date;
        import static AnInterface.CONSTANT;
        import static java.util.Date.parse;
        import static SomeClass.staticField;
        /**
         * Doc comment here for <code>SomeClass</code>
         * @param T type parameter
         * @see Math#sin(double)
         */
        @Annotation (name=value)
        public class SomeClass<T extends Runnable> { // some comment
          private T field = null;
          private double unusedField = 12345.67890;
          private UnknownType anotherString = "Another\nStrin\g";
          public static int staticField = 0;
          public final int instanceFinalField = 0;

          /**
           * Semantic highlighting:
           * Generated spectrum to pick colors for local variables and parameters:
           *  Color#1 SC1.1 SC1.2 SC1.3 SC1.4 Color#2 SC2.1 SC2.2 SC2.3 SC2.4 Color#3
           *  Color#3 SC3.1 SC3.2 SC3.3 SC3.4 Color#4 SC4.1 SC4.2 SC4.3 SC4.4 Color#5
           * @param param1
           * @param reassignedParam
           * @param param2
           * @param param3
           */
          public SomeClass(AnInterface param1, int[] reassignedParam,
                          int param2
                          int param3) {
            int reassignedValue = this.staticField + param2 + param3;
            long localVar1, localVar2, localVar3, localVar4;
            int localVar = "IntelliJ"; // Error, incompatible types
            System.out.println(anotherString + toString() + localVar);
            long time = parse("1.2.3"); // Method is deprecated
            new Thread().countStackFrames(); // Method is deprecated and marked for removal
            reassignedValue ++; 
            field.run(); 
            new SomeClass() {
              {
                int a = localVar;
              }
            };
            reassignedParam = new ArrayList<String>().toArray(new int[CONSTANT]);
          }
        }
        enum AnEnum { CONST1, CONST2 }
        interface AnInterface {
          int CONSTANT = 2;
          void method();
        }
        abstract class SomeAbstractClass {
          protected int instanceField = staticField;
        }
        """.trimIndent()
}