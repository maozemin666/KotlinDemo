package com.example.kotlindemo.`class`

import android.support.annotation.InterpolatorRes
import android.widget.Button

/**
 * 一个类中可以有一个主构造函数和多个次构造函数。
 * 主构造函数是类头的一部分
 * 它跟在类名（与可选参数后面）
 */
open class Person constructor(firstName: String) {
    //如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字

//    主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块
//    （initializer blocks）中

    constructor(parent: Person) : this("",parent) {}

//    委托给主构造函数会作为次构造函数的第一条语句，所有初始化块中的代码都会在次构造函数之前执行
//    即使该类没有主构造函数
    constructor(name:String, parent: Person) : this(name) {}

    //    初始化块按照它们出现在类体中的顺序执⾏,与属性初始化器交织在⼀起
    val firstProperty = "First property: $firstName".also(::println)

    init {
        println("first init block that prints ${firstName}")
    }

    val secondProperty = "Second property: ${firstName.length}".also(::println)

    init {
        println("second init block that prints ${firstName}")
    }

//    属性初始化构造器
    val customKey = firstName.toUpperCase()


    //默认修饰符为public
    fun foo2() {

    }
    //在Person.kt中可见
    private fun foo() {
    }

    public var bar: Int = 5

    private val a = 1
    protected open val b = 2
    //  相同模块可见
    internal val c = 6
    //默认为public
    val d : Int = 4

//    kotlin中外部类不能访问内部类的private成员
//    val f = Nested().f

    protected class Nested {
        public val e : Int = 5
        private val f = 6
    }


}

//声明属性以及从主构造函数初始化属性
class Person2(val firstName: String, val lastName: String,var age: Int) : Person(firstName) {
//    与普通属性⼀样，主构造函数中声明的属性可以是可变的（var）或只读的（val）

    val person = Person("maomao")
    val person2 = Person("maomao2",person)
//    val person3 = DontCreateMe()

    val customer = Customer()
    val customer2 = Customer("maomao")

    override val b: Int = 5  //'b'为protected

    protected val ne = Nested()
    private val ne2 = Nested()

}
class Person3(p: Person) {
    val person = p

    open fun test() {
        //局部变量、函数和类不能有可⻅性修饰符。
        class Inner {

        }

        var f = person.c
        f = person.d
    }
//    p.a
//    p.b  不可见

}

/**
 * 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前⾯
 */
class Customer public @Inject constructor(val name: String = "") {
//    在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会⽣成 ⼀个额外的⽆参构造函
//    数，它将使⽤默认值。
}

class DontCreateMe private constructor(){
    var clickCount = 0

    //对象表达式
    val ab: A = object : A(1), B {
        override fun testB() {
            /**
             * 就像 Java 匿名内部类⼀样，对象表达式中的代码可以访问来⾃包含它的作⽤域的变量。（与 Java 不同
            的是，这不仅限于final 或实际相当于final 的变量。）
             */
            clickCount++
        }

        override val y: Int
            get() = super.y

    }

    fun foo() {

        //只需要一个对象而已
        val adHoc = object {
            var x: Int = 5
            var y: Int = 6
        }
        print(adHoc.x + adHoc.y)


        /**
         * ，匿名对象可以⽤作只在本地和私有作⽤域中声明的类型。如果你使⽤匿名对象作为公有函数
        的返回类型或者⽤作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型，如果
        你没有声明任何超类型，就会是 Any 。在匿名对象中添加的成员将⽆法访问。
         */
        val x = foo2().x
//        val y = foo3().y

        DataProviderManager.registerDataProvider()

        aClass.registerB(object : B {
            override fun testB() {

            }
        })
    }

    private fun foo2() = object {
        val x: String = "x"
    }

    fun foo3() = object {
        val y: String = "y"
    }

    val demo = Outer().Inner().foo()

    //内部类
    private val aClass = A(1)


}

open class A(x: Int) {
    public open val y : Int = x
    fun registerB(b: B) {}
}

interface B {
    fun testB()
    fun testB2() {

    }
}

interface C {
    val prop: Int   //抽象的
    fun testC()
    fun testC2() {

    }
}

class Child : B, C {
    override val prop: Int
        get() = 1

    override fun testB() {
    }

    override fun testC() {
    }
}


/**
 * 这称为对象声明。并且它总是在 object 关键字后跟⼀个名称。就像变量声明⼀样，对象声明不是⼀个
表达式，不能⽤在赋值语句的右边。
对象声明的初始化过程是线程安全的。

注意：对象声明不能在局部作⽤域（即直接嵌套在函数内部），但是它们可以嵌套到其他对象声明或⾮内
部类中。
 */
object DataProviderManager {
    fun registerDataProvider() {

    }
}

//嵌套类和内部类
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }

}


