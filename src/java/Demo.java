import java.util.function.Function;

/**
 * Created by syy on 2017/5/18.
 */
public class Demo {


	private static void handler(Function<Integer,String> f,int x) {
		System.out.println(f.apply(x));
	}
	public static void main(String[] args) {


		handler(x->String.valueOf(x+10),111);

	}
}
