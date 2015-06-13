package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class RecipeComponentMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();


	public RecipeComponentMenu(final MainView main, final int level) {
		initWidget(this.hPanel);

		Anchor vis_receptkomp = new Anchor("Vis receptomponenter");
		hPanel.add(vis_receptkomp);
		vis_receptkomp.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRecipeComponents();
			}
		});
		
		Anchor tilfoej_receptkomponent = new Anchor("Tilføj receptkomponent");
		hPanel.add(tilfoej_receptkomponent);
		tilfoej_receptkomponent.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addRecipeComponent();
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

