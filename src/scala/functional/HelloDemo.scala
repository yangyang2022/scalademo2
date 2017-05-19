package functional

import scala.annotation.tailrec
import scala.{Option=>_,Either => _,_}

/**
  * Created by syy on 2017/5/14.
  */
object HelloDemo {

	def factory(n:Int):Long = {
		@tailrec
		def fac(acc:Long,n:Int):Long = {
			if(n == 1) acc
			else fac(acc*n,n-1)
		}
		fac(1,n)
	}

	def findFirst[A](array: Array[A],f:A=>Boolean):Int = {
		@tailrec
		def loop(n:Int):Int = {
			if(n >= array.length) -1
			else if(f(array(n))) n
			else loop(n+1)
		}
		loop(0)
	}
	def isSorted[A](as:Array[A],gt:(A,A)=>Boolean):Boolean = {
		@tailrec
		def go(n:Int):Boolean = {
			if(n >= as.length-1) true
			else if(gt(as(n),as(n+1)))false
			else go(n+1)
		}
		go(0)
	}

	def partiall[A,B,C](a:A,f:(A,B)=>C):B=>C = b => f(a,b)

	def compose[A,B,C](f:B=>C,g:A=>B):(A=>C) = a => f(g(a))
	def andthen[A,B,C](f:A=>B,g:B=>C):A=>C = a => g(f(a))


	sealed trait List[+A]
	case object Nil extends List[Nothing]
	case class Cons[A](head:A,tail: List[A]) extends List[A]
	object List{

		def apply[A](as:A*):List[A] = if(as.isEmpty) Nil else Cons(as.head,apply(as.tail:_*))

		def sum(ints:List[Int]):Int = ints match {
			case Nil => 0
			case Cons(x,xs) => x + sum(xs)
		}

		def product(ds:List[Int]):Double = ds match {
			case Nil => 1
			case Cons(0,_) => 0
			case Cons(x,xs) => x * product(xs)
		}

		def tail[A](xs:List[A]):List[A] = xs match {
			case Nil => sys.error("tail of empty list")
			case Cons(_,t) => t
		}

		def setHead[A](h:A,xs:List[A]):List[A] = xs match {
			case Nil => sys.error("tail of empty list")
			case Cons(_,t) => Cons(h,t)
		}

		def drop[A](xs:List[A],n:Int):List[A] = {
			if(n <= 0) xs
			else xs match {
				case Nil => Nil
				case Cons(_,t) => drop(t,n-1)
			}
		}

		def dropWhile[A](xs:List[A])(f:A=>Boolean):List[A] = xs match {
			case Cons(h,t) if f(h) => dropWhile(t)(f)
			case _ => xs
		}

		def append[A](a1:List[A],a2:List[A]):List[A] = a1 match {
			case Nil => a2
			case Cons(h,t) => Cons(h,append(t,a2))
		}

		def init[A](xs:List[A]):List[A] = xs match {
			case Nil => sys.error("init of empty list")
			case Cons(_,Nil) => Nil
			case Cons(h,t) => Cons(h,init(t))
		}

		def foldRight[A,B](xs:List[A],z:B)(f:(A,B)=>B): B = xs match {
			case Nil => z
			case Cons(h,t) => f(h,foldRight(t,z)(f))
		}
		def sum2(ns:List[Int]) = foldRight(ns,0)(_+_)
		def product2(ns:List[Double]) = foldRight(ns,1.0)(_*_)

		def length[A](xs:List[A]):Int = foldRight(xs,0)((_,acc)=>acc+1)

		def foldLeft[A,B](xs:List[A],z:B)(f:(B,A)=>B) :B = xs match {
			case Nil => z
			case Cons(h,t) => foldLeft(t,f(z,h))(f)
		}

		def sum3(xs:List[Int]):Int = foldLeft(xs,0)(_+_)
		def product3(xs:List[Int]):Int = foldLeft(xs,1)(_*_)
		def length3[A](xs:List[A]):Int = foldLeft(xs,0)((acc,_)=>acc+1)

		def reverse[A](xs:List[A]):List[A] = foldLeft(xs,List[A]())((acc,h)=>Cons(h,acc))

		def foldRightViafoldLeft[A,B](xs:List[A],z:B)(f:(A,B)=>B):B = foldLeft(reverse(xs),z)((b,a)=>f(a,b))

