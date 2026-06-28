package com.android.purebilibili.feature.settings

import java.io.File
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SettingsSubpageChromeStructureTest {

    private val subpageScreens = listOf(
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/PermissionSettingsScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/TipsSettingsScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/BlockedListScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/OpenSourceLicensesScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/JsonPluginEditorScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/PluginsScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/BottomBarSettingsScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/IconSettingsScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/screen/PlaybackSettingsScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/share/SettingsShareScreen.kt",
        "app/src/main/java/com/android/purebilibili/feature/settings/webdav/WebDavBackupScreen.kt"
    )

    private val standardContainerScreens = subpageScreens.filter {
        !it.endsWith("IconSettingsScreen.kt")
    }

    @Test
    fun settingsSubpages_useSharedTopBarChromePolicy() {
        subpageScreens.forEach { path ->
            val source = loadSource(path)
            assertTrue(
                source.contains("settingsSubpageTopAppBarColors()"),
                "$path should use settingsSubpageTopAppBarColors()"
            )
        }
    }

    @Test
    fun standardSettingsSubpages_useSharedContainerColor() {
        standardContainerScreens.forEach { path ->
            val source = loadSource(path)
            assertTrue(
                source.contains("settingsSubpageContainerColor()"),
                "$path should use settingsSubpageContainerColor()"
            )
        }
    }

    @Test
    fun settingsSubpages_doNotDeclareInlineTopAppBarColors() {
        val offenders = subpageScreens.filter { path ->
            loadSource(path).contains("TopAppBarDefaults.topAppBarColors(")
        }
        assertTrue(
            offenders.isEmpty(),
            "Settings sub-pages should route top bar colors through SettingsSubpageChromePolicy:\n" +
                offenders.joinToString("\n")
        )
    }

    @Test
    fun settingsSubpageChromePolicy_isCentralized() {
        val source = loadSource(
            "app/src/main/java/com/android/purebilibili/feature/settings/SettingsSubpageChromePolicy.kt"
        )
        assertTrue(source.contains("AppSurfaceTokens.groupedListContainer()"))
        assertFalse(source.contains("cardContainer()"))
    }

    private fun loadSource(path: String): String {
        val normalizedPath = path.removePrefix("app/")
        val sourceFile = listOf(
            File(path),
            File(normalizedPath)
        ).firstOrNull { it.exists() }
        require(sourceFile != null) { "Cannot locate $path from ${File(".").absolutePath}" }
        return sourceFile.readText()
    }
}