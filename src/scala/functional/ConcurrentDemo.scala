package functional

/**
  * Created by syy on 2017/5/15.
  */
object ConcurrentDemo {

	trait Par[+A]{
	}
	object Par{
//		def unit[A](a: =>A):Par[A]
	}
	def sum(ints:IndexedSeq[Int]):Int = {
		if(ints.size <= 1) ints.headOption.getOrElse(0)
		else {
			val (l,r) = ints.splitAt(ints.length>>1)
			sum(l) + sum(r)
		}
	}
	def main(args: Array[String]): Unit = {

		val ints = IndexedSeq(1 to 100:_*)
		println(s"sum is ${sum(ints)}")

	}
}
