package functional

/**
  * Created by syy on 2017/5/15.
  */

import functional.StreamDemo.Stream
object StreamDemo {
	import Stream._
	sealed trait Stream[+A]{
		def toList:List[A] = {
			def go(s:Stream[A],acc:List[A]):List[A] = s match {
				case Cons(h,t) => go(t(),h()::acc)
				case _ => acc
			}
			go(this,List()).reverse
		}
		def toListRecur:List[A] = this match {
			case Cons(h,t) => h() :: t().toListRecur
			case _ => List()
		}
		def take(n:Int):Stream[A] = this match {
			case Cons(h,t) if n>1 => cons(h(),t().take(n-1))
			case Cons(h,_) if n == 1 => cons(h(),empty)
			case _ => empty
		}
		def drop(n:Int):Stream[A] = this match {
			case Cons(_,t) if n > 0 => t().drop(n-1)
			case _ => this
		}
		def takeWhile(p:A=>Boolean):Stream[A] = this match {
			case Cons(h,t) if p(h()) => cons(h(),t() takeWhile p)
			case _ => empty
		}
		def exist(p:A=>Boolean):Boolean = this match {
			case Cons(h,t) => p(h()) || t().exist(p)
			case _ => false
		}

		def foldRight[B](z: =>B)(f:(A,=>B)=>B):B = this match {
			case Cons(h,t) => f(h(),t().foldRight(z)(f))
			case _ =>z
		}
		def exist1(p:A=>Boolean):Boolean = foldRight(false)((a,b)=>p(a) || b)

		def forall(p:A=>Boolean):Boolean = foldRight(true)((a,b)=>p(a) && b)
		def takeWhile1(p:A=>Boolean):Stream[A] = foldRight(empty[A])((h,t)=>if(p(h)) cons(h,t) else empty)

		def headOption:Option[A] = foldRight(None:Option[A])((h,_)=>Some(h))

		def map[B](f:A=>B):Stream[B] = foldRight(empty[B])((h,t)=>cons(f(h),t))

		def filter(f:A=>Boolean):Stream[A] = foldRight(empty[A])((h,t)=>if(f(h)) cons(h,t) else t)

		def append[B>:A](s: =>Stream[B]):Stream[B] = foldRight(s)((h,t)=>cons(h,t))

		def flatMap[B](f:A=>Stream[B]):Stream[B] = foldRight(empty[B])((h,t)=>f(h) append t)

		def find(p:A=>Boolean):Option[A] = filter(p).headOption

	}
	case object Empty extends Stream[Nothing]
	case class Cons[A](h:()=>A,t:()=>Stream[A]) extends Stream[A]

	object Stream{
		def cons[A](hd: =>A,t: =>Stream[A]):Stream[A] = {
			lazy val head = hd
			lazy val tail = t
			Cons(()=>head,()=>tail)
		}
		def empty[A]:Stream[A] = Empty
		def apply[A](a:A*):Stream[A] = if(a.isEmpty) empty else cons(a.head,apply(a.tail:_*))

		val ones:Stream[Int] = Stream.cons(1,ones)
		def constant[A](a: A):Stream[A] = {
			lazy val tail:Stream[A] = Cons(()=>a,()=>tail)
			tail
		}
		def from(n: Int):Stream[Int] = cons(n,from(n+1))

		val fibs:Stream[Int] = {
			def go(f0:Int,f1:Int):Stream[Int] = cons(f0,go(f1,f0+f1))
			go(0,1)
		}

		def unfold[A,S](z:S)(f:S=>Option[(A,S)]):Stream[A] = f(z) match {
			case Some((h,s)) => cons(h,unfold(s)(f))
			case None => empty
		}

		val fibs1: Stream[Int] = unfold((0,1)){case (f0,f1)=>Some((f1,(f1,f0+f1)))}
		def contant1[A](a:A):Stream[A] = unfold(a)(_ => Some((a,a)))
		val ones1: Stream[Int] = unfold(1)(_ => Some((1,1)))

	}
	def main(args: Array[String]): Unit = {

		val stream = Stream(1,2,3,4,5,6,7)
		val stream1 = Stream(11,22,33)
//		println(stream.toList)
//		println(stream.toListRecur)
//		println(stream.take(3).toList)
//		println(stream.drop(2).toList)
//		println(stream.takeWhile(_%2 ==0 ).toList)
//		println(stream.exist(_ == 20))
//		println(stream.exist1(_ == 20))
//		println(stream.forall(_<5))
//		println(stream.takeWhile1(_<3).toList)
//		println(stream.headOption.getOrElse("no such elememt"))
//		println(stream.map(_+10).toList)
//		println(stream.filter(_%2==0).toList)
//		println(stream.append(stream1).toList)
//		println(stream.find(_>4))

//		println(Stream.ones.take(5).toList)
//		println(Stream.constant(5).take(5).toList)
//		println(Stream.from(1).take(5).toList)
//		println(Stream.fibs.take(20).toList)
		println(Stream.fibs1.take(20).toList)
		println(Stream.ones1.take(10).toList)
		println(Stream.contant1(10).take(10).toList)

	}
}
