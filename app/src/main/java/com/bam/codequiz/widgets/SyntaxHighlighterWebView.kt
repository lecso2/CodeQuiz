package com.bam.codequiz.widgets

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView


/**
 * Custom WebView for code highlight
 * android/assets/www contains the js and css files
 * https://github.com/hossain-khan/android-syntax-highlighter
 */
class SyntaxHighlighterWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"
    }

    fun bindSyntaxHighlighter(
        formattedSourceCode: String,
        language: String = "kotlin",
        showLineNumbers: Boolean = false
    ) {
        settings.javaScriptEnabled = true
        loadDataWithBaseURL(
            /* baseUrl */ ANDROID_ASSETS_PATH,
            /* data */ prismJsHtmlContent(formattedSourceCode, language, showLineNumbers),
            /* mimeType */ "text/html",
            /* encoding */ "utf-8",
            /* historyUrl */  ""
        )
    }

    private fun prismJsHtmlContent(
        formattedSourceCode: String,
        language: String,
        showLineNumbers: Boolean = true
    ): String {
        return """<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="www/prism.css" rel="stylesheet"/>
    <script src="www/prism.js"></script>
</head>
<body>
<pre class="${if (showLineNumbers) "line-numbers" else ""}">
<code class="language-${language}">${formattedSourceCode}</code>
</pre>
</body>
</html>
"""
    }
}