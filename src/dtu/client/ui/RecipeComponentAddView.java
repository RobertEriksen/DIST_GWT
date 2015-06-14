package dtu.client.ui;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.DatabaseServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.UserDTO;
import dtu.shared.RecipeDTO;
import dtu.shared.ReceptKomponentDTO;

public class RecipeComponentAddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label recKompIdLbl;
	Label raavareidLbl;
	Label nomNetto;
	Label tolerance;
	
	TextBox recKompIdTxt;
	TextBox raavareIdTxt;
	TextBox nomNettoTxt;
	TextBox toleranceTxt;
	
	Button save = new Button("Tilf\u00F8j receptkomponent");

	// valid fields
	boolean rk_idValid = false;
	boolean raav_idValid = false;
	boolean nettoValid = false;
	boolean tolValid = false;

	public RecipeComponentAddView(final DatabaseServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("Tilfoej receptkomponent");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		recKompIdLbl = new Label("Recept ID:");
		recKompIdTxt = new TextBox();
		Label nameRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(0, 0, recKompIdLbl);
		addTable.setWidget(0, 1, recKompIdTxt);
		addTable.setWidget(0, 2, nameRulesLbl);

		raavareidLbl = new Label("Råvare ID:");
		raavareIdTxt = new TextBox();
		Label iniRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(1, 0, raavareidLbl);
		addTable.setWidget(1, 1, raavareIdTxt);
		addTable.setWidget(1, 2, iniRulesLbl);

		nomNetto = new Label("Nom. netto:");
		nomNettoTxt = new TextBox();
		Label passRulesLbl = new Label("(Decimaltal mellem 0.1% - 10.0%");
		addTable.setWidget(3, 0, nomNetto);
		addTable.setWidget(3, 1, nomNettoTxt);
		addTable.setWidget(3, 2, passRulesLbl);
		
		tolerance = new Label("Tolerance:");
		toleranceTxt = new TextBox();
		Label activeRulesLbl = new Label("(Decimaltal mellem 0.1% - 10.0%)");
		addTable.setWidget(4, 0, tolerance);
		addTable.setWidget(4, 1, toleranceTxt);
		addTable.setWidget(4, 2, activeRulesLbl);
		
		
		recKompIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		raavareIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		nomNettoTxt.setStyleName("gwt-TextBox-invalidEntry");
		toleranceTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save.setEnabled(false);
		addTable.setWidget(5, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// create new OperatoerDTO
				
				ReceptKomponentDTO newReceptKomponent = new ReceptKomponentDTO(Integer.valueOf(recKompIdTxt.getText()), Integer.valueOf(raavareIdTxt.getText()), Double.valueOf(nomNettoTxt.getText()) , Double.valueOf(toleranceTxt.getText()));
				
				// save on server
				clientImpl.service.createRecipeComponent(newReceptKomponent, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Receptkomponent gemt i database.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});


		// register event handlers

		recKompIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(recKompIdTxt.getText())) {
					recKompIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					raav_idValid = false;
				}
				else {
					recKompIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					raav_idValid = true;
				}
				checkFormValid();
			}

		});

		raavareIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(raavareIdTxt.getText())) {
					raavareIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					rk_idValid = false;
				}
				else {
					raavareIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					rk_idValid = true;
				}
				checkFormValid();
			}

		});
		
		nomNettoTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidNomNetto(nomNettoTxt.getText())) {
					nomNettoTxt.setStyleName("gwt-TextBox-invalidEntry");
					nettoValid = false;
				}
				else {
					nomNettoTxt.removeStyleName("gwt-TextBox-invalidEntry");
					nettoValid = true;
				}
				checkFormValid();
			}

		});

		toleranceTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidTolerance(toleranceTxt.getText())) {
					toleranceTxt.setStyleName("gwt-TextBox-invalidEntry");
					tolValid = false;
				}
				else {
					toleranceTxt.removeStyleName("gwt-TextBox-invalidEntry");
					tolValid = true;
				}
				checkFormValid();
			}

		});
		
		
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (rk_idValid&&raav_idValid&&nettoValid&&tolValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
