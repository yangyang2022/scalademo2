import java.util.List;

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


        //int factor = 2;
        //Function<Integer,Integer> f = (Integer i) -> factor * i;
        //List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        //Optional<Integer> sum1 = list.stream().map(f).reduce(Integer::sum);
        //System.out.println(sum1.get());
        //factor = 3;
        //Function<Integer,Integer> f2 = (Integer i) -> factor * i;

        //System.out.println(calc(8));
        calc(8);
    }

    private static void printarr(int[] arr){
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i]+" ,");
        }
        System.out.println();
    }
    public static boolean check(int[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == -1) count++;
        }
        return count != arr.length-1;
    }
    private static int get(int[] arr){
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != -1) return arr[i];
        }
        return -2;
    }
    public static void printcoll(List<Integer> coll){
        coll.forEach(e-> System.out.print(e+" ,"));
        System.out.println();
    }
    public static int getStep(int[] arr,int step){
        int count = 0;
        int i = step+1;
        i = i % arr.length;
        if(arr[i] != -1){
            count+=1;
            step+=1;
            if(count == 2){
                return step;
            }
        }
        return -1;
    }
    public static void calc(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = i;
        }
        int step = 2;
        while (check(arr)){
            arr[step] = -1;
            int count = 0;
            while (count != 2){
                if(arr[step] != -1) {
                    count+=1;
                    step += 1;
                }else step += 1;
                step = step % arr.length;
            }
            System.out.println("step: "+step);
        }
        printarr(arr);
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
