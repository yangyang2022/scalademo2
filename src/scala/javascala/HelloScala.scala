package javascala

/**
  * Created by syy on 2017/5/11.
  */
object HelloScala {

	def timed(body: =>Double) = {
		val start = System.nanoTime()
		body
		val res = (System.nanoTime()-start)/1000/1000.0
		println(s"Time: $res ms")
		res
	}

	def multi(x:Int):Double = x * 3.14
}
