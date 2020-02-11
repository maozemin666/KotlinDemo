package com.example.kotlindemo.`class`

import java.lang.AssertionError

class Address {
    var name: String = "m"
    var street: String = "z"
    var state: String? = "m"

    fun copyAddress(address: Address): Address {
        val result = Address()
        result.name = address.name
        result.state = address.state
        result.street = address.street
        return result
    }

//    var allByDefault: Int?    // 错误：需要显式初始化器，隐含默认 getter 和 setter
    var initialize = 1          // 类型 Int、默认 getter 和 setter

//    val simple: Int?          // 类型 Int、默认 getter、必须在构造函数中初始化
    val inferredType = 1        // 类型 Int 、默认 getter


    val isEmpty: Boolean
        get() = this.name == "m"

    //get()自动推断类型
    val isEmpty2
        get() = this.name == "m"


    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            setDataFromString(value)
        }

    private fun setDataFromString(value: String) {

    }

//    如果你需要改变⼀个访问器的可⻅性或者对其注解，但是不需要改变默认的实现，你可以定义访问器⽽
//    不定义其实现
    var setterVisibility: String = "abc"
        private set                             // 此 setter 是私有的并且有默认实现

    var setterWithAnnotation: Any? = null
        @Inject set                             // ⽤ Inject 注解此 setter

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap()
            }
            return _table ?: throw AssertionError("set to null by another thread")
        }

//    已知值的属性可以使⽤ const 修饰符标记为 编译期常量。这些属性需要满⾜以下要求：
//    位于顶层或者是 object 声明 或 companion object 的⼀个成员
//    以 String 或原⽣类型值初始化
//    没有⾃定义 getter
    object myObject {
        const val SUBSYSTEM_DEPRECATED: String = "this system is deprecated"
        @Deprecated(SUBSYSTEM_DEPRECATED)
        fun foo() {}
    }

    class MyClass {
        companion object Factory {
            const val SUBSYSTEM_DEPRECATED: String = "this system is deprecated"
        }
    }

//    你不能在构造函数内提供⼀个⾮ 空初始器。
//    但你仍然想在类体中引⽤该属性时避免空检查。
//    该属性或变量必须为⾮空类型，并且不能是原⽣类型。
    lateinit var subject: Person
    fun foo2() {
        subject = Person("m")
    }


}