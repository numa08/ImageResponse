package numa08.net.tiqa4k.impl

import android.os.Parcel
import android.os.Parcelable
import numa08.net.tiqa4k.Image
import numa08.net.tiqa4k.ResponseList
import numa08.net.tiqa4k.http.HttpResponse
import org.json.JSONObject
import java.io.BufferedInputStream
import java.net.HttpURLConnection

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

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val otherImage = other as? Image
        val retval = otherImage?.id == id
        return retval
    }

    companion object parcelableCreator {
        val CREATOR : Parcelable.Creator<ImageJsonImpl> = object : Parcelable.Creator<ImageJsonImpl> {
            override fun createFromParcel(source: Parcel): ImageJsonImpl? = ImageJsonImpl(source)
            override fun newArray(size: Int): Array<out ImageJsonImpl>?  = Array(size, {i -> ImageJsonImpl()})
        }

        fun CreateImagesList(resp: HttpResponse): ResponseList<Image> {
            val jsonArray = resp.asJSONArray
            val list = if (jsonArray != null) {
                val images = ResponsesListImpl<Image>(jsonArray.length())
                var i = 0
                while(i < jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val image = ImageJsonImpl(obj)
                    images.add(image)
                    i++
                }
                images
            } else {
                ResponsesListImpl<Image>()
            }
            return list
        }
    }
}