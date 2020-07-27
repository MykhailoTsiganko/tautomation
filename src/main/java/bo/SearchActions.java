package bo;

import com.google.inject.Inject;
import config.ConfigProperties;
import elements.PageElement;
import factory.DriverContainer;
import io.qameta.allure.Step;
import page.SearchPage;

import java.util.List;
import java.util.stream.Collectors;

public class SearchActions {
    @Inject
    private SearchPage searchPage;
    @Inject
    private ConfigProperties configProperties;

    @Step("open website : {url}")
    public void openWebSite() {
        DriverContainer.getDiver().get(configProperties.getBaseUrl());
    }

    @Step("find goods by word : {text}")
    public void findGoodsByText(String text) {
        searchPage.writeTextToColume(text);
        searchPage.findGoodsByText();
    }

    @Step("get all titles of fined goods")
    public List<String> getAllTitles() {
        return searchPage.getListOfFinedGoods().stream().map(PageElement::getText).collect(Collectors.toList());
    }
}
