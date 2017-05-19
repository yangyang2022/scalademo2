/**
  * Created by syy on 2017/5/4.
  */
object ScalaType2 {

//	class Service{
//		class Logger{
//			def log(message:String):Unit = println(s"log: $message")
//		}
//		val logger = new Logger
//	}

	trait Logger{
		def log(message:String):Unit
	}
	class ConsoleLogger extends Logger{
		override def log(message: String): Unit = println(s"log: $message")
	}
	trait Service{
		type  Log <: Logger
		val logger: Log
	}
	class Service1 extends Service{
		override type Log = ConsoleLogger
		val logger = new ConsoleLogger
	}

	case object Foo{
		override def toString: String = "Foo says hello!!"
	}
	def printFoo(foo: Foo.type ) = println(foo)

	trait Add[T]{
		def add(t1:T,t2:T):T
	}
	object Add{
		implicit val addInt = new Add[Int] {
			override def add(t1: Int, t2: Int): Int = t1+t2
		}
		implicit val addIntPair = new Add[(Int,Int)] {
			override def add(t1: (Int, Int), t2: (Int, Int)): (Int, Int) = (t1._1+t2._1,t1._2+t2._2)
		}
	}
	def sumSeq[T:Add](seq: Seq[T]):T = seq.reduce(implicitly[Add[T]].add(_,_))

	def main(args: Array[String]): Unit = {

//		println(Foo)

		println(sumSeq(Vector(1->10,2->20,3->30)))
		println(sumSeq(1 to 10))
//		println(sumSeq(Option(2)))

		import scala.language.higherKinds

	}
}
