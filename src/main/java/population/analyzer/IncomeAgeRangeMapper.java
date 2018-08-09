package population.analyzer;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;

public class IncomeAgeRangeMapper implements MapFunction <Person, Tuple3<String, String, Integer>>{
    @Override
    public Tuple3<String, String, Integer> map(Person person) throws Exception {
        AgeRangeMapper ageMapper =  new AgeRangeMapper();
        AgeRange ageRange = ageMapper.map(person);
        IncomeRangeMapper incomeMapper = new IncomeRangeMapper();
        IncomeRange incomeRange = incomeMapper.map(person);
        return new Tuple3<>(ageRange.range, incomeRange.range, 1);
    }
}
