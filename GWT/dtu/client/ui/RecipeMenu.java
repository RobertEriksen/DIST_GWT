package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import dtu.client.controller.MainView;

public class RecipeMenu extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();


	public RecipeMenu(final MainView main, final int level) {
		initWidget(this.hPanel);

		Anchor vis_recept = new Anchor("Vis recepter");
		hPanel.add(vis_recept);
		vis_recept.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showRecipes();
			}
		});
		
		Anchor tilfoej_recept = new Anchor("Tilføj recept");
		hPanel.add(tilfoej_recept);
		tilfoej_recept.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addRecipe();
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

