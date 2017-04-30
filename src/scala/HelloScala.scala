import java.nio.file.{Files, Paths}
import java.time.LocalTime
import java.util

import scala.Boolean
import scala.annotation.{switch, tailrec, varargs}
import scala.collection.immutable.ListMap
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source

/**
  * Created by yy on 2017/4/29.
  */

//import util.control.Breaks.breakable
//import util.control.Breaks.break

//case class Person(firstname:String,lastname:String)
class Person(var firstname:String,var lastname:String){
    def this(firstname:String){
        this(firstname,"default")
    }
    println("constrcutors begin ....")
    private val home = System.getProperty("user.home")
    var age = 10

    //some method
    override def toString: String = s"$firstname $lastname is ${age} years old"

    def printhome{println(s" home is ${home}")}
    def printfullname{println(this)}
    printhome
    printfullname
    println("still in constructors ... ")
}
object Person{

    def apply() = new Person("defaut","default")
    def apply(name:String) = new Person(name)
    def apply(firstname:String,lastname:String) = new Person(firstname,lastname)

}
object HelloScala {
    implicit class StringImprovment(s:String){
        def increment = s.map(c=>(c+1).toChar)
        def decrement= s.map(c=>(c-1).toChar)
        def hidall = s.replaceAll(".","*")

//        def plusOne = s.toInt + 1
        def asBoolean = s match {
            case "0" |"zero" | "" | " " => false
            case _ => true
        }
    }

    implicit class StringToInt(s:String){
        def toInt(radix:Int = 10) = Integer.parseInt(s,radix)
    }

