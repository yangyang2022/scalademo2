import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
//import scala.collection.mutable.HashMap;

/**
 * Created by yy on 2017/4/29.
 */
public class HelloJava {
	static Map<String,Integer> map;
	public static void main(String[] args) throws IOException {

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
		//calc(8);


		//System.out.println(new HelloJava().sum());

		//Map<String,Integer> maps = new HashMap<>();
		//Integer count = maps.getOrDefault("hello",0)+1;
		//System.out.println(count);

		//String path = ClassLoader.getSystemResource("data.txt").getPath();
		//System.out.println(path);
		//Files.readAllLines(Paths.get(path.substring(1))).forEach(System.out::println);

		//int i = 15;
		//while(i --> 10){
		//	System.out.println(i);
		//}
		//String s1 = "hello world";
		//String s2 = new String("hello world");
		//System.out.println(s1 == s2);
		//System.out.println(s1 == s2.intern());

		//map = new HashMap<>();
		//map.put("a",1);
		//map.put("b",2);
		//map.put("c",3);
		//map.put("d",2);
		//System.out.println(map);
		//
		//Consumer print = System.out::println;
		//BiConsumer printmap = (k,v)-> System.out.println(k+" -> "+v);
		//
		//Map<Integer,Set<String>> newmap = new HashMap<>();
		//
		//map.values().stream().collect(Collectors.groupingBy(Function.identity()))
		//		.forEach((k,v)->newmap.put(k,calcMap(v.get(0))));
		//System.out.println(newmap);


		//sorted("3,6,9,2,10");
		//sorted("foot,head,body");

		//String filepath = "C:\\mavenProject\\scalademo2\\src\\java\\HelloJava.java";
		//String words = Files.readAllLines(Paths.get(filepath)).stream().collect(Collectors.joining());
		//System.out.println(wordsmap(words));

		//System.out.println(isPerfect(6));
		//System.out.println(isPerfect(28));
		//System.out.println(isPerfect(44));

		//printnum(2233);
		//List foo = new ArrayList();
		//List<Object> f = foo;
		//f.add("hello");
		//f.add(1);
		//f.add(2);
		//f.add(3.14);
		//System.out.println(f);

	}

	private static void printnum(int n){
		if(n < 10000){
			System.out.println(n);
			printnum(n<<1);
			if(n > 5000) System.out.print("");
			else System.out.println(n);
		}
	}
	private static boolean isPerfect(int number){
		return aliquotSum(number) == number;
	}
	private static int aliquotSum(int number){
		return factorsOf(number).sum() - number;
	}
	private static IntStream factorsOf(int number){
		return IntStream.range(1,number+1).filter(x -> number % x == 0);
	}
	public static Map<String,Integer> wordsmap(String words){
		TreeMap<String,Integer> wordMap = new TreeMap<>();
		regexToList(words,"\\w+").stream()
				.map(String::toLowerCase)
				.filter(e->!NONWORDS.contains(e))
				.forEach(w->wordMap.put(w,wordMap.getOrDefault(w,0)+1));
		return wordMap;

	}
	private static List<String> NONWORDS = Arrays.asList("a","the","to","i");

	private static List<String> regexToList(String words,String regex){
		List<String> listwords = new ArrayList<>();
		Matcher matcher = Pattern.compile(regex).matcher(words);
		while (matcher.find()) listwords.add(matcher.group());
		return listwords;
	}
	private static void sorted(String s){
		Arrays.stream(s.split(",")).map(e->{
			try{
				return Integer.parseInt(e);
			}catch (Exception ex){
				return e;
			}
		}).sorted().forEach(e-> System.out.print(e+" ,"));
		System.out.println();
	}
	private static Set<String> calcMap(int value){
		Set<String> values = new HashSet<>();
		map.forEach((k,v)->{
			if(v.equals(value)) values.add(k);
		});
		return values;
	}
	private int sum() throws IOException {

		String path = this.getClass().getClassLoader().getResource("data.txt").getPath();

		int sum = Files.readAllLines(Paths.get(path.substring(1)))
				.stream().map(e -> e.split("\\D+"))
				.flatMap(Arrays::stream).filter(e->!e.equals(""))
				.mapToInt(Integer::parseInt).sum();
		return sum;
	}

}