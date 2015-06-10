package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class raavareMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public raavareMenu(final MainView main) {
		initWidget(this.hPanel);
		
		Anchor tilfoej_raavare = new Anchor("tilfoej raavare");
		hPanel.add(tilfoej_raavare);
		tilfoej_raavare.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addraavareOperator();
			}
		});
		
		Anchor ret_raavare = new Anchor("ret raavare / vis");
		hPanel.add(ret_raavare);
		ret_raavare.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.editRaavare();
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
	
