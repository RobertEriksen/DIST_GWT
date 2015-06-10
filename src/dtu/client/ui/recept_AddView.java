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

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.OperatoerDTO;
import dtu.shared.ReceptDTO;

public class recept_AddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label receptIdLbl;
	Label raavareidLbl;
	Label receptnameLbl;
	Label nomNetto;
	Label tolerance;
	
	TextBox receptIdTxt;
	TextBox raavareIdTxt;
	TextBox receptnameTxt;
	TextBox nomNettoTxt;
	TextBox toleranceTxt;
	
	Button save = new Button("Tilf\u00F8j");

	// valid fields
	boolean nameValid = false;
	boolean iniValid = false;
	boolean cprValid = false;
	boolean passValid = false;
	boolean activeValid = false;

	public recept_AddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("tilfoej Recept");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		receptIdLbl = new Label("Recept ID:");
		receptIdTxt = new TextBox();
		Label nameRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(0, 0, receptIdLbl);
		addTable.setWidget(0, 1, receptIdTxt);
		addTable.setWidget(0, 2, nameRulesLbl);

		raavareidLbl = new Label("Raavare ID:");
		raavareIdTxt = new TextBox();
		Label iniRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(1, 0, raavareidLbl);
		addTable.setWidget(1, 1, raavareIdTxt);
		addTable.setWidget(1, 2, iniRulesLbl);

		receptnameLbl = new Label("Recept Navn:");
		receptnameTxt = new TextBox();
		Label cprRulesLbl = new Label("(mellem 2 og 20 tegn)");
		addTable.setWidget(2, 0, receptnameLbl);
		addTable.setWidget(2, 1, receptnameTxt);
		addTable.setWidget(2, 2, cprRulesLbl);

		nomNetto = new Label("NomNetto:");
		nomNettoTxt = new TextBox();
		Label passRulesLbl = new Label("(Decimaltal mellem 0.1% - 10%");
		addTable.setWidget(3, 0, nomNetto);
		addTable.setWidget(3, 1, nomNettoTxt);
		addTable.setWidget(3, 2, passRulesLbl);
		
		tolerance = new Label("Tolerance:");
		toleranceTxt = new TextBox();
		Label activeRulesLbl = new Label("(Decimaltal mellem 0.1% - 10%)");
		addTable.setWidget(4, 0, tolerance);
		addTable.setWidget(4, 1, toleranceTxt);
		addTable.setWidget(4, 2, activeRulesLbl);
		
		
		receptIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		raavareIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		receptnameTxt.setStyleName("gwt-TextBox-invalidEntry");
		nomNettoTxt.setStyleName("gwt-TextBox-invalidEntry");
		toleranceTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);
		addTable.setWidget(5, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// create new OperatoerDTO
				ReceptDTO newRecept = new ReceptDTO(Integer.valueOf(receptIdTxt.getText()), Integer.valueOf(raavareIdTxt.getText()), receptnameTxt.getText(), Double.valueOf(nomNettoTxt.getText()) , Double.valueOf(toleranceTxt.getText()));
				

				// save on server
				clientImpl.service.createRecept(newRecept, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Operatoer gemt i kartotek.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});


		// register event handlers

		receptIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(receptIdTxt.getText())) {
					receptIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					nameValid = false;
				}
				else {
					receptIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					nameValid = true;
				}
				checkFormValid();
			}

		});

		raavareIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(raavareIdTxt.getText())) {
					raavareIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					iniValid = false;
				}
				else {
					raavareIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					iniValid = true;
				}
				checkFormValid();
			}

		});

		receptnameTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(receptnameTxt.getText())) {
					receptnameTxt.setStyleName("gwt-TextBox-invalidEntry");
					cprValid = false;
				}
				else {
					receptnameTxt.removeStyleName("gwt-TextBox-invalidEntry");
					cprValid = true;
				}
				checkFormValid();
			}

		});
		
		nomNettoTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidNomNetto(nomNettoTxt.getText())) {
					nomNettoTxt.setStyleName("gwt-TextBox-invalidEntry");
					passValid = false;
				}
				else {
					nomNettoTxt.removeStyleName("gwt-TextBox-invalidEntry");
					passValid = true;
				}
				checkFormValid();
			}

		});

		toleranceTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidTolerance(toleranceTxt.getText())) {
					toleranceTxt.setStyleName("gwt-TextBox-invalidEntry");
					activeValid = false;
				}
				else {
					toleranceTxt.removeStyleName("gwt-TextBox-invalidEntry");
					activeValid = true;
				}
				checkFormValid();
			}

		});
		
		
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (nameValid&&iniValid&&cprValid&&passValid&&activeValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
