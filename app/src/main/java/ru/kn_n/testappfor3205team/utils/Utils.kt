package ru.kn_n.testappfor3205team.utils

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import okhttp3.ResponseBody
import java.io.File
import java.io.IOException

val String.Companion.EMPTY: String
    get() = ""

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showOrHide(isVisible: Boolean) {
    if (isVisible) this.show()
    else this.hide()
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun prepareToWritingFile(outputFileName: String, context: Context): Uri {
    val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    } else {
        Uri.fromFile(
            File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                ).toString() + "/$outputFileName"
            )
        )
    }
    val values = ContentValues().apply {
        put(MediaStore.Downloads.DISPLAY_NAME, outputFileName)
    }
    return context.contentResolver.insert(collection, values)
        ?: throw IOException("MediaStore Uri couldn't be created")
}

fun ResponseBody.writeToStream(file: Uri, context: Context) {
    val outputStream = context.contentResolver.openOutputStream(file)
        ?: throw IOException("ContentResolver couldn't open $file outputStream")
    use { outputStream.use { byteStream().copyTo(it) } }
    Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show()
}