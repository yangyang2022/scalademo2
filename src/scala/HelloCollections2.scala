/**
  * Created by syy on 2017/5/5.
  */

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.atomic.LongAdder

import sun.management.counter.LongArrayCounter

import scala.collection.mutable
import scala.collection.parallel.{ForkJoinTaskSupport, Splitter}
import scala.io.Source
import scala.reflect.runtime.{universe => ru}
import scala.util.Random
import scala.util.parsing.json.JSON
object HelloCollections2 {

	def getType[T:ru.TypeTag](obj:T) = ru.typeTag[T]

	case class A[T]()

	def main(args: Array[String]): Unit = {

//		val t1 = Traversable(1,2,3)
//		println(getType(t1).tpe)

//		println(A[Int].isInstanceOf[A[String]])
//		println(A[String].isInstanceOf[A[Int]])

//		val s = List(List(1,2,3),List(2,3,4),List(5,6))
//		s.flatMap( i=> i + 1 ).foreach(println)
//		s.flatten.foreach(print)
//		s.flatMap(e=>e.map(_+10)).foreach(println)

//		s.flatMap(_+" ,").foreach(print)

//		val list = List("one","two","three")
//		list.flatMap(_.toList).foreach(print)

//		val matrix = Seq(Seq(1,2,3),Seq(4,5,6),Seq(7,8,9))
//		println(matrix)
//		println(matrix.transpose)
//
//		implicit def asPair(x:String):(Char,Int) = (x(0),x.substring(2).toInt)
//
//		val s2 = Seq("a_1","b_2","c_3")
//		val (a2,b2) = s2.unzip
//
//		println(a2)
//		println(b2)

//		val t = Traversable("movieId,score,name","2017,8.2,xiaowangzi","2018,8.3,diandinamei","2019,7.2,haimianbaobao")
//
//		val (a,b,c) = t.unzip3(x=>{val a = x.split(",");(a(0),a(1),a(2))})
//		println(a)
//		println(b)
//		println(c)


//		val list = Traversable(1 to 10:_*)
////		def isEven = (i:Int) => i%2 == 0
//		def isEvne:PartialFunction[Int,String] = {
//			case x if x % 2 == 0 => x+" is even!"
//		}
//		println(list collect isEvne)

		val path = ClassLoader.getSystemResource("data.txt").getPath
//		val list = Source.fromFile(path).getLines().flatMap(line => line.split("\\W+")).toList
//		println(list)

//		def fact(x:Int):Int = if(x < 1) 1 else x * fact(x-1)
//
//		val t = Traversable(1 to 5:_*)
//		t.scanLeft(1)(_*_).foreach(println)
//		t.scanRight(1)(_*_).foreach(println)

//		val t = Traversable("a","b","c","d","e")
//		println(("z" /: t)(_+_))
//		println((t :\ "z")(_+_))

//		val t = Traversable( 1 to 5:_*)
//		for (i <- t.tails) println(i)
//		println(t.slice(1,3))

//		val t = Traversable(5,7,4,3,2,1,9,0,12)
//		println(t.takeWhile(_<6))

//		val t = Traversable(1 to 10 : _*)
//		t withFilter(_<6) withFilter(_>2) withFilter(_ %2 == 0) foreach println
//
//		println(t groupBy(_%3))

//		val words = Traversable("a trait for traversable collections".split("\\W+"):_*)
//		def cmp = new Ordering[String]{
//			override def compare(x: String, y: String): Int = x.length - y.length
//		}
//		println(words.max(cmp))
//		println(words.min(cmp))
//		println(words.maxBy(_.length))
//		println(words.minBy(_.length))

//		val res = new Array[String](words.size)
//		val result = words.copyToArray(res)
//		for(i <- result) println(i)
//		println(result)
//		val t =Traversable(1 to 5 :_*)
//		println(t.toArray)
//		val result = new Array[Int](t.size)
//		t.copyToArray(result)
//		println(result.mkString(" ,"))
//		val buffer = mutable.Buffer[Int]()
//		t.copyToBuffer(buffer)
//		println(buffer)
//
//		println(t.repr)

//		val t3 = Traversable.fill(10)(Random.nextInt(100))
//		println(t3)
//
//		val t4 = Traversable.iterate(1,5)(_+1)
//		println(t4)

//		val t = Traversable(1 to 10000000:_*)
//		val part = t.par
//		val seqt = t.seq
//		part.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(50))
//
//		System.gc()
//		var time = System.currentTimeMillis()
//		println(part.sum)
//		println(System.currentTimeMillis()-time)
//
//		System.gc()
//		time = System.currentTimeMillis()
//		println(seqt.sum)
//		println(System.currentTimeMillis()-time)

//		val t = Iterable(1 to 16:_*)
//		val result = t.grouped(5)
//		result.foreach(println)

//		val t = Seq( 1 to 10:_*)
//		println(t.indexWhere(x => (x %2==0 ) && x < 5))
//		println(t.indexWhere(x => (x %2==0 ) && x < 5,2))
//
//		println(t.indexOfSlice(Seq(7,8)))
//		println(t.indexOfSlice(Seq(7,8),7))
//
//		println(t.prefixLength(_<5))
//		println(t.segmentLength(_<5,2))
//
//		println(t.patch(0,Seq(10,11,12,13,14),1))

//		val t = Seq.fill(30)(Random.nextInt(100))
//		println(t.sorted)
//		println(t.sortBy(x => (x.toString.length,x)))
//		println(t.sortWith(_ - _ < 0))

//		val t1 = Seq('A','B','C')
//		val t2 = t1.map(_.toInt)
//
//		println(t1)
//		println(t2)
//
//		println(t1.corresponds(t2)(_ == _.toChar))

//		val t1 = Seq(1,2,3,4)
//		val p = t1.permutations
//		p.foreach(println)
//		println("---------------------")
//		val c = t1.combinations(3)
//		c.foreach(println)

//		val s1 = Seq(1 to 5:_*)
//		val s2 = Seq(6 to 10:_*)
//		println(s1.andThen(s2)(2))
//		println(s2(s1(2)))
//		println(s1.applyOrElse(2,(_:Int)=>Int.MaxValue))
//		println(s1.applyOrElse(20,(_:Int)=>Int.MaxValue))
//
//		println(s1.orElse(s2)(2))
//
//		println(s1.lift(2))
//		println(s1.lift(20))
//
//		println(s1.compose((x:String)=>x.length-1)("hello"))
//
//		println("-----------------------------")
//		println(s1.runWith(println)(2))
//		println(s1.runWith(println)(3))

//		def f = (x:String)=>x.length-1
//		println(f("hello"))

//		val set = Set(1,2,3,4,5)
//		println(Set(1,4).subsetOf(set))
//		println(set.contains(2))
//		println(set(22))
//		println(set)
//		println(set ++ Set(4,5,6,7))

//		set --= Traversable(1,2,3)
//		println(set)

//		val s = mutable.SortedSet(5,4,3,2,1,0)
//		println(s)
//		val s1 = mutable.SortedSet(5,4,3,2,1)(Ordering[Int].reverse)
//		println(s1)

		def printline(s:String){println(s"-------------------------------- $s ---------------------------")}
//		var map = Map("1"->1,"2"->2,"3"->3).withDefaultValue(-1)
//		val mmap = mutable.Map("1"->1,"2"->2,"3"->3)
//
//		println(map.getOrElse("3",-1))
//		println(map.getOrElse("33",-1))
//		println(map("11"))
//
//		println(mmap.getOrElseUpdate("4",4))
//		println(mmap)
//
//		printline("remove")
//		map -= "1"
//		map -= "2"
//		println(map)
//		printline("filter")
//		println(mmap.filterKeys(_ == "2"))
//
//		val m = Map("a"->1,"b"->2,"c"->3)
//		println(m.transform((x,y)=>(x,x+"_"+y)))

//		val m = Map("a"->1,"b"->2,"c"->3)
//		println(m.andThen(_*100)("b"))
//		println(m.applyOrElse("b",(_:String)=>99))
//		println(m.applyOrElse("bb",(_:String)=>99))
//
//		println(m.map(n=>(n._2,n._1)))
//
//		printline("reverse")
//
//		val hashmap = mutable.HashMap("a"->1,"b"->2,"c"->3,"d"->2)
//		println(hashmap.groupBy(_._2).mapValues(_.keys))
//
//		printline("hashmap")

//		val set = Set("a","b","c")
//		println(set.zip(Stream.from(1)).toMap)

//		val lru = new LRUCache[String,Int](10)
//		for(i <- 1 to 20){
//			lru.put("a"+i,i)
//		}
//		println(lru)

//		val m = new mutable.HashMap[String,mutable.Set[String]]() with  mutable.MultiMap[String,String]
//		m.addBinding("friuts","apple1")
//		m.addBinding("friuts","apple2")
//		m.addBinding("friuts","apple3")
//		m.addBinding("friuts","apple4")
//		m.addBinding("meat","beaf")
//		println(m)
//		println(m.removeBinding("meat"))


//		val a = new Array[String](5)
//		println(a.length)

//		val aa = Array.ofDim[Int](2,3)
//		println(aa.length)
//		println(aa(0).length)

//		val a = Array(1 to 5:_*)
//		a(0) = 100
//		a.update(1,200)
//		println(a.mkString(" ,"))
//
//		val b = Array(10 to 15:_*)
//		println(Array.concat(a,b).mkString(" ,"))

//		val  t1 = Array(1 to 5:_*)
//		val t2 = Array.ofDim[Int](6)
//		Array.copy(t1,0,t2,0,5)
//		println(t2.mkString(" ,"))

//		val t = Array.range(1,5)
//		println(t.deep.toString())
//
//		val t2 = Array.iterate(1,10)(_*10)
//		println(t2.deep.toString())
//
//		val t3 = Array.fill(3,2)(Random.nextInt(100))
//		println(t3.deep.toString())

//		import scala.collection.Searching.search
//		val a = Array(1,2,3,4,5)
//		println(a.search(30))
//		println(a.deep.toString())

//		val maps = Map("a"->1,"b"->2,"c"->3,"d"->2)
//		println(maps)
//		println(maps.groupBy(_._2).mapValues(_.keys).foreach(println))

//		val regex = """(\d\d)-(\d\d)-(\d\d\d\d)""".r("month","day","year")
//		println(regex.findFirstMatchIn("10-01-1949").get.group("year"))
//		println(regex.findFirstMatchIn("10-01-1949").get.group("month"))
//		println(regex.findFirstMatchIn("10-01-1949").get.group("day"))

//		val date = """(\d\d)-(\d\d)-(\d\d\d\d)""".r
//		"10-01-1949" match {
//			case date(month,day,year) => println(s"$month - $day - $year")
//			case _ => println("no")
//		}

//		val s = "hello\nworld\r\n"
//		println(s)
//		println(s.length)
//		println(s.stripLineEnd)
//		println(s.stripPrefix("he"))
//		println(s.stripSuffix("ld"))

//		val name = "yangyang"
//		println(s"hello\n${name} and ${name.length}")
//		println(raw"hello\n${name} and ${name.length}")

//		val name = "yangyang"
//		val height = 2.26d
//		val person = json"""{ "name":"$name","height":"$height"}"""
//		println(person.get)
//
//		val js = """{ "name":"yangyang","height":"2.26"}"""
//		println(JSON.parseFull(js))

//		val b = mutable.Buffer(1 to 10:_*)
//		println(b :+ 4)
//		println(4 +: b)
//		println(b -= 1)
//		println(b --= Seq(2,3))

//		b.trimStart(2)
//		b.trimEnd(2)
//
//		println(b)

		var list = List(7,9,8,5,4,6,1,2,0,3)

//		def quickSort(xs: List[Int]): List[Int] = {
//			if (xs.isEmpty) xs
//			else
//				quickSort(xs.filter(x=>x<xs.head)):::xs.head::quickSort(xs.filter(x=>x>xs.head))
//		}
//		def quickSort(list: List[Int]):List[Int] = {
//			if(list.isEmpty) list
//			else quickSort(list.filter(_<list.head)):::list.head::quickSort(list.filter(_>list.head))
//		}
//
//		println(quickSort(list))

//		def quicksort(list: List[Int]):List[Int] = list match {
//			case Nil => Nil
//			case pivot::tail => {
//				val (less,greater) = tail.partition(_<pivot)
//				quicksort(less) ::: pivot :: quicksort(greater)
//			}
//		}
//		def isSorted(list: List[Int]) = list.isEmpty || list.view.zip(list.tail).forall(x=>x._1 < x._2)
//
//		println(isSorted(list))
//		list = quicksort(list)
//		println(list)
//		println(isSorted(list))

//		val t = Seq(Some(1),None,Some(3))
//		println(t.flatMap(x=>x))

//		val t = mutable.Stack(0,1,2,3,4,5)
//		println(t.size)
//		println(t.pop())
//
//		t.push(6)
//		t.push(7,8,9,10)
//
//		println(t.top)
//		println(t.size)
//		t(0) = 998
//		println(t)

//		class Person(val age:Int) extends Ordered[Person]{
//			override def compare(that: Person): Int = age - that.age
//
//			override def toString: String = s"Person($age)"
//		}
//
//		implicit def myord = new Ordering[Person]{
//			override def compare(x: Person, y: Person): Int = y compareTo x
//		}
//
//		val queue = mutable.PriorityQueue[Person]()
//		queue.enqueue(new Person(12))
//		queue.enqueue(new Person(20))
//		queue.enqueue(new Person(18))
//		queue.enqueue(new Person(36))
//		queue.enqueue(new Person(25))
//
//		println(queue)
//		println(queue.dequeueAll)

//		val s = 1 #:: 2 #:: 3 #:: Stream.empty[Int]
//		println(s)
//		println(s.tail.head)

//		def fibFrom(a:BigInt,b:BigInt):Stream[BigInt] = a#::fibFrom(b,a+b)
//		val fibs = fibFrom(1,1)
//		println(fibs.head)
//		println(fibs.drop(5).head)
//
//		def ffibs:Stream[BigInt] = BigInt(1)#::BigInt(1)#::fibs.zip(fibs.tail).map(n=>n._1+n._2)
//		println(ffibs(5))

//		def unifilted(i:Int):Stream[Int] = i #:: unifilted(i+1)
//		unifilted(1).filter(_!=3) take 5 foreach println
//
//		val s = Stream.continually(Random.nextInt(100))
//		s take 10 foreach println

//		Stream.from(1,10) take 10 foreach println

//		def merge(as:Stream[Int],bs:Stream[Int]):Stream[Int] = (as,bs) match {
//			case (Stream.Empty,bss)=>bss
//			case (ass,Stream.Empty) => ass
//			case (a#::ass,b#::bss) => if(a<b) a #:: merge(ass,bs)
//				else b #:: merge(as,bss)
//		}
//		val as = Stream.fill(5)(Random.nextInt(50)).sorted
//		val bs = Stream.fill(5)(Random.nextInt(50)).sorted
//		val merged = merge(as,bs)
//		merged take 10 foreach println

//		val lists = List(1 to 100000:_*).par
//		var sum = new LongAdder()
//		lists.foreach(sum.add(_))
//		println("sum: "+sum)

		println("hello scala")

	}

	implicit class JsonHelper(val sc:StringContext) extends AnyVal{
		def json(args:Any*):Option[Any] = {
			println("b: "+args)
			val s = sc.s(args:_*)
			println("s: "+s)
			JSON.parseFull(s)
		}
	}
	class LRUCache[A,B](cachesize:Int) extends mutable.LinkedHashMap[A,B]{
		override def put(key: A, value: B): Option[B] = {
			while (size >= cachesize){
				remove(firstEntry.key)
			}
			super.put(key, value)
		}
	}
}
