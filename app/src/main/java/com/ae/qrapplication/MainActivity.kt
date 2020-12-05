package com.ae.qrapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var createQrButton: Button
    private lateinit var imageQrView: ImageView

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
            generateQrCode()
        }
    }


    private fun generateQrCode() {
      try {
          val multiFormatWriter = MultiFormatWriter()
          val bitMatrix = multiFormatWriter.encode("Andres test", BarcodeFormat.QR_CODE, 500, 500)
          val barcodeEncoder = BarcodeEncoder()
          val bitmap = barcodeEncoder.createBitmap(bitMatrix)

          imageQrView.setImageBitmap(bitmap)
      } catch (e: Exception) {
          e.printStackTrace()
      }
    }

}