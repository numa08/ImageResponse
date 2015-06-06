package numa08.net.tiqa4k.impl

import android.os.Parcel
import android.os.Parcelable
import numa08.net.tiqa4k.ResponseList
import java.util.*

class ResponsesListImpl<T> : ResponseList<T>, ArrayList<T> where T : Parcelable {

    constructor() : super() {}

    constructor(size : Int) : super(size) {}

    constructor(col : Collection<T>) : super(col) {}

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
        val CREATOR : Parcelable.Creator<ResponsesListImpl<Parcelable>> = object : Parcelable.Creator<ResponsesListImpl<Parcelable>> {
            override fun createFromParcel(source: Parcel): ResponsesListImpl<Parcelable>? = ResponsesListImpl(source)
            override fun newArray(size: Int): Array<out ResponsesListImpl<Parcelable>>?  = Array(size, {i -> ResponsesListImpl<Parcelable>()})

        }
    }
}