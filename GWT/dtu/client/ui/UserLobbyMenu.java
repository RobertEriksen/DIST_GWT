package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class UserLobbyMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	// receive reference to MainView for call back
	public UserLobbyMenu(final MainView main) {
		initWidget(this.hPanel);
		
		Anchor playGame = new Anchor("Deltag i spil");
		hPanel.add(playGame);

		// call back the controller
		playGame.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.playGame();
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
