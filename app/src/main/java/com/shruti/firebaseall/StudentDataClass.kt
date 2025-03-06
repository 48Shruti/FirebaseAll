package com.shruti.firebaseall

data class StudentDataClass(var id : String = "",
    var name : String ?= "",
    var classStudent : Int ?= 0){
    constructor() : this("","",0)
}
