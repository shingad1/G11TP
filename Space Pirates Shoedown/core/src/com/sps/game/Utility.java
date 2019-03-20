package com.sps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;

/**
 * The class loads the assets used in the game.
 * @author Miraj Shah, Devin Shingadia
 * @version 1.0
 */
public final class Utility {

    /**
     * Manages the loading and storing of assets.
     */
    public static final AssetManager ASSET_MANAGER = new AssetManager();
    /**
     * Holds the name of the class.
     */
    private static final String TAG = Utility.class.getSimpleName();
    /**
     * Holds the path of the items texture.
     */
    private final static String ITEMS_TEXTURE_ATLAS_PATH = "Inventory/items.atlas";
    /**
     * Holds the path of the status ui skin.
     */
    private final static String STATUSUI_SKIN_PATH = "Inventory/statusui.json";
    /**
     * Holds the path of the status ui texture.
     */
    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "Inventory/statusui.atlas";
    /**
     * Holds the path of the ui skin
     */
    private final static String UISKIN_TEXTURE_PATH = "Inventory/uiskin.json";
    /**
     * Creates an InternalFileHandleResolver
     */
    private static InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();
    /**
     * Creates a new texture atlas for the Status UI
     */
    public static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    /**
     * Creates a new texture atlas for the items
     */
    public static TextureAtlas ITEMS_TEXTUREATLAS = new TextureAtlas(ITEMS_TEXTURE_ATLAS_PATH);
    /**
     * Creates a skin.
     */
    public static Skin STATUSUI_SKIN = new Skin (Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);

    /**
     * Checks to see if the assets it loaded, if it is it will unload it otherwise it will throw an error
     * @param assetFileNamePath
     */
    public static void unloadAsset(String assetFileNamePath){
        if(ASSET_MANAGER.isLoaded(assetFileNamePath)){
            ASSET_MANAGER.unload(assetFileNamePath);
        } else{
            Gdx.app.debug(TAG, "Assets is not loaded; There is nothing to unload " + assetFileNamePath);
        }
    }

    /**
     * Returns the progress of the AssetManager as a percentage
     * @return float
     */
    public static float loadCompleted(){
        return ASSET_MANAGER.getProgress();
    }

    /**
     * Returns the number of assets left to load
     * @return int
     */
    public static int numberAssetsQueued(){
        return ASSET_MANAGER.getQueuedAssets();
    }

    /**
     * Used to help load assets asynchronously in order to process the preload queue
     * @return int
     */
    public static boolean updateAssetLoading(){
        return ASSET_MANAGER.update();
    }

    /**
     * Returns true is the asset is loaded otherwise false
     * @param String fileName
     * @return boolean
     */
    public static boolean isAssetLoaded(String fileName){
        return ASSET_MANAGER.isLoaded(fileName);
    }

    /**
     * Loads the TMX file using the file path specified
     * @param String mapFileNamePath
     */
    public static void loadMapAsset(String mapFileNamePath){
        if(mapFileNamePath == null || mapFileNamePath.isEmpty()){
            return;
        }
        if(filePathResolver.resolve(mapFileNamePath).exists()){
            ASSET_MANAGER.setLoader(TiledMap.class, new TmxMapLoader(filePathResolver));//was tideMapLoader
            ASSET_MANAGER.load(mapFileNamePath, TiledMap.class);
            ASSET_MANAGER.finishLoadingAsset(mapFileNamePath);
            Gdx.app.debug(TAG, "Map loaded: " + mapFileNamePath);
        } else {
            Gdx.app.debug(TAG, "Map does not exist: " + mapFileNamePath);
        }
    }


    /**
     * Returns the map that has been loaded
     * @param String mapFileNamePath
     * @return TiledMap
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
     * @param String textureFileNamePath
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
     * @param String skinFileNamePath
     * @return Skin
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
     * @param String textureFileNamePath
     * @return Texture
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

    /**
     * Returns the JSON asset.
     * @param String jsonFileNamePath
     * @return JSON
     */
    public static Json getJsonAsset(String jsonFileNamePath) {
        Json json = null;
        if(ASSET_MANAGER.isLoaded(jsonFileNamePath)) {
            json = ASSET_MANAGER.get(jsonFileNamePath, json.getClass());
        } else {
            Gdx.app.debug(TAG, "Json File is not loaded: " + jsonFileNamePath);
        }
        return json;
    }
}
