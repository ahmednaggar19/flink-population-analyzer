package population.analyzer;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

public class GenderMapper implements MapFunction<Person, Tuple2<String, Integer>> {
    @Override
    public Tuple2<String, Integer> map(Person person) throws Exception {

            return new Tuple2<>(person.gender, 1);
            }
}
