package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class StudentRegistrationPage {

    SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail");

    public void setFirstName(String text) {
        firstNameInput.val(text);
    }

    public void setLastName(String text) {
        lastNameInput.val(text);
    }

    public void setEmail(String text) {
        userEmailInput.val(text);
    }


    public void setBaseInfo(String firstName, String lastName, String userEmail) {
//        firstNameInput.val(firstName);
//        lastNameInput.val(lastName);
//        userEmailInput.val(userEmail);

        setFirstName(firstName);
        setLastName(lastName);
        setEmail(userEmail);
    }


}
