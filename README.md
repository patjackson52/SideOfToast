#Side of Toast

_Quick, easy_ side navigation menu for Android.  

#Build the SideNav Menu
In your Activity:

	ToastMenuItem sourDoughItem = new ToastMenuItem.Builder(BREAD_MENU_ID, BREAD_ROW_TYPE)
		.addText(R.id.breadType, "Sourdough")
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
    	public void onToastMenuItemClick(SideNavView.ToastMenuItemClickEvent event) {
        switch(event.getItemId()) {
        	case (MY_FRAGMENT_MENU_ID):
        		//switch to MyFragment
        		break;        }
        

#Update Text/Images
