package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class CommoditiesMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public CommoditiesMenu(final MainView main, final int level) {
		initWidget(this.hPanel);
		
		Anchor tilfoej_raavare = new Anchor("tilføj råvare");
		hPanel.add(tilfoej_raavare);
		tilfoej_raavare.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addCommodities();
			}
		});
		
		Anchor ret_raavare = new Anchor("ret råvare / vis");
		hPanel.add(ret_raavare);
		ret_raavare.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.editCommodities();
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
	
