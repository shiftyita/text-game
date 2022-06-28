package it.shifty.textgame.events;

import it.shifty.textgame.core.dto.GameMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    public enum EventMessageType {
        GAME_MESSAGE, STATS_MESSAGE
    }

    private static EventManager eventManagerInstance;

    public static EventManager getInstance() {
        if (eventManagerInstance == null) {
            eventManagerInstance = new EventManager();
        }
        return eventManagerInstance;
    }
    Map<EventMessageType, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {
        this.listeners.put(EventMessageType.GAME_MESSAGE, new ArrayList<>());
    }

    public EventManager(EventMessageType... events) {
        for (EventMessageType event : events) {
            this.listeners.put(event, new ArrayList<>());
        }
    }

    public void subscribe(EventMessageType eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void notify(EventMessageType eventType, GameMessage message) {
        List<EventListener> users = listeners.get(eventType);
        if (users != null) {
            for (EventListener listener : users) {
                listener.publishEvent(eventType, message);
            }
        }
    }

}
