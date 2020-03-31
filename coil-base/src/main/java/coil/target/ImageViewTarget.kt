@file:OptIn(ExperimentalCoilApi::class)

package coil.target

import android.graphics.drawable.Drawable
import android.widget.ImageView
import coil.annotation.ExperimentalCoilApi

/** A [Target] that handles setting images on an [ImageView]. */
open class ImageViewTarget(
    override val view: ImageView
) : GenericViewTarget<ImageView>() {

    override fun applyDrawable(drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }

    override val drawable: Drawable?
        get() = view.drawable
}
