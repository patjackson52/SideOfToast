package com.twotoasters.sideoftoast;

import android.content.res.Configuration;

import com.twotoasters.sideoftoast.mvp.SideNavModel;
import com.twotoasters.sideoftoast.mvp.SideNavPresenter;
import com.twotoasters.sideoftoast.mvp.SideNavView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class SideNavPresenterTest {

    public static final int TEST_MENU_ID = 0;
    public static final int TEST_RES_ID = 1;
    public static final int TEST_IMAGE_ID = 2;
    public static final String TEST_STRING = "Test String";
    public static final int TEST_MENU_POSITION = 3;

    @Mock SideNavModel model;
    @Mock SideNavView view;
    @Mock SideOfToast sideOfToast;
    private SideNavPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(sideOfToast.isSlidingContent()).thenReturn(true);
        when(model.getSideOfToast()).thenReturn(sideOfToast);
        presenter = new SideNavPresenter(model, view);
    }

    @Test
    public void itShouldSetSlidingMode() {
        verify(view).setSlidingContent(true);
    }

    @Test
    public void itShouldSetupSideNavMenu() {
        verify(view).setMenuItems(any(SideOfToast.class));
    }


    @Test
    public void itShouldRefreshAfterRotation() {
        when(model.getCurrentSelectedPosition()).thenReturn(TEST_MENU_POSITION);
        presenter.refresh();
        verify(view).refresh();
        verify(view, times(2)).setMenuItems(sideOfToast);
        verify(view).setCurrentSelectedPosition(TEST_MENU_POSITION);
    }

    @Test
    public void itShouldUpdateModelAfterMenuTap() {
        presenter.onToastItemSelected(
                new Events.ToastMenuItemClickEvent(TEST_MENU_POSITION, TEST_MENU_ID));
        verify(model).setCurrentSelectedPosition(TEST_MENU_POSITION);
    }

    @Test
    public void itShouldUpdateImageResource() {
        Events.SetResourceEvent event = new Events.SetResourceEvent(TEST_MENU_ID,
                TEST_RES_ID,
                TEST_IMAGE_ID
        );
        presenter.onSetResource(event);
        verify(model).updateImageResource(TEST_MENU_ID, TEST_RES_ID, TEST_IMAGE_ID);
        verify(view, times(2)).setMenuItems(model.getSideOfToast());
    }

    @Test
    public void itShouldUpdateStringResource() {
        Events.SetResourceEvent event = new Events.SetResourceEvent(TEST_MENU_ID,
                TEST_RES_ID,
                TEST_STRING
        );
        presenter.onSetResource(event);
        verify(model).updateStringResource(TEST_MENU_ID, TEST_RES_ID, TEST_STRING);
        verify(view, times(2)).setMenuItems(model.getSideOfToast());
    }

    @Test
    public void itShouldPassConfigurationChangesToDrawerToggle() {
        Configuration config = new Configuration();
        presenter.onConfigurationChanged(config);
        verify(view).setDrawerToggleConfiguration(config);
    }
}
