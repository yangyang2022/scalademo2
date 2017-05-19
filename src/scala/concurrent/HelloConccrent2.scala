package concurrent


import java.util.concurrent.ForkJoinPool
import java.util.concurrent.atomic.AtomicLong

import scala.collection.{GenIterable, GenSeq, GenSet, mutable}
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.io.Source
import scala.util.control.NonFatal
import scala.util.{Failure, Random, Success}
import scala.async.Async._
import scala.collection.concurrent.TrieMap
import scala.collection.immutable.WrappedString
import scala.collection.parallel.{ForkJoinTaskSupport, ParSeq, SeqSplitter}
import scala.concurrent.duration.Duration

/**
  * Created by syy on 2017/5/11.
  */
object HelloConccrent2 {

	def log(s:String) = println(s)

	var tasks = mutable.Queue[()=>Unit]()

	def sleep(mills:Long){
		Thread.sleep(mills)
	}

	def sleep1s() = sleep(1000)
	def sleep2s() = sleep(2000)
	def sleep5s() = sleep(5000)

	object Worker extends Thread{
		var terminated = false

		def poll() : Option[()=>Unit] = tasks.synchronized{
			if(tasks.isEmpty && !terminated) tasks.wait()
			if(!terminated) Some(tasks.dequeue()) else None
		}
		override def run(): Unit = poll() match {
			case Some(task) => task();run()
			case None =>
		}

