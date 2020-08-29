package com.example.huparidiary

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection
public class  imageupload {
    private val CLIENT_ID = "62865de1937fd8c"
 var urll:String="vv";
    public fun uploadImageToImgur(image: Bitmap) :String{
        getBase64Image(image, complete = { base64Image ->
            GlobalScope.launch(Dispatchers.Default) {
                var url = URL("https://api.imgur.com/3/image")

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
                    Log.i("edddd", "uploadImageToImgur: "+body)
                    outputStreamWriter.flush()
                    val response = httpsURLConnection.inputStream.bufferedReader()
                            .use { it.readText() }  // defaults to UTF-8
                    val jsonObject = JSONTokener(response).nextValue() as JSONObject
                    val data = jsonObject.getJSONObject("data")
                    Log.d("TAG", "Link is : ${data.getString("link")}")
                     urll=data.getString("link");
                    try {
                      var  doc = Jsoup.connect("https://mibtechnologies.in/hupariapp/uploadCategory.php?uid=cggfdg&catname=lolwafsdsdgf&catimage=${data.getString("link")}").get()  // <2>

                    }catch (e: Exception){

                    }

                }

            }

        })
        return urll


    }

    private fun getBase64Image(image: Bitmap, complete: (String) -> Unit) {
        GlobalScope.launch {
            val outputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            val b = outputStream.toByteArray()
            complete(Base64.encodeToString(b, Base64.DEFAULT))
        }
    }
}