package population.analyzer;

import org.apache.flink.api.common.functions.MapFunction;

public class AgeRangeMapper implements MapFunction<Person, AgeRange> {
    @Override
    public AgeRange map(Person person) throws Exception {
            Integer age=person.age;
            String ageRange;
            if(age<13)
            {
            ageRange="child";
            }
            else if(age<20)
            {
            ageRange="teenager";
            }
            else if(age<50)
            {
            ageRange="adult";
            }
            else {
            ageRange = "old";
            }
            //AgeRange agerange =new AgeRange(ageRange,1);
            AgeRange ageRange1 = new AgeRange();
            ageRange1.range = ageRange;
            ageRange1.count = 1;
            return ageRange1;
            //return agerange;
            }
}
