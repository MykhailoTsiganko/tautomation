package modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import config.PropertyFileProvider;

public class PropertyModule extends AbstractModule {

    @Override
    protected void configure() {
        Names.bindProperties(binder(), new PropertyFileProvider().get());
    }
}
