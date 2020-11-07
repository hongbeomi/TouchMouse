package github.hongbeomi.touchmouse

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.doOnPreDraw


class TouchMouseView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val touchMouseOption: TouchMouseOption? = null
) : View(context) {

    private var mPosX = 0f
    private var mPosY = 0f

    private var cursorPaint = Paint()
    private var cursorPaintAlpha = CURSOR_PAINT_ALPHA
    private var cursorColor = Color.CYAN
    private var cursorCircleRadius = CURSOR_CIRCLE_RADIUS
    private var cursorCustomDrawable: Drawable? = null

    private var isDrawableMode = false
    private var isEndTouchEvent = false
    private var startTouchTracking = false

    private val fadeOutAnimator = ValueAnimator.ofInt(cursorPaintAlpha, 0)

    init {
        setCursorCustomDrawable()
        setFadeOutAnimator()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        startTouchTracking = true
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> onActionDown(event)
            MotionEvent.ACTION_MOVE -> onActionMove(event)
            MotionEvent.ACTION_UP -> {
                isEndTouchEvent = true
                fadeOutAnimator.start()
            }
        }
        return false
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!startTouchTracking) return

        setUpPaint()

        canvas?.apply {
            save()

            fun drawCustomDrawable() {
                if (!isEndTouchEvent) {
                    cursorCustomDrawable?.let {
                        val bitmap = it.toBitmap()
                        val startX = mPosX - bitmap.width / 2
                        val startY = mPosY - bitmap.height / 2
                        this.drawBitmap(bitmap, startX, startY, null)
                    }
                }
            }

            fun drawCircleCursor() {
                drawCircle(mPosX, mPosY, cursorCircleRadius, cursorPaint)
            }

            if (isDrawableMode) {
                drawCustomDrawable()
            } else {
                drawCircleCursor()
            }

            restore()
        }
    }

    private fun setCursorCustomDrawable() {
        touchMouseOption?.cursorDrawable?.let {
            cursorCustomDrawable = ContextCompat.getDrawable(context, it)
            isDrawableMode = true
        } ?: run { isDrawableMode = false }
    }

    private fun onActionDown(event: MotionEvent) {
        isEndTouchEvent = false
        cursorPaintAlpha = CURSOR_PAINT_ALPHA
        mPosX = event.rawX
        mPosY = event.rawY
        postInvalidate()
    }

    private fun onActionMove(event: MotionEvent) {
        val dx = event.rawX - mPosX
        val dy = event.rawY - mPosY
        mPosX += dx
        mPosY += dy
        postInvalidate()
    }

    private fun setUpPaint() {
        cursorPaint.apply {
            isAntiAlias = true
            color = getPaintColor()
            alpha = cursorPaintAlpha
        }
    }

    private fun getPaintColor(): Int {
        touchMouseOption?.cursorColor?.let {
            return it
        } ?: return cursorColor
    }


    private fun setFadeOutAnimator() {
        fadeOutAnimator.apply {
            duration = FADE_OUT_DURATION
            interpolator = LinearInterpolator()
            addUpdateListener {
                cursorPaintAlpha -= 10
                postInvalidate()
            }
        }
    }

    companion object {
        const val CURSOR_PAINT_ALPHA = 200
        const val FADE_OUT_DURATION = 500L
        const val CURSOR_CIRCLE_RADIUS = 50f
    }

}