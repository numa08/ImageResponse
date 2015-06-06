package numa08.net.tiqa4k.impl

import android.os.Parcel
import android.os.Parcelable
import numa08.net.tiqa4k.Image
import org.json.JSONObject

open class ImageJsonImpl : Image {

    //{"id":"3om","ext":"jpg","height":1442,"width":1036,"source_url":"http://example.com/image1.jpg"}

    override var id: String
    override var ext: String
    override var height: Long
    override var width: Long
    override var sourceURL: String
    override val originalURL: String
        get() = "http://img.tiqav.com/${id}.${ext}"
    override val thumbnailURL: String
        get() = "http://img.tiqav.com/${id}.th.jpg"
    override val glitchURL: String
        get() = "http://img.tiqav.com/${id}.glitch"

    constructor() {
        id = ""
        ext = ""
        height = 0
        width = 0
        sourceURL = ""
    }

    constructor(json : JSONObject) {
        id = json.getString("id")
        ext = json.getString("ext")
        height = json.getLong("height")
        width = json.getLong("width")
        sourceURL = json.getString("source_url")
    }

    private constructor(source : Parcel) {
        id = source.readString()
        ext = source.readString()
        height = source.readLong()
        width = source.readLong()
        sourceURL = source.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(ext)
        dest.writeLong(height)
        dest.writeLong(width)
        dest.writeString(sourceURL)
    }

    override fun describeContents(): Int = 0

    companion object parcelableCreator {
        val CREATOR : Parcelable.Creator<ImageJsonImpl> = object : Parcelable.Creator<ImageJsonImpl> {
            override fun createFromParcel(source: Parcel): ImageJsonImpl? = ImageJsonImpl(source)
            override fun newArray(size: Int): Array<out ImageJsonImpl>?  = Array(size, {i -> ImageJsonImpl()})
        }
    }
}