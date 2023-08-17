package manager;

import models.Gender;
import models.Hobby;
import models.StudentDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface HelperStudent extends HelperBase{

    default void selectForms(){
        click(By.xpath("//div[@class='category-cards']/div[2]"));
    }
    default void selectPracticeForm(){
        click(By.xpath("//span[.='Practice Form']"));
    }
    default void fillForm(StudentDTO model){
        type(By.id("firstName"), model.getFirstName());
        type(By.id("lastName"), model.getLastName());
        type(By.id("userEmail"), model.getEmail());
        selectGender(model.getGender());
        type(By.id("userNumber"), model.getPhone());
//        type(By.xpath("//input[@id='dateOfBirthInput']"), model.getBirthday());
        typeBDay(model.getBirthday());
        addSubject(model.getSubjects());
        selectHobby(model.getHobbies());
        type(By.id("currentAddress"),model.getAddress());
        typeState(model.getState());
        typeCity(model.getCity());

    }

    default void typeBDay(String birthday){

       LocalDate date = LocalDate.parse(birthday,DateTimeFormatter.ofPattern("dd MM yyyy"));
       LocalDate nowDate = LocalDate.now();
       String bdDay = String.format("//div[.='%s']",date.getDayOfMonth());
       click(By.xpath("//input[@id='dateOfBirthInput']"));

       int startToBirthMonth =  nowDate.getYear() - date.getYear() == 0 ?
                nowDate.getMonthValue() - date.getMonthValue() :
             12 - nowDate.getMonthValue() + date.getMonthValue();
        for(int i = 0; i < startToBirthMonth; i++){
            click(By.xpath("//button[@aria-label='Previous Month']"));
            pause(1000);
        }
        click(By.xpath(bdDay));

//        JavascriptExecutor js = (JavascriptExecutor) wd;
//        js.executeScript("document.querySelector('#autocomplete').setAttribute('autocomplete', 'off')");
//        js.executeScript("document.querySelector('#autocomplete').setAttribute('autocapitalize', 'none')");

    }


    default void selectGender(Gender gender){
        if(gender.equals(Gender.MALE)){
            click(By.xpath("//label[.='Male']"));
        }else if(gender.equals(Gender.FEMALE)){
            click(By.xpath("//label[.='Female']"));
        }else {
            click(By.xpath("//label[.='Other']"));
        }

    }
    default void addSubject(String subjects){
      String[] split = subjects.split(",");
      String locator = "subjectsInput";
      click(By.id(locator));
      for(String subject: split){
          wd.findElement(By.id(locator)).sendKeys(subject);
          wd.findElement(By.id(locator)).sendKeys(Keys.ENTER);

      }
    }

    default void selectHobby(List<Hobby> hobbies){
        for(Hobby hobby: hobbies){
            switch (hobby){
                case SPORTS:
                    click(By.xpath("//label[.='Sports']"));
                    break;
                case READING:
                    click(By.xpath("//label[.='Reading']"));
                    break;
                case MUSIC:
                    click(By.xpath("//label[.='Music']"));
                    break;
            }
        }

    }
    default void typeState(String state){
        wd.findElement(By.id("react-select-3-input")).sendKeys(state);
        wd.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
    }
    default void typeCity(String city){
        wd.findElement(By.id("react-select-4-input")).sendKeys(city);
        wd.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);
    }

    default void submit(){
        click(By.id("submit"));
    }

}
