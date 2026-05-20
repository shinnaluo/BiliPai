package com.android.purebilibili.feature.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Terminal
import androidx.compose.ui.graphics.vector.ImageVector
import com.android.purebilibili.core.theme.UiPreset
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.ChartBar
import io.github.alexzhirkevich.cupertino.icons.outlined.DocOnDoc
import io.github.alexzhirkevich.cupertino.icons.outlined.House
import io.github.alexzhirkevich.cupertino.icons.outlined.TextBubble
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SettingsSemanticIconPolicyTest {

    @Test
    fun homeFeedEntry_usesHomeSemanticIconInsteadOfAnalyticsIcon() {
        val icon = resolveSettingsSemanticIcon(SettingsIconRole.HOME_FEED, UiPreset.IOS)

        assertSameVectorAsset(CupertinoIcons.Outlined.House, icon)
        assertNotEquals(CupertinoIcons.Default.ChartBar.name, icon.name)
    }

    @Test
    fun md3HomeFeedEntry_usesMaterialHomeSemanticIcon() {
        assertSameVectorAsset(
            Icons.Outlined.Home,
            resolveSettingsSemanticIcon(SettingsIconRole.HOME_FEED, UiPreset.MD3)
        )
    }

    @Test
    fun settingsSceneRoles_useConcreteDomainIcons() {
        assertSameVectorAsset(
            CupertinoIcons.Outlined.TextBubble,
            resolveSettingsSemanticIcon(SettingsIconRole.INTERACTION_COMMENT, UiPreset.IOS)
        )
        assertSameVectorAsset(
            CupertinoIcons.Outlined.DocOnDoc,
            resolveSettingsSemanticIcon(SettingsIconRole.DATA_BACKUP, UiPreset.IOS)
        )
        assertSameVectorAsset(
            Icons.Outlined.Terminal,
            resolveSettingsSemanticIcon(SettingsIconRole.DIAGNOSTICS, UiPreset.MD3)
        )
    }

    private fun assertSameVectorAsset(expected: ImageVector, actual: ImageVector) {
        assertEquals(expected.name, actual.name)
        assertEquals(expected.defaultWidth, actual.defaultWidth)
        assertEquals(expected.defaultHeight, actual.defaultHeight)
        assertEquals(expected.viewportWidth, actual.viewportWidth)
        assertEquals(expected.viewportHeight, actual.viewportHeight)
    }
}
