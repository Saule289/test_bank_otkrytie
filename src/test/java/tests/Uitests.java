package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import tests.data.City;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class Uitests extends TestBase {

    @Test
    @DisplayName("В верхнем хедере перечислены все категории клиентов")
    public void checkHeaderNames() {

        step("Открываем главную страницу", () -> {

            pageObjects.openPage();
        });
        step("Проверяем названия первого заголовка хедера", () -> {
            pageObjects.checkNamesOfHeaders("Частным клиентам");
        });
        step("Проверяем названия первого заголовка хедера", () -> {
            pageObjects.checkNamesOfHeaders("Малому и среднему бизнесу");
        });
        step("Проверяем названия первого заголовка хедера", () -> {
            pageObjects.checkNamesOfHeaders("Корпорациям");
        });
        step("Проверяем названия первого заголовка хедера", () -> {
            pageObjects.checkNamesOfHeaders("Финансовым институтам");
        });
    }

    @ParameterizedTest(name = "В выдаче поиска присутсвутет введенное название")
    @ValueSource(strings = {
            "Ипотека", "Осаго", "Инвестиции", "Универсальная карта"
    })
    void searchCheck(String searchInput) {
        step("Открываем главную страницу", () -> {
            pageObjects.openPage();
        });
        step("Тап на строку поиска", () -> {
            $(".HeaderSearch_search-icon__Y1fgh").click();
        });

        step("Ввод эемента для поиска", () -> {
            $("input.open-ui-search-input").setValue(searchInput).pressEnter();
        });

        step("Первая строка в выдаче поиска содержит запрашиваемую информацию", () -> {
            $(".card__top-container").shouldHave(Condition.text(searchInput));
        });
    }

    @Test
    @DisplayName("Проверка количество прделожений банка в соотвествии с категорией клиентов")
    public void checkBankProposal() {

        step("Открываем главную страницу", () -> {
            pageObjects.openPage();
        });

        step("Выбрать категорию и проверить количество доступных продуктов клиента", () -> {
            if (pageObjects.checkCategoryOfClient().equals(pageObjects.checkCategoryOfClient().allClients)) {
                results.checkResultsOfAvailableProducts(8);
            }
            if (pageObjects.checkCategoryOfClient().equals(pageObjects.checkCategoryOfClient().paymentClients)) {
                results.checkResultsOfAvailableProducts(5);
            }
            if (pageObjects.checkCategoryOfClient().equals(pageObjects.checkCategoryOfClient().premiumClients)) {
                results.checkResultsOfAvailableProducts(5);
            }
        });
    }

    @Test
    @DisplayName("Проверка отображения суммы и срока депозита, установленные по умолчанию в депозитном калькуторе")
    public void checkResultsDeposits() {
        step("Открываем главную страницу", () -> {
            pageObjects.openPage();
        });

        step("Закрываем cookies", () -> {
            pageObjects.closeCookies();
        });

        step("Кликаем по полю со сроком на депозитном калькулторе", () -> {
            pageObjects.clickOnFieldTermOnDepositCalculator();
        });

        step("Выбираем первый срок - в конце срока -  начисления процентов на депозитном калькулторе", () -> {
            $(withText("В конце срока")).click();
            ;
        });

        step("Отображение ставки и суммы дохода по вклдау по выбранному сроку", () -> {
            results.rateAnDSumOfChosenTerm("9,50%", "85 500 ₽", "385 500 ₽");
        });

        step("Кликаем по полю со сроком на депозитном калькулторе", () -> {
            pageObjects.clickOnFieldTermOnDepositCalculator();
        });

        step("Выбираем второй срок - в начале срока -  начисления процентов на депозитном калькуляторе", () -> {
            $(withText("В начале срока")).click();
            ;
        });

        step("Отображение ставки и суммы дохода по вклдау по выбранному сроку", () -> {
            results.rateAnDSumOfChosenTerm("9,50%", "85 500 ₽", "385 500 ₽");
        });

        step("Кликаем по полю со сроком на депозитном калькуляторе", () -> {
            pageObjects.clickOnFieldTermOnDepositCalculator();
        });

        step("Выбираем третий срок - в начале срока -  начисления процентов на депозитном калькуляторе", () -> {
            $(withText("Ежемесячно")).click();
            ;
        });

        step("Отображение ставки и суммы дохода по вклдау по выбранному сроку", () -> {
            results.rateAnDSumOfChosenTerm("9,90%", "89 100 ₽", "389 100 ₽");
        });
    }

    @CsvSource({
            "Кредиты, Кредит наличными",
            "Карты, Дебетовые карты",
            "Ипотека, Семейная ипотека",
            "Автокредиты, Автокредиты"

    })
    @ParameterizedTest(name = "Проверка кликабльности вкладок и перходов по вкладкам меню")
    void searchInformation(

            String divisions,
            String proposals
    ) {
        step("Открываем главную страницу", () -> {
            pageObjects.openPage();
        });

        step("Клик по выбранной вкладке" + " " + divisions, () -> {
            $$(".HeaderMenu_header-menu__6loXt").findBy(text(divisions)).click();
        });

        step("Проверка первого элемента" + " " + proposals + " " + "по выбранной вклдаке" + " " + divisions, () -> {
            $$(".HeaderMenuContentSection_header-menu-content-section__rNx3I").first().shouldHave(text(proposals));
        });
    }


    @Test
    @DisplayName("Проверка названий ссылок по нтернет-банку по категории клиента")
    public void checkNameOfReferencesInInternetbBank() {

        {
            step("Открываем главную страницу", () -> {
                pageObjects.openPage();
            });

            step("Клик на кнопку Интернет-банк", () -> {
                $$(".open-ui-button-text").findBy(text("Интернет-банк")).click();
            });

            step("Проверка перечисления категорий клиентов в модальном окне", () -> {

                results.choiceOfCategoriesOfClientsForInternetBank("Для частных лиц");
                results.choiceOfCategoriesOfClientsForInternetBank("Для бизнеса");
                results.choiceOfCategoriesOfClientsForInternetBank("Для корпораций");
            });


            step("Проверка перечисления ссылок для перехода в интернет-банк в модальном окне", () -> {

                results.referencesForInternetBank("Интернет-банк Открытие");
                results.referencesForInternetBank("Мобильное приложение");
                results.referencesForInternetBank("Демоверсия");
                results.referencesForInternetBank("Бизнес-портал");
                results.referencesForInternetBank("Открытие-Бизнес Онлайн");
            });
        }
    }


    static Stream<Arguments> choiceOfCity() {
        return Stream.of(
                Arguments.of(City.Астрахань, "Астрахань"),
                Arguments.of(City.Воронеж, "Воронеж"),
                Arguments.of(City.Волгоград, "Волгоград"),
                Arguments.of(City.Москва, "Москва")
        );
    }

    @MethodSource("choiceOfCity")
    @ParameterizedTest(name = "Проверка смены геолокации в хедере и футере")
    void chosenCityReflectedInTheRightCornerOfPage(
            City city,
            String location
    ) {
        step("Открываем главную страницу", () -> {
            pageObjects.openPage();
        });

        step("Кликнуть на город в верхнем правом углу", () -> {
            $(".HeaderTopBlock_current-city-name__MPH2d").click();
        });

        step("Ввести название города", () -> {
            $(".open-ui-search-input").setValue(String.valueOf(city)).pressEnter();
        });

        step("В выдаче поиска кликнуть на город", () -> {
            $(".City_city-title__F8zgx").click();
        });

        step("Выбранный город отображен в правом верхнем углу", () -> {
            $(".HeaderTopBlock_current-city-name__MPH2d").shouldHave(Condition.text(location));
        });

        step("Выбранный город отображен в футере страницы", () -> {
            $(".FooterCity_city-text__KFkUl").shouldHave(Condition.text(location));
        });
    }


    @Test
    @DisplayName("Проверка наличия перечисления драгоценных металлов в блоке Курс валют")
    public void preciousMetalNameCheck() {
        step("Открываем главную страницу", () -> {
            pageObjects.openPage();
        });

        step("Закрываем cookies", () -> {
            pageObjects.closeCookies();
        });

        step("Скролл до курса валют, выбрать вкладку Драгметаллы", () -> {
            $$(".open-ui-tabs-item").findBy(text("Драгметаллы")).click();
        });

        step("Проверка названий драгметаллов", () -> {
            results.checkPrecisiusMetalname("Золото");
            results.checkPrecisiusMetalname("Серебро");
            results.checkPrecisiusMetalname("Платина");
            results.checkPrecisiusMetalname("Палладий");
        });

        step("Проверка отсутствие название валют во вкладке Драгметаллы", () -> {
            results.checkAbsenceOfCurrencyName("USD");
            results.checkAbsenceOfCurrencyName("RUB");
        });
    }


}
