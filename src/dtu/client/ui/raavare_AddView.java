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
import dtu.shared.RaavareDTO;
import dtu.shared.RaavareDTO;

public class raavare_AddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label idLbl;
	Label nameLbl;
	Label lvrLbl;
	
	TextBox idTxt;
	TextBox nameTxt;
	TextBox lvrTxt;
	
	Button save = new Button("Tilf\u00F8j");

	// valid fields
	boolean idValid = false;
	boolean nameValid = false;
	boolean lvrValid = false;

	public raavare_AddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("Tilfoej raavarer");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		idLbl = new Label("Raavare ID:");
		idTxt = new TextBox();
		Label idRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(0, 0, idLbl);
		addTable.setWidget(0, 1, idTxt);
		addTable.setWidget(0, 2, idRulesLbl);

		nameLbl = new Label("Raavare navn:");
		nameTxt = new TextBox();
		Label nameRulesLbl = new Label("(minimum 2 tegn og maksimum 20)");
		addTable.setWidget(3, 0, nameLbl);
		addTable.setWidget(3, 1, nameTxt);
		addTable.setWidget(3, 2, nameRulesLbl);
		
		lvrLbl = new Label("Leverandoer:");
		lvrTxt = new TextBox();
		Label lvrRulesLbl = new Label("(minimum 2 og maksimum 20 tegn)");
		addTable.setWidget(5, 0, lvrLbl);
		addTable.setWidget(5, 1, lvrTxt);
		addTable.setWidget(5, 2, lvrRulesLbl);
		
		idTxt.setStyleName("gwt-TextBox-invalidEntry");
		nameTxt.setStyleName("gwt-TextBox-invalidEntry");
		lvrTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for 'ï¿½'
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);
		addTable.setWidget(6, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// create new RaavareDTO
				//lav korrekt konstuktør
				RaavareDTO newRaavare = new RaavareDTO(Integer.valueOf(idTxt.getText()), nameTxt.getText(), lvrTxt.getText());
				// save on server
				clientImpl.service.createRaavare(newRaavare, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Raavare gemt i kartotek.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});


		// register event handlers

		idTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(idTxt.getText())) {
					idTxt.setStyleName("gwt-TextBox-invalidEntry");
					idValid = false;
				}
				else {
					idTxt.removeStyleName("gwt-TextBox-invalidEntry");
					idValid = true;
				}
				checkFormValid();
			}

		});

		
		
		nameTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(nameTxt.getText())) {
					nameTxt.setStyleName("gwt-TextBox-invalidEntry");
					nameValid = false;
				}
				else {
					nameTxt.removeStyleName("gwt-TextBox-invalidEntry");
					nameValid = true;
				}
				checkFormValid();
			}

		});

		
		
		lvrTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(lvrTxt.getText())) {
					lvrTxt.setStyleName("gwt-TextBox-invalidEntry");
					lvrValid = false;
				}
				else {
					lvrTxt.removeStyleName("gwt-TextBox-invalidEntry");
					lvrValid = true;
				}
				checkFormValid();
			}

		});
		
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (idValid&&nameValid&&lvrValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
