package validators;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;

public class SearchValidation {

    @Step("verify all titles contain the word : {word}")
    public void verifyAllTitlesContainsWords(List<String> list, String word) {
        list.forEach(titles ->
                Assert.assertTrue(titles.toLowerCase().contains(word.toLowerCase())
                        , String.format("Title [%s] does not contain the word :" + word, titles))
        );
    }
}
