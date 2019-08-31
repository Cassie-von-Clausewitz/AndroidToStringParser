package com.kyleriedemann.view

import android.view.View

fun View.humanReadableToString() = parseStringFromView(this.toString())

fun String.parseFromViewToString() : String {
    return parseStringFromView(this)
}

private fun parseStringFromView(input: String) : String {
    val className = input.removeRange(input.indexOfFirst { ch -> ch == '{' }, input.length)

    var subString = input.removeRange(0, input.indexOfFirst { ch -> ch == '{' })
    subString = subString.removePrefix("{")
    subString = subString.removeSuffix("}")

    val hashCode = subString.substringBefore(" ")

    var rect = subString.substringAfterLast(" ")
    val left = rect.substringBefore(",")
    val top = rect.substringAfter(",").substringBefore("-")
    val right = rect.substringBeforeLast(",").substringAfter("-")
    val bottom = rect.substringAfterLast(",")

    rect = "\"Left\": $left, \"Top\": $top, \"Right\": $right, \"Bottom\": $bottom"

    val flags = subString.subSequence(
            subString.indexOfFirst { ch -> ch == ' ' },
            subString.indexOfLast { ch -> ch == ' ' }
    ).filterNot { ch -> ch == ' ' }.trim()

    var visible = when (flags[0]) {
        'V' -> "Visible"
        'I' -> "Invisible"
        'G' -> "Gone"
        else -> "?"
    }
    visible = "\"Visible\": \"$visible\""

    val focusable = "\"Focusable\": ${flags[1] == 'F'}"

    val enabled = "\"Enabled\": ${flags[2] == 'E'}"

    val willDraw = "\"Will Draw\": ${flags[3] != 'D'}"

    val showingHorizontalScrollbars = "\"Showing Horizontal Scrollbars\": ${flags[4] == 'H'}"

    val showingVerticalScrollbars = "\"Showing Vertical Scrollbars\": ${flags[5] == 'V'}"

    val clickable = "\"Clickable\": ${flags[6] == 'C'}"

    val longClickable = "\"Long Clickable\": ${flags[7] == 'L'}"

    val contextClickable = "\"Context Clickable\": ${flags[8] == 'X'}"

    val isRootNamespace = "\"Is Root Namespace\": ${flags[9] == 'R'}"

    val focused = "\"Focused\": ${flags[10] == 'F'}"

    val selected = "\"Selected\": ${flags[11] == 'S'}"

    var pressed = when (flags[12]) {
        'p' -> "Prepressed"
        'P' -> "Pressed"
        else -> "Neither"
    }
    pressed = "\"Pressed\": \"$pressed\""

    val hovered = "\"Hovered\": ${flags[13] == 'H'}"

    val activated = "\"Activated\": ${flags[14] == 'A'}"

    val invalidated = "\"Invalidated\": ${flags[15] == 'I'}"

    val dirty = "\"Is Dirty\": ${flags[16] == 'D'}"

    val viewFlags = listOf(
            visible, focusable, enabled, willDraw,
            showingHorizontalScrollbars, showingVerticalScrollbars,
            clickable, longClickable, contextClickable, isRootNamespace,
            focused, selected, pressed, hovered, activated, invalidated, dirty
    )

    val sb = StringBuilder()
    sb.append("{")
    sb.append("\"View\": \"$className\", ")
    sb.append("\"HashCode\": \"$hashCode\", ")
    sb.append("\"Rect\": { $rect }, ")
    sb.append("\"Flags\": {")
    viewFlags.forEachIndexed { index, s ->
        run {
            sb.append(s)
            if (index != viewFlags.lastIndex) {
                sb.append(", ")
            }
        }
    }
    sb.append("}")
    sb.append("}")

    return sb.toString()
}
