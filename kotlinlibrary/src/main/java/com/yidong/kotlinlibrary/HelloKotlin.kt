package com.yidong.kotlinlibrary

/**
 * Created by Administrator on 2017/11/23.
 */
object HelloKotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        var x = 2
        var y = 5
        val sum: (Int, Int) -> Int = { x, y -> x + y }
        print("sum(2, 5) is " + sum(x, y))
    }
}