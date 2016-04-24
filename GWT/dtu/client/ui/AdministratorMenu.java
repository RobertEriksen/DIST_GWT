package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class AdministratorMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();

	// receive reference to MainView for call back
	public AdministratorMenu(final MainView main) {
		initWidget(this.hPanel);
		
		Anchor bruger_Administration = new Anchor("Bruger");
		hPanel.add(bruger_Administration);
		bruger_Administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){	
				main.showUserMenu();
			}
		});
		
		Anchor lobby_Administration = new Anchor("Find Lobby");
		hPanel.add(lobby_Administration);
		lobby_Administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){	
				main.showLobbyMenu();
			}
		});
		
	}
}
