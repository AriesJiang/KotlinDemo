package com.yidong.extension

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by Aries on 2017/11/28.
 * 扩展定义在file中，不能是class 相关博客 http://www.jianshu.com/p/2cfd5cf301db
 */
fun Any?.string() : String = if(this == null) "null obj" else toString()  // 扩展方法

fun Context.extensionToast(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    Log.d("text", "Toast msg : $msg")
}