package page;

import elements.PageElement;

import java.util.List;


public interface StrategyBox {
    void openRandomGood();

    String getTitleOfGood();

    void moveGoodToBox();

    List<PageElement> getAllTitleInBox();
}
