import scala.annotation.tailrec
import scala.concurrent.Future
import scala.io.Source
import scala.util.control.NonFatal
import scala.util.parsing.json._

//import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by yy on 2017/4/29.
  */

object HelloDemo {
    def main(args: Array[String]): Unit = {



//        val pf1:PartialFunction[Any,String] = {case s:String => "Yes"}
//        val pf2:PartialFunction[Any,Double] = {case s:Double => 3.14}
//        val pf = pf1 orElse pf2
//
//        println(pf(2.3))

//        val p1 = Point(x = 3.3,y = 4.4)
//        println(p1)
//        println(p1.copy(y=6.6))

//        val s = Circle(Point(1.1,2.2),1.1)
//        s.draw(Point(1.0,2.0))(str=>println(s"ShapesDrawing :${str}"))

//        def sleep(mills:Long): Unit ={
//            Thread.sleep(mills)
//        }
//        def dowork(index:Int)={
//            sleep((math.random()*1000).toLong)
//            index
//        }
//        (1 to 5).foreach{
//            index =>
//                val future = Future{dowork(index)}
//                future.onComplete{
//                    case answer => println(s"get answei: ${answer.get}")
//                    case _ => println("failure")
//                }
//        }
//
//        sleep(5000)

//        def factorial(i:Int):Long = {
//            @tailrec
//            def fact(acc:Long,i:Int):Long={
//                if(i == 1) acc
//                else fact(acc*i , i-1)
//            }
//            fact(1,i)
//        }
//        (1 to 5).foreach(i=>println(factorial(i)))

//        def joiner(strings:String*):String = strings.mkString("-")
//        println(joiner(List("hello","world"):_*))
//        println('id.name)

//        val f = (s:String) => s.length
//        println(f.getClass)

//        def isEven = (s:Int) => s % 2 == 0
//        List(1,2,3,4,5,6) filter isEven foreach println

//        def countLines(filename:String) ={
//            val path = this.getClass.getClassLoader.getResource(filename).getPath
//            Manager(Source.fromFile(path)){source=>
//                val size = source.getLines().size
//                println(s"file ${filename} has ${size} lines")
//                if(size > 10) throw new RuntimeException("Big File")
//            }
//        }
//        countLines("scala.txt")


//        def continue(condition: => Boolean)(body: => Unit):Unit = {
//            if(condition){
//                body
//                continue(condition)(body)
//            }
//        }
//
//        var i = 1
//        continue(i <= 5){
//            println(s"i= ${i}")
//            i+=1
//        }
//        for(bread <-Bread.values){
//            println(s"${bread.id}\t${bread}")
//        }

//        val items = Seq(("Pencil",0.52),("Paper",1.35),("NoteBook",2.34))
//        for(((k1,k2),v) <- items.zipWithIndex){
//            println(s"$k1-> $k2")
//        }

//        val list = 1+:2+:3+:4+:Nil
//        println(list)
//        def process[T](list:Seq[T]):Unit = list match {
//            case +:(head,tail) => println(head);process(tail)
//            case Nil => println(" Nil")
//        }
//        process(list)


//        val with1:With[String,Int] = With("Foo",1)
//        val with2:String With Int = With("Bar",2)
//        Seq(with1,with2) foreach{w => w match {
//            case s With i => println(s"${s} with $i")
//            case _ => println(s"unkown ${w}")
//        }}

//        def calcTax(amonut:Float)(implicit rate:Float):Float = amonut * rate
//        implicit val  curentRate1 = 0.8f
//        implicit val  curentRate2 = 0.1f
//        println(calcTax(50000f))

//        val list = MyList(List(1,3,5,2,4))
//        println(list.sortedBy1(i => -i))
//        println(list.sortedBy2(i => -i))

//        val list = List(1->11,2->22,3->33)
//        println(list.toMap)

//        import M._
//        println(m(Seq(1,2,3)))
//        println(m(Seq("hello","world")))

//        import Interpolators._
//        val name ="yangyang"
//        val book = "Programing scala"
//
//        val jsonobj = json"{name: $name ,book:$book}"
//        println(jsonobj)

//        implicit class AddressToJson(address: Address)extends ToJson{
//            override def toJson(level: Int): String = {
//                val (outdent,indent) = indentation(level)
//                s"""{
//                  |${indent}"street":"${address.street}",
//                  |${indent}"city":   "${address.city}"
//                  |$outdent}
//                """.stripMargin
//            }
//        }
//        implicit class PersonToJson(person: Person) extends ToJson{
//
//            override def toJson(level: Int): String = {
//                val (outdent,indent) = indentation(level)
//                s"""{
//                  |${indent}"name":   "${person.name}"
//                  |${indent}"address": "${person.address.toJson(level+1)}"
//                  |$outdent}
//                """.stripMargin
//            }
//        }
//
//        val a = Address("Scala Lane","Anytown")
//        val p = Person("Bulk Trends",a)
//        println(a.toJson())
//        println()
//        println(p.toJson())

//        def m(pair:Tuple2[Int,String]) = println(pair)
//        m(1,"two")

//        var factor = 2
//        val multiplier = (i:Int) => i*factor
//        val sum1 = (1 to 10) filter (_%2 ==0 ) map multiplier reduce(_*_)
//        factor = 3
//        val sum2 = (1 to 10) filter (_%2 ==0 ) map multiplier reduce(_*_)
//        println(sum1)
//        println(sum2)
//
//        def m1(multiplier:Int=>Int) = {
//            (1 to 10) filter (_%2 ==0 ) map multiplier reduce(_*_)
//        }
//        def m2:Int=>Int = {
//            var factor = 2
//            val mm = (i:Int) => i*factor
//            mm
//        }
//        println(m1(m2))
//        factor =4
//        println(m1(m2))


//        val seq = Seq("hello","seq")
//        val seq1 = seq :+ "end"
//        val seq2 = "head" +: seq
//        println(seq)
//        println(seq1)
//        println(seq2)
//        val list = (List(1,2,3,4,5,6) foldRight List.empty[String]){
//            (x,list) => ("["+x+"]")::list
//        }
//        println(list)
//        val list2 = (List(1,2,3,4,5,6) foldLeft List.empty[String]){
//            (list,x) => ("["+x+"]")::list
//        }
//        println(list2)
//        println((0 /: List(1,2,3))(_-_))

        println("hello scala")



    }
}

