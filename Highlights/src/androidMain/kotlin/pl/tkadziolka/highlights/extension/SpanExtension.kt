package pl.tkadziolka.highlights.extension

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.*

internal fun spannable(func: () -> SpannableString) = func()
private fun span(s: CharSequence, o: Any) =
   (if (s is String) SpannableString(s) else s as? SpannableString
         ?: SpannableString("")).apply { setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
private fun span(s: CharSequence, o: Any, start: Int, end: Int) =
     (if (s is String) SpannableString(s) else s as? SpannableString
         ?: SpannableString("")).apply { setSpan(o, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

internal operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))
internal operator fun SpannableString.plus(s: String) = SpannableString(TextUtils.concat(this, s))
// Bold
internal fun bold(s: CharSequence) = span(s, StyleSpan(Typeface.BOLD))
internal fun bold(s: SpannableString) = span(s, StyleSpan(Typeface.BOLD))
internal fun bold(s: CharSequence, start: Int, end: Int) = span(s, StyleSpan(Typeface.BOLD), start, end)
internal fun bold(s: SpannableString, start: Int, end: Int) = span(s, StyleSpan(Typeface.BOLD), start, end)
// Italic
internal fun italic(s: CharSequence) = span(s, StyleSpan(Typeface.ITALIC))
internal fun italic(s: SpannableString) = span(s, StyleSpan(Typeface.ITALIC))
internal fun italic(s: CharSequence, start: Int, end: Int) = span(s, StyleSpan(Typeface.ITALIC), start, end)
internal fun italic(s: SpannableString, start: Int, end: Int) = span(s, StyleSpan(Typeface.ITALIC), start, end)
// Underline
internal fun underline(s: CharSequence) = span(s, UnderlineSpan())
internal fun underline(s: SpannableString) = span(s, UnderlineSpan())
internal fun underline(s: CharSequence, start: Int, end: Int) = span(s, UnderlineSpan(), start, end)
internal fun underline(s: SpannableString, start: Int, end: Int) = span(s, UnderlineSpan(), start, end)
// Strikethrough
internal fun strike(s: CharSequence) = span(s, StrikethroughSpan())
internal fun strike(s: SpannableString) = span(s, StrikethroughSpan())
internal fun strike(s: CharSequence, start: Int, end: Int) = span(s, StrikethroughSpan(), start, end)
internal fun strike(s: SpannableString, start: Int, end: Int) = span(s, StrikethroughSpan(), start, end)
// Color
internal fun color(color: Int, s: CharSequence) = span(s, ForegroundColorSpan(color))
internal fun color(color: Int, s: SpannableString) = span(s, ForegroundColorSpan(color))
internal fun color(color: Int, s: CharSequence, start: Int, end: Int) = span(s, ForegroundColorSpan(color), start, end)
internal fun color(color: Int, s: SpannableString, start: Int, end: Int) = span(s, ForegroundColorSpan(color), start, end)
// Background
internal fun background(color: Int, s: CharSequence) = span(s, BackgroundColorSpan(color))
internal fun background(color: Int, s: SpannableString) = span(s, BackgroundColorSpan(color))
internal fun background(color: Int, s: CharSequence, start: Int, end: Int) = span(s, BackgroundColorSpan(color), start, end)
internal fun background(color: Int, s: SpannableString, start: Int, end: Int) = span(s, BackgroundColorSpan(color), start, end)
// Url
internal fun url(s: CharSequence) = span(s, URLSpan(s.toString()))
internal fun url(s: SpannableString) = span(s, URLSpan(s.toString()))
internal fun url(s: CharSequence, start: Int, end: Int) = span(s, URLSpan(s.toString()), start, end)
internal fun url(s: SpannableString, start: Int, end: Int) = span(s, URLSpan(s.toString()), start, end)
// Normal
internal fun normal(s: CharSequence) = span(s, SpannableString(s))
internal fun normal(s: SpannableString) = span(s, SpannableString(s))
internal fun normal(s: CharSequence, start: Int, end: Int) = span(s, SpannableString(s), start, end)
internal fun normal(s: SpannableString, start: Int, end: Int) = span(s, SpannableString(s), start, end)