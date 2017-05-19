
/**
  * Created by syy on 2017/5/3.
  */

abstract class Widget

trait Observer[State]{
	def reciveUpdate(state: State):Unit
}
trait Subject[State]{
	private var observers:List[Observer[State]] = Nil
	def addObserver(observer: Observer[State]) = observers ::= observer
	def notifyObservers(state: State) = observers.foreach(_.reciveUpdate(state))
}
trait Clickable{
	protected def updateUI():Unit
	def click():Unit = updateUI()
}

//class Button(val name:String) extends Widget{
//	def click():Unit = updateUI()
//	def updateUI():Unit = println("updateUI ... ")
//}
class Button(val name:String) extends Widget with Clickable{
	override protected def updateUI(): Unit = println("updateui ... ")
}

trait ObservableClicks extends Clickable with Subject[Clickable]{
	override def click(): Unit = {
		super.click()
		notifyObservers(this)
	}
}
trait VetoableClicks extends Clickable{
	val maxAllowed = 1
	private var count = 0

	override def click(): Unit = {
		if(count < maxAllowed){
			count+=1
			super.click()
		}
	}
}
//class ObserableButton(name:String) extends Button(name) with Subject[Button]{
//	override def click(): Unit = {
//		super.click()
//		notifyObservers(this)
//	}
//}
//class ButtonCountObserver extends Observer[Button]{
//	var count = 0
//	override def reciveUpdate(state: Button): Unit = count+=1
//}
//class ButtonWithCallback(val label:String,val callbacks:List[()=>Unit]) extends Widget{
//	protected def updateUI():Unit = println("updateUI ... ")
//	def click():Unit = {
//		updateUI()
//		callbacks.foreach(f => f())
//	}
//}
//object ButtonWithCallback{
//	def apply(label:String,callback:()=>Unit) = new ButtonWithCallback(label,List(callback))
//	def apply(label:String) = new ButtonWithCallback(label,Nil)
//}
object HelloObejct {

	def main(args: Array[String]): Unit = {
//		val bb = new ObserableButton("click me")

//		val bb = new Button("click me") with Subject[Button]{
//			override def click(): Unit = {
//				super.click()
//				notifyObservers(this)
//			}
//		}

		val bb = new Button("clcik me") with ObservableClicks with VetoableClicks{
			override val maxAllowed: Int = 2
		}

//		val b1 = new ButtonCountObserver
//		val b2 = new ButtonCountObserver

		class ClickObserver extends Observer[Clickable]{
			var count = 0
			override def reciveUpdate(state: Clickable): Unit = count += 1
		}
		val b1 = new ClickObserver
		val b2 = new ClickObserver

		bb.addObserver(b1)
		bb.addObserver(b2)

		(1 to 5).foreach(_ => bb.click())
		println(b1.count)
		println(b2.count)

	}
}
