package tests.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class PageObjects {

    public SelenideElement
            cookies = $(byText("Закрыть"));
    public SelenideElement paymentClients = $("#paymentClients");
    public SelenideElement allClients = $("#allClients");
    public SelenideElement premiumClients = $("#premiumClients");



    public PageObjects openPage() {
        open(baseUrl);
        return this;
    }

    public PageObjects closeCookies() {

        cookies.click();
        return this;
    }

    public PageObjects checkNamesOfHeaders(String value) {
        $("#headerTopBlockTabsId").shouldHave(text(value));
        $("#headerTopBlockTabsId").shouldHave(text(value));
        $("#headerTopBlockTabsId").shouldHave(text(value));
        $("#headerTopBlockTabsId").shouldHave(text(value));

        return this;
    }

    public PageObjects checkCategoryOfClient() {
        paymentClients.click();
        allClients.click();
        premiumClients.click();

        return this;
    }

    public PageObjects clickOnFieldTermOnDepositCalculator() {
        $(".open-ui-select").click();
               return this;
    }



    }

