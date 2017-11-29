package com.yidong

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.yidong.extension.extensionToast
import com.yidong.extension.string
import kotlinx.android.synthetic.main.custom_drawerlayout.*
import kotlinx.android.synthetic.main.custom_toolbar.*

//  对Context的扩展，增加了toast方法。为了更好的看到效果，我还加了一段log日志，也可以独立在一个kt file中
fun Context.toast(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    Log.d("text", "Toast msg : $msg")
}

class MainActivity : AppCompatActivity() {

    //声明相关变量
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var lvLeftMenu: ListView? = null
    private val lvs = arrayOf("List Item 01", "List Item 02", "List Item 03", "List Item 04")
    private var arrayAdapter: ArrayAdapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Sample.part.demo()
        initView()
//        toast("hello, Extension")
//        var a = 1
//        toast("hello, ${a.string()}")
        var b : View? = null
        extensionToast("hello, ${b.string()}")
    }

    /**
     * 属性的get set
     */
    fun attr() {
        var sample = Sample()
        println(sample.setTest)   // null
        sample.attrOut()
//        sample.setTest = "123"
        println(sample.setTest)   // 123
    }

    private fun findViews() {
        mDrawerLayout = findViewById<View>(R.id.dl_left) as DrawerLayout?
        lvLeftMenu = findViewById<View>(R.id.lv_left_menu) as ListView?
    }

    private fun initView() {
        findViews() //获取控件
        hello.setText("Hi!")
        tl_custom.setTitle("Toolbar")//设置Toolbar标题
        tl_custom!!.setTitleTextColor(Color.parseColor("#ffffff")) //设置标题颜色
        setSupportActionBar(tl_custom)
        supportActionBar!!.setHomeButtonEnabled(true) //设置返回键可用
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, tl_custom, R.string.open, R.string.close) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
            }
        }
        (mDrawerToggle as ActionBarDrawerToggle).syncState()
        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        //设置菜单列表
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs)
        lvLeftMenu?.setAdapter(arrayAdapter)
    }
}
