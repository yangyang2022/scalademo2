import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by syy on 2017/5/10.
 */
public class Concurrent1 {
	private static void sleep(long mills){
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static void sleep1s(){
		sleep(1000);
	}
	private static void sleep2s(){
		sleep(2000);
	}
	private static Callable<String> callable(String result,long sleepSeconds){
		return ()->{
			TimeUnit.SECONDS.sleep(sleepSeconds);
			return result;
		};
	}
	static class User{
		private String name;
		private Integer age;
		private Role role;

		public User() {
		}

		public User(String name, Integer age, Role role) {
			this.name = name;
			this.age = age;
			this.role = role;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "User{" +
					"name='" + name + '\'' +
					", age=" + age +
					", role=" + role +
					'}';
		}
	}
	static class Role{
		private String name;

		public Role() {
		}

		public Role(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Role{" +
					"name='" + name + '\'' +
					'}';
		}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

		//new Thread(()->{
		//	String name = Thread.currentThread().getName();
		//	System.out.println("hello: "+name);
		//}).start();
		//System.out.println("main end ... ");
		//sleep1s();

		//ExecutorService service = Executors.newWorkStealingPool();

		//List<Callable<String>> tasks = Arrays.asList(()->"task1",()->"task2",()->"task3");
		//
		//service.invokeAll(tasks).stream().map(f -> {
		//	try {
		//		return f.get();
		//	} catch (Exception e) {
		//		e.printStackTrace();
		//		throw new RuntimeException("exception ... ");
		//	}}).forEach(System.out::println);

		//List<Callable<String>> tasks = Arrays.asList(
		//		callable("task1",1),
		//		callable("task2",2),
		//		callable("task3",3));
		//String result = service.invokeAny(tasks);
		//System.out.println("result: "+result);

		//ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		//
		//Runnable task = ()-> System.out.println("scheduleding: "+System.currentTimeMillis());

		//ScheduledFuture<?> schedule = service.schedule(task, 3, TimeUnit.MILLISECONDS);
		//
		//TimeUnit.MILLISECONDS.sleep(1377);
		//
		//long remaindelay = schedule.getDelay(TimeUnit.MILLISECONDS);
		//System.out.printf("remian delay: %s ms",remaindelay);

		//int initdelay = 0;
		//int period = 1;
		//service.scheduleWithFixedDelay(task,initdelay,period,TimeUnit.SECONDS);



		ExecutorService service = Executors.newScheduledThreadPool(2);

		Consumer print = System.out::println;
		BiConsumer printmap = (k,v)-> System.out.println(k+" -> "+v);

		//IntStream.range(0,10000).forEach(e->service.submit(Concurrent1::increment));
		//
		//ConcurrentUtils.stop(service);
		//System.out.println("count: "+count);

		//ReentrantLock lock = new ReentrantLock();
		//
		//service.submit(()->{
		//	System.out.println("Locked: "+lock.isLocked());
		//	System.out.println("Held by me: "+lock.isHeldByCurrentThread());
		//	System.out.println("Lock acquire: "+lock.tryLock());
		//});
		//
		//service.submit(()->{
		//	lock.lock();
		//	try {
		//		sleep1s();
		//	}finally {
		//		lock.unlock();
		//	}
		//});
		//
		//Map<String,String> map = new HashMap<>();
		//ReadWriteLock lock = new ReentrantReadWriteLock();
		//
		//service.submit(()->{
		//	lock.writeLock().lock();
		//	try {
		//		sleep1s();
		//		map.put("foo","bar");
		//	}finally {
		//		lock.writeLock().unlock();
		//	}
		//});
		//
		//Runnable task = ()->{
		//	lock.readLock().lock();
		//	try {
		//		System.out.println(map.get("foo"));
		//		sleep1s();
		//	}finally {
		//		lock.readLock().unlock();
		//	}
		//};
		//
		//service.submit(task);
		//service.submit(task);
		//service.submit(task);
		//service.submit(task);


		//Semaphore semaphore = new Semaphore(5);
		//
		//Runnable runtask = ()->{
		//	boolean permit = false;
		//	try {
		//		permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
		//		if(permit){
		//			System.out.println("semaphor acquire ... ");
		//			sleep(5000);
		//		}else {
		//			System.out.println("can't acquire semaphor ... ");
		//		}
		//	} catch (InterruptedException e) {
		//		e.printStackTrace();
		//	}finally {
		//		if(permit){
		//			semaphore.release();
		//		}
		//	}
		//};
		//
		//IntStream.range(0,10).forEach(e->service.submit(runtask));

		//LongAdder adder = new LongAdder();
		//IntStream.range(0,1000).forEach(e->service.submit(adder::increment));
		//
		//System.out.println(adder.sumThenReset());
		//System.out.println(adder.intValue());

		//LongBinaryOperator op = (x,y)->{
		//	long res = 2*x + y;
		//	System.out.println("x: "+x+" ,y: "+y+" ,res: "+res);
		//	return res;
		//};
		//LongBinaryOperator op = (x,y)->2*x+y;
		//
		//LongAccumulator accumulator = new LongAccumulator(op,1l);
		//
		//IntStream.range(0,10).forEach(e->service.submit(() -> accumulator.accumulate(e)));
		//
		//ConcurrentUtils.stop(service);
		//
		//System.out.println("result: "+accumulator.get());

		//ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
		//map.put("foo", "bar");
		//map.put("han", "solo");
		//map.put("r2", "d2");
		//map.put("c3", "p0");

		//String value = map.putIfAbsent("c4","p3");
		//System.out.println(value);

		//map.replaceAll((k,v)->"r2".equals(k) ? "d3" : v);
		//
		//System.out.println(map.get("r2"));
		//System.out.println(map.size());

		//map.compute("foo",(k,v)->k+v);
		//map.merge("foo","boo",(oldvalue,newvalue)->newvalue+"was"+oldvalue);

		//map.forEach(printmap);

		//map.forEach(1,(key, value) ->
		//		System.out.printf("key: %s; value: %s; thread: %s\n",
		//				key, value, Thread.currentThread().getName()));
		//
		//String result = map.reduce(1,(k,v)->k+"="+v,(s1,s2)->s1+" ,"+s2);
		//System.out.println(result);


		//String s = String.join(":","foo","bar","hellobar");
		//
		//System.out.println(s);
		//
		//String s1 = s.chars().distinct().mapToObj(c -> String.valueOf((char) c))
		//		.sorted()
		//		.collect(Collectors.joining());
		//System.out.println(s1);
		//
		//String s2 = Pattern.compile(":")
		//		.splitAsStream(s)
		//		.filter(e -> e.contains("bar"))
		//		.sorted()
		//		.collect(Collectors.joining(":"));
		//System.out.println(s2);

		//Pattern pattern = Pattern.compile(".*gmail\\.com");
		//long count = Stream.of("yangyang2022@gmail.com","bob@gmail.com", "alice@hotmail.com").filter(pattern.asPredicate()).count();
		//System.out.println(count);

		Path path = Paths.get("c:\\code\\data2.txt");
		//Files.list(path)
		//		.map(String::valueOf)
		//		.filter(file->!file.startsWith("."))
		//		.sorted()
		//		.forEach(print);

		//int maxDepth = 5;
		//List<String> list = Files.walk(path, maxDepth)
		//		.map(String::valueOf)
		//		.filter(e -> e.endsWith(".java"))
		//		.collect(Collectors.toList());
		//System.out.println(list);

		//List<String> lines = Files.readAllLines(path);
		//lines.add("hello world1");
		//lines.add("end line2");
		//Files.write(path,lines);

		Role admin = new Role("admin");
		Role guan1 = new Role("guan1");
		Role guan2 = new Role("guan2");

		List<User> users = Arrays.asList(
				new User("shen",12,admin),
				new User("yang",22,guan1),
				new User("hehe",33,guan2),
				new User("haha1",22,admin),
				new User("haha2",23,admin),
				new User("haha3",24,admin),
				new User("haha4",25,admin)
		);

		Map<Integer,Integer> intmap = new HashMap<>();
		intmap.put(1,11);
		intmap.put(2,22);
		intmap.put(3,33);
		intmap.put(4,44);
		intmap.put(5,55);

		Map<String,Integer> maps = new HashMap<>();
		maps.put("a",1);
		maps.put("b",2);
		maps.put("c",3);
		maps.put("d",2);

		//List<String> namelists = StreamEx.of(users).map(User::getName).prepend("first").append("last").toList();
		//System.out.println(namelists);
		//
		//Map<Role, List<User>> roleListMap = StreamEx.of(users).groupingBy(User::getRole);
		//System.out.println(roleListMap);

		//String joining = StreamEx.of(1, 2, 3, 4, 5).joining(" ,");
		//System.out.println(joining);

		//Set<Role> roles = StreamEx.ofKeys(roleListMap, list -> list.size() > 2).toSet();
		//System.out.println(roles);

		//Map<String, String> stringMap = EntryStream.of(intmap)
		//		.mapKeys(String::valueOf)
		//		.mapValues(String::valueOf)
		//		.toMap();
		//System.out.println(stringMap);

		System.out.println("hello world");

	}
	//static int count = 0;
	//private static synchronized void increment(){
	//	count = count+1;
	//}
}
