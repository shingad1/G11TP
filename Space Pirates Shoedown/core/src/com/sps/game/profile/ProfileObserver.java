package com.sps.game.profile;

public interface ProfileObserver {

    public static enum ProfileEvent{
        PROFILE_LOADED,
        SAVING_PROFILE,
        CLEAR_CURRENT_PROFILE
    }

    void onNotify(ProfileManager profileManager, ProfileEvent event);
}
