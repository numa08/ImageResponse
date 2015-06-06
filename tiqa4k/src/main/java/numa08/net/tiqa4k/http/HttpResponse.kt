package numa08.net.tiqa4k.http

import numa08.net.tiqa4k.TiqavException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection

class HttpResponse(private val conn : HttpURLConnection) {


    fun getResponseHeader(name : String) : String? {
        return conn.getHeaderField(name)
    }

    fun dissconnect() {
        conn.disconnect()
    }

    val asString : String?
        get() {
            if (responseAsString != null) {
                return responseAsString
            }
            var stream : InputStream? = null
            var br : BufferedReader? = null
            val retval : String? = try {
                stream = conn.getInputStream()
                br = BufferedReader(InputStreamReader(stream, "UTF-8"))
                val buf = StringBuffer()
                while(true) {
                    val line = br.readLine() ?: break
                    buf.append(line).append("\n")
                }
                buf.toString()
            } catch (ex : IOException) {
                throw TiqavException("can not read response", ex)
            } finally {
                stream?.close()
                br?.close()
                dissconnect()
            }
            responseAsString = retval
            return retval
        }

    val asJSONObject : JSONObject?
        get() {
            if(responseAsJSONObject != null) {
                return responseAsJSONObject
            }
            val resp = asString
            val jsonObject = resp.let {
                try {
                    JSONObject(it)
                } catch(ex : JSONException) {
                    throw TiqavException("can not parse json", ex)
                }
            }
            responseAsJSONObject = jsonObject
            return responseAsJSONObject
        }

    val asJSONArray : JSONArray?
        get() {
            if(responseAsJSONArray != null) {
                return responseAsJSONArray
            }
            val resp = asString
            val jsonArray = resp.let {
                try {
                    JSONArray(it)
                } catch(ex : JSONException) {
                    throw TiqavException("can not parse json", ex)
                }
            }
            responseAsJSONArray = jsonArray
            return responseAsJSONArray
        }

    private var responseAsString : String? = null
    private var responseAsJSONObject : JSONObject? = null
    private var responseAsJSONArray : JSONArray? = null
}