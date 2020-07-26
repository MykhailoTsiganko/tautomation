package inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.PropertyModule;
import modules.ValidatorModule;
import modules.WebPageModule;

import java.util.Objects;

public class GuiceInjector {
    private static Injector injector;

    public static <T> T getBean(Class<T> clazz) {
        return getInjector().getInstance(clazz);
    }

    private static Injector getInjector() {
        if (Objects.isNull(injector)) {
            injector = Guice.createInjector(new WebPageModule(), new PropertyModule(),
                    new ValidatorModule());
        }
        return injector;
    }
}
