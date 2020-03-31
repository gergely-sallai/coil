@file:OptIn(ExperimentalCoilApi::class)

package coil.target

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import coil.annotation.ExperimentalCoilApi
import coil.transition.TransitionTarget

/** A [Target] that handles setting images on an any [View]. */
abstract class GenericViewTarget<T : View> : PoolableViewTarget<T>, TransitionTarget<T>, DefaultLifecycleObserver {

    /** Function that can be used to apply drawables differently in implementations. **/
    abstract fun applyDrawable(drawable: Drawable?)

    private var isStarted = false

    override fun onStart(placeholder: Drawable?) = setDrawable(placeholder)

    override fun onSuccess(result: Drawable) = setDrawable(result)

    override fun onError(error: Drawable?) = setDrawable(error)

    override fun onClear() = setDrawable(null)

    override fun onStart(owner: LifecycleOwner) {
        isStarted = true
        updateAnimation()
    }

    override fun onStop(owner: LifecycleOwner) {
        isStarted = false
        updateAnimation()
    }

    /** Replace the [View]'s current drawable with [drawable]. */
    protected open fun setDrawable(newDrawable: Drawable?) {
        (drawable as? Animatable)?.stop()
        applyDrawable(newDrawable)
        updateAnimation()
    }

    /** Start/stop the current [Drawable]'s animation based on the current lifecycle state. */
    protected open fun updateAnimation() {
        val animatable = drawable as? Animatable ?: return
        if (isStarted) animatable.start() else animatable.stop()
    }
}
