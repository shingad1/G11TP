package com.sps.game.Map;

import com.sps.game.maps.HomeWorldMap;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import com.sps.test.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class MapTest {
    private Map homeWorldMap;

    @Before
    public void setUp(){
        homeWorldMap = new HomeWorldMap();
    }
    @Test
    public void getCurrentMapType(){
        assertEquals(MapFactory.MapType.HomeWorldMap1, homeWorldMap.getCurrentMapType());
    }
}
