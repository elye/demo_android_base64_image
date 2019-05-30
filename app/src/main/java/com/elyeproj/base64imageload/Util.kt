import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.annotation.DrawableRes
import android.util.Base64
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader


fun Context.readRawTextFile(id: Int): String {
    val stringBuffer = StringBuffer()
    val inputStream = resources.openRawResource(id)
    val reader = BufferedReader(InputStreamReader(inputStream))
    try {
        var line = reader.readLine()
        while (line != null) {
            stringBuffer.append(line)
            stringBuffer.append('\n')
            line = reader.readLine()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return stringBuffer.toString()
}

fun String.logLongMessage() {
    val maxLogSize = 4000
    for (i in 0..length / maxLogSize) {
        val start = i * maxLogSize
        var end = (i + 1) * maxLogSize
        end = if (end > length) length else end
        android.util.Log.d("MYLOG", substring(start, end))
    }
}

fun String.decodeBase64IntoBitmap(): Bitmap {
    val imageBytes = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun Bitmap.encodeBitmapIntoBase64(compressFormat: Bitmap.CompressFormat, quality: Int = 0): String {
    val baseArrayOutputStream = ByteArrayOutputStream()
    compress(compressFormat, quality, baseArrayOutputStream)
    val imageBytes = baseArrayOutputStream.toByteArray()
    return Base64.encodeToString(imageBytes, Base64.DEFAULT)
}

fun Context.encodeImageIntoBase64(@DrawableRes resourceId: Int,
                                  compressFormat: Bitmap.CompressFormat,
                                  quality: Int = 0): String {
    val bitmap = BitmapFactory.decodeResource(resources, resourceId)
    return bitmap.encodeBitmapIntoBase64(compressFormat, quality)
}

