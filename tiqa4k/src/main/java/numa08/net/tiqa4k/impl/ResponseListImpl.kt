package numa08.net.tiqa4k.impl

import android.os.Parcel
import android.os.Parcelable
import numa08.net.tiqa4k.ResponseList
import java.util.*

class ResponsesListImpl : ResponseList<Parcelable>, ArrayList<Parcelable> {

    constructor() : super() {}

    constructor(size : Int) : super(size) {}

    constructor(col : Collection<out Parcelable>) : super(col) {}

    private constructor(source : Parcel) {
        val className = source.readString()
        val size = source.readInt()
        var i = 0
        while(i < size) {
            add(source.readParcelable(Class.forName(className).getClassLoader()))
            i++
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        if(size() == 0 ){
            return
        }
        dest.writeString(this.get(0).javaClass.getName())
        dest.writeInt(size())
        this.forEach { dest.writeParcelable(it, 0) }
    }

    override fun describeContents(): Int = 0

    companion object parcelable {
        val CREATOR : Parcelable.Creator<ResponsesListImpl> = object : Parcelable.Creator<ResponsesListImpl> {
            override fun createFromParcel(source: Parcel): ResponsesListImpl? = ResponsesListImpl(source)
            override fun newArray(size: Int): Array<out ResponsesListImpl>?  = Array(size, {i -> ResponsesListImpl()})

        }
    }
}