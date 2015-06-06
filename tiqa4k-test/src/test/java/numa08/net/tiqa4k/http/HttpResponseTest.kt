package numa08.net.tiqa4k.http

import numa08.net.tiqa4k_test.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

RunWith(RobolectricTestRunner::class)
Config(constants = BuildConfig::class)
class HttpResponseTest {

    Test fun loadJSONArray() {
        val conn = JSONArrayHttpConnection()
        val resp = HttpResponse(conn)
        val jsonArray = resp.asJSONArray

        assertNotNull(jsonArray)
        assertEquals(jsonArray?.length(), 2)
    }

    Test fun loadJSONObject() {
        val conn = JSONObjectHttpConnection()
        val resp = HttpResponse(conn)
        val jsonObject = resp.asJSONObject

        assertNotNull(jsonObject)
        assertEquals(jsonObject?.getString("id"), "3om")
    }
}

private class JSONArrayHttpConnection : HttpURLConnection(URL("http://google.com")) {
    override fun connect() {}

    override fun usingProxy(): Boolean = false

    override fun disconnect() {}

    override fun getInputStream(): InputStream? {
        return javaClass<JSONArrayHttpConnection>().getClassLoader().getResourceAsStream("test_json_array.json")
    }
}

private class JSONObjectHttpConnection : HttpURLConnection(URL("http://google.com")) {
    override fun connect() {}

    override fun usingProxy(): Boolean = false

    override fun disconnect() {}

    override fun getInputStream(): InputStream? {
        return javaClass<JSONArrayHttpConnection>().getClassLoader().getResourceAsStream("test_json_object.json")
    }
}