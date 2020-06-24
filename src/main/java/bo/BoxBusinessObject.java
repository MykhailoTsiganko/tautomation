package bo;

import elements.PageElement;
import factory.DriverFactory;
import org.testng.Assert;
import page.Context;
import page.StrategyBox;

import java.util.List;
import java.util.stream.Collectors;

public class BoxBusinessObject {
    private final StrategyBox strategy;

    public BoxBusinessObject() {
        String devise = System.getProperty("devise");
        this.strategy = new Context(devise).getStrategy();
    }
    public void openWebSite(String url){
        DriverFactory.getDiver().get(url);
    }

    public void choseRandomGoods() {
        strategy.openRandomGood();
    }

    public String getGoodTitle() {
        return strategy.getTitleOfGood();
    }

    public void moveGoodToBox() {
        strategy.moveGoodToBox();
    }

    public List<String> getAllTitleInBox() {
        return strategy.getAllTitleInBox()
                .stream()
                .map(PageElement::getText)
                .collect(Collectors.toList());
    }

    public void verifySelectedGoodIsInBox(List<String> titlesInBox, String selectedTitle) {
        Assert.assertTrue (titlesInBox.stream().anyMatch(titles -> titles.equals(selectedTitle))
                , String.format("The good with title [%s] not exist in box", selectedTitle));
    }

}
