import manager.HelperStudent;
import models.Gender;
import models.Hobby;
import models.StudentDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentFormTest extends TestBase implements HelperStudent {

    @BeforeMethod
    public void precondition(){
        hideAD();
        selectForms();
        hideAD();
        selectPracticeForm();

    }

    @Test
    public void studentFormPositive(){
        List<Hobby> hobbies = new ArrayList<>();
        hobbies.add(Hobby.MUSIC);
        hobbies.add(Hobby.READING);
        StudentDTO student = StudentDTO.builder()
                .firstName("Jane")
                .lastName("Air")
                .email("jane33@mail.com")
                .gender(Gender.FEMALE)
                .phone("1234567890")
                .birthday("13 06 2023")
                .subjects("Maths,Physics")
                .hobbies(hobbies)
                .address("Main street, 25")
                .state("NCR")
                .city("Delhi")
                .build();

        fillForm(student);
        submit();
    }




}
