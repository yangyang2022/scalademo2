import com.intellij.util.Consumer;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

/**
 * Created by syy on 2017/5/10.
 */
public class HelloJava2 {

	@FunctionalInterface
	interface Converter<F,T>{
		T convert(F from);
	}

	static class Something{
		String startWith(String s){
			return "startwith: "+String.valueOf(s.charAt(0));
		}
	}
	static class Person{
		private String name;
		private int age;

		public Person() {
		}

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}

	@FunctionalInterface
	interface PersonFactory<P extends Person>{
		P create(String firstname,String lastname);
	}

	static class Bar{
		private String name;

		public Bar(String name) {
			this.name = name;
		}
	}
	static class Foo{
		private String name;

		List<Bar> bars = new ArrayList<>();

		public Foo(String name) {
			this.name = name;
		}
	}
	public static void main(String[] args) throws IOException {

		//Converter<String,Integer> converter = Integer::valueOf;
		//System.out.println(converter.convert("123"));
		//
		//Something something = new Something();
		//Converter<String,String> ssconverter = something::startWith;
		//System.out.println(ssconverter.convert("JAVA"));

		//PersonFactory<Person> personFactory = Person::new;
		//Person p1 = personFactory.create("shen","yangyang");
		//System.out.println(p1);

		//Supplier<Person> supplier = Person::new;
		//System.out.println(supplier.get());
		//
		//Function<String,Integer> ftoi = Integer::parseInt;
		//Function<Integer,String> itos = String::valueOf;
		//
		//System.out.println(ftoi.andThen(itos).apply("123"));
		//
		//System.out.println(UUID.randomUUID());

		Consumer print = System.out::println;
		BiConsumer printmap = (k,v)-> System.out.println(k+" -> "+v);

		Map<String,Integer> map = new HashMap<>();
		//for (int i = 0; i < 10; ++i) {
		//	map.putIfAbsent(i, "val " + i);
		//}
		//map.forEach(printmap);
		//System.out.println(map.getOrDefault(32,"not found!"));
		//map.computeIfPresent(3,(num,val)->val+num);
		//map.computeIfAbsent(23,num -> "val "+num);

		//System.out.println(map.get(3));
		//map.merge(9,"val998", String::concat);
		//System.out.println(map.get(9));

		//map.forEach(printmap);
		//
		//String path = "C:\\code\\data.json";
		//Map<String, Long> collect = Files.readAllLines(Paths.get(path))
		//		.stream()
		//		.map(line -> line.split("\\W+"))
		//		.flatMap(Arrays::stream)
		//		.filter(e -> !e.equals(""))
		//		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		//
		//collect.forEach(printmap);

		//LocalDate date1 = LocalDate.of(2013,2,2);
		//LocalDate date2 = LocalDate.now();
		//
		//long days = ChronoUnit.MONTHS.between(date1, date2);
		//System.out.println(days);

		//Stream.of(1,2,3,4,5)
		//		.filter(s->{
		//			System.out.println("filter: "+s);
		//			return true;
		//		})
		//		.forEach(System.out::println);

		//Stream.of("d2", "a2", "b1", "b3", "c")
		//		.map(s -> {
		//			System.out.println("map: "+s);
		//			return s.toUpperCase();
		//		})
		//		.anyMatch(s->{
		//			System.out.println("anymatch: "+s);
		//			return s.startsWith("A");
		//		});

		List<Person> persons =
				Arrays.asList(
						new Person("Max", 18),
						new Person("Peter", 23),
						new Person("Pamela", 23),
						new Person("David", 12));

		//Double averageAge = persons.stream().collect(Collectors.averagingDouble(Person::getAge));
		//System.out.println(averageAge);
		//
		//IntSummaryStatistics is = persons.stream().collect(Collectors.summarizingInt(Person::getAge));
		//System.out.println(is);

		//Map<Integer, String> map1 = persons.stream()
		//		.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + " ;" + name2));
		//System.out.println(map1);

		//Collector<Person,StringJoiner,String> personCollect = Collector.of(
		//		() -> new StringJoiner(" | "),
		//		(j,p) -> j.add(p.name.toUpperCase()),
		//		StringJoiner::merge,
		//		StringJoiner::toString
		//);
		//String collect = persons.stream().collect(personCollect);
		//System.out.println(collect);
		//
		//Integer ageSum = persons.stream().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
		//System.out.println(ageSum);
		//
		//Person reduce = persons.stream().reduce(new Person("", 0), (p1, p2) -> {
		//	p1.age += p2.age;
		//	p1.name += p2.name;
		//	return p1;
		//});
		//System.out.println(reduce);


		//ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		//System.out.println(forkJoinPool.getParallelism());

		List<Foo> foos = new ArrayList<>();

		//create foos
		IntStream.range(0,4).forEach(i -> foos.add(new Foo("foo"+i)));

		//create bars
		foos.forEach(f->IntStream.range(0,4).forEach(i->f.bars.add(new Bar("Bar"+i+" < "+f.name))));

		foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));



	}
}
