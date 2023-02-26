package tests.pages;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Results {
    public void checkResultsOfAvailableProducts(int value) {
        $$(".swiper-pagination-bullet.swiper-pagination-bullet-active").shouldHave(CollectionCondition.size(value));
    }

    public void rateAnDSumOfChosenTerm(String percent, String income, String finalIncome) {
        $(".ResultItem_result-item-value-wrapper__NLIcH").shouldHave(text(percent));
        $$(".ResultItem_result-item-value-wrapper__NLIcH").get(2).shouldHave(text(finalIncome));
        $$(".ResultItem_result-item-value-wrapper__NLIcH").last().shouldHave(text(income));
    }

    public void choiceOfCategoriesOfClientsForInternetBank(String categories)
    {
        $(".ButtonMenu_button-menu__lsGsO").shouldHave(text(categories));

    }

    public void referencesForInternetBank(String references)
    {
        $(".ButtonMenu_button-menu__lsGsO").shouldHave(text(references));

    }

    public void checkPrecisiusMetalname(String metal)
    {
        $(".CurrencyExchange_currency-exchange-wrapper__K_gc4").shouldHave(text(metal));

    }

    public void checkAbsenceOfCurrencyName(String currency)
    {
        $(".CurrencyExchange_currency-exchange-wrapper__K_gc4").shouldNotHave(text(currency));

    }

}

