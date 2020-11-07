package github.hongbeomi.touchmouse

object TouchMouseManager {

    private var options: TouchMouseOption? = null
    private var onTouchMouseOptionChangedListener: OnTouchMouseOptionChangedListener? = null

    fun setOptions(touchMouseOption: TouchMouseOption) {
        options = touchMouseOption
        onTouchMouseOptionChangedListener?.onChangedOptions(options)
    }

    fun clear() {
        options = null
        onTouchMouseOptionChangedListener?.onChangedOptions(options)
    }

    fun getOptions() = options

    fun addOnTouchMouseOptionChangedListener(l: OnTouchMouseOptionChangedListener) {
        this.onTouchMouseOptionChangedListener = l
    }

    fun removeOnTouchMouseOptionChangedListener() {
        this.onTouchMouseOptionChangedListener = null
    }

}