package se.tennander.hobo.web;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.javalin.Javalin;

@Module
public class WebModule {

  @Provides
  @Singleton
  Javalin provideJavalin() {
    return Javalin.create();
  }
}
