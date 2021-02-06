package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.FileUtils.readStringFromFile;

public class StudentRegistrationFormWithExecuteScriptTests extends TestBase {

    static String firstName = "Alex",
            lastName = "Alexov",
            email = "aa@aa.com",
            gender = "Other",
            mobile = "1234567890",
            dayOfBirth = "10",
            monthOfBirth = "May",
            yearOfBirth = "1988",
            subject1 = "Chemistry",
            subject2 = "Commerce",
            hobby1 = "Sports",
            hobby2 = "Reading",
            hobby3 = "Music",
            picture = "1.png",
            currentAddress = "Montenegro 123",
            state = "Uttar Pradesh",
            city = "Merrut";

    static Map<String, String> expectedData  = new HashMap<>();

    @BeforeAll
    static void successfulFillFormTest() throws JsonProcessingException {
        String firstName = "Alex";
        String lastName = "Alexov";

        expectedData.put("Student Name", firstName + " " + lastName);

        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").val(mobile);
        // set date
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();
        // set subject
        $("#subjectsInput").val(subject1);
        $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
        $("#subjectsInput").val(subject2);
        $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
        // set hobbies
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#hobbiesWrapper").$(byText(hobby3)).click();
        // upload image
//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/" + picture));
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
        // set current address
        $("#currentAddress").val(currentAddress);
        // set state and city
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    @ParameterizedTest(name = "{1} {2}")
    @MethodSource("getTableDataAsStream")
    void checkTableData(String key, String value) {
        System.out.println(key + " " + value);
        assertThat(expectedData.get(key), containsString(value));
    }

    public static Stream<Arguments> getTableDataAsStream() throws JsonProcessingException {
        return createList(getBodyContentWithExecuteScript()).stream();
    }

    private static List<Arguments> createList(Map<String, String> data) {
        return data.entrySet()
                .stream()
                .map(e -> Arguments.of(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public static Map<String, String> getBodyContentWithExecuteScript() throws JsonProcessingException {
        String jsCode = readStringFromFile("./src/test/resources/javascript/get_table_data.js");
        String browserResponse = executeJavaScript(jsCode);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = mapper.readValue(browserResponse,
                new TypeReference<Map<String, String>>(){});
        System.out.println(data);

        return data;


//        TypeReference<HashMap<String, String>> typeRef
//                = new TypeReference<HashMap<String, String>>() {};
//        Map<String, String> map = mapper.readValue(jsonInput, typeRef);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        return mapper.readValue(browserResponce, StudentData.class);
    }
//
//    @ParameterizedTest
//    @MethodSource("provideStringsForIsBlank")
//    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
//        assertEquals(expected, Strings.isBlank(input));
//    }
//
//    private static Stream<Arguments> provideStringsForIsBlank() {
//        return Stream.of(
//                Arguments.of(null, true),
//                Arguments.of("", true),
//                Arguments.of("  ", true),
//                Arguments.of("not blank", false)
//        );
//    }
}