    def main(args: Array[String]): Unit = {

//        Array(1,2,3).map(_*2).foreach(println)
//        println("hello".getClass.getName)
//        println("hello".drop(2).take(2).capitalize)
//        val speech =
//            """
//              |hello world
//              |ni hao
//              |yangyanga
//              |5 1 holiday
//            """.stripMargin
//        println(speech)

//        val age = 12
//        println(s"${age} is ${age+12} and ${age == 12}")

//        def tolower(c:Char):Char = (c.toByte+1).toChar
//        println("hello".map(tolower))
//        val toLower = (c:Char) =>(c.toByte+1).toChar
//        println("hello".map(toLower))

//        val match1 = "\\d+".r.findFirstIn("123 hello wo123rld")
//        println(match1.foreach(println))
//        match1 match {
//            case Some(s) => println(s"Fount: $s")
//            case None =>
//        }


//        val parttern = "([0-9]+)\\s+([a-z]+)".r
//        val parttern(count,fruit) = "100     apple"
//        println(s"$count and $fruit")

//        print("hello"(1)) //like "hello".apply(1)

//        println("hello".increment)
//        println("hello".asBoolean)
//        println("zero".asBoolean)
//        println("0".asBoolean)
//        println("   ".asBoolean)
//        println("123a".plusOne)


//        println(Short.MaxValue)
//        println(Short.MinValue)
//        println(LocalTime.now().isAfter(LocalTime.now().plusHours(1)))

//        println("15".toInt(16))
//        println("12345".toInt(8))

//        for(i <- 1 to 10 if i<4) println(i)
//        val array= Array.ofDim(2,2)
//        for{
//            i <- 1 to 2
//            j <- 1 to 3
//        }println(s"i=${i},j=${j}")s


//        println("break .... ")
//        breakable{
//            for(i <- 1 to 10){
//                println(i)
//                if(i > 3) break
//            }
//        }
//        println("continue ... ")
//        var numops = 0
//        val serachme = "hello world hello yangyang"
//        for(i<- 0 until serachme.length){
//            breakable{
//                if(serachme.charAt(i) != 'o') break()
//                else numops+=1
//            }
//        }
//        println(s"o is ${numops}")
//
//        val count = serachme.count(_ == 'o')
//        println(count)

//        val i=1
//        val two = 2
//        val x = (two: @switch) match {
//            case 1 => "one"
//            case two => "two"
//            case _ => "ohter"
//        }
//        println(s"x is ${x}")

//        def fun(x:Any) =x match {
//            case x @ List(1,_*) => println(s"list is ${x}")
//            case x @ Some(_) => println(s"some is ${x}")
//            case p @ Person(first,"yangyang") => println(s"${p}")
//            case _ => println("no thing")
//        }
//        fun(List(1,2,3,4))
//        fun(Map(1->2,3->4))
//        fun(Some("hello"))
//        fun(Person("hello","yangyang1"))

//        def listOfThing(list: List[Int]):String = list match {
//            case head::tail => head+" "+listOfThing(tail)
//            case Nil => "haha"
//        }
//
//        println(listOfThing(List(1,2,3)))


//        def whilst(condition: =>Boolean)(code: =>Unit): Unit ={
//            while (condition){
//                code
//            }
//        }
//        @tailrec
//        def whilst2(condition: =>Boolean)(code: =>Unit):Unit = {
//            if(condition){
//                code
//                whilst2(condition)(code)
//            }
//        }
//
//        var i = 0
//        whilst2(i<5){
//            println(s"i== ${i}")
//            i+=1
//        }

//        val p = new Person("shen","yangyang")
//        p.age = 123
//
//        print(p)

//        println(Person("hello"))
//        println(Person("hello","wrold"))
//        println(Person())

//        val path = this.getClass.getClassLoader.getResource("data.txt").getPath
//        Source.fromFile(path).getLines().foreach(println)


//        val dog = new Dog
//        println(dog)

//        val person = Person2("yangyang")
//        person.name= "shem"
//        println(person.name)
//        val persons = new ArrayBuffer[Person2]
//        persons += person
//        println(persons)


//        def getStockInfo() = {
//            ("Netfix",100.02,101)
//        }
//
//        val (name,age,score) = getStockInfo()
//
//        println(s"${name} and ${age} and ${score}")

//        @throws(classOf[RuntimeException])
//        def printAll(strings: String*): Unit ={
//            strings.foreach(println)
//        }
//        val list = List("hello","wrold","ni","hao","yang",null)
//        printAll(list:_*)

//        printAll("hello".getClass.getName)
//        println(classOf[String])
//        println(classOf[String].getMethods)
//        println(classOf[StringImprovment])


//        CashRegister.open
//        CashRegister.close


//        val dog = new Dog
//        dog.speak
//        dog.startTail
//        dog.stopTail
//        dog.run

//        val emp = new Enterprice
//        println(emp.hello("password"))
//        println(emp.start)


//        val p = new BadManner with Angry
//        p.speak

//        def exec(block: =>Int): Unit ={
//            val res = block
//            println(s"res is ${res}")
//        }
//        exec(()=>println("hello world"))
//        exec{
//            println("hello world")
//        }
//        exec({println("hello world");998})


//        def exec(f:String=>Unit,name:String): Unit ={
//            f(name)
//        }
//        def hello(name:String): Unit ={
//            println(name)
//        }
//        exec(hello,"yangyang")

//        val sum = (a:Int,b:Int,c:Int ) => a+b+c
//        val f = sum(1,2,_:Int)
//        println(f(30))

//        def saysomething(prefix:String) = (s:String)=>(postfix:String) =>prefix+" "+s+postfix
//        println(saysomething("hello")("yangyang")(" haha"))

//        new PartialFunction[Int,String]{
//            val nums = Array("one","two","three","foue")
//            override def isDefinedAt(x: Int): Boolean = x >= 0 && x <=3
//
//            override def apply(v1: Int): String = nums(v1-1)
//        }

//        val set2  = Set(1,2,4)
//        val sets = scala.collection.mutable.Set(1,2,4,1,2,3)
//        println(sets+1)
//        println(set2+1)

//        (Stream from 1).take(10).foreach(println)

//        val list = List("one","two","three","four","five")
//        for((k,v) <- list.zip(Stream from 1)){
//            println(s"$k is $v")
//        }
//        (list.zip(Stream from 1)).foreach(d => println(s"${d._1} and ${d._2}"))

//        val x = Vector(Some(1),None,Some(2),None)
//        println(x.flatten)

//        val bag = List("1","2","three","4","five")
//        def toInt(in:String):Option[Int] = {
//            try{
//                Some(Integer.parseInt(in))
//            }catch {
//                case e:Exception => None
//            }
//        }
//        println(bag.flatMap(toInt).sum)

//        val x = List(15,10,5,8,20,12)
//        println(x.groupBy(_ > 10))
//        println(x.partition(_>10))
//        println(x.span(_<20))
//        println(x.splitAt(2))

//        val couples = List(("Kim","Al"),("Julia","Terry"))
//        val (women,man) = couples.unzip
//        println(women)
//        println(man)
//        println(women.zip(man))

//        val map = Map(3->30,1->10,2->20)
//        println(map.transform((k,v)=>k+v))
//        val map = Map("Kim"->90,"AL"->85,"Melissa" -> 95,"Emily"-> 91,"Hannah" -> 92)
//        println(ListMap(map.toSeq.sortBy(_._2):_*))
//        println(map.toSeq.sortBy(_._2))
//        Files.list(Paths.get("C:\\Program Files (x86)")).forEach(println)

//        def nums = {
//            var list = new util.ArrayList[Int]()
//            list.add(1)
//            list.add(2)
//            list.add(3)
//            list.add(4)
//            list
//        }
//        val list = nums
//        list.forEach(println)

//        val nums = HelloJava.getNumbers
//        import scala.collection.JavaConverters._
//        println(HelloJava.sum(seqAsJavaList(Seq(1,2,3,4,5,6))))
//        println(HelloJava.sum(bufferAsJavaList(ListBuffer(1,2,3,4,5,6))))
//        println(HelloJava.sum(bufferAsJavaList(ArrayBuffer(1,2,3,4,5,6))))


    }
}

