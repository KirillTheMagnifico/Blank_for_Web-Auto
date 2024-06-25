package pages;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement logo = $x("/html/body/div[1]/header/div[1]/div/div[7]/a/img"),
            AuthButton = $(".enter-h .greyLink.enterOpen"),
            Email =  $x("/html/body/div[1]/header/div[4]/div[1]/div[2]/div[1]/form/div[1]/div/input"),
            Password = $x("/html/body/div[1]/header/div[4]/div[1]/div[2]/div[1]/form/div[2]/div/input"),
            Enter = $(".popupEn .popupEn-f .tab-content .btn-wrap .blackBtn, .popupEn .popupEn-f .tab-content .btn-wrap .grayBtn"),
            Items = $(".cardBox .tovarImg-wrap .absLink "),
            Text = $("footer .bottom-f .text-f"),
            cartAddedButton = $(".buyGoods .blackBtn"),
            popupSuccessText = $(new ByText("Товар добавлен")),
            goToCartButton = $(".popupAdded .blackBtn"),
            deleteAllFromCart = $(".removeLink"),
            emptyText = $(new ByText("Ваша корзина пуста")),
            logoutButton = $(".go-out");

    public HomePage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }
    public HomePage openPageProfile(String pageAddress) {
        open(pageAddress);
        return this;
    }

     public HomePage CheckLogo() {
        logo.shouldBe(visible);;
        return this;
     }

     public HomePage ViewText() {
       Text.scrollTo();
       sleep(1000);
       Text.shouldBe(visible);
       return this;
    }
    public HomePage Authorization() {
        AuthButton.click();
        Email.setValue("serkirsuperstar@mail.ru");
        Password.setValue("Al25Kir0693!!");
        Enter.click();
        openPageProfile("https://shop.tastycoffee.ru/profile");
        String currentUrl = "https://shop.tastycoffee.ru/profile";
        assertThat(currentUrl, containsString("profile"));
        return this;

    }
    public HomePage cartAdded() {
        sleep(1000);
        Items.click();
        cartAddedButton.click();
        popupSuccessText.shouldBe(visible);
        goToCartButton.click();
        deleteAllFromCart.click();
        emptyText.shouldBe(visible);
        return this;
    }

        public void logout() {
            logoutButton.click();
        }
}
