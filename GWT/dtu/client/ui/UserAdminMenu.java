package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class UserAdminMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	// receive reference to MainView for call back
	public UserAdminMenu(final MainView main) {
		initWidget(this.hPanel);
		
		Anchor showUsers = new Anchor("Vis brugere");
		hPanel.add(showUsers);

		// call back the controller
		showUsers.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showUsers();
			}
		});

		Anchor back = new Anchor("Tilbage");
		hPanel.add(back);
		back.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){	
				main.clearContentView();
				main.showAdministratorMenu();
		}
	});
}
}
