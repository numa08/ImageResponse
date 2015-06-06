package test

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class JSONArrayHttpConnection : HttpURLConnection(URL("http://google.com")) {
    override fun connect() {}

    override fun usingProxy(): Boolean = false

    override fun disconnect() {}

    override fun getInputStream(): InputStream? {
        return javaClass<JSONArrayHttpConnection>().getClassLoader().getResourceAsStream("test_json_array.json")
    }
}

class JSONObjectHttpConnection : HttpURLConnection(URL("http://google.com")) {
    override fun connect() {}

    override fun usingProxy(): Boolean = false

    override fun disconnect() {}

    override fun getInputStream(): InputStream? {
        return javaClass<JSONArrayHttpConnection>().getClassLoader().getResourceAsStream("test_json_object.json")
    }
}