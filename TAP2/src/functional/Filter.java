package functional;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by milax on 28/09/15.
 */
public class Filter {

    public static <T> List<T> filter(List<T> list, Predicate<T> f) {
        List<T> result = new LinkedList<T>();
        for (T elem : list) {
            if (f.test(elem))
                result.add(elem);
        }
        return result;
    }

    public static void main(String args[]) {
        List<String> elems = new LinkedList<>();
        elems.add("tap");
        elems.add("lp");
        elems.add("ptap");
        elems.add("lp");
        List<String> result = Filter.filter(elems, (String p) -> p.startsWith("p"));
        for (String elem : result)
            System.out.println(elem);

        List<Integer> elems2 = new LinkedList<>();
        for(int i=0; i<20; i++){
            elems2.add(i);
        }
        List<Integer> result2 = Filter.filter(elems2, x -> x%2==0);
        for (Integer i : result2){
            System.out.print("\t"+i);
        }

    }
}