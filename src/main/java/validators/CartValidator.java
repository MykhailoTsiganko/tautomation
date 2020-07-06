package validators;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;

public class CartValidator {

    @Step("verify is selected good with title : {selectedTitle} , in cart")
    public void verifySelectedGoodIsInCart(List<String> titlesInBox, String selectedTitle) {
        Assert.assertTrue(titlesInBox.stream().anyMatch(titles -> titles.equals(selectedTitle))
                , String.format("The good with title [%s] not exist in box", selectedTitle));
    }
}
