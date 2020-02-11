package com.example.kotlindemo.base


class KotlinBase {

    //顶层变量
    val PI = 3.14
    var x = 0

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum2(a: Int, b: Int): Int = a + b

    fun printSum(a: Int, b: Int): Unit {
        print("print  sum of $a and $b  is ${a + b}")
    }

    fun printSum2(a: Int, b: Int) {   //或者省略Unit类型
        print("print  sum of $a and $b  is ${a + b}")

        //只读局部变量,只能赋值一次
        val a1 = 2
        val a3: Int  // 如果没有初始值类型不能省略
        a3 = 3

        //可重新赋值的变量使⽤ var 关键字
        var b1 = 2
        var b2: Int = 1
        b2 += 1

        x += 1
//    PI += 1
    }

    /**
     *可变长参数函数可以使用"vararg"关键字标识类似Java中的public void setData(Object... objects)。
     */
    fun vars(vararg args: Int) {
        for (arg in args) {
            print(arg)
        }
    }

    val sumLambda: (Int, Int, Int) -> Int = { a,b,c -> a+b+c}

    //if表达式
    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    //    if作为表达式
    fun maxOf2(a: Int, b: Int): Int = if (a > b) a else b

    //代码块
    fun maxOf3(a: Int, b: Int): Int = if (a > b) {
        print("choose a")
        a
    } else {
        print("choose b")
        b
    }

    //使⽤可空值及null 检测
    fun parseInt(str: String): Int? {
        if (str != null && str.length > 0) {
            return str.length
        } else {
            return null
        }
    }

    //空安全
    fun emptySafety() {
        var a: String = "abc"
//        a = null          //a不可能为null

        var b: String? = "abc"
        b = null

        var l = a.length   //保证不会NPE
//        l = b.length     //变量b可能为null

//        在条件中检查为null
//        1.
        var l2 = if (b != null) {
            b = null
            b?.length
        } else {
            -1
        }
        var l3 = if (b != null) b.length else "abc"
        val l4 = if (b != null) {
            b = null
            b?.length
        } else {
            "abc"
        }

//        2.
        val c: String? = "kotlin"
        if (c != null && c.length > 0) {
            print("String of length is ${c.length}")
        } else {
            print("Empty String")
        }
        /**
         * 这只适⽤于 c 是不可变的情况（即在检查和使⽤之间没有修改过的局部变量 ，或者不可覆盖并
        且有幕后字段的 val 成员），因为否则可能会发⽣在检查之后 c ⼜变为 null 的情况。
         */

        //安全的调用
        print(a.length)         //无需安全调用
        print(b?.length)        //如果 b ⾮空，就返回 b.length ，否则返回 null

//        a?.b?.c?.d            //如果任意⼀个属性（环节）为空，这个链式调⽤就会返回 null

//         如果 `person` 或者 `person.department` 其中之⼀为空，都不会调⽤该函数：
//        person?.department?.head = managersPool.getManager()
    }

    /**
     * is 运算符检测⼀个表达式是否某类型的⼀个实例
     */
    fun getStringLength(obj: Any): Int? {
//        1.
        if (obj is String) {
            //obj在该条件下自动转化为String
            return obj.length
        }
//        2.
        if (obj !is String) {
            return null
        }

//        3.
        if (obj is String && obj.length > 0) {
            // `obj` 在 `&&` 右边⾃动转换成 `String` 类型
            return obj.length
        }
        return null
    }


}




