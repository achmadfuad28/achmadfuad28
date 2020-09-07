@file:JvmName("Toaster")

package com.framework.common.extensions

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.framework.R


/**
 * Toaster
 * Extension to display the Toast message directly from Context, Activity, and Fragment
 */

fun Fragment.showToast(@StringRes stringResource: Int) = context?.showToast(stringResource)

fun Fragment.showToast(message: String) = context?.showToast(message)

fun Context.showToast(@StringRes stringResource: Int) = showToast(getString(stringResource))

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.showLongToast(@StringRes stringResource: Int) = context?.showLongToast(stringResource)

fun Fragment.showLongToast(message: String) = context?.showLongToast(message)

fun Context.showLongToast(@StringRes stringResource: Int) = showLongToast(getString(stringResource))

fun Context.showLongToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showSnackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setTextColor(android.R.color.white).show()

fun Context.showSnackbar(view: View, @StringRes stringResource: Int) = Snackbar.make(view, stringResource, Snackbar.LENGTH_SHORT).setTextColor(android.R.color.white).show()

fun Fragment.showSnackbar(view: View, @StringRes stringResource: Int) = context?.showSnackbar(view, stringResource)

fun Fragment.showSnackbar(view: View, message: String) = context?.showSnackbar(view, message)

fun Context.showLongSnackbar(view: View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_LONG).setTextColor(android.R.color.white).show()

fun Context.showLongSnackbar(view: View, @StringRes stringResource: Int) = Snackbar.make(view, stringResource, Snackbar.LENGTH_LONG).setTextColor(android.R.color.white).show()

fun Fragment.showLongSnackbar(view: View, message: String) = context?.showLongSnackbar(view, message)

fun Fragment.showLongSnackbar(view: View, @StringRes stringResource: Int) = context?.showLongSnackbar(view, stringResource)

fun Snackbar.setTextColor(color: Int): Snackbar {
    val tv = view.findViewById(R.id.snackbar_text) as TextView
    tv.setTextColor(color)

    return this
}