package com.twotoasters.sideoftoast;

import com.twotoasters.sideoftoast.mvp.SideNavModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class SideNavModelTest {

    public static final int TEST_MENU_ID = 0;
    public static final int TEST_RES_ID = 1;
    public static final int TEST_IMAGE_ID = 2;
    public static final int TEST_LAYOUT_ID = 3;
    public static final String TEST_STRING = "Test String";

    @Mock SideOfToast sideOfToast;
    private SideNavModel model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        model = new SideNavModel(sideOfToast, 1);
    }
    @Test
    public void itShouldUpdateStringResource() {
       model.updateStringResource(TEST_MENU_ID,
               TEST_LAYOUT_ID,
               TEST_STRING);
       verify(sideOfToast).updateStringResource(TEST_MENU_ID,
               TEST_LAYOUT_ID,
               TEST_STRING);
    }

    @Test
    public void itShouldUpdateImageResource() {
        model.updateImageResource(TEST_MENU_ID,
               TEST_LAYOUT_ID,
               TEST_IMAGE_ID);
       verify(sideOfToast).updateImageResource(TEST_MENU_ID,
               TEST_LAYOUT_ID,
               TEST_IMAGE_ID);
    }
}
