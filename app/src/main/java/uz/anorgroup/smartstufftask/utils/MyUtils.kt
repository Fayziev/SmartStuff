package uz.anorgroup.smartstufftask.utils

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber

fun <T : ViewBinding> T.scope(f: T.() -> Unit) {
    f(this)
}

fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), message, duration).show()
}


fun EditText.toInt(): Int =
    this.text.toString().toInt()