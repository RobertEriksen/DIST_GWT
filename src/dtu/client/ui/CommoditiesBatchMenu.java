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

		Anchor opret_RaavareBatch = new Anchor("Opret råvarebatch");
		hPanel.add(opret_RaavareBatch);
		opret_RaavareBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addCommoditiesBatch();
			}
		});

		Anchor vis_RaavareBatch = new Anchor("Vis råvarebatch");
		hPanel.add(vis_RaavareBatch);
		vis_RaavareBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showCommoditiesBatch();
			}
		});

		Anchor back = new Anchor("Tilbage");
		hPanel.add(back);
		back.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){	
				main.clearContentView();
				if (level == 4){
					main.showAdministratorMenu(level);
				}
				else if (level == 3){
					main.showpharmacistMenu(level);
				}

				else if (level == 2){
					main.showForemanMenu(level);
				}
			}
		});
	}
}

