package bo;

import com.google.inject.Inject;
import elements.PageElement;
import factory.DriverFactory;
import io.qameta.allure.Step;
import page.SearchPage;

import java.util.List;
import java.util.stream.Collectors;

public class SearchActions {
    @Inject
    private SearchPage searchPage;

    @Step("open website : {url}")
    public void openWebSite(String url) {
        DriverFactory.getDiver().get(url);
    }

    @Step("find goods by word : {text}")
    public void findGoodsByText(String text){
        searchPage.writeTextToColume(text);
        searchPage.findGoodsByText();
    }

    @Step("get all titles of fined goods")
    public List<String> getAllTitles(){
        return searchPage.getListOfFinedGoods().stream().map(PageElement::getText).collect(Collectors.toList());
    }
}
