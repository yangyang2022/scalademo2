/**
 * Created by yy on 2017/4/29.
 */
public class HelloJava {
    public static void main(String[] args) {

        //System.out.println("hello java");

        //try {
        //    new MyThrower().exceptionThrower();
        //} catch (Exception e) {
        //    System.out.println("exception "+e.getMessage());
        //}

        //new Printer().printall("hello","world","haha");
        //
        //int sum = new MathDemo().sum(1, 22);
        //System.out.println(sum);
        //int sum1 = new MathDemo2().sum(11, 22);
        //System.out.println(sum1);

        //Set<Integer> sets = new TreeSet<>(((o1, o2) -> {if(o1 - o2 >= 0) return 1;else return -1;}));
        //sets.add(1);
        //sets.add(1);
        //sets.add(1);
        //System.out.println(sets);





    }
    //public static List<Integer> getNumbers(){
    //    List<Integer> list = new ArrayList<>();
    //    list.add(11);
    //    list.add(22);
    //    list.add(33);
    //    list.add(44);
    //    return list;
    //}
    //public static Integer sum(List<Integer> list){
    //    return list.stream().mapToInt(e->e).sum();
    //}
}

class MathDemo implements MathTrait{
    @Override
    public int sum(int x, int y) {
        return x+y;
    }
}

class MathDemo2 extends MathWrapper{

}
