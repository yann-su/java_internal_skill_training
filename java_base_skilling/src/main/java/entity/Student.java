package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student extends Person{

    private String name;

    private int age;

    private String sex;


    public static int Sort(Student student1,Student student2){
        return student2.getAge() - student1.getAge() ;
    }


}
