package com.android.purebilibili.feature.list

import com.android.purebilibili.core.store.HomeSettings
import com.android.purebilibili.core.theme.UiPreset

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HistoryFilterTabChromePolicyTest {
    @Test
    fun liquidDock_usesBottomBarMatchedSizingWhenGlobalLiquidGlassEnabled() {
        val spec = resolveHistoryFilterTabChromeSpec(
            homeSettings = HomeSettings(androidNativeLiquidGlassEnabled = true),
            uiPreset = UiPreset.IOS
        )

        assertTrue(spec.useLiquidDock)
        assertEquals(HISTORY_FILTER_LIQUID_DOCK_HEIGHT_DP, spec.heightDp)
        assertEquals(HISTORY_FILTER_LIQUID_DOCK_INDICATOR_HEIGHT_DP, spec.indicatorHeightDp)
        assertEquals(HISTORY_FILTER_LIQUID_DOCK_LABEL_FONT_SIZE_SP, spec.labelFontSizeSp)
        assertEquals(null, spec.itemWidthDp)
        assertTrue(spec.dragSelectionEnabled)
    }

    @Test
    fun liquidDock_disabledWhenGlobalLiquidGlassReuseOff() {
        val spec = resolveHistoryFilterTabChromeSpec(
            homeSettings = HomeSettings(androidNativeLiquidGlassEnabled = false),
            uiPreset = UiPreset.IOS
        )

        assertFalse(spec.useLiquidDock)
        assertFalse(spec.dragSelectionEnabled)
    }

    @Test
    fun itemWidth_scalesWithFilterCount() {
        assertEquals(56, resolveHistoryFilterTabItemWidthDp(filterCount = 5))
        assertEquals(60, resolveHistoryFilterTabItemWidthDp(filterCount = 4))
        assertEquals(66, resolveHistoryFilterTabItemWidthDp(filterCount = 3))
    }
}