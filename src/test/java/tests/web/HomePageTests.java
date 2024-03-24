package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static io.qameta.allure.Allure.step;

@Feature("Home page testing")
@Tag("tests")
public class HomePageTests extends TestBase {
    HomePage homePage = new HomePage();

    @Test
    @Tag("web")
    @DisplayName("Проверка страницы")
    @Story("Проверка загрузки главной страницы")
    void checkHomePageTitleTest() {
        step("Откроем главную и проверим логотип компании", () -> {
            homePage.openPage("https://shop.tastycoffee.ru/");
            homePage.CheckLogo();
        });
    }

    @Test
    @Tag("web")
    @DisplayName("Проскроллим страничку до самого низа")
    @Story("Скролл страницы")
    void ScrollTest() {
        step("Откроем главную", () -> {
            homePage.openPage("https://shop.tastycoffee.ru/");
            homePage.ViewText();
        });
    }

    @Test
    @Tag("web")
    @DisplayName("Авторизуемся на главной странице")
    @Story("Проверка авторизации")
    void authTest() {
        step("Откроем главную", () -> {
            homePage.openPage("https://shop.tastycoffee.ru/");
            homePage.Authorization();
        });
    }
}