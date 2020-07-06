package modules;

import bo.CartActions;
import com.google.inject.AbstractModule;
import page.CartWebPage;

public class CartPageModule  extends AbstractModule {
    @Override
    protected void configure() {
        bind(CartActions.class);
        bind(CartWebPage.class);
    }
}
