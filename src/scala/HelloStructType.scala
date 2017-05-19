import scala.io.Source
import scala.util.control.NonFatal

/**
  * Created by syy on 2017/5/4.
  */
object HelloStructType {

	trait Subject{
		type State
		type Observer = {def receiveUpdate(state: Any):Unit}
		private var observers:List[Observer] = Nil
		def addObserver(observer: Observer):Unit = observers ::= observer
		def notifyAllObservers(state: State) = observers.foreach(_.receiveUpdate(state))
	}
	object Observer{
		def receiveUpdate(state: Any):Unit = println("got one! "+state)
	}

	trait SubjectFunc{

		type State
		type Observer = State=>Unit

		private var observers:List[Observer] = Nil

		def addObserver(observer: Observer) = observers ::= observer

		def notifyAllObserver(state: State) = observers.foreach(o=>o(state))

	}
	object manage{
		def apply[R<:{def close():Unit},T](resource: =>R)(f:R=>T): Unit ={
			var res:Option[R] = None
			try {
				res = Some(resource)
				f(res.get)
			}catch {
				case NonFatal(ex) => println(s"Non fatal exception: ${ex}")
			}finally {
				if(res != None){
					println("closing resource ... ")
					res.get.close()
				}
			}
		}
	}
	def callbyName(block: =>String)={
		println(".... ")
		println(block)
	}
	def callByValue(block:()=>String)={
		println("....")
		println(block.apply())
	}
	def main(args: Array[String]): Unit = {

//		val subject =new Subject {
//			override type State = Int
//			protected var count = 0
//			def increment():Unit = {
//				count += 1
//				notifyAllObservers(count)
//			}
//		}
//
//		subject.increment()
//		subject.increment()
//		subject.addObserver(Observer)
//		subject.increment()
//		subject.increment()

//		val subject = new SubjectFunc {
//			override type State = Int
//			protected var count = 0
//			def increment():Unit = {
//				count += 1
//				notifyAllObserver(count)
//			}
//		}
//		val observer1:Int=>Unit = (state:Int)=>println("get state1: "+state)
//		val observer2:Int=>Unit = (state:Int)=>println("get state2: "+state)
//
//		subject.increment()
//		subject.increment()
//		subject.addObserver(observer1)
//		subject.addObserver(observer2)
//		subject.increment()
//		subject.increment()

//		val path = this.getClass.getClassLoader.getResource("data.txt").getPath
//		manage(Source.fromFile(path)){
//			resource =>
//				resource.getLines().foreach(println)
//		}

		callbyName("hello")
		callByValue(()=>"hello")

	}
}
