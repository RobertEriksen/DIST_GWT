package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class ProductBatchMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();


	public ProductBatchMenu(final MainView main, final int level) {
		initWidget(this.hPanel);
		
		Anchor vis_ProduktBatch = new Anchor("Vis produktbatch");
		hPanel.add(vis_ProduktBatch);
		vis_ProduktBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showProductBatch();
			}
		});

		Anchor opret_ProduktBatch = new Anchor("Opret produktbatch");
		hPanel.add(opret_ProduktBatch);
		opret_ProduktBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addProductBatch();
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

