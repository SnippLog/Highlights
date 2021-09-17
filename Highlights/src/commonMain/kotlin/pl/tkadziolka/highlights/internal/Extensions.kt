package pl.tkadziolka.highlights.internal

fun String.indicesOf(phrase: String, ignoreCase: Boolean = true): List<Int> {
    val indices = mutableListOf<Int>()
    var index: Int = indexOf(string = phrase, ignoreCase = ignoreCase)
    while (index >= 0) {
        indices += index
        index = indexOf(string = phrase, startIndex = index + 1, ignoreCase = ignoreCase)
    }
    return indices
}

fun Char.isNewLine(): Boolean {
    val stringChar = this.toString()
    return stringChar == "\n" || stringChar == "\r" || stringChar == "\r\n"
}

fun String.lengthToEOF(start: Int = 0): Int {
    if (all { it.isNewLine().not() }) return length - start
    var endIndex = start
    while (this.getOrNull(endIndex)?.isNewLine()?.not() == true) { endIndex++ }
    return endIndex - start
}