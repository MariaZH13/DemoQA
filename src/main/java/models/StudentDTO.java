package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class StudentDTO {
    String firstName;
    String lastName;
    String email;
//    String gender;
    Gender gender;
    String phone;
    String birthday;
    String subjects;
//    String hobbies;
    List<Hobby> hobbies;
    String address;
    String state;
    String city;

}
