package numa08.net.tiqa4k.impl.conf

import android.os.Parcel
import android.os.Parcelable
import numa08.net.tiqa4k.conf.Configuration
import java.util.*

class DefaultConfiguration : Configuration {

    override val endpointURL: String

    override val searchPath: String

    override val searchNewestPath: String

    override val searchRandomPath: String

    override val getImagesPath: String


    constructor() {
        endpointURL = "http://api.tiqav.com"
        searchPath = "search.json"
        searchNewestPath = "search/newest.json"
        searchRandomPath = "search/random.json"
        getImagesPath = "images"
    }

    private constructor(source : Parcel) {
        endpointURL = source.readString()
        searchPath = source.readString()
        searchNewestPath = source.readString()
        searchRandomPath = source.readString()
        getImagesPath = source.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(endpointURL)
        dest.writeString(searchPath)
        dest.writeString(searchNewestPath)
        dest.writeString(searchRandomPath)
        dest.writeString(getImagesPath)
    }

    override fun describeContents(): Int = 0

    companion object creator {
        val CREATOR : Parcelable.Creator<DefaultConfiguration> = object : Parcelable.Creator<DefaultConfiguration> {
            override fun newArray(size: Int): Array<out DefaultConfiguration>? = Array(size, { i -> DefaultConfiguration()})

            override fun createFromParcel(source: Parcel): DefaultConfiguration? = DefaultConfiguration(source)
        }
    }
}