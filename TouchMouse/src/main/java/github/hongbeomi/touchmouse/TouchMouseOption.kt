package github.hongbeomi.touchmouse

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class TouchMouseOption(
    @ColorRes
    val cursorColor: Int? = null,
    @DrawableRes
    val cursorDrawable: Int? = null
)