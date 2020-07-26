package modules;

import com.google.inject.AbstractModule;
import validators.CartValidator;
import validators.SearchValidation;

public class ValidatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CartValidator.class);
        bind(SearchValidation.class);
    }
}
