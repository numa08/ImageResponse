package numa08.net.tiqa4k

public class TiqavException : Exception {
    constructor(th : Throwable) : super(th) {}
    constructor(me : String, th : Throwable) : super(me, th) {}
    constructor(me : String) : super(me)
}