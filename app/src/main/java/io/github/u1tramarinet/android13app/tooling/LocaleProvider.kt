package io.github.u1tramarinet.android13app.tooling

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import java.util.Locale

class LocaleProvider : PreviewParameterProvider<Locale> {
    override val values: Sequence<Locale>
        get() = sequenceOf(
            Locale.JAPAN,
            Locale.US,
        )
}



@Preview(
    name = "ja",
    group = "locales",
    locale = "ja",
)
@Preview(
    name = "en",
    group = "locales",
    locale = "en",
)
annotation class LocalePreviews

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

@LocalePreviews
@FontScalePreviews
annotation class CommonPreview

@CommonPreview
@Composable
fun ComposePreview() {
    Text(text = "hoge")
}