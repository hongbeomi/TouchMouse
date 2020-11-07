package github.hongbeomi.touchmouse

import android.app.Activity
import android.app.Application
import android.os.Bundle

class TouchMouseActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {

    private var touchMouse: TouchMouse? = null
    private val touchMouseManager = TouchMouseManager

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {}
    override fun onActivityStarted(p0: Activity) {}

    override fun onActivityResumed(p0: Activity) {
        initialTouchMouse(p0)

        TouchMouseManager.addOnTouchMouseOptionChangedListener(
            object : OnTouchMouseOptionChangedListener {
                override fun onChangedOptions(option: TouchMouseOption?) {
                    initialTouchMouse(p0)
                }
            }
        )
    }

    override fun onActivityPaused(p0: Activity) {
        TouchMouseManager.removeOnTouchMouseOptionChangedListener()
    }

    override fun onActivityStopped(p0: Activity) {}
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
    override fun onActivityDestroyed(p0: Activity) {}

    private fun initialTouchMouse(activity: Activity) {
        touchMouse?.doOnPreBuild()
        touchMouse = TouchMouse(activity, touchMouseManager.getOptions())
        touchMouse?.build()
    }

}