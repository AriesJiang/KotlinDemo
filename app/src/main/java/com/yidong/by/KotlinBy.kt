package com.yidong.by

import android.util.Log
import com.yidong.extension.string

/**
 * Created by Administrator on 2017/11/28.
 */
interface Base{
    fun print()
}

class BaseImpl(val x: Int) : Base{
    override fun print() {
        Log.d("BaseImpl", "BaseImpl -> ${x.string()}")
    }
}

class Printer(b: Base) : Base by b

fun test(){
    val a = BaseImpl(5)
    Printer(a).print()
}