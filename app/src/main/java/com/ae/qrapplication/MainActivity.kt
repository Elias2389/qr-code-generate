package com.ae.qrapplication

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var createQrButton: Button
    private lateinit var imageQrView: ImageView

    private val QR_IMAGE_SIZE: Int = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        fetchQrCode()
    }

    private fun setupViews() {
        createQrButton = findViewById(R.id.button)
        imageQrView = findViewById(R.id.imageView_qr)
    }

    private fun fetchQrCode() {
        createQrButton.setOnClickListener {
            setupBitmapQ()
        }
    }

    private fun setupBitmapQ() {
      try {
          getBitMatrix().let {
              val bitmap = createBitmap(it)
              imageQrView.setImageBitmap(bitmap)
          }
      } catch (e: Exception) {
          e.printStackTrace()
      }
    }

    private fun getBitMatrix(): BitMatrix {
        return MultiFormatWriter()
            .encode(
                "Test to generate bar code",
                BarcodeFormat.QR_CODE,
                QR_IMAGE_SIZE,
                QR_IMAGE_SIZE
            )
    }

    private fun createBitmap(bitMatrix: BitMatrix): Bitmap {
        return BarcodeEncoder().createBitmap(bitMatrix)
    }
}