package com.example.huparidiary

import android.graphics.Bitmap
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private val CLIENT_ID = "62865de1937fd8c"

private fun uploadImageToImgur(image: Bitmap) {
    getBase64Image(image, complete = { base64Image ->
        GlobalScope.launch(Dispatchers.Default) {
            val url = URL("https://api.imgur.com/3/image")

            val boundary = "Boundary-${System.currentTimeMillis()}"

            val httpsURLConnection =
                    withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }
            httpsURLConnection.setRequestProperty("Authorization", "Client-ID $CLIENT_ID")
            httpsURLConnection.setRequestProperty(
                    "Content-Type",
                    "multipart/form-data; boundary=$boundary"
            )

            httpsURLConnection.requestMethod = "POST"
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = true

            var body = ""
            body += "--$boundary\r\n"
            body += "Content-Disposition:form-data; name=\"image\""
            body += "\r\n\r\n$base64Image\r\n"
            body += "--$boundary--\r\n"


            val outputStreamWriter = OutputStreamWriter(httpsURLConnection.outputStream)
            withContext(Dispatchers.IO) {
                outputStreamWriter.write(body)
                outputStreamWriter.flush()
            }


            // ...

        }

    })




}
private fun getBase64Image(image: Bitmap, complete: (String) -> Unit) {
    GlobalScope.launch {
        val outputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val b = outputStream.toByteArray()
        complete(Base64.encodeToString(b, Base64.DEFAULT))
    }
}