		def shutdown() = tasks.synchronized{
			terminated = true
			tasks.notify()
		}

	}
	def main(args: Array[String]): Unit = {

//		Worker.start()
//
//		def async(body: =>Unit) = tasks.synchronized{
//			tasks.enqueue(()=>body)
//			tasks.notify()
//		}
//		async{println("hello");sleep1s()}
//		async{println("world");sleep2s()}
//
//		sleep5s()
//		println("main end ... ")
//		Worker.shutdown()

//		Future{log("the future is here !")}
//		log("main future ... ")
//		sleep1s()

		val path = "c:\\code\\data.txt"
//		val buildFile:Future[String] = Future{
//			val f = Source.fromFile(path)
//			try f.getLines().mkString("\n") finally f.close()
//		}
//		log(s"state: ${buildFile.isCompleted}")
//		sleep1s()
//		log(s"state: ${buildFile.isCompleted}")
//		log(s"value:\n: ${buildFile.value}")

//		val url = "https://www.google.co.jp/?gws_rd=cr&ei=qEIUWZT6H8qd0gLvj7yYDA#q=java"
		val urlSpec = getUrl(path)

//		urlSpec.onComplete{
//			case Success(content) => println(content)
//			case Failure(content) => println(content)
//		}
//		urlSpec foreach {
//			case line => log(find(line,"合肥"))
//		}
//		urlSpec.failed

//		val f = Future{throw new InterruptedException}
//		val g = Future{throw new IllegalArgumentException}
//
//		f.failed foreach {case t=>println(s"error: $t")}
//		g.failed foreach {case t=>println(s"error: $t")}

//		f.failed foreach{case NonFatal(t) => println(s"error: $t")}
//		g.failed foreach{case NonFatal(t) => println(s"error: $t")}

//		val file = Source.fromURL("http://tieba.baidu.com/f?kw=java&fr=index&red_tag=k1561727401")
//		file.getLines().foreach(println)


//		sleep2s()

//		async {
//			log("start ... ")
//			await {delay(1)}
//			log("done!")
//		}

//		log("main start ... ")
//		sleep2s()

//		countdown(10){n => log(s"T-minus $n seconds")} foreach{
//			case _ => log("This programer is over!")
//		}
//
//		sleep(12 * second)

//		var startTime = System.nanoTime()
//		val futures = for(_ <- 0 until 16) yield Future{blocking{Thread.sleep(1000)}}
//
//		for(f<-futures) Await.ready(f,Duration.Inf)
//		val endTime = System.nanoTime()
//		log(s"Total time: ${(endTime-startTime)/1000000} ms" )
//		log(s"Total CPUs: ${Runtime.getRuntime.availableProcessors()}")

//		val sum = (0 until 100000).par.count(x => x.toString == x.toString.reverse)
//		println(s"sum: $sum")


//		timed{
//			println((1 until 100000).count(x => x.toString == x.toString.reverse))
//		}

//		val numbers = Random.shuffle(Vector.tabulate(5000000)(i=>i))
//		timed{numbers.max}
//		timed{numbers.par.max}

//		val uid = new AtomicLong(0L)
//		timed{for(i <- 0 to 10000000) uid.incrementAndGet()}
//		timed{for(i <- (0 to 10000000).par) uid.incrementAndGet()}va

//		val fjpool = new ForkJoinPool(2)
//		val taskSupport = new ForkJoinTaskSupport(fjpool)
//		val numbers = Random.shuffle(Vector.tabulate(5000000)(i=>i))
//		timed{
//			val pnum = numbers.par
//			pnum.tasksupport = taskSupport
//			val n = pnum.max
//			println(s"max is $n")
//		}

//		val num = 1000000
//		val list = List.fill(num)("")
//		val vector = Vector.fill(num)("")
//
//			timed(list.par)
//			timed(vector.par)

//		val a = (0 to 1000).toSet
//		val b = (0 to 1000 by 4).toSet
//
//		val seqRes = intersectionSize(a,b)
//		val parRes = intersectionSize(a.par,b.par)
//
//		println(s"Seq: ${seqRes}")
//		println(s"Par: ${parRes}")

//		val doc = mutable.ArrayBuffer.tabulate(200)(i=>s"Page $i,")
//		def test(doc:GenIterable[Int]): Unit ={
//			val seqText = doc.seq.reduceLeft(_-_)
//			val parText = doc.par.reduce(_-_)
//			log(s"Seq: $seqText")
//			log(s"Par: $parText")
//		}
//		test(doc)
//		test(doc.toSet)

//		test(0 until 30)

//		val cache = TrieMap[Int,String]()
//		for(i <- 0 to 100) cache(i) = i.toString
//		timed{
//			for((number,string) <- cache) cache(-number) = s"-$number"
//		}
//		log(s"cache: ${cache.keys.toList.sorted}")

	}

//	class ParString(val string: String) extends ParSeq[Char]{
//		override def apply(i: Int): Char = string.charAt(i)
//
//		override protected[parallel] def splitter: SeqSplitter[Char] = ??? //new ParStringSplitter(string,0,string.length)
//
//		override def length: Int = string.length
//
//		override def seq: Seq[Char] = new WrappedString(string)
//	}


	def intersectionSize(a:GenSet[Int],b:GenSet[Int]):Int = {
		var total = 0
//		for(x <- a) if(b.contains(x)) total += 1 //error
		for(x<-a) total = a.count(x=>b.contains(x))
		total
	}
	@volatile var dummy:Any = _
	def timed(body: =>Unit):Unit = {
		val start = System.nanoTime()
		dummy = body
		val end = System.nanoTime()
		println(s"time: ${((end-start)/1000)/1000.0} ms")
	}

	def second = 1000
	def countdown(n:Int)(f:Int=>Unit):Future[Unit] = async{
		var i = n
		while(i > 0) {
			f(i)
			await{delay(1)}
			i -= 1
		}
	}
	def delay(n:Int):Future[Unit] = async {
		blocking{Thread.sleep(n*1000)}
	}
	def getUrl(url:String):Future[List[String]] = Future{
		val f = Source.fromFile(url)
		try f.getLines().toList finally f.close()
	}
	def find(lines: List[String],keyword:String):String = lines.zipWithIndex collect{
		case (line,n) if line.contains(keyword) => (n,line)
	} mkString "\n"

}
