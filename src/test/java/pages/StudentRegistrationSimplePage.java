package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class StudentRegistrationSimplePage {

    public void setFirstName(String text) {
        $("#firstName").val(text);
    }

    public void setLastName(String text) {
        $("#lastName").val(text);
    }

    public void setEmail(String text) {
        $("#userEmail").val(text);
    }

}
