import scala.io.Source

/**
  * Created by syy on 2017/5/3.
  */

object HelloObject2 {

	case class Address(city:String,state:String,zip:String)
	case class Employee(name:String,salary:Double,address: Address)
	abstract class PayRoll{
		def netpay(employee: Employee):Double = {
			val fextax = calcFedTaxes(employee.salary)
			val statetax = calcStateTaxes(employee.salary,employee.address)
			employee.salary - fextax - statetax
		}
		def calcFedTaxes(salary:Double):Double
		def calcStateTaxes(salary:Double,address: Address):Double
	}
	object Payroll2014 extends PayRoll{
		val stateRate = Map("xx"->0.05,"yy"->0.03,"zz"->0.0)

		override def calcFedTaxes(salary: Double): Double = salary * 0.25

		override def calcStateTaxes(salary: Double, address: Address): Double = salary*stateRate(address.state)
	}

	class NotFixed{
		val maxAllowed = 1
	}
	class Child extends NotFixed{
		override val maxAllowed: Int = 123 //val can exchanged
	}
	trait Abstarct{
		println("in abstarct ... ")
		val value:Int
		lazy val inverse:Double = 1.0 / value
//		println("out abstarct: value= "+value+" ,inverse= "+inverse)
	}

	trait TestAlowed{
		def maxAlowed:Int = 1
	}
	class TestObject extends TestAlowed{
		override val maxAlowed: Int = 123
	}
	class C1{
		def m = print("C1 ")
	}
	trait T1 extends C1{
		override def m: Unit = {print("T1 ");super.m}
	}
	trait T2 extends C1{
		override def m: Unit = {print("T2 ");super.m}
	}
	trait T3 extends C1{
		override def m: Unit = {print("T3 ");super.m}
	}
	class C2A extends T2{
		override def m: Unit = {print("C2A ");super.m}
	}
	class C2 extends C2A with T1 with T2 with T3{
		override def m: Unit = {print("C2 ");super.m}
	}
	def calcLineriaztion(obj:C1,name:String) = {
		print(s"$name :")
		obj.m
	}

	def main(args: Array[String]): Unit = {

//		calcLineriaztion(new C2,"C2")

//		val tom = Employee("Tom",10000.0,Address("Mytown","xx","12345"))
//		val jane = Employee("Jane",11000.0,Address("Mytown","yy","678"))
//		val tompay = Payroll2014.netpay(tom)
//		val janepay = Payroll2014.netpay(jane)
//		println(tompay)
//		println(janepay)
//		println((new Child).maxAllowed)

//		val obj = new Abstarct {
//			override val value: Int = 12
//		}
//		println("main: "+obj.value+" ,inverse: "+obj.inverse)

//		val obj = new {
//			val value = 11
//		} with Abstarct
//		println("main: "+obj.value+" ,inverse: "+obj.inverse)

//		println((new TestObject()).maxAlowed)

//		val path = this.getClass.getClassLoader.getResource("data.txt").getPath
//		println(Source.fromFile(path).getLines()
//		  .flatMap(line => line.split("\\D+"))
//		  .filter(!_.equals("")).map(_.toInt).sum)


	}

}
