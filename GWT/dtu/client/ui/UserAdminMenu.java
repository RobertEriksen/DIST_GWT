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
	public UserAdminMenu(final MainView main, final int level) {
		initWidget(this.hPanel);
		
		Anchor showUsers = new Anchor("Vis brugere");
		hPanel.add(showUsers);

		// call back the controller
		showUsers.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showUsers();
			}
		});
	
		// use unicode escape sequence \u00F8 for '�'
		Anchor add = new Anchor("Tilf\u00F8j brugere");
		hPanel.add(add);
		add.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addUser();
			}
		});
		
		Anchor edit = new Anchor("Ret brugere");
		hPanel.add(edit);
		edit.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.editUsers();
			}
		});
		
		Anchor delete = new Anchor("Slet brugere");
		hPanel.add(delete);
		delete.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.deleteUsers();
			}
		});
		
		Anchor back = new Anchor("Tilbage");
		hPanel.add(back);
		back.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){	
				main.clearContentView();
				if(level==4){
					main.showAdministratorMenu(level);
				}
				else if(level == 3){
					main.showpharmacistMenu(level);
				}
				
				else if(level == 2){
					main.showForemanMenu(level);
			}
		}
	});
}
}
