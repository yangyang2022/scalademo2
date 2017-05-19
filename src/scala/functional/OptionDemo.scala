package functional

/**
  * Created by syy on 2017/5/15.
  */
object OptionDemo {

	def Try[A](a: => A):Option[A] = try Some(a) catch{case e:Exception => None}

	def sequence[A](xs:List[Option[A]]):Option[List[A]] = xs match {
		case Nil => Some(Nil)
		case h::t => h flatMap(hh => sequence(t) map (hh::_))
	}

	def square(x:Double):Double = x * x

	def if2[A](cond:Boolean,onTrue:()=>A,onFalse:()=>A):A = if(cond) onTrue() else onFalse()
	def if3[A](cond:Boolean,onTrue: =>A,onFalse: =>A):A = if(cond) onTrue else onFalse

	def main(args: Array[String]): Unit = {

//		sequence(sys.error("fail ... "))
		if2(111<22 ,()=>println("true"),()=>println("false"))
		if3(false,sys.error("fail..."),println("false ... "))
	}
}
