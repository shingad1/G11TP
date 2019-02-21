package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sps.game.Utility;

public class InventoryTest
{
    private InventoryUI inventoryUI;
    private StoreInventoryUI storeInventoryUI;
    private Stage stage;

    private static final String INVENTORY_FULL = "Your inventory is full :(";

    public InventoryTest() {

        stage = new Stage(new ScreenViewport());

        Json json = new Json();
        Dialog messageBoxUI = new Dialog("Message", Utility.STATUSUI_SKIN, "solidbackground") {
            {
                button("OK");
                text(INVENTORY_FULL);
            }

            @Override
            protected void result(final Object object) {
                cancel();
                setVisible(false);
            }
        };

        statusUI = new StatusUI();
        statusUI.setVisible(true);
        statusUI.setPosition(0, 0);
        statusUI.setKeepWithinStage(false);
        statusUI.setMovable(false);

        inventoryUI = new InventoryUI();
        inventoryUI.setKeepWithinStage(false);
        inventoryUI.setMovable(false);
        inventoryUI.setVisible(false);
        inventoryUI.setPosition(statusUI.getWidth(), 0);

        storeInventoryUI = new StoreInventoryUI();
        storeInventoryUI.setMovable(false);
        storeInventoryUI.setVisible(false);
        storeInventoryUI.setPosition(0, 0);

        stage.addActor(storeInventoryUI);
        stage.addActor(inventoryUI);

        storeInventoryUI.validate();
        inventoryUI.validate();

        Array<Actor> actors = inventoryUI.getInventoryActors();
        for(Actor actor : actors){
            stage.addActor(actor);
        }

        Array<Actor> storeActors = storeInventoryUI.getInventoryActors();
        for(Actor actor : storeActors ){
            stage.addActor(actor);
        }

        storeInventoryUI.addObserver((StoreInventoryObserver) this);
        inventoryUI.addObserver((InventoryObserver) this);

        ImageButton inventoryButton = statusUI.getInventoryButton();
        inventoryButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                inventoryUI.setVisible(inventoryUI.isVisible() ? false : true);
            }
        });

        storeInventoryUI.getCloseButton().addListener(new ClickListener() {
                                                          @Override
                                                          public void clicked(InputEvent event, float x, float y) {
                                                              storeInventoryUI.savePlayerInventory();
                                                              storeInventoryUI.cleanupStoreInventory();
                                                              storeInventoryUI.setVisible(false);
                                                              //mapMgr.clearCurrentSelectedMapEntity();
                                                          }
                                                      }
        );

        @Override
        public void onNotify(){
            boolean firstTime = profileManager.getIsNewProfile();

            if (firstTime) {
                InventoryUI.clearInventoryItems(inventoryUI.getInventorySlotTable());
                InventoryUI.clearInventoryItems(inventoryUI.getEquipSlotTable());
                inventoryUI.resetEquipSlots();

                questUI.setQuests(new Array<QuestGraph>());

                //add default items if first time
                Array<InventoryItem.ItemTypeID> items = player.getEntityConfig().getInventory();
                Array<InventoryItemLocation> itemLocations = new Array<InventoryItemLocation>();
                for (int i = 0; i < items.size; i++) {
                    itemLocations.add(new InventoryItemLocation(i, items.get(i).toString(), 1, InventoryUI.PLAYER_INVENTORY));
                }
                InventoryUI.populateInventory(inventoryUI.getInventorySlotTable(), itemLocations, inventoryUI.getDragAndDrop(), InventoryUI.PLAYER_INVENTORY, false);
                profileManager.setProperty("playerInventory", InventoryUI.getInventory(inventoryUI.getInventorySlotTable()));

                //start the player with some money
                statusUI.setGoldValue(20);
                statusUI.setStatusForLevel(1);

                clock.setTotalTime(60 * 60 * 12); //start at noon
                profileManager.setProperty("currentTime", _clock.getTotalTime());
            } else {
                int goldVal = profileManager.getProperty("currentPlayerGP", Integer.class);

                Array<InventoryItemLocation> inventory = profileManager.getProperty("playerInventory", Array.class);
                InventoryUI.populateInventory(_inventoryUI.getInventorySlotTable(), inventory, inventoryUI.getDragAndDrop(), InventoryUI.PLAYER_INVENTORY, false);

                Array<InventoryItemLocation> equipInventory = profileManager.getProperty("playerEquipInventory", Array.class);
                if (equipInventory != null && equipInventory.size > 0) {
                    inventoryUI.resetEquipSlots();
                    InventoryUI.populateInventory(inventoryUI.getEquipSlotTable(), equipInventory, inventoryUI.getDragAndDrop(), InventoryUI.PLAYER_INVENTORY, false);
                }

                Array<QuestGraph> quests = profileManager.getProperty("playerQuests", Array.class);
                _questUI.setQuests(quests);

                int xpMaxVal = profileManager.getProperty("currentPlayerXPMax", Integer.class);
                int xpVal = profileManager.getProperty("currentPlayerXP", Integer.class);

                int hpMaxVal = profileManager.getProperty("currentPlayerHPMax", Integer.class);
                int hpVal = profileManager.getProperty("currentPlayerHP", Integer.class);

                int mpMaxVal = profileManager.getProperty("currentPlayerMPMax", Integer.class);
                int mpVal = profileManager.getProperty("currentPlayerMP", Integer.class);

                int levelVal = profileManager.getProperty("currentPlayerLevel", Integer.class);

                //set the current max values first
                statusUI.setXPValueMax(xpMaxVal);
                statusUI.setHPValueMax(hpMaxVal);
                statusUI.setMPValueMax(mpMaxVal);

                statusUI.setXPValue(xpVal);
                statusUI.setHPValue(hpVal);
                statusUI.setMPValue(mpVal);

                //then add in current values
                statusUI.setGoldValue(goldVal);
                statusUI.setLevelValue(levelVal);

                float totalTime = profileManager.getProperty("currentTime", Float.class);
                clock.setTotalTime(totalTime);
            }
        }

    }
}
