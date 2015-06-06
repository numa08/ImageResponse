package numa08.net.tiqa4k.conf

import android.os.Parcelable

public interface Configuration : Parcelable {

    val endpointURL : String

    val searchPath : String

    val searchNewestPath : String

    val searchRandomPath : String

    val getImagesPath : String
}