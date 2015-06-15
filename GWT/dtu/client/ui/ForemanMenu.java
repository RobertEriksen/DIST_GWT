package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class ForemanMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();

	// receive reference to MainView for call back
	public ForemanMenu(final MainView main, final int level) {
		initWidget(this.hPanel);

		Anchor raavarebatch_administration = new Anchor("Råvarebatch");
		hPanel.add(raavarebatch_administration);
		raavarebatch_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showCommoditiesBatchMenu(level);
			}
		});

		Anchor produktbatch_administration = new Anchor("Produktbatch");
		hPanel.add(produktbatch_administration);
		produktbatch_administration.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showProductBatchMenu(level);			}
		});
	}
}
