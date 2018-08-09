package population.analyzer;

public class Person {
    public String name, email, coutry, gender,ageRange,incomeRange;
    public Integer age, income;

    public Person(String name, String email, String gender, String country, Integer age, Integer income) {
        this.name = name;
        this.email = email;
        this.coutry = coutry;
        this.gender = gender;
        this.age = age;
        this.income = income;
        /*if(this.age<13)
            {
                this.ageRange="child";
            }
         else if(this.age<20)
            {
                this.ageRange="teenager";
            }
        else if(this.age<50)
            {
                this.ageRange="adult";
            }
        else {
            this.ageRange = "old";
             }
*/
    }

}
