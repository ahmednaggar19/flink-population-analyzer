package population.analyzer;

import akka.stream.impl.io.InputStreamSinkStage;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import javax.xml.crypto.Data;


public class PopulationAnalyzer {

    public static void main (String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> inputStream = env.readTextFile("inputFile.txt");
        DataStream<Person> population = inputStream.map(new MapFunction<String, Person>() {
            @Override
            public Person map(String s) throws Exception {
                String[] splittedLine = s.split(",");
                Person person = new Person(splittedLine[0], splittedLine[3], splittedLine[4], splittedLine[5],
                        Integer.parseInt(splittedLine[1]), Integer.parseInt(splittedLine[2]));

                return person;
            }
        });

        DataStream<AgeRange> ageRangeStream = population.map(new AgeRangeMapper());
        DataStream<IncomeRange> incomeRangeStream = population.map(new IncomeRangeMapper());
        DataStream<Tuple2<String, Integer>> rangeCount = ageRangeStream.map(new MapFunction<AgeRange, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(AgeRange ageRange) throws Exception {
                return new Tuple2<>(ageRange.range, ageRange.count);
            }
        }).keyBy(0).sum(1);
        rangeCount.print();
        DataStream<Tuple2<String, Integer>> incomeCount = incomeRangeStream.map(new MapFunction<IncomeRange, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(IncomeRange incomeRange) throws Exception {
                return new Tuple2<>(incomeRange.range, incomeRange.count);
            }
        }).keyBy(0).sum(1);
//        incomeCount.print();
        DataStream<Tuple2<String, Integer>> genderCount = population.map(new GenderMapper()).keyBy(0);
//        genderCount.print();
        DataStream<Tuple3<String, String, Integer>> ageIncomeStream = population.map(new IncomeAgeRangeMapper())
                .keyBy(0, 1).sum(2);
        ageIncomeStream.print();
        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
