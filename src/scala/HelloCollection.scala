/**
  * Created by syy on 2017/5/3.
  */

import collection.concurrent.TrieMap
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
object HelloCollection {

	trait Builder[-Elem,+To]{
		def +=(elem:Elem):Builder.this.type
		def clear()
		def result():To
	}
	class ListBuilder[T] extends Builder[T,List[T]]{

		private var storage = Vector.empty[T]

		override def +=(elem: T): ListBuilder.this.type ={
			storage :+= elem
			this
		}
		override def clear(): Unit = storage = Vector.empty[T]

		override def result(): List[T] = storage.toList
	}

	class Parent(val value:Int){
		override def toString: String = s"${this.getClass.getName}($value)"
	}
	class Child(value:Int) extends Parent(value)

	case class Opt[+A](value:A=null){
		def getOrElse[B >: A](default: A=>B) = if(value != null) value else default
	}

	def main(args: Array[String]): Unit = {


//		val op:Option[Child] = Option(new Child(1))
//		val p1:Parent = op.getOrElse(new Parent(10))


//		println(1+:Seq(1,2))
//		println(1.0 +: Seq(1,3))

//		val op1:Option[Child] = Option(null)
//		val p1 = op1.getOrElse(new Parent(10))
//		println(p1)
//
//		val op2:Option[Parent] = Option[Parent](null)
//		val p2 = op2.getOrElse(new Parent(10))
//		val p3 = op2.getOrElse(new Child(11))
//		println(p2)
//		println(p3)
//
//		val op3:Option[Parent] = Option[Child](null)
//		val p4 = op2.getOrElse(new Parent(10))
//		val p5 = op2.getOrElse(new Child(11))
//		println(p4)
//		println(p5)


//		var lb = new ListBuilder[Int]
//		(1 to 10).foreach(i => lb += i)
//		println(lb.result())

//		val set = mutable.BitSet(1,2,3,4,5)
//		println(set)
//		println(set.map(_.toString))
//		println("xyz".map(_.toInt))


	}
}
