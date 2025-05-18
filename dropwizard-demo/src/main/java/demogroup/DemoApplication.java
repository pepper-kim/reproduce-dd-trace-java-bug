package demogroup;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "Demo";
    }

    @Override
    public void initialize(final Bootstrap<DemoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DemoConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
