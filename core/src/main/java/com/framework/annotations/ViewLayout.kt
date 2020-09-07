package com.framework.annotations

import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import android.view.View

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewLayout(@LayoutRes @NonNull val value: Int = View.NO_ID)