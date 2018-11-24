package se.tennander.hobo.web;

import dagger.Module;
import dagger.Provides;
import io.javalin.Javalin;

@Module
public class WebModule {
  @Provides
  Javalin provideJavalin() {
    return Javalin.create();
  }
}
