import javascala.HelloScala;
import scala.collection.mutable.LinkedHashMap;

import java.io.IOException;

public class HelloJavaScala {

	static class Name{
		private String firstname;
		private String lastname;

		public Name(String firstname, String lastname) {
			this.firstname = firstname;
			this.lastname = lastname;
		}
	}
	private static LinkedHashMap<Integer,Name> map = new LinkedHashMap<>();

	public static void main(String[] args) throws IOException {

		//Function1<String,Integer> string2Int = Integer::parseInt;
		//
		//System.out.println(string2Int.apply("123"));
		//
		//Tuple2<String,Integer> s1 = new Tuple2<>("on2", 2);
		//System.out.println(s1._1+" - "+s1._2);

		//map.update(1,new Name("shen","yangyang"));
		//System.out.println(map.get(1).get());

		//Consumer print = System.out::println;
		//String path = ClassLoader.getSystemResource("data.txt").getPath();
		//int sum = Files.readAllLines(Paths.get(path.substring(1)))
		//		.stream().map(line -> line.split("\\D+"))
		//		.flatMap(Arrays::stream)
		//		.filter(e->!e.equals(""))
		//		.mapToInt(Integer::parseInt)
		//		.sum();
		//System.out.println("sum: "+sum);
		//
		//System.out.println(Runtime.getRuntime().availableProcessors());

		//Function<Integer,Object> op = (Integer x)-> x*3.14;
		double multi = HelloScala.multi(99);
		System.out.println(multi);

	}

}
