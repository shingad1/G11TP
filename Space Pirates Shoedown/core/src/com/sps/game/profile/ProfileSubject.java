package com.sps.game.profile;

import com.badlogic.gdx.utils.Array;

/**
 * This class creates the observers so that the save file is updated.
 * @author Miraj Shah
 * @version 1.0
 */
public class ProfileSubject {

    /**
     * Holds all the observers.
     */
    private Array<ProfileObserver> observers;

    public ProfileSubject(){
        observers = new Array<ProfileObserver>();
    }

    /**
     * Adds an observer to the ArrayList observers.
     * @param ProfileObserver profileObserver
     */
    public void addObserver(ProfileObserver profileObserver){
        observers.add(profileObserver);
    }

    /**
     * Removes a specific observer from the observers ArrayList.
     * @param ProfileObserver profileObserver
     */
    public void removeObserver(ProfileObserver profileObserver){
        observers.removeValue(profileObserver, true);
    }

    /**
     * Calls the onNotify method of all the observers.
     * @param ProfileManage profileManager
     * @param ProfileObserver.ProfileEvent event
     */
    protected void notify (ProfileManager profileManager, ProfileObserver.ProfileEvent event){
        for(ProfileObserver observer: observers){
            observer.onNotify(profileManager, event);
        }
    }
}
