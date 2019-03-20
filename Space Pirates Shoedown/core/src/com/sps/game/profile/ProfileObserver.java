package com.sps.game.profile;

/**
 * This interface holds all the ProfileEvents and methods needed.
 * @author Miraj Shah
 * @version 1.0
 */
public interface ProfileObserver {
    /**
     * Holds the different states of the game.
     */
    public static enum ProfileEvent{
        PROFILE_LOADED,
        SAVING_PROFILE,
        CLEAR_CURRENT_PROFILE
    }

    /**
     * Observes the current events and takes out the appropriate action according to the event.
     * @param ProfileManager profileManager
     * @param ProfileEvent event
     */
    void onNotify(ProfileManager profileManager, ProfileEvent event);
}
