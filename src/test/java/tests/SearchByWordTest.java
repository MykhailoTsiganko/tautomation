package tests;

import bo.SearchActions;
import com.google.inject.Inject;
import config.PropertyFile;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import validators.SearchValidation;

import java.util.List;

public class SearchByWordTest extends BaseTest {
    @Inject
    private SearchActions searchActions;
    @Inject
    private SearchValidation validation;

    @DataProvider(name = "SearchDataProvider",parallel = true)
    private Object[] getData(){
        Object [] date = {"motorola","iphone","nike"};
        return date;
    }

    @Test(description = "search goods by word",dataProvider ="SearchDataProvider" ,
            threadPoolSize = 3,invocationCount = 1)
    public void searchGoodsByWord(String word){
        searchActions.openWebSite();
        searchActions.findGoodsByText(word);
        List<String> listTitles = searchActions.getAllTitles();
        validation.verifyAllTitlesContainsWords(listTitles,word);

    }
}
