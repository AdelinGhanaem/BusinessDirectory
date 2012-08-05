package com.businessdirecotory.server;

import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.dispatch.ActionHandlerMetadata;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.gcommons.collect.ImmutableMap;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class LazyActionHandlerRepository implements ActionHandlerRepository {


  private Injector injector;

  private ImmutableMap<Class<? extends Action>, Class<? extends ActionHandler<? extends Action, ? extends Response>>> handlers;

  @Inject
  public LazyActionHandlerRepository(Set<ActionHandlerMetadata> actionHandlerMetadata, Injector injector) {

    this.injector = injector;

    Map<Class<? extends Action>, Class<? extends ActionHandler<? extends Action, ? extends Response>>> map = new HashMap<Class<? extends Action>, Class<? extends ActionHandler<? extends Action, ? extends Response>>>();

    for (ActionHandlerMetadata meta : actionHandlerMetadata) {
      map.put(meta.getAction(), meta.getHandler());
    }
    handlers = ImmutableMap.copyOf(map);
  }

  /**
   * Gets {@link com.evo.gad.dispatch.ActionHandler} from the companiesRepository by providing the type of the
   * action class.
   * <p/>
   * <p/>
   *
   * @param actionClass the action class
   * @return the ActionHandler that is responsible for handling of the provided action
   */
  public ActionHandler getActionHandler(Class<? extends Action> actionClass) {

    if (!handlers.containsKey(actionClass)) {
      return null;
    }
    //the Action handler class ...
    Class<? extends ActionHandler<? extends Action, ? extends Response>> handler = handlers.get(actionClass);
    //gets a key for an inject type ...
    ActionHandler<? extends Action, ? extends Response> instance = injector.getInstance(Key.get(handler));

    return instance;
  }
}
