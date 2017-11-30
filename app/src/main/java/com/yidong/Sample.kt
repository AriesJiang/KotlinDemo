package com.yidong

import android.util.Log
import com.yidong.by.PropertyExample
import com.yidong.by.kotlinBy

/**
 * Created by Aries on 2017/11/23.
 */
class Sample {

    var name: String = "abc"
        get() = field.toUpperCase()
        set(value) {
            field = "Name: $value" // 解析字符串并赋值给其他属性
        }
    var setTest: String? = null
        private set

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    fun double(x: Int): Int {
        return 2 * x
    }

    fun foo(bar: Int = 0, baz: Int) {
        /* …… */
        Log.d("MainActivity", "bar=" + bar + "    baz" + baz)
    }

    fun fooStr(str1: String = "123", baz: Int) {
        /* …… */
        Log.d("MainActivity", "str1=" + str1 + "    baz" + baz)
    }

    fun fooLambda(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {
        /* …… */
        qux()
        Log.d("MainActivity", "fooLambda  bar=" + bar + "    baz" + baz)
    }

    fun compare(a: String, b: String): Boolean = a.length < b.length

    private fun isOdd(x: Int) = x % 2 != 0
    fun isOdd(s: String) = s == "brillig" || s == "slithy" || s == "tove"

    /**
     * 函数嵌套
     */
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    fun doCompost() {
        val sample = Sample()
        fun length(s: String) = s.length

        val oddLength = compose(sample::isOdd, ::length)
        val strings = listOf("a", "ab", "abc")

        println(strings.filter(oddLength)) // 输出 "[a, abc]"
    }

    /**
     * 属性的get set
     */
    fun attr() {
        var sample = Sample()
        println(sample.name)   // ABC
        sample.name = "aaa"
        println(sample.name)   //NAME: AAA

        println(sample.setTest)   // may
        sample.setTest = "123"
        println(sample.setTest)   // may

    }
    /**
     * 属性的get set
     */
    fun attrOut() {
        println("attrOut" + setTest)   // may
        setTest = "123"
        println("attrOut" + setTest)   // may
    }

    fun property() {
        println("table.size =" + table.size)   // may
        if (table is HashMap<String, Int>) println("table is HashMap")
    }

    //相当于静态方法
    companion object part{
        fun demo() {
            //Lambda 表达式
            val sum: (Int, Int) -> Int = { x, y -> x + y }
            Log.d("MainActivity", "sum=" + sum(2, 7))

            //闭包
            var ints = arrayOf(1, -2, 3, 5, 10)
            var sumInts = 0
            var doubles = 1.2
            sumInts = doubles.toInt()
            Log.d("MainActivity", "doubles.toInt() sumInts=" + sumInts)
            Log.d("MainActivity", "sumInts.toDouble()=" + sumInts.toDouble())

            ints.filter { it > 0 }.forEach {
                sumInts += it
            }
            Log.d("MainActivity", "sumInts=" + sumInts)

            //带接收者的函数字面值
            val sumInt = fun Int.(other: Int): Int = this + other
            Log.d("MainActivity", "sumInt=" + 1.sumInt(2))

            val represents: String.(Int) -> Boolean = { other -> toIntOrNull() == other }
            println("123".represents(123)) // true
            Log.d("MainActivity", "represents=" + "123".represents(123))

            fun testOperation(op: (String, Int) -> Boolean, a: String, b: Int, c: Boolean) =
//                assert(op(a, b) == c)
                    op(a, b)

            val isAssert = testOperation(represents, "100", 100, true) // OK
            Log.d("MainActivity", "testOperation=" + isAssert)

            val sample = Sample()
            val sampleDoble = sample.double(11)
            Log.d("MainActivity", "sampleDoble=" + sampleDoble)
            sample.foo(1, 3)
            sample.foo(baz = 3)
            sample.fooStr(baz = 3)
            // sample.fooStr(333) 会报错
            sample.fooStr("fooStr", 333)
            //sample.fooLambda
            sample.fooLambda(8) { Log.d("MainActivity", "hello1")} // 使用默认值 baz = 1
            sample.fooLambda { Log.d("MainActivity", "hello2") }    // 使用两个默认值 bar = 0 与 baz = 1

//        var sampleNotNull: Sample
//        sampleNotNull = null

            var sampleNullable: Sample? = Sample()
            sampleNullable = null

            JavaSample.dome()

            // Lambda表达式与匿名函数 Kotlin标准库 ArrayList<E> https://kotlinlang.org/api/latest/jvm/stdlib/index.html
            var maxLengthStr = max(arrayListOf("January", "February", "March", "April", "May", "June"),
                    { a, b -> a.length < b.length })
            Log.d("MainActivity", "maxLengthStr=" + maxLengthStr)
            // 知识点 函数引用 使用 :: 操作符
            var maxLengthStr2 = max(arrayListOf("July", "August", "September", "October", "November", "December"),
                    sample::compare)
            Log.d("MainActivity", "maxLengthStr2=" + maxLengthStr2)
            var maxInt = max(arrayListOf(1, 2, 3, 4, 5, 6),
                    { a, b -> a < b })
            Log.d("MainActivity", "maxInt=" + maxInt)

            val numbers = listOf(1, 2, 3)
            println(numbers.filter({it % 2 != 0}))

            val predicate: (String) -> Boolean = sample::isOdd   // 引用到 isOdd(x: String)
            Log.d("MainActivity", "predicate=" + predicate("111"))
            Log.d("MainActivity", "predicate=" + predicate("brillig"))

            sample.doCompost()
            sample.attr()
            sample.property()

            by()
        }

        fun <T> max(collection: Collection<T>, less: (T, T) -> Boolean): T? {
            var max: T? = null
            for (it in collection)
                if (max == null || less(max, it))
                    max = it
            return max
        }

        //委托
        fun by() {
            //类委托
            kotlinBy()
            //属性委托
            val e = PropertyExample()
            println(e.p)
            e.p = "NEW"
        }
    }
}
