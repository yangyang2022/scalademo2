/**
  * Created by syy on 2017/5/4.
  */
object HelloFunction {

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

	trait Reduce[T,-M[T]]{
		def reduce(m:M[T])(f:(T,T)=>T):T
	}
	object Reduce{
		implicit def seqReduce[T] = new Reduce[T,Seq] {
			override def reduce(m: Seq[T])(f: (T, T) => T): T = m reduce f
		}
		implicit def optionReduce[T] = new Reduce[T,Option] {
			override def reduce(m: Option[T])(f: (T, T) => T): T = m reduce f
		}
	}
	def sum[T:Add,M[T]](container:M[T])(implicit reduce: Reduce[T,M]):T =
		reduce.reduce(container)(implicitly[Add[T]].add(_,_))

	def main(args: Array[String]): Unit = {

		println(sum(Vector(1->10,2->20,3->30)))
	}
}
