package com.example.kotlindemo.base.`interface`

interface Named {
    val name: String
}

interface PersonInterface : Named {
    val firstName: String
    val lastName: String
    override val name: String
        get() = "$firstName $lastName"
}

data class Employee(
    override val firstName: String,
    override val lastName: String
) : PersonInterface

class Extension {
    //可空接收者
    fun Any?.toString(): String {
        if (this == null)  return "null"
        return toString()
    }

    val <T> List<T>.lastIndex: Int
        get() = size - 1

//    由于扩展没有实际的将成员插⼊类中，因此对扩展属性来说幕后字段是⽆效的。这就是为什么扩
//    展展属属性性不不能能有有初始化器。他们的⾏为只能由显式提供的 getters/setters 定义。
    var Extension.foo: Int
        get() = 1
        set(value) = TODO()

    private fun baz() {}

//    在这样的扩展内部，有多个 隐式接收者 —— 其中的对象成员可以⽆需通过限定符访问。
    fun D.foo() {
        bar()
        baz()

//    对于分发接收者与扩展接收者的成员名字冲突的情况，扩展接收者优先。要引⽤分发接收者的成员你可以使⽤ 限定的 this 语法
        toString()                  //D.toString
        this@Extension.toString()   //Extension.toString
    }

    fun caller(d: D) {
        d.foo()
    }
}
class D {
    fun bar() {}
}
