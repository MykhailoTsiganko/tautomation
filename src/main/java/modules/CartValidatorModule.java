package modules;

import com.google.inject.AbstractModule;
import validators.CartValidator;

public class CartValidatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CartValidator.class);
    }
}
