package numa08.net.tiqa4k.api

import numa08.net.tiqa4k.Image
import numa08.net.tiqa4k.ResponseList
import numa08.net.tiqa4k.TiqavException

public interface ImageResource {
    fun getImage(id : Long) : ResponseList<Image>
}