package github.hongbeomi.touchmouse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_drawable.*

class CustomDrawableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_drawable)

        TouchMouseManager.setOptions(
            TouchMouseOption(
                cursorColor = R.color.black,
                cursorDrawable = R.drawable.ic_iron_man
            )
        )

        button_clear.setOnClickListener {
            TouchMouseManager.clear()
        }
    }

}