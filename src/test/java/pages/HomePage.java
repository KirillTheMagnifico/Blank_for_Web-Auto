package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement logo = $x("/html/body/div[1]/header/div[1]/div/div[7]/a/img"),
            AuthButton = $(".enter-h .greyLink.enterOpen"),
            Email =  $x("/html/body/div[1]/header/div[4]/div[1]/div[2]/div[1]/form/div[1]/div/input"),
            Password = $x("/html/body/div[1]/header/div[4]/div[1]/div[2]/div[1]/form/div[2]/div/input"),
            Enter = $(".popupEn .popupEn-f .tab-content .btn-wrap .blackBtn, .popupEn .popupEn-f .tab-content .btn-wrap .grayBtn");

    public HomePage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }
     public HomePage CheckLogo() {
        logo.shouldBe(visible);;
        return this;
     }

     public HomePage ViewText() {
       $x("/html/body/footer/div[2]/div/div/div[1]/div").scrollTo();
       sleep(10000);
       return this;
    }
    public HomePage Authorization() {
        AuthButton.click();
        Email.setValue("serkirsuperstar@mail.ru");
        Password.setValue("Al25ki06");
        Enter.click();
        openPage("https://shop.tastycoffee.ru/profile");
        sleep(10000);
        return this;
    }
}


