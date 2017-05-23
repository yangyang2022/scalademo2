package functional

import scala.collection.generic.IsTraversableLike

/**
  * Created by syy on 2017/5/19.
  */
object ProgramingScala {
//	import math.Ordering
//	import math.Ordered
//	case class MyList[A](list: List[A]){
//		def sortBy1[B](f:A=>B)(implicit ord:Ordering[B]):List[A] = list.sortBy(f)
//
//		def sortedBy2[B:Ordering](f:A=>B):List[A] = list.sortBy(f)
//	}
	def main(args: Array[String]): Unit = {
//
//		val list = List(1,2,3,4,5)
//
//		val slist = MyList(list).sortBy1(i => -i)
//		println(slist)
//
//		val slist2 = MyList(list).sortedBy2(i => -i)
//		println(slist2)

//		object M{
//			implicit object IntMarker
//			implicit object StringMarker
//
//			def m(seq: Seq[Int])(implicit i:IntMarker.type ):Unit = println(s"Seq[Int]: $seq")
//			def m(seq: Seq[String])(implicit s:StringMarker.type ):Unit = println(s"Seq[String]: $seq")
//		}
//		import M._
//		m(List(1,2,3))
//		m(List("hello","world"))

//		def reduceLeft[A,B](seq: Seq[A])(f:A=>B):Seq[B] = {
//			def rl(acc:Seq[B],s:Seq[A]):Seq[B] = s match {
//				case head +: tail => rl(f(head)+:acc,tail)
//				case _ => acc
//			}
//			rl(Seq.empty[B],seq)
//		}
//		def reduceRight[A,B](seq: Seq[A])(f:A=>B):Seq[B] = seq match {
//			case head +: tail => f(head) +: reduceLeft(tail)(f)
//			case _ => Seq.empty[B]
//		}
//
//	   val list = List(1,2,3,4,5)
//		println(s"rl: ${reduceLeft(list)(i=>2*i)}")
//		println(s"rr: ${reduceRight(list)(i=>2*i)}")

//	val fibs:Stream[BigInt] = BigInt(0)#::BigInt(1)#::fibs.zip(fibs.tail).map(n=>n._1+n._2)
//		fibs.take(10).foreach(i=>println(s"i=$i"))

	trait Config[+A]{
		def map[B](f:A=>B):Config[B]
		def flatmap[B](f:A=>Config[B]):Config[B]
		def get:A
	}
	object Config{
		def apply[A](data: =>A) = new Config[A] {
			override def get: A = data

			override def map[B](f: (A) => B): Config[B] = ???

			override def flatmap[B](f: (A) => Config[B]): Config[B] = ???
		}
	}
	trait Functor[T[_]]{
		def apply[A](a: A):T[A]
		def map[A,B](a: T[A])(f:A=>B):T[B]
	}
	object ConfigAsFunctor extends Functor[Config]{
		override def apply[A](a: A): Config[A] = Config(a)

		override def map[A, B](a: Config[A])(f: (A) => B): Config[B] = a.map(f)
	}
	implicit def functorOps[F[_]:Functor,A](ma:F[A]) = new {
		val functor = implicitly[Functor[F]]
		final def map[B](f:A=>B):F[B] =functor.map(ma)(f)
	}
	new Traversable[String]{
		override def foreach[U](f: (String) => U): Unit = ???
	}
//	var x = 1
//	val res1 = Config{x+=1;x}
//	println(res1.get)
//	println(res1.get)
//	println(res1.get)
//	println(res1.get)

	}
}
