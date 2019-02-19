package com.sps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public final class Utility {

    /**
     * Manages the loading and storing of assets
     */
    public static final AssetManager ASSET_MANAGER = new AssetManager();

    private static final String TAG = Utility.class.getSimpleName();
    private final static String UISKIN_PATH = "core/assets/Skins/uiskin.json";

    private static InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();

    /**
     * Checks to see if the assets it loaded, if it is it will unload it otherwise it will throw an error
     * @param assetFileNamePath
     */
    public static void unloadAsset(String assetFileNamePath){
        if(ASSET_MANAGER.isLoaded(assetFileNamePath)){
            ASSET_MANAGER.unload(assetFileNamePath);
        } else{
            Gdx.app.debug(TAG, "Assest is not loaded; There is nothing to unload " + assetFileNamePath);
        }
    }

    /**
     * Returns the progress of the AssetManager as a percentage
     * @return
     */
    public static float loadCompleted(){
        return ASSET_MANAGER.getProgress();
    }

    /**
     * Returns the number of assets left to load
     * @return
     */
    public static int numberAssetsQueued(){
        return ASSET_MANAGER.getQueuedAssets();
    }

    /**
     * Used to help load assets asynchronously in order to process the preload queue
     * @return
     */
    public static boolean updateAssetLoading(){
        return ASSET_MANAGER.update();
    }

    /**
     * Returns true is the asset is loaded otherwise false
     * @param fileName
     * @return
     */
    public static boolean isAssetLoaded(String fileName){
        return ASSET_MANAGER.isLoaded(fileName);
    }

    /**
     * Loads the TMX file using the file path specified
     * @param mapFileNamePath
     */
    public static void loadMapAsset(String mapFileNamePath){
        if(mapFileNamePath == null || mapFileNamePath.isEmpty()){
            return;
        }
        if(filePathResolver.resolve(mapFileNamePath).exists()){
            ASSET_MANAGER.setLoader(TiledMap.class, new TideMapLoader(filePathResolver));
            ASSET_MANAGER.load(mapFileNamePath, TiledMap.class);
            ASSET_MANAGER.finishLoadingAsset(mapFileNamePath);
            Gdx.app.debug(TAG, "Map loaded: " + mapFileNamePath);
        } else {
            Gdx.app.debug(TAG, "Map does not exist: " + mapFileNamePath);
        }
    }


    /**
     * Returns the map that has been loaded
     * @param mapFileNamePath
     * @return
     */
    public static TiledMap getMapAsset(String mapFileNamePath){
        TiledMap map = null;

        if(ASSET_MANAGER.isLoaded(mapFileNamePath)){
            map = ASSET_MANAGER.get(mapFileNamePath, TiledMap.class);
        } else{
            Gdx.app.debug(TAG, "Map is not loaded: " + mapFileNamePath);
        }
        return map;
    }

    /**
     * Loads the images file as a Texture asset
     * @param textureFileNamePath
     */
    public static void loadTextureAsset(String textureFileNamePath){
        if(textureFileNamePath == null || textureFileNamePath.isEmpty()){
            return;
        }
        if(filePathResolver.resolve(textureFileNamePath).exists()){
            ASSET_MANAGER.setLoader(Texture.class, new TextureLoader(filePathResolver));
            ASSET_MANAGER.finishLoadingAsset(textureFileNamePath);
        } else{
            Gdx.app.debug(TAG, "Texture does not exist " + textureFileNamePath);
        }
    }

    /**
     * Returns the skin asset loaded
     * @param skinFileNamePath
     * @return Returns a skin
     */

    public static Skin loadSkinAsset(String skinFileNamePath) {
        Skin skin = null;

        if (ASSET_MANAGER.isLoaded(skinFileNamePath)) {
            skin = ASSET_MANAGER.get(skinFileNamePath, Skin.class);
        } else {
            Gdx.app.debug(TAG, "Skin is not loaded: " + skinFileNamePath);
        }
        return skin;
    }


    /**
     * Returns the texture asset loaded
     * @param textureFileNamePath
     * @return
     */
    public static Texture getTextureAssest(String textureFileNamePath){
        Texture texture = null;
        if (ASSET_MANAGER.isLoaded(textureFileNamePath)){
            texture = ASSET_MANAGER.get(textureFileNamePath, Texture.class);
        } else{
            Gdx.app.debug(TAG, "Texture is not loaded: " + textureFileNamePath);
        }
        return texture;
    }
}
