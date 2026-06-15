package com.vishesh;

import com.vishesh.guice.HibernateModule;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class ShowMeTheMoneyApplication extends Application<ShowMeTheMoneyConfiguration> {
    private final ScanningHibernateBundle<ShowMeTheMoneyConfiguration> hibernateBundle =
            new ScanningHibernateBundle<ShowMeTheMoneyConfiguration>("com.vishesh.core") {
                @Override
                public io.dropwizard.db.DataSourceFactory getDataSourceFactory(ShowMeTheMoneyConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };

    public static void main(final String[] args) throws Exception {
        new ShowMeTheMoneyApplication().run(args);
    }

    @Override
    public String getName() {
        return "ShowMeTheMoney";
    }

    @Override
    public void initialize(final Bootstrap<ShowMeTheMoneyConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new MigrationsBundle<ShowMeTheMoneyConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ShowMeTheMoneyConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
        bootstrap.addBundle(
                GuiceBundle.builder()
                        .enableAutoConfig("com.vishesh")
                        .modules(new HibernateModule(hibernateBundle))
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

    }

}
