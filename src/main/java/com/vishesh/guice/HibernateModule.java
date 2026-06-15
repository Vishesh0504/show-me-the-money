package com.vishesh.guice;

import com.google.inject.AbstractModule;
import com.vishesh.ShowMeTheMoneyConfiguration;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import org.hibernate.SessionFactory;

public class HibernateModule extends AbstractModule {

    private final ScanningHibernateBundle<ShowMeTheMoneyConfiguration> hibernateBundle;

    public HibernateModule(ScanningHibernateBundle<ShowMeTheMoneyConfiguration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {
        bind(SessionFactory.class).toInstance(hibernateBundle.getSessionFactory());
    }
}
