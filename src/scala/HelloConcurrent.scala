/**
  * Created by syy on 2017/5/4.
  */
object HelloConcurrent {

	import scala.concurrent.{Await,Future}
	import scala.concurrent.duration.Duration
	import scala.concurrent.ExecutionContext.Implicits.global
	import scala.util.{Success,Failure,Try}

	case class ThatOdd(i:Int) extends RuntimeException(s"odd $i recevied!")

	def main(args: Array[String]): Unit = {

//		val futures = (0 to 9) map {
//			i => Future{
//				val s = i.toString
//				println(s)
//				s
//			}
//		}
//		val f = Future.reduceLeft(futures)((s1,s2)=>s1+s2)
//		f.onComplete{
//			case Success(e) => println(e)
//			case Failure(e) => println(e)
//		}
//		val n = Await.result(f,Duration.Inf)

//		val dnComplete:PartialFunction[Try[String],Unit] = {
//			case s @ Success(_) => println(s)
//			case f @ Failure(_) => println(f)
//		}
//
//		val futures = (0 to 9) map {
//			case i if i % 2 == 0 => Future.successful(i.toString)
//			case i => Future.failed(ThatOdd(i))
//		}
//		val f = futures map (_ onComplete dnComplete)
//
//		Thread.sleep(2000)
	}
}
