package tests;

import bo.BoxBusinessObject;
import config.PropertyFile;
import factory.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoodsInBoxTest {
    private BoxBusinessObject boxBusinessObject;

    @BeforeMethod
    public void setUp(){
        boxBusinessObject = new BoxBusinessObject();
    }

    @Test
    public void moveGoodsToBox(){
        boxBusinessObject.openWebSite(PropertyFile.getProperty("website"));
        boxBusinessObject.choseRandomGoods();
        String title = boxBusinessObject.getGoodTitle();
        boxBusinessObject.moveGoodToBox();
        List<String> titleList = boxBusinessObject.getAllTitleInBox();
        boxBusinessObject.verifySelectedGoodIsInBox(titleList,title);
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
