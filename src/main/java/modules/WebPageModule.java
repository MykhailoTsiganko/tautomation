package modules;

import bo.CartActions;
import bo.CommonActions;
import bo.SearchActions;
import com.google.inject.AbstractModule;
import pages.CartWebPage;
import pages.SearchPage;

public class WebPageModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CartActions.class);
        bind(CartWebPage.class);
        bind(SearchActions.class);
        bind(SearchPage.class);
        bind(CommonActions.class);
    }
}
