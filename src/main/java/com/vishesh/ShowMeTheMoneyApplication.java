package com.vishesh;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class ShowMeTheMoneyApplication extends Application<ShowMeTheMoneyConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ShowMeTheMoneyApplication().run(args);
    }

    @Override
    public String getName() {
        return "ShowMeTheMoney";
    }

    @Override
    public void initialize(final Bootstrap<ShowMeTheMoneyConfiguration> bootstrap) {

        bootstrap.addBundle(
                GuiceBundle.builder()
                        .enableAutoConfig("com.vishesh")
                        .build()
        );

        bootstrap.addBundle(new SwaggerBundle<ShowMeTheMoneyConfiguration>(){
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ShowMeTheMoneyConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final ShowMeTheMoneyConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
