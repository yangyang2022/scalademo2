package concurrent

import java.lang.Runnable

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by syy on 2017/5/9.
  */
object HelloConcurrent1 {

	trait Logging{
		def log(s:String):Unit
		def warn(s:String) = log("WARN: "+s)
		def error(s:String) = log("ERROR: "+s)
	}
	class PrintLogging extends Logging{
		override def log(s: String): Unit = println(s)
	}
	class Account(val name:String,var money:Int = 0)
	private val transfers = ArrayBuffer[String]()
	def logTransfer(name:String,n:Int) = transfers.synchronized{
		transfers += s"transfer to account: '$name' = $n"
	}
	def add(account: Account,n:Int) = account.synchronized{
		account.money += n
		if(n > 10) logTransfer(account.name,n)
	}

	def send(a:Account,b:Account,n:Int) = a.synchronized{
		b.synchronized{
			a.money -= n
			b.money += n
		}
	}
	def main(args: Array[String]): Unit = {
//		new PrintLogging().error("hello world")

//		def runtwice(body: =>Unit) = {
//			body
//			body
//		}
//		runtwice{
//			println("run twice ... ")
//		}

//		val pair = (0 until 4).flatMap(x => (0 until 4).map(y => (x,y)))
//		println(pair)

//		def check[T](seq: Seq[T])(condition:T=>Boolean):Boolean = seq.forall(condition(_))
//		val res = check(1 until 10)(40 / _ > 5)
//		println(res)

//		def sum(num:Long):Long = (0L to num).sum
//
//		def time(block: =>Unit) = {
//			val start = System.currentTimeMillis()
//			block
//			println((System.currentTimeMillis() - start)+" ms")
//		}
//		time(sum(1000000000L))
//		println(Thread.currentThread().getName)
//		val thread = new Thread(){
//				override def run(): Unit = {
//					println("new thread running ... ")
//				}
//			}
//		thread.start()
//		thread.join()
//		println("main end ... ")

		def sleep(time:Long):Unit = {
			Thread.sleep(time)
		}
		def sleep1s() = sleep(1000)
		def sleep2s() = sleep(2000)
		def sleep3s() = sleep(3000)
		def sleep5s() = sleep(5000)

		def log(s:String):Unit = println(s)

		def thread(body: =>Unit):Thread = {
			val t = new Thread(){
				override def run(): Unit = body
			}
			t.start()
			t
		}
//		val t = thread{
//			sleep(1000)
//			log("New Thread Running ... ")
//			sleep(1000)
//			log("Still running ... ")
//			sleep(1000)
//			log("complete")
//		}
//		t.join()
//		log("new thread join ... ")
//
//		log("main thread end ... ")

		val jane = new Account("Jane",100)
		val john = new Account("John",100)

//		val t1 = thread{add(jane,5)}
//		val t2 = thread{add(john,50)}
//		val t3 = thread{add(jane,70)}
//
//		t1.join()
//		t2.join()
//		t3.join()
//
//		log(s"---------------transfers -------- \n $transfers")

//		val t1 = thread{for(i <- 0 to 100) send(jane,john,1)}
//		val t2 = thread{for(i <- 0 to 100) send(john,jane,1)}
//		t1.join()
//		t2.join()
//		log(s"jane=${jane.money},john=${john.money}")

//		val lock = new AnyRef
//		var message:Option[String] = None
//		val greeter = thread{
//			lock.synchronized{
//				while (lock == None) lock.wait()
//				log(message.get)
//			}
//		}
//		lock.synchronized{
//			message = Some("hello")
//			lock.notify()
//		}
//		greeter.join()


//		var tasks = mutable.Queue[()=>Unit]()
//		object Worker extends Thread{
//
//			var terminated = false
//
//			def poll():Option[()=>Unit] = tasks.synchronized{
//				while (tasks.isEmpty && !terminated) tasks.wait()
//				if(!terminated) Some(tasks.dequeue()) else None
//			}
//
//			def shutdown():Unit = tasks.synchronized{
//				terminated = true
//				tasks.notify()
//			}
//			@tailrec
//			override def run(): Unit = poll() match {
//				case Some(task) => task();run()
//				case None =>
//			}
//		}
//
//		def asynchronus(body: =>Unit) = tasks.synchronized{
//			tasks.enqueue(()=>body)
//			tasks.notify()
//		}
//
//		asynchronus{println("hello")}
//		asynchronus{println("wrold")}
//
//		Worker.start()
//
//		sleep(1000)
//		Worker.shutdown()
//		log("main end ... ")


//		@volatile var terminated = false

		import scala.concurrent._

		def execute(body: =>Unit) = ExecutionContext.global.execute(()=>body)

//		val executor = new forkjoin.ForkJoinPool
//		executor.execute(() => log("this is run executor ... "))

//		execute{
//			log("this is is running executor ... ")
//		}
//		log("main ... ")
//		sleep(500)
//		for(i <- 0 until 32 ) execute{
//			sleep(2000)
//			log(s"Task $i completed ... ")
//		}
//		sleep(10000)
//		log("main end ... ")

//		lazy  val obj = new AnyRef
//		lazy  val non = s"made by ${Thread.currentThread().getName}"
//		execute{
//			log(s"EC sees obj = $obj")
//			log(s"EC sees non = $non")
//		}
//		log(s"Main sees obj = $obj")
//		log(s"Main sees non = $non")
//
//		sleep1s()

//		object Lazy{log("Running lazy cons ... ")}
//		log("Main Thread is about to reference lazy")
//		Lazy
//		log("main .. ")
		val buffer = mutable.ArrayBuffer[Int]()
		def asyncAdd(seq: Seq[Int]) = execute{
			buffer.synchronized{
				buffer ++= seq
				log(s"buffer: $buffer")
			}
		}
		asyncAdd(0 until 10)
		asyncAdd(11 until 20)

		sleep1s()
		println(buffer)

	}
}
