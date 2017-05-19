package functional

/**
  * Created by syy on 2017/5/15.
  */
object StateDemo {


	trait RNG{
		def nextInt:(Int,RNG)
	}

	case class SimpleRNG(seed:Long) extends RNG{
		override def nextInt: (Int, RNG) = {
			val newseed: Long = (seed * 0x5DEECE66DL+0xBL) & 0xffffffffffffffL
			val nextRNG = SimpleRNG(newseed)
			val n = (newseed >>> 16).toInt
			(n,nextRNG)
		}
	}

	def nonNegativeInt(rng: RNG):(Int,RNG) = {
		val (e,r) = rng.nextInt
		(if(e<0) -(e+1) else e,r)
	}

	def double(rng:RNG):(Double,RNG) = {
		val (e,r) = rng.nextInt
		(e*1.0/Int.MaxValue,r)
	}

	def intDouble(r: RNG):((Int,Double),RNG) = {
		val (e1,r1) = r.nextInt
		val (e2,r2) = double(r1)
		((e1,e2),r2)
	}
	def doubleInt(r:RNG):((Double,Int),RNG) = {
		val (e1,r1) = r.nextInt
		val (e2,r2) = double(r1)
		((e2,e1),r2)
//		val ((i,d),r) = intDouble(r)
//		((d,i),r)
	}
	def double3(r:RNG):((Double,Double,Double),RNG) = {
		val (e2,r2) = double(r)
		val (e3,r3) = double(r2)
		val (e4,r4) = double(r3)
		((e2,e3,e4),r4)
	}

	def ints(count:Int)(r:RNG):(List[Int],RNG) = {
		if(count == 0) (List(),r)
		else {
			val (x,r1) = r.nextInt
			val (xs,r2) = ints(count-1)(r1)
			(x::xs,r2)
		}
	}

	type Rand[+A] = RNG => (A,RNG)

	val int:Rand[Int] = _.nextInt

	def unit[A](a:A):Rand[A] = rng => (a,rng)

	def map[A,B](s:Rand[A])(f:A=>B):Rand[B] = rng => {
		val (a,rng2) = s(rng)
		(f(a),rng2)
	}
	def nonNegativeEven:Rand[Int] = map(nonNegativeInt)(i=>i-i%2)
	def double2:Rand[Double] = map(nonNegativeEven)(_/(Int.MaxValue.toDouble+1))

	def map2[A,B,C](ra:Rand[A],rb:Rand[B])(f:(A,B)=>C):Rand[C] = rng => {
		val (a,r1) = ra(rng)
		val (b,r2) = rb(r1)
		(f(a,b),r2)
	}

	def both[A,B](ra:Rand[A],rb:Rand[B]):Rand[(A,B)] = map2(ra,rb)((_,_))
	val intDouble1: Rand[(Int, Double)] = both(int,double)
	val doubleInt1: Rand[(Double, Int)] = both(double,int)

	def sequnce[A](fs:List[Rand[A]]):Rand[List[A]] = fs.foldRight(unit(List[A]()))((f,acc)=>map2(f,acc)(_ :: _))

	def flatMap[A,B](f:Rand[A])(g:A=>Rand[B]):Rand[B] = rng => {
		val (a,r1) = f(rng)
		g(a)(r1)
	}
	def nonNegativeLessThan(n:Int):Rand[Int] = {
		flatMap(nonNegativeInt){i=>
			val mod = i % n
			if (i + (n-1) - mod >= 0) unit(mod) else nonNegativeLessThan(n)
		}
	}

	def main(args: Array[String]): Unit = {
//		val rand = new scala.util.Random()
//		println(rand.nextDouble())
//		println(rand.nextDouble())
//		println(rand.nextDouble())

//		val rng = SimpleRNG(42)
//		println(rng.nextInt)
//		println(rng.nextInt)
//		println(rng.nextInt)

//		println(nonNegativeInt(SimpleRNG(46)))
//		println(double(SimpleRNG(123)))
//		println(ints(10)(SimpleRNG(42))._1)
//		println(nonNegativeEven(SimpleRNG(46)))
//		println(int(SimpleRNG(123)))

		val simleRNG = SimpleRNG(2334322)
//		println(double2(simleRNG))
		println(intDouble1(simleRNG))
		println(doubleInt1(simleRNG))
	}
}
