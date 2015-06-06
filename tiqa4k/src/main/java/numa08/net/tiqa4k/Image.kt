package numa08.net.tiqa4k

import android.os.Parcelable
interface Image : Parcelable {
    val id : String
    val ext : String
    val height : Long
    val width : Long
    val sourceURL : String
    val originalURL : String
    val thumbnailURL : String
    val glitchURL : String
}
