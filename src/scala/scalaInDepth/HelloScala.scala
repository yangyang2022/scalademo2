package scalaInDepth

import java.io._
import java.nio.file.{Files, Paths, StandardCopyOption}
import java.security.{AccessController, PrivilegedAction}
import java.util
import java.util.concurrent.Callable
import java.util.stream.Collectors

import scala.annotation.tailrec
import scala.collection.generic.CanBuildFrom
import scala.collection.{IterableLike, SeqLike, TraversableOnce, mutable}
import scala.collection.mutable.ArrayBuffer
import scala.collection.parallel.immutable.ParVector
import scala.io.Source
import scala.reflect.ClassTag
import scala.reflect.io.Streamable.Bytes
import scala.tools.reflect.WrappedProperties.AccessControl
import scala.util.control.NonFatal

/**
  * Created by syy on 2017/5/12.
  */
object HelloScala {

	class Parent{
		def foo(bar:Int = 1,baz:Int = 2):Int = bar+baz
	}
	class Child extends Parent{
		override def foo(baz: Int = 3, bar: Int = 4): Int = super.foo(bar, baz)
	}

	trait Animal{
		def talk:String
	}
	trait Dog extends Animal{
		 def talk: String = "Woof"
	}
	trait Cat extends Animal{
		 def talk: String = "Meow"
	}
	import java.io.{File=>JFile}
	class FileWrapper(val file:JFile){
		def /(next:String) = new FileWrapper(new JFile(file,next))
		override def toString: String = file.getCanonicalPath
	}
	object FileWrapper{
		implicit def wrap(file: JFile):FileWrapper = new FileWrapper(file)
		implicit def unwrap(wrapper: FileWrapper):File = wrapper.file
	}

