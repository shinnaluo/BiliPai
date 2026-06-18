package com.android.purebilibili.feature.dynamic.components

import java.io.File
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DynamicCommentImagePreviewStructureTest {

    @Test
    fun dynamicCommentPictures_openInAppPreviewInsteadOfBrowser() {
        val source = File(
            "src/main/java/com/android/purebilibili/feature/dynamic/components/DynamicCommentSheet.kt"
        ).readText()

        assertFalse(source.contains("Intent.ACTION_VIEW"))
        assertFalse(source.contains("Uri.parse("))
        assertTrue(source.contains("ImagePreviewDialog("))
        assertTrue(source.contains("resolveReplyPreviewTextContent(reply)"))
        assertTrue(source.contains("onImagePreview = { images, index, rect, textContent ->"))
    }
}
