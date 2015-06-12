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

		Anchor Opret_ProduktBatch = new Anchor("Opret ProduktBatch");
		hPanel.add(Opret_ProduktBatch);
		Opret_ProduktBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addProduktBatchOperator();
			}
		});

		Anchor Vis_ProduktBatch = new Anchor("Vis ProduktBatch");
		hPanel.add(Vis_ProduktBatch);
		Vis_ProduktBatch.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showProduktBatch();
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