	def main(args: Array[String]): Unit = {

//		println(System.getProperty("java.io.tmpdir"))
//		val p = new Parent
//		println(p.foo()) //3
//		val c = new Child
//		println(c.foo()) //7
//		val y:Parent = new Child
//		println(y.foo()) //?
//		println(c.foo(bar = 1))//4
//		println(y.foo(bar = 1)) //4 error ==> 5

//		val kittydog = new Cat with Dog
//		println(kittydog.talk)

//		val list = List.range(1,11)
//		println(list.filter(_%3==0))
//
//		println(List.range(1,10).reduceRight(_-_))
//		println((List.range(1,10) :\ 0)(_+_))
//		println((0 /: List.range(1,10))(_+_))

//		def answer:PartialFunction[Int,Int] = {case d:Int if d !=0 => 42 /d}
//		println(answer(0))

//		def filter(xs:List[Int],pred:Int=>Boolean) :List[Int] = {
//			if(xs.isEmpty) xs
//			else if(pred(xs.head)) xs.head::filter(xs.tail,pred)
//			else filter(xs.tail,pred)
//		}
//		def divideBy(n:Int)(x:Int) = (x % n) == 0
//		val nums = List.range(1,9)
//
//		println(filter(nums,divideBy(2)))
//		println(filter(nums,divideBy(3)))

//		def test[A,B](f:A=>B) = new (A=>B){
//			def apply(x:A):B = f(x)
//		}
//		def memorize[A,B](f:A=>B) = new (A=>B){
//			val cache = scala.collection.mutable.Map[A,B]()
//			def apply(x:A):B = cache.getOrElseUpdate(x,f(x))
//			cache.foreach{case (k,v) => println(k+" -> "+v)}
//		}
//
//		def adder(x:Int) = x + 1
//
//		def e = (x:Int) => x+2
//
//		def namehsah = memorize(adder)
//		def tt = test(e)
//
//		println(tt(1))
//		println(tt(2))
//		println(tt(3))

//		println(namehsah(1))
//		println(namehsah(2))
//		println(namehsah(3))


//		def isPali(s:String) = s.reverse == s
//		def findPali(seq: Seq[String]) = seq find isPali
//		def words = Seq("hello","helleh1","e","nameman")
//
//		println(findPali(words.view take 10))
//
//		val theUrl = ""
//		val head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//		val xmlString = Source.fromURL(theUrl).mkString
//		import scala.xml._
//		val xml = XML.load(head+xmlString)
//
//		val city = xml \\ "location" \\ "@city"
//		val state = xml \\ "location" \\ "@region"
//		val temp = xml \\ "condition" \\"@temp"
//
//		println(city+" ,"+ state+" ,"+temp)


//		def dividby(n:Int)(x:Int) = x / n
//		def dividby2 = dividby(2)(_)
//		println(dividby2(10))
//
//		def f1 = (i:Int,j:Int)=>Math.pow(i,j)
//		def f2 = (i:Int,j:Int) => {
//			var sum:Int = 1
//			Range(1,j+1).foreach(sum *= _)
//			sum
//		}
//		List(f1,f2).foreach(e=>println(e(2,5)))

//		def filter(xs:List[Int],p:Int=>Boolean):List[Int] = {
//			if(xs.isEmpty) xs
//			else if(p(xs.head)) xs.head :: filter(xs.tail,p)
//			else filter(xs.tail,p)
//		}
//
//		def dividby(n:Int)(x:Int) = (x % n) == 0
//
//		println(filter(List.range(1,10),dividby(2)))
//		println(filter(List.range(1,20),dividby(3)))

//		def quicksort(xs:List[Int]):List[Int] =
//			if(xs.isEmpty) xs
//				else quicksort(xs.filter(_<xs.head)):::xs.head::quicksort(xs.filter(_>xs.head))
//		println(quicksort(List(3,8,5,2,1,6)))

//		def print(n:Int): Unit ={
//			if(n < 10000){
//				println(n)
//				print(n<<1)
//				println(n)
//			}
//		}
//		print(1237)

//		implicit val x:Int = 998
//		def findAnInt(implicit x:Int) = x
//		println(findAnInt)
//		println(findAnInt(122))

//		trait Foo
//		object Foo{
//			implicit val x = new Foo {
//				override def toString: String = "Companion Foo"}

//			implicit val list = List(new Foo {})
//		}

//		implicit val y = new Foo {
//			override def toString: String = "root foo ... "}
//
//		def method(implicit foo: Foo) = println(foo)
//		method(new Foo {
//			override def toString: String = "my foo ... "})
//		implicitly[List[Foo]].foreach(println)
//
//		trait BinaryFormat[T]{
//			def asBinary(entity:T) :Array[Byte]
//		}
//		trait Foo{}
//		object Foo{
//			implicit val binaryFormat = new BinaryFormat[Foo] {
//				override def asBinary(entity: Foo): Array[Byte] = "serializedFoo".getBytes
//			}
//		}
//
//		println(Foo.binaryFormat.toString)

//		object Foo{
//			trait Bar
//			implicit def newbar = new Bar {
//				override def toString: String = "Implicite bar ... "
//			}
//		}
//		println(implicitly[Foo.Bar])
//		println(Foo.newbar)

//		trait Foo
//		implicit def intToString(x:Int): String = x.toString
//
//		implicit def stringToFoo(s:String) = new {def foo():Unit = println("i am foo ... ")}
//
//		def foo(msg:String) = println(msg)
//		foo(55)
//		"hello".foo()

//		import FileWrapper.wrap
//		val cur = new JFile(".")
//		println(cur)
//		val newfile = cur / "temp.txt"
//		val add = Files.readAllLines(Paths.get(newfile.toString)).add("hello")
//		println(newfile)
//		Files.createFile(newfile.toPath)
//
//		class Matrix(private val repr:Array[Array[Double]]) {
//			def row(idx:Int):Seq[Double] = repr(idx)
//			def col(idx:Int):Seq[Double] = {
//				repr.foldLeft(ArrayBuffer[Double]()){
//					(buffer,currentRow)=>buffer.append(currentRow(idx))
//						buffer
//				}.toArray
//			}
//			lazy val rowRank = repr.length
//			lazy val colRank = if(rowRank>0) repr(0).length else 0
//
//			override def toString: String = "Matrix"+repr.foldLeft(""){
//				(msg,row)=>msg+row.mkString("\n|"," | ","|")
//			}
//		}
//
//		val m = new Matrix(Array(Array(1,2,3),Array(3,4,5),Array(6,7,8)))
//		println(m)
//		println(m.row(1))
//		println(m.col(1))
//		println(m.rowRank+" ,"+m.colRank)

//		class Outer{
//			trait Inner
//			def y = new Inner {}
//			def foo(x:this.Inner) = null
//			def bar(x:Outer#Inner) = println("hello ... ")
//		}
//
//		val x = new Outer
//		val y = new Outer
//		println(x.y)
//		println(x.foo(x.y))
////		println(x.foo(y.y))
//		println(x.bar(y.y))

//		val filepath = "C:\\mavenProject\\scalademo2\\pom.xml"
//		object manage{
//			def apply[R<:{def close():Unit},T](resource: =>R)(f:R=>T) = {
//				try f(Some(resource).get)
//				finally {
//					if(resource != null) {
//						println("file closeing ... ")
//						resource.close()
//					}
//				}
//			}
//		}
//		manage(Files.lines(Paths.get(filepath))){lines =>{
//				val l = lines.collect(Collectors.joining("\n"))
//				println(l)
//			}
//		}

//		object Resources{
//			type Resource = {def close():Unit}
//			def closeResource(r:Resource) ={println("closing ... "); r.close()}
//		}
//		Resources.closeResource(System.in)

//		type T = {
//			type X = Int
//			def x:X
//			type Y
//			def y:Y
//		}
//		object Foo{
//			type X = Int
//			def x:X = 5
//			type Y = String
//			def y:Y = "Hello World"
//		}
//
//		def test(t:T):T#X = t.x

//		class A{
//			type B >: List[Int]
//			def foo(a:B) = println(a)
//		}
//
//		val x = new A{type B = Traversable[Int]}
//		x.foo(Set(1,2,3,4))

//		class A{
//			type B <: Traversable[Int]
//			def count(b: B) = b.foldLeft(0)(_+_)
//		}
//		val x = new A{type B = List[Int]}
//		val count = x.count(List(1,2,3,4,5))
//		println(s"count: ${count}")
//
//		val y = new A{type B = Set[Int]}
//		val count2 = y.count(Set(1,2,3,4))
//		println(s"count2: ${count2}")
//
//		type Callback[T] = (T) => Unit
//		val x:Callback[Int] = y=>println(y+1)
//		x(9)
//
//		def foo[M[_]](f:M[Int]) = f
//		val xx = foo[Callback](x)
//		xx(123)

//		def foo2[M[_,_]](f:M[Function1]) = f

//		val yy = foo[({type X[Y] = (Y) => Unit})#X]((x:Int)=>println(x+3))
//		yy(123)


//		class T[+A]{}
//		val x = new T[AnyRef]
//		val y:T[Any] = x
//		val yy:T[String] = x
//		trait T[-A]{
//			def dd(a: A) = a
//		}

//		def foo(x:Any):String = "Hello I recevie a "+x
//		def bar(x:String):Any = foo(x)
//		println(foo(123))
//		println(bar("enhen?"))

//		trait Func[-Arg,+Return]
//		val x = new Func[Any,String] {}
//		val y:Func[String,Any] = x

//		trait List[+T]{
//			def ++[A>:T](other:List[A]):List[A]
//		}
//		class EmptyList[T] extends List[T]{
//			def ++[A>:T](other:List[A]): List[A] = other
//		}
//
//		val s = new EmptyList[String]
//		val ints = new EmptyList[Int]
//		val any = new EmptyList[Any]
//		val anyRef = new EmptyList[AnyRef]
//
//		println(s++ints)
//		println(s++any)
//		println(s++anyRef)

//		def first[A](x:Traversable[A]) = (x.head,x)
//		println(first(Array(1,2)))

//		def first[A:ClassTag](x:Array[A]) = Array(x(0))
//		println(first(Array(998,2,3,4)).apply(0))
//
//		val x:Array[_] = Array(123,2)
//		println(first(x).apply(0))

//		def foo[A](col:List[A])(f:A=>Boolean) = null
//		foo(List("string"))(_.isEmpty)

//		def peek[A,C <: Traversable[A]](coll: C) = (coll.head,coll)
//		println(peek(List(1,2,3)))
//
//		def peek2[C,A](coll:C)(implicit ev: C <:< Traversable[A]) = (coll.head,coll)
//		println(peek2(List(1,2,3)))

//		implicit object StringNumberic extends Numeric[String]{
//			override def plus(x: String, y: String): String = x+y
//
//			override def zero: String = ""
//
//			override def minus(x: String, y: String): String = ???
//
//			override def times(x: String, y: String): String = ???
//
//			override def negate(x: String): String = ???
//
//			override def fromInt(x: Int): String = ???
//
//			override def toInt(x: String): Int = ???
//
//			override def toLong(x: String): Long = ???
//
//			override def toFloat(x: String): Float = ???
//
//			override def toDouble(x: String): Double = ???
//
//			override def compare(x: String, y: String): Int = ???
//		}
//
//		println(List("one","two","three").sum)

//		trait FileLike{
//			def name:String
//			def exists:Boolean
//			def isDirectory:Boolean
//			def children:Seq[FileLike]
//			def child(name:String):FileLike
//			def mkdirs():Unit
//			def content:InputStream
//			def writeContent(otherContent:InputStream):Unit
//		}
//		object SyncUtils{
//
//			def synchronize(from:FileLike,to:FileLike):Unit = {
//
//				def syncFile(file1:FileLike,file2:FileLike):Unit = {
//					file2.writeContent(file1.content)
//				}
//
//				def syncDirectory(dir1:FileLike,dir2:FileLike):Unit = {
//					def findFile(file:FileLike,dir:FileLike):Option[FileLike] = {
//						dir.children.find(e => e.name == file.name)
//					}
//					for(file1 <- dir1.children){
//						val file2 = findFile(file1,dir2).getOrElse(dir2.child(file1.name))
//						if(file1.isDirectory) file2.mkdirs()
//						syncFile(file2,file1)
//					}
//				}
//				if(from.isDirectory){
//					syncDirectory(from,to)
//				}else{
//					syncFile(from,to)
//				}
//			}
//		}
// ========================================= important ========================================
//		trait FileLike[T] {
//			def name(file:T):String
//			def isDirectory(file:T):Boolean
//			def children(directory:T):Seq[T]
//			def child(parent:T,name:String):T
//			def mkdirs(file:T):Unit
//			def content(file:T):InputStream
//			def writeContent(file:T,otherContent:InputStream):Unit
//		}
//		def synchronize[F:FileLike,T:FileLike](from:F,to:T):Unit = {
//
//			val fromhelper = implicitly[FileLike[F]]
//			val tohelper = implicitly[FileLike[T]]
//
//			def syncFile(file1:F,file2:T):Unit = {
//				tohelper.writeContent(file2,fromhelper.content(file1))
//			}
//
//			def syncDirs(dirs1:F,dirs2:T):Unit = {
//
//				def findFile(file:F,dir:T):Option[T] = tohelper.children(dirs2).find(e => fromhelper.name(file).equals(tohelper.name(e)) )
//
//				for(file1 <- fromhelper.children(dirs1)){
//					val file2 = findFile(file1,dirs2).getOrElse(tohelper.child(dirs2,fromhelper.name(file1)))
//					if(fromhelper.isDirectory(file1)){
//						tohelper.mkdirs(file2)
//					}
//					synchronize[F,T](file1,file2)
//				}
//			}
//			if(fromhelper.isDirectory(from)){
//				syncDirs(from,to)
//			}else{
//				syncFile(from,to)
//			}
//		}
//		object FileLike{
//
//			implicit  val ioFileLike = new FileLike[JFile] {
//				override def name(file: JFile): String = file.getName
//
//				override def isDirectory(file: JFile): Boolean = file.isDirectory
//
//				override def children(directory: JFile): Seq[JFile] = directory.listFiles()
//
//				override def child(parent: JFile, name: String): JFile = new JFile(parent,name)
//
//				override def mkdirs(file: JFile): Unit = file.mkdirs()
//
//				override def content(file: JFile): InputStream = new FileInputStream(file)
//
//				override def writeContent(file: JFile, otherContent: InputStream): Unit =
//					Files.copy(otherContent,file.toPath,StandardCopyOption.REPLACE_EXISTING)
//			}
//		}

//		synchronize(new JFile("c:\\code\\groovy"),new JFile("c:\\code\\hscode"))
	// ========================================= important ========================================

//		trait Sorter[A,B]{
//			def sort(a:A): B
//		}
//		def sort[A,B](coll:A)(implicit sorter:Sorter[A,B]) = sorter.sort(coll)

// ========================================= scala collection ========================================

		val filepath = "c:\\code\\data2.txt"

//		class FileLineTraversable(file: JFile) extends Traversable[String]{
//			override def foreach[U](f: (String) => U): Unit = {
//				val input = new BufferedReader(new FileReader(file))
//				try {
//					var line = input.readLine()
//					while (line != null){
//						f(line)
//						line = input.readLine()
//					}
//				}finally {
//					println("file closing ... ")
//					input.close()
//				}
//			}
//
//			override def toString(): String = s"{Lines of ${file.getAbsolutePath}"
//		}
//
//		val x = new FileLineTraversable(new JFile(filepath))
//		val y = for{line <- x ; word <- line.split("\\s+")} yield word
//		println(y)

//		val names = Iterable("Jsoh","Jim")
//		val address = Iterable("Anhui","Beijing")
//
//		val n = names.iterator
//		val a = address.iterator
//		while(n.hasNext && a.hasNext){
//			println(n.next() +" lives at : "+a.next())
//		}

//		val seq = Seq(2,1,30,-2,20,1,2,0)
//		println(seq.sliding(2).map(_.sum).toList)
//		println(seq.tails map (_.take(2)) filter(_.length>1) map (_.sum) toList)

//		val encodes = Map(1->"One",2->"Two",3->"Three").withDefaultValue("not existed")
//		println(List(1,2,5,6) map encodes)


//		val s = 1#::{println("Hi");2}#::{println("Hello");3}#::Stream.Empty
//		println(s)
//		println(s(3))

//		def fibss(a:Int):Int = if(a<2) 1 else fibss(a-1)+fibss(a-2)
//		def fibs = {
//			def f(a:Int,b:Int):Stream[Int] = a#::f(b,a+b)
//			f(0,1)
//		}
//		println(fibs(50))
//		println(fibss(50))
//		fibs take(20) foreach println

//		val p = (1 to 1000).par.foldLeft(Set[String]()){
//			(set,value) => set+Thread.currentThread().getName
//		}
//		println(p)
//
//		(1 to 10).par map {e => Thread.sleep(1000);Thread.currentThread().getName} foreach println
//
//		println(Runtime.getRuntime.availableProcessors())

//		object QuickSort{
//			def sort[T](a:Iterable[T])(implicit n:Ordering[T]):Iterable[T] = {
//				if(a.size < 2) a
//				else {
//					import n._
//					val pivot = a.head
//					sort(a.filter(_ < pivot)) ++ sort(a.filter(_ == pivot)) ++ sort(a.filter(_ > pivot))
//				}
//			}
//		}
//		println(QuickSort.sort(List(5,3,4,2,1)))

//================================================= sort =================================
//		trait Sortable[A]{
//			def sort(a:A):A
//		}
//		object Sorter{
//			def sort[Coll](coll: Coll)(implicit s:Sortable[Coll]) = s.sort(coll)
//		}
//		trait GenericSortTrait{
//			implicit def quickSort[T,Coll](implicit
//			                               ev0: Coll <:< IterableLike[T,Coll],
//			                               cbf: CanBuildFrom[Coll,T,Coll],n:Ordering[T]) = new Sortable[Coll] {
//				override def sort(a: Coll): Coll = if(a.size < 2) a
//				else {
//					import n._
//					val pivot = a.head
//					val (lower:Coll,tmp:Coll) = a.partition(_<pivot)
//					val (upper:Coll,same:Coll) = tmp.partition(_>pivot)
//					val b = cbf()
//					b.sizeHint(a.size)
//					b ++= sort(lower)
//					b ++= same
//					b ++= sort(upper)
//					b.result()
//				}
//			}
//		}
//		object Sortable extends GenericSortTrait

//		println(Sorter.sort(List(5,4,3,1,2)))

//		object Now{
//			def sayHello = println("say hello ... ")
//		}
//		object Simulate{
//			def once(behavior: =>Unit) = new {def right(now: Now.type ):Unit = println("now: "+now.sayHello)}
//		}
//		Simulate once{println("simulate ... ")} right Now

//		object Resources{
//			def closeResource(r:{def close():Unit}) = {println("closing file ... ");r.close()}
//		}
//		val fileReader = new FileReader(new JFile("c:\\code\\data2.txt"))
//		Resources.closeResource(fileReader)

//		type T = {
//		    type X = Int
//		    def x:X
//		    type Y
//		    def y:Y
//		}
//		object foo{
//			type X = Int
//			def x:X = 5
//			type  Y = String
//			def y:Y = "hello foo ... "
//		}
//		trait Observable{
//
//			type Handle
//
//	        private def callbacks = Map[Handle,this.type =>Unit]()
//
//	        def observe(callback:this.type =>Unit):Handle = {
//		        val handler = createHandle(callback)
//		        callbacks += (handler -> callback)
//		        handler
//	        }
//	        def unobserve(handle: Handle):Unit = callbacks -= handle
//
//	        protected def createHandle(callback: this.type =>Unit):Handle
//
//	        protected def notifyAllObservers():Unit = for(callback <- callbacks.values) callback(this)
//		}
//		trait DefaultHandle extends Observable{
//			type Handle = (this.type => Unit)
//			override protected def createHandle(callback: (DefaultHandle.this.type) => Unit): (DefaultHandle.this.type) => Unit = callback
//		}
//
//		class IntStore(private var value:Int) extends Observable with DefaultHandle {
//			def get:Int = value
//			def set(newValue:Int):Unit = {
//				value = newValue
//				notifyAllObservers()
//			}
//			override def toString: String = s"IntStore(${value})"
//		}
//
//		val x = new IntStore(5)
//		val h1 = x.observe(println)
//		x.set(22)
//		x.set(33)
//		x.unobserve(h1)
//		x.set(44)
//		println(x)


//		class A{
//			type B >: List[Int]
//			def foo(b: B) = println(b)
//		}
//		val x = new A{type B = Traversable[Int]}
//		x.foo(Set(1,2,3,4))
//
//		val y = new A{type B = Set[Int]}
//		y.foo(Set(1,2,3))

//		type callback[T] = (T) => Unit
//		val x:callback[Int] =  y => println(y+2)
//
//		def foo[M[_]](f:M[Int]) = f
//		foo[callback](x)(123)

//		def foo[M[_]](f:M[Int]) = f
//		foo[({type X[Y] = (Y)=>Unit})#X]((x:Int)=>println(x))(123)

//		def foo(x:Any):String = "hello,i get: "+x
//		def bar(x:String):Any = foo(x)

//		trait Function[-Arg,+Return]
//		val x = new Function[Any,String] {}
//
//		val y:Function[Int,Any] = x

//		trait List[+T]{
//			def ++[A >: T](other:List[A]):List[A]
//		}
//		class EmptyList[T] extends List[T]{
//			override def ++[A>:T](other: List[A]): List[A] = other
//		}
//		val ints = new EmptyList[Int]
//		val ss = new EmptyList[String]
//		ss ++ ints
//		def foo(x:List[_>:Int]) = x
//		println(foo(List(1,2,3)))


//		def peek[A,C<:Traversable[A]](coll:C) = (coll.head,coll)
//		peek(List(1,2,3))

//		def peek[A,C](coll:C)(implicit env:C <:< Traversable[A]) = (coll.head,coll)
//		println(peek(List(1,2,3)))

//		trait Sortable[A]{
//			def sort(a:A):A
//		}
//		object Sorter{
//			def sort[Coll](coll: Coll)(implicit s:Sortable[Coll]) = s.sort(coll)
//		}
//		trait GenericSortTriat{
//			implicit def quickSort[T,Coll](implicit en0:Coll <:< IterableLike[T,Coll],
//			                               cbf:CanBuildFrom[Coll,T,Coll],
//			                               n:Ordering[T]) = new Sortable[Coll] {
//				override def sort(a: Coll): Coll = if(a.size < 2) a else{
//					import n._
//					val pivot = a.head
//					val (lower:Coll,tmp:Coll) = a.partition(_ < pivot)
//					val (upper:Coll,same:Coll) = tmp.partition(_ > pivot)
//					val b = cbf()
//					b.sizeHint(a.size)
//					b ++= sort(lower)
//					b ++= same
//					b ++= sort(upper)
//					b.result()
//				}
//			}
//		}
//		trait ArraySortTrait{
//			implicit def arraySort[T](implicit ct:ClassTag[T],n:Ordering[T]):Sortable[Array[T]] = (a: Array[T]) => {
//				import n._
//				val b = a.clone()
//				var i = 0
//				while (i < a.length) {
//					var j = i
//					while (j > 0 && b(j - 1) > b(j)) {
//						val tmp = b(j)
//						b(j) = b(j - 1)
//						b(j - 1) = tmp
//						j -= 1
//					}
//					i += 1
//				}
//			}
//		}
//		object Sortable extends ArraySortTrait with GenericSortTriat
//
//		println(Sorter.sort(Array(1,2,5,4)))

//		object manage{
//			def apply[R<:{def close():Unit},T](resource: =>R)(f:R=>T) = {
//				var res:Option[R] = None
//				try{
//					res = Some(resource)
//					f(res.get)
//				}catch {
//					case NonFatal(e) => println(s"Non fatal exception: ${e.getMessage}")
//				}finally {
//					println("closing ... ")
//					if(res.isDefined) res.get.close()
//				}
//			}
//		}
//		manage(Files.lines(Paths.get("c:\\code\\data2.txt"))){lines =>
//			lines.forEach(e=>System.out.println(e))
//		}
//		manage(Source.fromFile(filepath)){lines =>
//			println(lines.mkString)
//			val size = lines.getLines().size
//			println(s"file $filepath has $size lines ")
//			if(size > 5) throw new RuntimeException("Big File ... ")
//		}
//		@tailrec
//		def continue(cond: =>Boolean)(body: =>Unit): Unit ={
//			if(cond){
//				body
//				continue(cond)(body)
//			}
//		}
//		var count = 0
//		continue(count < 5){
//			println(s"count: $count")
//			count += 1
//		}

//		object Serialization{
//			case class Rem[A](a: A){
//				def serialized:String = s"-- $a --"
//			}
//			type Writable[A] = A => Rem[A]
//
//			implicit val fromInt:Writable[Int] = (i:Int) => Rem(i)
//			implicit val fromFloat:Writable[Float] = (f:Float) =>Rem(f)
//			implicit val fromString:Writable[String] = (s:String) =>Rem(s)
//		}
//		import Serialization._
//		object RemoteConnection{
//			def write[T:Writable](t:T):Unit = println(t.serialized)
//		}
//		RemoteConnection.write(100)
//		RemoteConnection.write(100.11f)
//		RemoteConnection.write("hello scala")

		trait Add[T]{
			def add(t1:T,t2:T):T
		}
		object Add{
			implicit val addInt = new Add[Int] {
				override def add(t1: Int, t2: Int): Int = t1+t2
			}
			implicit val addPair = new Add[(Int,Int)]{
				override def add(t1: (Int, Int), t2: (Int, Int)): (Int, Int) = (t1._1+t2._1,t2._2+t2._2)
			}
			implicit val addString = new Add[String] {
				override def add(t1: String, t2: String): String = t1.concat(t2)
			}
		}
		val seq = Seq(1,2,3,4,5)
		val sum2 = seq.reduce(implicitly[Add[Int]].add(_,_))
//		println(s"sum2 $sum2")
//		def sumSeq[T:Add](seq: Seq[T]):T = seq reduce(implicitly[Add[T]].add(_,_))
//
//		case class MyList[A](list: List[A]){
//			def sortedBY[B:Ordering](f:A=>B):List[A] = list.sortBy(f)(implicitly[Ordering[B]])
//		}

//		println(sumSeq(1 to 10))
//		println(sumSeq(Vector(1->10,2->20,3->30)))
//		println(sumSeq(Seq("hello","world","shen","yangyang")))

//		import scala.language.higherKinds
//		trait Reduce[T,-M[_]]{
//			def reduce(seq: M[T])(f:(T,T)=>T):T
//		}
//		object Reduce{
//			implicit def seqReduce[T] = new Reduce[T,Seq] {
//				override def reduce(seq: Seq[T])(f: (T, T) => T): T = seq reduce f
//			}
//			implicit def optionReduce[T] = new Reduce[T,Option] {
//				override def reduce(opt: Option[T])(f: (T, T) => T): T = opt reduce f
//			}
//		}
//		def sum[T:Add,M[_]](container:M[T])(implicit red: Reduce[T,M]):T = red.reduce(container)(implicitly[Add[T]].add(_,_))
//
//		trait Reduce1[-M[T]]{
//			def reduce[T](m: M[T])(f:(T,T)=>T):T
//		}
//		object Reduce1{
//			implicit val seqReduce = new Reduce1[Seq] {
//				override def reduce[T](m: Seq[T])(f: (T, T) => T): T = m reduce f
//			}
//			implicit val optionReduce = new Reduce1[Option] {
//				override def reduce[T](m: Option[T])(f: (T, T) => T): T = m reduce f
//			}
//		}
//		def sum1[T:Add,M[_]:Reduce1](container:M[T]):T = implicitly[Reduce1[M]].reduce(container)(implicitly[Add[T]].add(_,_))

//		println(sum(Vector(1->10,2->20,3->30)))
//		println(sum(1 to 10))
//		println(sum(Option(998)))
//
//		println(sum1(Vector(1->10,2->20,3->30)))
//		println(sum1(1 to 10))
//		println(sum1(Option(998)))
//		println(sum[Int,Option](None))

		trait Reduce[T,-M[_]]{
			def reduce(m: M[T])(f:(T,T)=>T):T
		}
		object Reduce{
			implicit def seqReduce[T] = new Reduce[T,Seq] {
				override def reduce(m: Seq[T])(f: (T, T) => T): T = m reduce f
			}
			implicit def optionReduce[T] = new Reduce[T,Option] {
				override def reduce(m: Option[T])(f: (T, T) => T): T = m reduce f
			}
		}
		def sum[T:Add,M[_]](container:M[T])(implicit red:Reduce[T,M]) = red.reduce(container)(implicitly[Add[T]].add(_,_))

//		println(sum(Vector(1->10,2->20,3->30)))
//		println(sum(1 to 10))
//		println(sum(Option(998)))

		trait Reduce1[-M[_]]{
			def reduce[T](m: M[T])(f:(T,T)=>T):T
		}
		object Reduce1{
			implicit val seqReduce = new Reduce1[Seq] {
				override def reduce[T](m: Seq[T])(f: (T, T) => T): T = m reduce f
			}
			implicit val optReduce = new Reduce1[Option] {
				override def reduce[T](m: Option[T])(f: (T, T) => T): T = m reduce f
			}
		}
		def sum1[T:Add,M[_]:Reduce1](container:M[T]):T = implicitly[Reduce1[M]].reduce(container)(implicitly[Add[T]].add(_,_))

//		println(sum1(Vector(1->10,2->20,3->30)))
//		println(sum1(1 to 10))
//		println(sum1(Option(998)))

//
//		trait Functor[A,+M[_]]{
//			def map2[B](f:A=>B):M[B]
//		}
//		object Functor{
//			implicit class SeqFunctor[A](seq: Seq[A]) extends Functor[A,Seq]{
//				override def map2[B](f: (A) => B): Seq[B] = seq map f
//			}
//			implicit class OptionFunctor[A](opt:Option[A]) extends Functor[A,Option]{
//				override def map2[B](f: (A) => B): Option[B] = opt map f
//			}
//			implicit class MapFunctor[K,V1](mapKV1:Map[K,V1]) extends Functor[V1,({type X[Y] = Map[K,Y]})#X]{
//				override def map2[B](f: (V1) => B): Map[K, B] = mapKV1 map {
//					case (k,v) => (k,f(v))
//				}
//			}
//		}
//		import Functor._
//		println(List(1,2,3) map2(_*2))
//		println(Option(2) map2(_*2))
//		val m = Map("one"->1,"two"->2,"three"->3)
//		println(m map2(_*2))

//		trait Parent[T <: Parent[T]]{
//			def make:T
//		}
//		case class Child1(s:String) extends Parent[Child1]{
//			override def make: Child1 = Child1(s"Child1 make: $s")
//		}
//		case class Child2(s:String) extends Parent[Child2]{
//			override def make: Child2 = Child2(s"Child2 make: $s")
//		}
//		val c1 = Child1("c1")
//		val c2 = Child1("c2")
//		val c11 = c1.make
//		val c22 = c2.make
//
//		println(c1)
//		println(c2)
//		println(c11)
//		println(c22)

		trait Functor[F[_]]{ //F is a container
			def map3[A,B](fa:F[A])(f:A=>B):F[B]
		}
		object FunctionF{
			def map[A,A2,B](func:A=>A2)(f:A2=>B):A=>B = {
				val functor = new Functor[({type X[Y] = A => Y})#X] {
					def map3[A3,B](func:A=>A3)(f:A3=>B):A=>B = (a:A) => f(func(a))
				}
				functor.map3(func)(f)
			}
		}
		object SeqF extends Functor[Seq]{
			override def map3[A, B](fa: Seq[A])(f: (A) => B): Seq[B] = fa map f
		}
		object OptionF extends Functor[Option]{
			override def map3[A, B](fa: Option[A])(f: (A) => B): Option[B] = fa map f
		}
//		val fii:Int=>Int = i=>i*2
//		val fid:Int =>Double = i => i*2.1
//		val fds:Double=>String = d =>d.toString
//
//		println(SeqF.map3(List(1,2,3))(fii))
//		println(SeqF.map3(List())(fii))
//
//		println(OptionF.map3(Some(2))(fii))
//		println(OptionF.map3(None)(fii))
//
//		val fa = FunctionF.map(fid)(fds)
//		println(fa(2))
//
//		val fb = FunctionF.map[Int,Double,String](fid)(fds)
//		println(s"fb: ${fb(2)}")

//		def foo[M[_]](f:M[Int]) = f
//		foo[({type X[Y] = Y => Unit})#X]((x:Int)=>println(x))(123)

		trait Monad[M[_]]{
			def flatmap[A,B](fa:M[A])(f:A=>M[B]):M[B]
			def unit[A](a: =>A):M[A]
			//some others
			def bind[A,B](fa:M[A])(f:A=>M[B]):M[B] = flatmap(fa)(f)
			def >>=[A,B](fa:M[A])(f:A=>M[B]):M[B] = flatmap(fa)(f)
			def pure[A](a: =>A):M[A] = unit(a)
			def `return`[A](a: =>A):M[A] = unit(a)
		}
		object SeqM extends Monad[Seq]{
			override def flatmap[A, B](fa: Seq[A])(f: (A) => Seq[B]): Seq[B] = fa flatMap f
			override def unit[A](a: => A): Seq[A] = Seq(a)
		}
		object OptionM extends Monad[Option]{
			override def flatmap[A, B](fa: Option[A])(f: (A) => Option[B]): Option[B] = fa flatMap f

			override def unit[A](a: => A): Option[A] = Option(a)
		}

		val seqF:Int=>Seq[Int] = i => 1 to i
		val optf:Int=>Option[Int] = i => Option(i+1)

//		println(SeqM.flatmap(List(1,2,3,4,5))(seqF))
//		println(SeqM.flatmap(List.empty[Int])(seqF))
//		println(OptionM.flatmap(Some(9))(optf))
//		println(OptionM.flatmap(None)(optf))

		import scala.util.{Success,Failure,Try}
		type Step = Int=> Try[Int]
		val successFulSteps:Seq[Step] = List((i:Int)=>Success(i+5),(i:Int)=>Success(i+10))
		val partiallySucc:Seq[Step] = List((i:Int)=>Success(i+5),(i:Int)=>Failure(throw new RuntimeException("FAIL!!!")))

		def sumCounts(countSteps:Seq[Step]):Try[Int] = {
			val zero:Try[Int] = Success(0)
			(countSteps foldLeft zero){
				(sumTry,step)=>sumTry flatMap(i=>step(i))
			}
		}
//		println(sumCounts(successFulSteps))
//		println(sumCounts(partiallySucc))


//		val list = List(1,2,3,4,5)
//		println(list.foldRight(0)(_-_))
//		println("list: "+list.reduceRight(_-_))
//		val fl = (acc:String,x:String)=>s"($acc)-($x)"
//		val fr = (x:String,y:String)=>s"($y)-($x)"
//
//		val list2 = list.map(_.toString)
//		println(list2.reduceRight(fr))
//		println(list.reduceRight(_-_))

		object RemoveBlanks{
			def apply(path:String,compressBlank:Boolean = false): Seq[String] ={
				for{
					line <- Source.fromFile(path).getLines.toSeq
					if !line.matches("""^\s*$""")
						line2 = if(compressBlank) line.replaceAll("\\s+"," ") else line
				} yield line2
			}
		}
		val blanks = RemoveBlanks(filepath,compressBlank = true)
		blanks.foreach(println)


	}
}
