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
		
		Anchor Bruger_Administration = new Anchor("Bruger Administration");
		hPanel.add(Bruger_Administration);
		Bruger_Administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){	
				main.showMenuView();
			}
		});
	
		
		Anchor Raavare_administration = new Anchor("Raavare administration");
		hPanel.add(Raavare_administration);
		// use unicode escape sequence \u00F8 for '�'
//		Anchor add = new Anchor("Tilf\u00F8j operatør");
//		hPanel.add(add);
		Raavare_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRaavareMenu();
			}
		});
		
		
		Anchor Recept_administration = new Anchor("Recept administration");
		hPanel.add(Recept_administration);
		Recept_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showReceptMenu();
			}
		});
		
		
		
		
		Anchor Raavarebatch_administration = new Anchor("Raavarebatch administration");
		hPanel.add(Raavarebatch_administration);
		Raavarebatch_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRaavareBatchMenu();
			}
		});
		
		Anchor Produktbatch_administration = new Anchor("Produktbatch administration");
		hPanel.add(Produktbatch_administration);
	}
}