case class Address(street:String,city:String)
case class Person(name:String,address: Address)
trait ToJson{
    def toJson(level:Int = 0):String
    val INDENTATION = " "
    def indentation(level:Int=0):(String,String) = (INDENTATION*level,INDENTATION*(level+1))
}

object Interpolators{

    implicit class jsonForStringContext(sc:StringContext){
        def json(values:Any*):JSONObject = {
            val keyre = """^[\s{,]*(\S+):\s*""".r
            println("parts: "+sc.parts)
            println(values)

            val keys = sc.parts map{
                case keyre(key) => key
                case str => str
            }
            val kvs =keys zip values
            JSONObject(kvs.toMap)
        }
    }
}
class Bad{
    def m(i:Int)(implicit s:String) = "foo"

}
object M{
    implicit object IntType
    implicit object StringType
    def m(seq:Seq[Int])(implicit i:IntType.type ):Unit = println(s"Seq[Int]: ${seq}")
    def m(seq:Seq[String])(implicit s:StringType.type ):Unit = println(s"Seq[String]:${seq}")

}
case class MyList[A](list: List[A]){
    def sortedBy1[B](f:A=>B)(implicit ord:Ordering[B]):List[A] = {
        list.sortBy(f)(ord)
    }
    def sortedBy2[B:Ordering](f:A=>B):List[A] = {
        list.sortBy(f)(implicitly[Ordering[B]])
    }
}
case class With[A,B](a: A,b: B)

object Bread extends Enumeration{
    type Bread = Value
    val deboramn = Value("deboramn")
    val yorkie = Value("yorkie")
    val scottie = Value("scottie")
    val dane = Value("dane")
    val portie = Value("portie")
}
object Manager{
    def apply[R<:{def close():Unit},T](resource: => R)(f:R=>T)={
        var res:Option[R] = None
        try {
            res = Some(resource)
            f(res.get)
        }catch {
            case NonFatal(ex) => println(s"Non fatal exception: ${ex}")
        }finally {
            if(res != None){
                println("Closing resource... ")
                res.get.close()
            }
        }
    }
}
case class Point(x:Double = 0.0,y:Double = 0.0){
    def shift(deltax:Double=0.0,deltay:Double = 0.0)= {
        copy(x+deltax,y+deltay)
    }
}
abstract class Shape{
    def draw(offset:Point)(f:String=>Unit)={
        f(s"draw(offset=${offset}),${this.toString}")
    }
}
case class Circle(center:Point,radius:Double) extends Shape
case class Rectangle(lowerleft:Point,height:Double,width:Double) extends Shape

