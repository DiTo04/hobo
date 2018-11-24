package se.tennander.hobo.web;

import javax.inject.Inject;

import io.javalin.Javalin;

public class StaticFileServer {
  private final Javalin javalin;

  @Inject
  StaticFileServer(Javalin javalin) {
    this.javalin = javalin;
  }

  public void start() {
    javalin.enableStaticFiles("public/");
  }
}
