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
    @DisplayName("Проверка страницы Kinopoisk")
    @Story("Проверка загрузки главной страницы")
    void checkHomePageTitleTest() {
        step("Откроем главную", () -> {
            homePage.openPage("https://www.kinopoisk.ru");
        });
    }

    @Test
    @Tag ("web")
    @DisplayName("Проверим поиск")
    @Story("Заполним строку поиска запросом и найдем что-то")
    void SearchTest() {
        step("Откроем главную", () -> {
            homePage.openPage("https://www.kinopoisk.ru");
        });
    }
}
