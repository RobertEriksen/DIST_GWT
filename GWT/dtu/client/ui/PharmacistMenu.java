package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class PharmacistMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();

	// receive reference to MainView for call back
	public PharmacistMenu(final MainView main, final int level) {
		initWidget(this.hPanel);

		Anchor raavare_administration = new Anchor("Råvare");
		hPanel.add(raavare_administration);
		raavare_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showCommoditiesMenu(level);
			}
		});

		Anchor raavarebatch_administration = new Anchor("Råvarebatch");
		hPanel.add(raavarebatch_administration);
		raavarebatch_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showCommoditiesBatchMenu(level);
			}
		});

		Anchor recept_administration = new Anchor("Recept");
		hPanel.add(recept_administration);
		recept_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRecipeMenu(level);
			}
		});

		Anchor receptKomponent_administration = new Anchor("Receptkomponent");
		hPanel.add(receptKomponent_administration);
		receptKomponent_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRecipeKomponentMenu(level);
			}
		});

		Anchor produktbatch_administration = new Anchor("Produktbatch");
		hPanel.add(produktbatch_administration);
		produktbatch_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showProductBatchMenu(level);
			}
		});
	}
}
