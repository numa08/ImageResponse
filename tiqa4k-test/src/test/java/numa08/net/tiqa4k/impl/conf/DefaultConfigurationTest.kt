package numa08.net.tiqa4k.impl.conf

import android.os.Bundle
import android.os.Parcel
import numa08.net.tiqa4k.conf.Configuration
import numa08.net.tiqa4k.impl.ImageJsonImpl
import numa08.net.tiqa4k_test.BuildConfig
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

RunWith(RobolectricTestRunner::class)
Config(constants = BuildConfig::class)
class DefaultConfigurationTest {

    var config : Configuration? = null

    Before fun initConfig() {
        config = DefaultConfiguration()
    }

    Test fun storeParcel() {
        val bundle = Bundle()
        bundle.putParcelable("config", config)
        val parcel = Parcel.obtain()
        bundle.writeToParcel(parcel, 0)


        parcel.setDataPosition(0)
        val bundle2 = parcel.readBundle()
        bundle2.setClassLoader(javaClass<ImageJsonImpl>().getClassLoader())
        val extract : Configuration = bundle2.getParcelable("config")

        assertEquals(config?.endpointURL, extract.endpointURL)
        assertEquals(config?.getImagesPath, extract.getImagesPath)
        assertEquals(config?.searchNewestPath, extract.searchNewestPath)
        assertEquals(config?.searchRandomPath, extract.searchRandomPath)
    }
}