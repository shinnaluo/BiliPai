package com.android.purebilibili.navigation3

import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class BiliPaiNavDisplayHostStructureTest {

    @Test
    fun navDisplayHostOwnsNavigation3RenderingAndSharedTransitionScope() {
        val source = navDisplayHostSource()

        assertTrue(source.contains("NavDisplay("))
        assertTrue(source.contains("entryProvider"))
        assertTrue(source.contains("LocalNavAnimatedContentScope.current"))
        assertTrue(source.contains("ProvideAnimatedVisibilityScope("))
        assertTrue(source.contains("LocalVideoCardSharedElementSourceRoute provides key.toLegacyRoute()"))
        assertTrue(source.contains("sharedTransitionScope = sharedTransitionScope"))
        assertTrue(source.contains("predictivePopTransitionSpec"))
    }

    @Test
    fun navDisplayHostScopesEntryStateWithLifecycleNavigation3Decorator() {
        val source = navDisplayHostSource()
        val buildFile = buildFileSource()

        assertTrue(buildFile.contains("androidx.lifecycle:lifecycle-viewmodel-navigation3:"))
        assertTrue(source.contains("rememberSaveableStateHolderNavEntryDecorator"))
        assertTrue(source.contains("rememberViewModelStoreNavEntryDecorator"))
    }

    @Test
    fun navDisplayHostPreservesApplicationExtrasForEntryViewModels() {
        val source = navDisplayHostSource()

        assertTrue(source.contains("ProvideNavigation3ViewModelApplicationExtras("))
        assertTrue(source.contains("LocalViewModelStoreOwner provides patchedOwner"))
        assertTrue(source.contains("APPLICATION_KEY"))
    }

    @Test
    fun navDisplayHostDoesNotRegisterClassicBackInterceptor() {
        val source = navDisplayHostSource()

        assertTrue(source.contains("NavDisplay("))
        assertTrue(source.contains("onBack = onBack"))
        kotlin.test.assertFalse(source.contains("import androidx.activity.compose.BackHandler"))
        kotlin.test.assertFalse(source.contains("BackHandler("))
    }

    private fun navDisplayHostSource(): String {
        return listOf(
            File("app/src/main/java/com/android/purebilibili/navigation3/BiliPaiNavDisplayHost.kt"),
            File("src/main/java/com/android/purebilibili/navigation3/BiliPaiNavDisplayHost.kt")
        ).first { it.exists() }.readText()
    }

    private fun buildFileSource(): String {
        return listOf(
            File("app/build.gradle.kts"),
            File("build.gradle.kts")
        ).first { it.exists() }.readText()
    }
}