		def appendViaFoldRight[A](l:List[A],r:List[A]):List[A] = foldRight(l,r)(Cons(_,_))

		def appendlist[A](xs:List[List[A]]):List[A] = foldRight(xs,Nil:List[A])(append)

		def addOne(xs:List[Int]):List[Int] = foldRight(xs,Nil:List[Int])((h,t)=>Cons(h+1,t))
		def doubleToString(xs:List[Double]):List[String] = foldRight(xs,Nil:List[String])((h,t)=>Cons(h.toString,t))

		def map[A,B](xs:List[A])(f:A=>B):List[B] = foldRight(xs,Nil:List[B])((h,t)=>Cons(f(h),t))
		def filter[A](xs:List[A])(f:A=>Boolean):List[A] = foldRight(xs,Nil:List[A])((h,t)=>if(f(h)) Cons(h,t) else t) // t is acc

//		def flatmap[A,B](xs:List[A])(f:A=>List[B]):List[B] = foldRight(xs,Nil:List[B])((h,t)=>Cons(f(h),t))
//		def filterViaFlatmap

		def zip(a:List[Int],b:List[Int]):List[Int] = (a,b) match {
			case (_,Nil) => Nil
			case (Nil,_) => Nil
			case ((Cons(a1,at),Cons(b1,bt))) => Cons(a1+b1,zip(at,bt))
		}

		def zipWith[A,B,C](a:List[A],b:List[B])(f:(A,B)=>C):List[C] = (a,b) match {
			case (_,Nil) => Nil
			case (Nil,_) =>Nil
			case ((Cons(a1,at),Cons(b1,bt))) => Cons(f(a1,b1),zipWith(at,bt)(f))
		}

		def startWith[A](l:List[A],prefix:List[A]):Boolean = (l,prefix) match {
			case (_,Nil) => true
			case (Cons(h1,t1),Cons(h2,t2))if h1 == h2 => startWith(t1,t2)
			case _ => false
		}
		def hasSubSequenece[A](sup:List[A],sub:List[A]):Boolean = sup match {
			case Nil => sub == Nil
			case _ if startWith(sup,sub) => true
			case Cons(_,t) => hasSubSequenece(t,sub)
		}
	}

	trait Tree[+A]
	case class Leaf[A](value:A) extends Tree[A]
	case class Branch[A](left:Tree[A],right:Tree[A]) extends Tree[A]


	object Tree{
		def size[A](tree: Tree[A]):Int = tree match {
			case Leaf(_) => 1
			case Branch(l,r) => 1+size(l)+size(r)
		}
		def maximum[A](t:Tree[Int]):Int = t match {
			case Leaf(a) => a
			case Branch(l,r) => maximum(l) max maximum(r)
		}
		def depth[A](t:Tree[A]):Int = t match {
			case Leaf(_) => 1
			case Branch(l,r) => 1 + (depth(l) max depth(r))
		}
		def map[A,B](t:Tree[A])(f:A=>B):Tree[B] = t match {
			case Leaf(a) => Leaf(f(a))
			case Branch(l,r) => Branch(map(l)(f),map(r)(f))
		}

		def fold[A,B](t:Tree[A])(f:A=>B)(g:(B,B)=>B):B = t match {
			case Leaf(a) => f(a)
			case Branch(l,r) => g(fold(l)(f)(g),fold(r)(f)(g))
		}
		def size1[A](t:Tree[A]):Int = fold(t)(a=>1)(1+_+_)
		def depth1[A](t:Tree[A]):Int = fold(t)(a=>0)((d1,d2)=>1+(d1 max d2))
		def max1[A](t:Tree[Int]):Int = fold(t)(a=>a)(_ max _)
		def map1[A,B](t:Tree[A])(f:A=>B) = fold(t)(a => Leaf(f(a)): Tree[B])(Branch(_,_))
	}

	sealed trait Option[+A]{

		def map[B](f:A=>B):Option[B] = this match {
			case None => None
			case Some(a) => Some(f(a))
		}

		def flatmap[B](f:A=>Option[B]):Option[B] = map(f) getOrElse None

		def getOrElse[B>:A](default: =>B):B = this match {
			case None => default
			case Some(a) => a
		}

