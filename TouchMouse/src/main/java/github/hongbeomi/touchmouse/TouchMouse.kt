package github.hongbeomi.touchmouse

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.PixelFormat
import android.view.*
import androidx.appcompat.app.AppCompatActivity

class TouchMouse(
    private val context: Context,
    private val touchMouseOption: TouchMouseOption? = null
) : View.OnTouchListener {

    private var windowManager: WindowManager? = null
    private var touchView: TouchMouseView? = null

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpLayout() {

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )

        windowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
        touchView = TouchMouseView(context, touchMouseOption = touchMouseOption)

        windowManager?.addView(touchView, params)
        touchView?.setOnTouchListener(this)
    }

    fun build() {
        setUpLayout()
    }

    fun doOnPreBuild() {
        if (windowManager != null && touchView != null) {
            windowManager?.removeViewImmediate(touchView)
            windowManager = null
            touchView = null
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        (context as AppCompatActivity).dispatchTouchEvent(p1)
        return false
    }

}