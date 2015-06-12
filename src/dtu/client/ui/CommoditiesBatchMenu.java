package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class CommoditiesBatchMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();


	public CommoditiesBatchMenu(final MainView main, final int level) {
		initWidget(this.hPanel);

		Anchor Opret_RaavareBatch = new Anchor("Opret RåvareBatch");
		hPanel.add(Opret_RaavareBatch);
		Opret_RaavareBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addCommoditiesBatch();
			}
		});

		Anchor Vis_RaavareBatch = new Anchor("Vis RåvareBatch");
		hPanel.add(Vis_RaavareBatch);
		Vis_RaavareBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showCommoditiesBatch();
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

