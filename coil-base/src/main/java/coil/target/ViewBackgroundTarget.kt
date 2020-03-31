@file:OptIn(ExperimentalCoilApi::class)

package coil.target

import android.graphics.drawable.Drawable
import android.view.View
import coil.annotation.ExperimentalCoilApi

/** A [Target] that handles setting images as background of a [View]. */
open class ViewBackgroundTarget(
    override val view: View
) : GenericViewTarget<View>() {
    override fun applyDrawable(drawable: Drawable?) {
        view.background = drawable
    }

    override val drawable: Drawable?
        get() = view.background
}
