import HelloType.ButtonSubjectObserver

import scala.collection.mutable
/**
  * Created by syy on 2017/5/3.
  */
object HelloType {


	object Serialization{
		case class Rem[A](value:A){
			def serialized:String = s"--- $value ---"
		}
		type Writable[A] = A => Rem[A]
		implicit val fromInt:Writable[Int] = (i:Int) =>Rem(i)
		implicit val fromString:Writable[String] = (s:String) =>Rem(s)
		implicit val fromDouble:Writable[Double] = (d:Double) =>Rem(d)
		implicit val fromFloat:Writable[Float] = (f:Float) =>Rem(f)
	}

	trait ExzampleTraint{
		type t1
		type t2 >: t3 <: t1
		type t3 <: t1
		type t4 <: Seq[t1]
	}
	trait T1{val name1:String}
	trait T2 extends T1{val name2:String}
	case class C(name1:String,name2:String) extends T2
	object ExzampleObject extends ExzampleTraint{
		override type t1 = T1
		override type t2 = T2
		override type t3 = C
		override type t4 = Vector[T1]
	}


	abstract class SubjectObserver{

		type S <: Subject
		type O <: Observer

		trait Subject{
			self:S =>
			private var oberservers = List[O]()
			def addObserver(observer: O) = oberservers ::= observer
			def notifyAllObservers() = oberservers.foreach(_.reciveUpdate(self))
		}
		trait Observer{
			def reciveUpdate(subject: S)
		}
	}

	case class Button(label:String){
		def click():Unit = {println("button click ... ")}
	}
	object ButtonSubjectObserver extends SubjectObserver{

		type S = ObservableButton
		type O = ButtonObserver

		class ObservableButton(label:String) extends Button(label) with Subject{
			override def click(): Unit = {
				super.click()
				notifyAllObservers()
			}
		}
		trait ButtonObserver extends Observer{
			def reciveUpdate(subject: ObservableButton)
		}
	}


	trait Persistence{def startPersistence():Unit}
	trait Midtier{def startMidtier():Unit}
	trait UI{def startUI():Unit}

	trait DataBase extends Persistence{
		override def startPersistence(): Unit = println("starting database ... ")
	}
	trait BizLogic extends Midtier{
		override def startMidtier(): Unit = println("start bizlogin midtier ... ")
	}
	trait WebUI extends UI{
		override def startUI(): Unit = println("start webui ... ")
	}
	trait AppDemo{
		self:Persistence with Midtier with WebUI =>
		def run() = {
			startPersistence()
			startMidtier()
			startUI()
		}
	}
	object MyApp extends AppDemo with DataBase with BizLogic with WebUI


	class C1{
		self =>
		def talk(message:String) = println("C1.talk: "+message)
		class C2{
			class C3{
				def talk(message:String) = self.talk(message)
			}
			val c3 = new C3
		}
		val c2 = new C2
	}
	def main(args: Array[String]): Unit = {

		val c1 = new C1
		c1.talk("Hello")
		c1.c2.c3.talk("World")

//		import Serialization._
//		object RemoteConnection{
//			def write[T:Writable](t:T):Unit = println(t.serialized)
//		}
//		RemoteConnection.write("hello")
//		RemoteConnection.write(1.23)
//		RemoteConnection.write(1.44f)
//		RemoteConnection.write(123)

//		import ButtonSubjectObserver._
//		class ButtonClickObserver extends ButtonObserver{
//			val clicks = new mutable.HashMap[String,Int]()
//
//			override def reciveUpdate(button: ObservableButton): Unit = {
//				val count = clicks.getOrElse(button.label,0)+1
//				clicks.update(button.label,count)
//			}
//		}
//
//		val buttons = Vector(new ObservableButton("one"),new ObservableButton("two"))
//		val observer = new ButtonClickObserver
//		buttons.foreach(_.addObserver(observer))
//		for(i <- 0 to 2) buttons(0).click()
//		for(i <- 0 to 4) buttons(1).click()
//
//		println(observer.clicks)
//
//		println("--------------------")
//		MyApp.run()

	}
}
