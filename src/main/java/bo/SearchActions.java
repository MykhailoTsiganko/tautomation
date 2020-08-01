package bo;

import com.google.inject.Inject;
import config.ConfigProperties;
import io.qameta.allure.Step;
import pages.SearchPage;

import java.util.List;

public class SearchActions {
    @Inject
    private SearchPage searchPage;
    @Inject
    private ConfigProperties configProperties;

    @Step("find goods by word : {text}")
    public void findGoodsByText(String text) {
        searchPage.writeTextToColume(text);
        searchPage.findGoodsByText();
    }

    @Step("get all titles of fined goods")
    public List<String> getAllTitles() {
        return searchPage.getListOfFinedGoods().getTexts();
    }
}
