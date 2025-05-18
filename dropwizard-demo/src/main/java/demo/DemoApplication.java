package demo;

import demo.resources.PinnedResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "demo";
    }

    @Override
    public void initialize(final Bootstrap<DemoConfiguration> bootstrap) {
    }

    @Override
    public void run(final DemoConfiguration configuration, final Environment environment) {
        PinnedResource resource = new PinnedResource();
        environment.jersey().register(resource);
    }
}
