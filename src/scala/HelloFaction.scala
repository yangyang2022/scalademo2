import scala.io.StdIn
import scala.runtime.Nothing$

/**
  * Created by syy on 2017/5/3.
  */

class CSuper{def msuper(){println("msuper ... ")}}
class C extends CSuper{def m(){println("c ... ")}}
class CSub extends C {def msub(){println("msub ... ")}}

//class Container[+A](var value:A)
case class Persons(name:String,age:Int)

object HelloFaction {

	def main(args: Array[String]): Unit = {

//		var f:C=>C = (c:C) => new C
//		f = (c:CSuper) => new CSub
//		f  = (c:CSuper) => new C
//		f  = (c:C) => new CSub
////		f  = (c:CSub) => new CSub //error
//
//		val line = StdIn.readLine()
//		println(line)

		val p1 = Persons("yangyang",12)
		val p2 = Persons("yangyang",12)
		val p3 = Persons("yangyang",13)

		println(p1 eq p2)
		println(p1 == p2)

		println(Array(1,2) == Array(1,2))
		println(Array(1,2) sameElements Array(1,2))
	}
}
