package net.ihaha.sunny.base.utils.extensions

import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import net.ihaha.sunny.base.utils.typealiases.UnitHandler


data class ScrollPosition(var index: Int = 0, var top: Int = 0) {
    fun drop() {
        index = 0
        top = 0
    }
}

fun View.toast(
    message: Any,
    duration: Int = Toast.LENGTH_SHORT
) = context?.toast(message, duration)

fun Fragment.toast(
    message: Any,
    duration: Int = Toast.LENGTH_SHORT
) = context?.toast(message, duration)

fun Fragment.alert(
    message: Any,
    dialogTitle: Int? = null,
    okTitle: Int? = null,
    showCancel: Boolean = true,
    cancelTitle: Int? = null,
    cancelHandler: UnitHandler? = null,
    okHandler: UnitHandler? = null
) = context?.alert(message, dialogTitle, okTitle, showCancel, cancelTitle, cancelHandler, okHandler)

fun TextView.clear() {
    this.text = null
    this.setOnClickListener(null)
}

fun ImageView.clear(isOnlyImage: Boolean = false) {
    this.setImageResource(0)
    this.setImageBitmap(null)
    this.setImageDrawable(null)
    if (isOnlyImage) this.setOnClickListener(null)
}

fun Button.clear(isClearText: Boolean = true) {
    if (isClearText) this.text = null
    this.setOnClickListener(null)
}

fun CheckBox.clear() {
    this.setOnCheckedChangeListener(null)
}

fun Toolbar.clear() {
    this.title = null
    this.setNavigationOnClickListener(null)
}

fun AutoCompleteTextView.clear() {
    onFocusChangeListener = null
    setOnEditorActionListener(null)
    setOnClickListener(null)
    setAdapter(null)
}

fun ViewGroup.clear() {
    this.children
        .asSequence()
        .forEach { it.clearView() }
}

fun TabLayout.clear() {
    this.clearOnTabSelectedListeners()
    this.removeAllTabs()
}

fun EditText.clear() {
    setOnEditorActionListener(null)
    onFocusChangeListener = null
    setOnClickListener(null)
}

fun View.clearView() {
    when (this) {
        is ViewGroup -> this.clear()
        is ImageView -> this.clear()
        is Button -> this.clear()
        is AutoCompleteTextView -> this.clear()
        is EditText -> this.clear()
        is TextView -> this.clear()
        is CheckBox -> this.clear()
        is Toolbar -> this.clear()
        is TabLayout -> this.clear()
    }
}

fun View.hide(gone: Boolean = true) {
    visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

// slide the view from below itself to the current position
fun View.slideUp() {
    this.visibility = View.VISIBLE
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        this.height.toFloat(), // fromYDelta
        0f
    )                // toYDelta
    animate.duration = 500
    this.startAnimation(animate)
}

// slide the view from its current position to below itself
fun View.slideDown() {
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        0f, // fromYDelta
        this.height.toFloat()
    ) // toYDelta
    animate.duration = 500
    this.startAnimation(animate)
    this.visibility = View.GONE
}