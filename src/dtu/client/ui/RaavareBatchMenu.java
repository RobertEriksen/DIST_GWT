package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class RaavareBatchMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public RaavareBatchMenu(final MainView main) {
		initWidget(this.hPanel);
		
//		Anchor Opret_RaavareBatch = new Anchor("Opret RaavareBatch");
//		hPanel.add(Opret_RaavareBatch);
//		Opret_RaavareBatch.addClickHandler(new ClickHandler(){
//			public void onClick(ClickEvent event){				
//				main.addreceptOperator();
//			}
//		});
		
		Anchor Vis_RaavareBatch = new Anchor("Vis RaavareBatch");
		hPanel.add(Vis_RaavareBatch);
//		Vis_RaavareBatch.addClickHandler(new ClickHandler(){
//			public void onClick(ClickEvent event){				
//				main.showRecepts();
//			}
//		});
		
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
	
