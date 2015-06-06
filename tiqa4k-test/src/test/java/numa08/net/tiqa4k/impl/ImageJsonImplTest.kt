package numa08.net.tiqa4k.impl

import android.os.Bundle
import android.os.Parcel
import numa08.net.tiqa4k_test.BuildConfig
import org.hamcrest.CoreMatchers
import org.json.JSONArray
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File
import kotlin.test.assertEquals

RunWith(javaClass<RobolectricTestRunner>())
Config(constants = javaClass<BuildConfig>())
public class ImageJsonImplTest {

    var rawJson : String = """
    [{"id":"1aM","ext":"jpg","height":244,"width":349,"source_url":"http://jan.2chan.net/jun/b/src/1260207203308.jpg"},{"id":"1eb","ext":"jpg","height":171,"width":250,"source_url":"http://jan.2chan.net/jun/b/src/1260357395329.jpg"},{"id":"3pl","ext":"jpg","height":393,"width":333,"source_url":"http://aug.2chan.net/jun/b/src/1299649215376.jpg"},{"id":"17X","ext":"jpg","height":234,"width":250,"source_url":"http://feb.2chan.net/jun/b/src/1260028253082.jpg"},{"id":"3om","ext":"jpg","height":1442,"width":1036,"source_url":"http://mar.2chan.net/jun/b/src/1299601311318.jpg"},{"id":"eG","ext":"jpg","height":352,"width":180,"source_url":"http://jun.2chan.net:81/b/src/1258354976577.jpg"},{"id":"y0","ext":"jpg","height":480,"width":353,"source_url":"http://feb.2chan.net/jun/b/src/1259721162848.jpg"},{"id":"eF","ext":"jpg","height":628,"width":920,"source_url":"http://feb.2chan.net/jun/b/src/1258354830343.jpg"},{"id":"eI","ext":"jpg","height":418,"width":564,"source_url":"http://feb.2chan.net/jun/b/src/1258356479609.jpg"},{"id":"uv","ext":"jpg","height":187,"width":218,"source_url":"http://feb.2chan.net/jun/b/src/1259155602117.jpg"},{"id":"16n","ext":"jpg","height":250,"width":242,"source_url":"http://jan.2chan.net/jun/b/src/1260009727145.jpg"},{"id":"3qZ","ext":"jpg","height":1232,"width":1248,"source_url":"http://apr.2chan.net/jun/b/src/1299673009675.jpg"},{"id":"16p","ext":"jpg","height":648,"width":547,"source_url":"http://jun.2chan.net:81/b/src/1260010024961.jpg"},{"id":"1zI","ext":"jpg","height":459,"width":494,"source_url":"http://dec.2chan.net:81/b/src/1257944541365.jpg"},{"id":"ug","ext":"jpg","height":698,"width":859,"source_url":"http://jun.2chan.net:81/b/src/1259151472085.jpg"},{"id":"5LA","ext":"png","height":250,"width":188,"source_url":"http://morotyo.net/tmp/11796a7bdc2611fbf4582a613db1d5b6.png"},{"id":"W2","ext":"jpg","height":258,"width":436,"source_url":"http://dec.2chan.net:81/may/b/src/1259009939701.jpg"},{"id":"18b","ext":"jpg","height":214,"width":250,"source_url":"http://jun.2chan.net:81/b/src/1260029512115.jpg"},{"id":"227","ext":"jpg","height":244,"width":349,"source_url":"http://aug.2chan.net/jun/b/src/1288385928736.jpg"},{"id":"9C","ext":"jpg","height":244,"width":349,"source_url":"http://feb.2chan.net/jun/b/src/1258125634769.jpg"},{"id":"1de","ext":"jpg","height":214,"width":389,"source_url":"http://apr.2chan.net/jun/b/src/1260349126323.jpg"},{"id":"2vm","ext":"jpg","height":251,"width":234,"source_url":"http://mar.2chan.net/jun/b/src/1289549948861.jpg"},{"id":"2F","ext":"jpg","height":251,"width":234,"source_url":"http://jul.2chan.net/jun/b/src/1287219103053.jpg"},{"id":"c7","ext":"png","height":675,"width":540,"source_url":"http://feb.2chan.net/jun/b/src/1258146634427.png"},{"id":"ce","ext":"jpg","height":395,"width":564,"source_url":"http://feb.2chan.net/jun/b/src/1258159193852.jpg"},{"id":"cr","ext":"jpg","height":221,"width":398,"source_url":"http://feb.2chan.net/jun/b/src/1258171867551.jpg"},{"id":"mt","ext":"jpg","height":377,"width":261,"source_url":"http://apr.2chan.net/may/b/src/1258775384015.jpg"},{"id":"1F2","ext":"jpg","height":316,"width":458,"source_url":"http://apr.2chan.net/may/b/src/1259693028706.jpg"},{"id":"2H9","ext":"jpg","height":250,"width":242,"source_url":"http://aug.2chan.net/jun/b/src/1290014933818.jpg"},{"id":"56C","ext":"jpg","height":221,"width":398,"source_url":"http://aug.2chan.net/jun/b/src/1338379664378.jpg"}]
    """

    Test fun createImageJsonImpl() {
        val json = rawJson.let {JSONArray(it)}.let { it.getJSONObject(0) }
        val image = ImageJsonImpl(json)

        assertEquals("1aM", image.id)
        assertEquals("jpg", image.ext)
        assertEquals(244L, image.height)
        assertEquals(349L, image.width)
        assertEquals("http://jan.2chan.net/jun/b/src/1260207203308.jpg", image.sourceURL)
    }

    Test fun storeParcel() {
        val json = rawJson.let {JSONArray(it)}.let { it.getJSONObject(0) }
        val image = ImageJsonImpl(json)

        val bundle = Bundle()
        bundle.putParcelable("image", image)
        val parcel = Parcel.obtain()
        bundle.writeToParcel(parcel, 0)


        parcel.setDataPosition(0)
        val bundle2 = parcel.readBundle()
        bundle2.setClassLoader(javaClass<ImageJsonImpl>().getClassLoader())
        val extract : ImageJsonImpl = bundle2.getParcelable("image")

        assertEquals(image.id, extract.id)
        assertEquals(image.ext, extract.ext)
        assertEquals(image.height, extract.height)
        assertEquals(image.width, extract.width)
        assertEquals(image.sourceURL, extract.sourceURL)
    }
}