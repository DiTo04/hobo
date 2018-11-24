package se.tennander.hobo.logic;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import se.tennander.hobo.Listener;

@Module
public abstract class LogicModule {

  @Binds
  @IntoSet
  abstract Listener provideHealthCheckLogger(HealthCheckLogger impl);
}
