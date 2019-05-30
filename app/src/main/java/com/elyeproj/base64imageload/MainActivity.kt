package com.elyeproj.base64imageload

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import decodeBase64IntoBitmap
import encodeImageIntoBase64
import kotlinx.android.synthetic.main.activity_main.*
import logLongMessage
import readRawTextFile

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rawString = readRawTextFile(R.raw.test_image)
        rawString.logLongMessage()

        val decodedBitamp = rawString.decodeBase64IntoBitmap()
        text_image.setImageBitmap(decodedBitamp)

        val encodeString = encodeImageIntoBase64(R.drawable.test_image, Bitmap.CompressFormat.JPEG)
        encodeString.logLongMessage()

        encode_image.setImageBitmap(encodeString.decodeBase64IntoBitmap())
    }
}
