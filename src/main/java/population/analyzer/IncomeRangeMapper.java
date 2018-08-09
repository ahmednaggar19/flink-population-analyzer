package population.analyzer;

import org.apache.flink.api.common.functions.MapFunction;

public class IncomeRangeMapper implements MapFunction<Person, IncomeRange> {
    @Override
    public IncomeRange map(Person person) throws Exception {
            Integer income=person.income;
            String incomeRange;
            if(income<10000)
            {
                    incomeRange="1k-10k";
            }
            else if(income<50000)
            {
                    incomeRange="10k-50k";
            }
            else
            {
                    incomeRange="50k-100k";
            }
            IncomeRange incomeRangeObj= new IncomeRange(incomeRange, 1);
            return incomeRangeObj;
            }
}
