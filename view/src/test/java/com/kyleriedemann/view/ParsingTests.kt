package com.kyleriedemann.view

import org.junit.Test

import org.junit.Assert.*

/**
 * Examples using [parseFromViewToString] to convert the output from the [toString] method on [View]
 * to convert the output to something resembling human readable JSON
 */
class ParsingTests {
    private val coordinatorLayoutToString = "android.support.design.widget.CoordinatorLayout{9ef9382 V.E...... .......D 0,0-1080,1731}"
    private val coordinatorOutput = """{"View": "android.support.design.widget.CoordinatorLayout", "HashCode": "9ef9382", "Rect": { "Left": 0, "Top": 0, "Right": 1080, "Bottom": 1731 }, "Flags": {"Visible": "Visible", "Focusable": false, "Enabled": true, "Will Draw": true, "Showing Horizontal Scrollbars": false, "Showing Vertical Scrollbars": false, "Clickable": false, "Long Clickable": false, "Context Clickable": false, "Is Root Namespace": false, "Focused": false, "Selected": false, "Pressed": "Neither", "Hovered": false, "Activated": false, "Invalidated": false, "Is Dirty": true}}"""

    @Test
    fun coordinatorLayout() {
        val actual = coordinatorLayoutToString.parseFromViewToString()
        assertEquals(coordinatorOutput, actual)
    }

    private val bottomNavigationViewToString = "com.google.android.material.bottomnavigation.BottomNavigationView{f9cf5d1 V.E...... ......ID 0,0-0,0 #7f0800be app:id/navigation}"
    private val bottomNavOutput = """{"View": "com.google.android.material.bottomnavigation.BottomNavigationView", "HashCode": "f9cf5d1", "Rect": { "Left": app:id/navigation, "Top": app:id/navigation, "Right": app:id/navigation, "Bottom": app:id/navigation }, "Flags": {"Visible": "Visible", "Focusable": false, "Enabled": true, "Will Draw": true, "Showing Horizontal Scrollbars": false, "Showing Vertical Scrollbars": false, "Clickable": false, "Long Clickable": false, "Context Clickable": false, "Is Root Namespace": false, "Focused": false, "Selected": false, "Pressed": "Neither", "Hovered": false, "Activated": false, "Invalidated": true, "Is Dirty": true}}"""

    @Test
    fun bottomNavigationView() {
        val actual = bottomNavigationViewToString.parseFromViewToString()
        assertEquals(bottomNavOutput, actual)
    }

    private val constraintLayoutToString = "androidx.constraintlayout.widget.ConstraintLayout{9608836 V.E...... ......I. 0,0-0,0 #7f08005d app:id/container}"
    private val constraintOutput = """{"View": "androidx.constraintlayout.widget.ConstraintLayout", "HashCode": "9608836", "Rect": { "Left": app:id/container, "Top": app:id/container, "Right": app:id/container, "Bottom": app:id/container }, "Flags": {"Visible": "Visible", "Focusable": false, "Enabled": true, "Will Draw": true, "Showing Horizontal Scrollbars": false, "Showing Vertical Scrollbars": false, "Clickable": false, "Long Clickable": false, "Context Clickable": false, "Is Root Namespace": false, "Focused": false, "Selected": false, "Pressed": "Neither", "Hovered": false, "Activated": false, "Invalidated": true, "Is Dirty": false}}"""

    @Test
    fun constraintLayout() {
        val actual = constraintLayoutToString.parseFromViewToString()
        assertEquals(constraintOutput, actual)
    }
}
