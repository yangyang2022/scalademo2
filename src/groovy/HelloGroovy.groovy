/**
 * Created by syy on 2017/5/12.
 */
class HelloGroovy {

    static class Employee{def name,salary}
    def paidmore(amounts){
        return {Employee e -> e.salary > amounts}
    }
    def isHighPaid = paidmore(10000)

    def Closure makeCounter(){
        def local_var = 1
        return {return local_var+1}
    }
    def test(){
//        def smith = new Employee(name: "Fred",salary: 12000)
//        def home = new Employee(name: "Home",salary: 8000)
//
//        println(isHighPaid(smith))
//        println(isHighPaid(home))

//        def c1 = makeCounter()
//        c1()
//        c1()
//        c1()
//        def c2 = makeCounter()
//        c2()
//        c2()
//        c2()
//        println("c1= ${c1()} ,c2=${c2()}")

    }
    static def filter(list,predict){
        def newlist = []
        list.each{
            if(predict(it)) newlist<<it
        }
        return newlist
    }
    static def filterRec(list,pred){
        if(list.size() == 0) return list
        if(pred(list.head())) [] + list.head() + filterRec(list.tail(),pred)
        else filterRec(list.tail(),pred)
    }
    static void main(String... args) {

//        println((1..10).inject(0,{a,b -> a+b}))
//        println((1..10).collect {it+=1})
//
//        def words = ["the","quick","brown","fox","jumed"]
//        def s = words.collect {it.length()}
//        println(s)

//        def list = [[1,2,3],[3,4,5],[11,22]]
//        println(list.flatten{it+=1})

//        new HelloGroovy().test()

//        def adder = {x,y -> x+y}
//        def inc = adder.curry(1)
//        println("inc: ${inc(8)}")

        def modby2 = {x -> x%2 == 0}
        println(filter(1..20,modby2))
        println(filterRec(1..20,modby2))

    }
}
