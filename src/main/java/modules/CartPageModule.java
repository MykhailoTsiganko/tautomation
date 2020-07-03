package modules;

import com.google.inject.AbstractModule;
import page.AbstractBasePage;
import page.CartWebPage;

public class CartPageModule  extends AbstractModule {
    @Override
    protected void configure() {
        bind(CartWebPage.class);
    }
}
