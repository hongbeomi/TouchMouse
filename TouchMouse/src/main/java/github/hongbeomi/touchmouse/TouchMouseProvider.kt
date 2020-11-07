package github.hongbeomi.touchmouse

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.annotation.GuardedBy

class TouchMouseProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        Log.d("hongbeomi", "touchMouseProvider initialization successful")
        setUpTouchMouseView(context as Application)
        return true
    }

    private fun setUpTouchMouseView(application: Application) {
        application.registerActivityLifecycleCallbacks(
            TouchMouseActivityLifecycleCallback()
        )
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        throw UnsupportedOperationException()
    }

    override fun getType(p0: Uri): String? {
        throw UnsupportedOperationException()
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        throw UnsupportedOperationException()
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        throw UnsupportedOperationException()
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        throw UnsupportedOperationException()
    }

}