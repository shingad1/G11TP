package com.sps.game.profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Enumeration;
import java.util.Hashtable;

public class ProfileManager extends ProfileSubject {

    private Json json;

    private static ProfileManager profileManager;

    private Hashtable<String, FileHandle> _profiles = null;

    private ObjectMap<String, Object> profileProperties = new ObjectMap<String, Object>();

    private String profileName;

    private static final String SAVEGAME_SUFFIX = ".sav";

    public static final String DEFAULT_PROFILE = "default";

    private ProfileManager(){
        json = new Json();
        _profiles = new Hashtable<String, FileHandle>();
        _profiles.clear();
        profileName = DEFAULT_PROFILE;
        storeAllProfiles();
    }

    /**
     * Returns the single instance of ProfileManager
     * @return ProfileManager
     */
    public static final ProfileManager getInstance(){
        if(profileManager == null){
            profileManager = new ProfileManager();
        }
        return profileManager;
    }

    /**
     * Creates an array of profile Strings to display in the UI
     * @return profiles
     */
    public Array<String> getProfilelList(){
        Array<String> profiles = new Array<String>();
        for(Enumeration<String> e = _profiles.keys(); e.hasMoreElements();){
            profiles.add(e.nextElement());
        }
    return profiles;
    }

    /**
     * Returns the file handle if it exists for the specified profile name
     * @param profile
     * @return
     */
    public FileHandle getProfileFile(String profile){
        if(!doesProfileExist(profile)){
            return null;
        }
        return _profiles.get(profile);
    }

    /**
     * Checks to see if the profile name exists in the hashtable
     * @param profileName
     * @return True if the file exists and false if it does not
     */
    public boolean doesProfileExist(String profileName){
        return _profiles.containsKey(profileName);
    }

    /**
     * Will check to see whether local storage is available and if it is, store all available profile files into out member Hashtable for access later
     */
    public void storeAllProfiles(){
        if(Gdx.files.isLocalStorageAvailable()){
            FileHandle[] files = Gdx.files.local(".").list(SAVEGAME_SUFFIX);
            for(FileHandle file: files) {
                _profiles.put(file.nameWithoutExtension(), file);
            }
        } else{
            return;
        }
    }

    /**
     * Takes a profile name string, a serialized JSON string of data and a boolean value whether or not we want to
     * overwite the file if it exists. Create the file based on the profile name and see if it exits. If the file does
     * exist, it will ve overwritten.
     * @param profileName
     * @param fileData
     * @param overwrite
     */
    public void writeProfileToStorage(String profileName, String fileData, boolean overwrite){
        String fullFilename = profileName + SAVEGAME_SUFFIX;
        boolean localFileExists = Gdx.files.internal(fullFilename).exists();
        if(localFileExists && !overwrite){
            return;
        }

        FileHandle file = null;

        if(Gdx.files.isLocalStorageAvailable()){
            file = Gdx.files.local(fullFilename);
            file.writeString(fileData, !overwrite);
        }

        _profiles.put(profileName, file);
    }

    /**
     * Will add a key/value pair property to the ObjectMap member object
     * @param key
     * @param object
     */
    public void setProperty(String key, Object object){
        profileProperties.put(key, object);
    }

    /**
     * Takes a key string and a calls type as input parameters. If it cannot find the key it will return null
     * otherwise it will return the value found cast to the class type
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T extends Object> T getProperty(String key, Class<T> type){
        T property = null;
        if(!profileProperties.containsKey(key)){
            return property;
        }
        property = (T) profileProperties.get(key);
        return property;
    }

    /**
     * Notify all observers with a SAVING_PROFILE profile event. Observers that are subscribed to this notification will store
     * their current properties in preparation for serialization. Convert the ObjectMap that contains all of our properties into
     * a JSON string and then write it out to the profile file
     */
    public void saveProfile(){
        notify(this, ProfileObserver.ProfileEvent.SAVING_PROFILE);
        String text = json.prettyPrint(json.toJson(profileProperties));
        writeProfileToStorage(profileName, text, true);
    }

    /**
     * Verifies that the profile file exists and if it does, it deserialize's the file into ObjectMap for all the properties
     * A notification with a PROFILE_LOADED profile event is sent to all registered observers so that the classes that own
     * those properties can correctly initialize's the data.
     */
    public void loadProfile(){
        String fullProfileFileName = profileName + SAVEGAME_SUFFIX;
        boolean doesProfileFileExist = Gdx.files.internal(fullProfileFileName).exists();
        if(!doesProfileFileExist){
            System.out.println("File doesn't exist!");
            return;
        }
        profileProperties = json.fromJson(ObjectMap.class, _profiles.get(profileName));
        notify(this, ProfileObserver.ProfileEvent.PROFILE_LOADED);
    }

    /**
     * Sets the profile string passed in as the currently loaded profile, if it exists,
     * otherwise it loads the default profile
     * @param pName
     */
    public void setCurrentProfile(String pName){
        if( doesProfileExist(pName)){
            profileName = pName;
        } else{
            profileName = DEFAULT_PROFILE;
        }
    }
}