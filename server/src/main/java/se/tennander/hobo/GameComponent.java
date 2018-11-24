package se.tennander.hobo;

import javax.inject.Singleton;

import dagger.Component;
import se.tennander.hobo.events.EventStreamModule;
import se.tennander.hobo.game.LogicModule;
import se.tennander.hobo.web.WebModule;

@Component(modules = {EventStreamModule.class, WebModule.class, LogicModule.class})
@Singleton
public interface GameComponent {
  GameServer getServer();
}
