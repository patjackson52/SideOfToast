#Side of Toast

_Quick, easy_ side navigation menu for Android.  

#Build the SideNav Menu
In your Activity:

	ToastMenuItem sourDoughItem = new ToastMenuItem.Builder(SOURDOUGH_MENU_ID, BREAD_ROW_TYPE)
		.addText(R.id.txt_bread_type, "Sourdough")
		.addImage(R.id.img_bread, R.drawable.sourdough)
		.build(this);
		
	SideOfToast sideMenu = SideToast.Builder()
		.addItemViewType(BREAD_ROW_TYPE, R.layout.row_bread)
		.addItem(sourDoughItem);
		.build()
		.create();
				

#Create It

SideOfToast.Builder.create() will insert a [NavigationDrawer](https://developer.android.com/design/patterns/navigation-drawer.html) with your menu inside it.  Do not add a NavigationDrawer to your xml layout.

	sideMenu.create();
	

#Supply SideNav Layout (OPTIONAL):

* Pass layout in SideOfToast.Builder constructor
* Must include a ListView with id r.id.sideOfToastListView  (included with in SideOfToast war).
	
		SideOfToast sideMenu = SideOfToast.Build(R.layout.my_awesome_layout);
		
#Respond to Item Clicks
SideOfToast fires an [Otto event](https://github.com/square/otto) with the corresponding item ID when an item is tapped. 

	    @Subscribe
    	public void onToastMenuItemClick(Events.ToastMenuItemClickEvent event) {
        switch(event.getItemId()) {
        	case (SOURDOUGH_MENU_ID):
        		//switch to MyFragment
        		break;        }
        

#Update Text/Images

Text and Images can be updated with Otto events:

	bus.post(Events.SetResourceEvent(SOURDOUGH_MENU_ID,
				R.id.img_bread,
				R.drawable.toasted_sourdough);

#Create Row Layouts
SideOfToast uses a ListView to display the nav menu.  You must supply a layout for the items.  This must be passed to the SideOfToaster.Builder with  `.setItemViewType(TYPE_CONSTANT, R.id.layout)`.  You may have several different item layouts.

Example Item Layout:

	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:layout_width="match_parent"
    	android:layout_height="48dp"
    	android:background="@drawable/cool_background_selector"
    	android:orientation="horizontal" >

    	<ImageView
      	  	android:id="@+id/img_bread_type"
        	android:layout_centerVertical="true"
        	android:layout_width="24dp"
        	android:layout_height="24dp"
        	android:layout_margin="16dp" />

    	<TextView
        	android:id="@+id/txt_bread"
        	android:layout_width="match_parent"
        	android:gravity="center_vertical"
        	android:layout_toRightOf="@+id/img_bread_type" />

    	<View
        	android:id="@+id/divSidebar"
        	style="@style/DividerAppStyle"
        	android:layout_width="match_parent"/>
	</RelativeLayout>
	
#Footers

There are cases where you may want to attach a footer to the bottom of your Side nag menu.  To do this use  `ToastMenuFooterItem`. 