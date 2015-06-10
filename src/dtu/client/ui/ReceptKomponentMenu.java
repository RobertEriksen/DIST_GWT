package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class ReceptKomponentMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public ReceptKomponentMenu(final MainView main) {
		initWidget(this.hPanel);
		
		Anchor tilfoej_recept = new Anchor("Tilfoej Recept");
		hPanel.add(tilfoej_recept);
		tilfoej_recept.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addreceptKomponentOperator();
			}
		});
		
		Anchor tilfoej_receptkomponent = new Anchor("Tilfoej Receptkomponent");
		hPanel.add(tilfoej_receptkomponent);
		tilfoej_receptkomponent.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addreceptKomponentOperator();
			}
		});
		
		Anchor ret_raavare = new Anchor("Vis ReceptKomponent");
		hPanel.add(ret_raavare);
		ret_raavare.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRecepts();
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
	
