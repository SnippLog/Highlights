package pl.tkadziolka.highlights.internal

import pl.tkadziolka.highlights.model.CodeStructure
import pl.tkadziolka.highlights.model.SyntaxLanguage
import pl.tkadziolka.highlights.model.SyntaxLanguage.*
import pl.tkadziolka.highlights.internal.SyntaxTokens.ALL_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.ALL_MIXED_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.COFFEE_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.CPP_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.CSHARP_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.C_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.JAVA_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.JSCRIPT_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.KOTLIN_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.PERL_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.PYTHON_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.RUBY_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.RUST_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.SH_KEYWORDS
import pl.tkadziolka.highlights.internal.SyntaxTokens.SWIFT_KEYWORDS
import pl.tkadziolka.highlights.internal.locator.*
import pl.tkadziolka.highlights.internal.locator.CommentLocator
import pl.tkadziolka.highlights.internal.locator.KeywordLocator
import pl.tkadziolka.highlights.internal.locator.LiteralLocator
import pl.tkadziolka.highlights.internal.locator.MultilineCommentLocator
import pl.tkadziolka.highlights.internal.locator.StringLocator

internal object CodeAnalyzer {

    fun analyze(code: String, language: SyntaxLanguage = DEFAULT): CodeStructure =
        when(language) {
            DEFAULT -> analyzeCodeWithKeywords(code, ALL_KEYWORDS)
            MIXED -> analyzeCodeWithKeywords(code, ALL_MIXED_KEYWORDS)
            C -> analyzeCodeWithKeywords(code, C_KEYWORDS)
            CPP -> analyzeCodeWithKeywords(code, CPP_KEYWORDS)
            JAVA -> analyzeCodeWithKeywords(code, JAVA_KEYWORDS)
            KOTLIN -> analyzeCodeWithKeywords(code, KOTLIN_KEYWORDS)
            RUST -> analyzeCodeWithKeywords(code, RUST_KEYWORDS)
            CSHARP -> analyzeCodeWithKeywords(code, CSHARP_KEYWORDS)
            COFFEESCRIPT -> analyzeCodeWithKeywords(code, COFFEE_KEYWORDS)
            JAVASCRIPT -> analyzeCodeWithKeywords(code, JSCRIPT_KEYWORDS)
            PERL -> analyzeCodeWithKeywords(code, PERL_KEYWORDS)
            PYTHON -> analyzeCodeWithKeywords(code, PYTHON_KEYWORDS)
            RUBY -> analyzeCodeWithKeywords(code, RUBY_KEYWORDS)
            SHELL -> analyzeCodeWithKeywords(code, SH_KEYWORDS)
            SWIFT -> analyzeCodeWithKeywords(code, SWIFT_KEYWORDS)
        }

    private fun analyzeCodeWithKeywords(code: String, keywords: List<String>): CodeStructure =
        CodeStructure(
            tokens = TokenLocator.locate(code),
            marks = MarkLocator.locate(code),
            punctuations = PunctuationLocator.locate(code),
            keywords = KeywordLocator.locate(code, keywords),
            strings = StringLocator.locate(code),
            literals = LiteralLocator.locate(code),
            comments = CommentLocator.locate(code),
            multilineComments = MultilineCommentLocator.locate(code),
            annotations = AnnotationLocator.locate(code),
        )
}