		def orElse[B>:A](ob: =>Option[B]):Option[B] = this match {
			case None => ob
			case Some(a) => this
		}

		def filter(f:A=>Boolean):Option[A] = this match {
			case Some(a) if f(a) => this
			case _ => None
		}
	}

	object Option{

		def means(xs:Seq[Double]):Option[Double] = if(xs.isEmpty) None else Some(xs.sum/xs.length)

		def variance(xs:Seq[Double]):Option[Double] = means(xs) flatmap(m => means(xs.map(x=>math.pow(x-m,2))))

		def lift[A,B](f:A=>B):Option[A]=>Option[B] = _ map f

	}
	case class Some[A](get:A) extends Option[A]
	case object None extends Option[Nothing]

	def Try[A](a: =>A):Option[A] = try Some(a) catch{case e:Exception => None}


	def main(args: Array[String]): Unit = {


//		println(s"fact(5): ${factory(5)}")
//		println(s"fact(50): ${factory(50)}")

//		println(findFirst(Array("hello","world","he"),(s:String)=>s == "he"))
//		println(s"isSorted: ${isSorted(Array(1,2,3,4),(a:Int,b:Int)=>a > b)}")

//		def adder(x:Int)(y:Int) = x+y
//
//		println(s"add: ${andthen(adder(2),adder(3))(10)}")
//		println(s"add: ${compose(adder(3),adder(2))(10)}")

//		val list = List(1 to 5:_*)
//		val list2 = List(2,1,5,0,1,4)
//		val list3 = List(11,22,33)
//
//		val dlist = List(1.1,2.2,3.3)
//
//		val nilList = Nil
//		println(s"sum: ${List.sum(list)}")
//		println(s"product: ${List.product(list)}")
//		println(s"tail: ${List.tail(nilList)}")

//		println(s"setHead: ${List.setHead(998,list)}")
//		println(s"setHead: ${List.setHead(998,nilList)}")

//		println(s"drop: ${List.drop(list,3)}")

//		println(s"drop: ${List.dropWhile(list2,(x:Int) => x<3)}")
//		println(s"drop: ${List.dropWhile(list)(x=>x<3)}")

//		println(s"init: ${List.init(list)}")

//		println(s"sum2: ${List.sum2(list)}")
//		println(s"product2: ${List.product(list)}")

//		println(List.foldRight(List(1,2,3,4),Nil:List[Int])(Cons(_,_))) //get the original list
//		println(s"length: ${List.length(list)}")

//		println(s"sum3: ${List.sum3(list)}")
//		println(s"product3: ${List.product3(list)}")
//		println(s"length3: ${List.length3(list)}")

//		println(s"reverse: ${List.reverse(list)}")

//		println(s"foldRight: ${List.foldRightViafoldLeft(list,0)(_+_)}")
//		println(s"appendViaFoldRight: ${List.appendViaFoldRight(list,list2)}")

//		println(s"appendList: ${List.appendlist(List(list,list2,list3))}")

//		println(s"addOne: ${List.addOne(list3)}")
//		println(s"dlist: ${List.doubleToString(dlist)}")

//		println(s"mapList: ${List.map(list3)(e=>e+1)}")
//		println(s"filter: ${List.filter(list)(_%2 != 0)}")

//		println(s"zip: ${List.zip(list,list2)}")
//		println(s"zipWith: ${List.zipWith(list,list2)(_+_)}")

//		println(s"has Sub: ${List.hasSubSequenece(list,List(1,3,2))}")

//		val tree = Branch(Branch(Leaf("a"),Leaf("b")),Branch(Leaf("c"),Leaf("d")))
//		val tree1 = Branch(Branch(Leaf(1),Leaf(3)),Branch(Leaf(2),Leaf(4)))
//		println(s"size: ${Tree.size1(tree)}")
//		println(s"max: ${Tree.max1(tree1)}")
//		println(s"depth: ${Tree.depth1(tree)}")
//		println(s"Treemap: ${Tree.map1(tree1)(_+1)}")

//		val seq = Seq(50.0,100.0,100,60,50)
//		println(s"variance: ${Option.variance(seq)}")
//		println(s"means: ${Option.means(seq)}")

//		val abs0 = Option.lift(math.abs)
//		println(abs0(Some(-123)).getOrElse("some other "))

//		println(Try("123a".toInt))

	}
}
