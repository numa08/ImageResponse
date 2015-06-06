package numa08.net.tiqa4k.api

import android.os.Parcelable
import numa08.net.tiqa4k.Image
import numa08.net.tiqa4k.ResponseList

public interface SearchResource: Parcelable {
    fun search(query : String) : ResponseList<Image>
    fun searchNewest() : ResponseList<Image>
    fun searchRandom() : ResponseList<Image>
}