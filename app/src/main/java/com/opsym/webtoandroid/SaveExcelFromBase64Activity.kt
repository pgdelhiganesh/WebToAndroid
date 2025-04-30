package com.example.webtoandroid

import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class SaveExcelFromBase64Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent?.data
        val fileName = uri?.getQueryParameter("filename") ?: "SaveExcelFromBase64.xlsx"
        val base64Data = uri?.getQueryParameter("data")

        if (!base64Data.isNullOrEmpty()) {
            try {
                val fileBytes = Base64.decode(base64Data, Base64.DEFAULT)
                val file = File(getExternalFilesDir(null), fileName)
                FileOutputStream(file).use { it.write(fileBytes) }

                Toast.makeText(this, "Saved to ${file.absolutePath}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Invalid or empty data", Toast.LENGTH_LONG).show()
        }

        finish()
    }
}
