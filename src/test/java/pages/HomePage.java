package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement Search = $(By.ByClassName.name("searchTerm")),
            SearchButton = $x("/html/body/div[1]/div[1]/div[1]/div/div[3]/div/div[7]/div/div/form/div/div[2]/a[2]"),
            SearchText =  $x("/html/body/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div/div/span/div/h1");
    public HomePage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    // public HomePage clickSearch() {
    //   Search.setValue("Колбаса");
    //    SearchButton.click();
    //    return this;
    // }

    //public HomePage verifyText() {
    //   SearchText.shouldBe(visible);
    //   return this;
    // }

    // public HomePage clickStartNavLink(String linkText) {
    //   $x("//a[@class='nav-link'][contains(text(),'" + linkText + "')]").click();
    //   return this;
    //}
}