class Printer{
    @varargs
    def printall(strings:String*): Unit ={
        strings.foreach(println)
    }
}

trait MathTrait{
    def sum(x:Int,y:Int) = x+y
}

class MathWrapper extends MathTrait

//@SerialVersionUID(12312l)
//class MyThrower extends Serializable{
//
//    @throws(classOf[RuntimeException])
//    def exceptionThrower(): Unit ={
//        throw new RuntimeException("Exception..... ")
//    }
//}

//class BadManner
//trait Angry{
//    println("You won't like me ... ")
//    def speak{println("ooooooooooooo ... ")}
//}
//trait StartFleetCore {
//    this:StarShip =>
//}
//class StarShip
//class RomulanShip
//class Enterprise extends StarShip with StartFleetCore
//class Enter2 extends RomulanShip with StartFleetCore//error

//trait WrapCore{
//    this: {
//        def hello(password:String):Boolean
//        def start:Unit
//    } =>
//}
//class StartShip
//class Enterprice extends StartShip with WrapCore{
//    def hello(password:String):Boolean = {
//        if(password == "password"){println("core reject");true}
//        else false
//    }
//    def start{println("core start ... ")}
//}

//abstract class Animal{
//    def speak
//}
//trait WaggingTail{
//    def startTail{println("tail start ... ")}
//    def stopTail{println("tail stop ... ")}
//}
//trait FourLeggedAnimal{
//    def walk
//    def run
//}
//class Dog extends Animal with WaggingTail with FourLeggedAnimal{
//    override def speak: Unit = println("Dog says woof!")
//
//    override def walk: Unit = println("dog is walking ... ")
//
//    override def run: Unit = println("dog is running ... ")
//}
//trait Animal{
//    val greeting:Option[String]
//    var age:Option[Int]
//
//    override def toString: String = s"i say ${greeting} and i am ${age} yeras old"
//}
//class Dog extends Animal{
//    override val greeting: Option[String] = Some("Woof")
//    override var age: Option[Int] = Some(2)
//}
//
//case class Person2(var name:String)
//
//object CashRegister{
//    def open{println("open ... ")}
//    def close{println("close ... ")}
//}