package dtu.client.ui;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.OperatoerDTO;

public class AddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label nameLbl;
	Label iniLbl;
	Label cprLbl;
	Label passLbl;
	
	TextBox nameTxt;
	TextBox iniTxt;
	TextBox cprTxt;
	TextBox passTxt;
	
	Button save = new Button("Tilf\u00F8j");

	// valid fields
	boolean nameValid = false;
	boolean iniValid = false;
	boolean cprValid = false;
	boolean passValid = false;

	public AddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();

		// total height of widget. Components are distributed evenly
		addPanel.setHeight("300px");	
		initWidget(this.addPanel);

		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel iniPanel = new HorizontalPanel();
		HorizontalPanel cprPanel = new HorizontalPanel();
		HorizontalPanel passPanel = new HorizontalPanel();

		nameLbl = new Label("Navn:");
		nameLbl.setWidth("70px");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);

		iniLbl = new Label("Initialer:");
		iniLbl.setWidth("70px");
		iniTxt = new TextBox();
		//		iniTxt.setWidth("5em");
		iniTxt.setHeight("1em");
		iniPanel.add(iniLbl);
		iniPanel.add(iniTxt);

		cprLbl = new Label("CPR:");
		cprLbl.setWidth("70px");
		cprTxt = new TextBox();
		//		cprTxt.setWidth("5em");
		cprTxt.setHeight("1em");
		cprPanel.add(cprLbl);
		cprPanel.add(cprTxt);

		passLbl = new Label("Password:");
		passLbl.setWidth("70px");
		passTxt = new TextBox();
		//		passTxt.setWidth("5em");
		passTxt.setHeight("1em");
		passPanel.add(passLbl);
		passPanel.add(passTxt);

		// use unicode escape sequence \u00F8 for '�'
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// V.2
				// create new OperatoerDTO
				OperatoerDTO newOperatoer = new OperatoerDTO(nameTxt.getText(), iniTxt.getText(), cprTxt.getText(), passTxt.getText());

				// save on server
				clientImpl.service.createOperator(newOperatoer, new AsyncCallback<Void>() {

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

		iniTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidInitials(iniTxt.getText())) {
					iniTxt.setStyleName("gwt-TextBox-invalidEntry");
					iniValid = false;
				}
				else {
					iniTxt.removeStyleName("gwt-TextBox-invalidEntry");
					iniValid = true;
				}
				checkFormValid();
			}

		});

		cprTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidCpr(cprTxt.getText())) {
					cprTxt.setStyleName("gwt-TextBox-invalidEntry");
					cprValid = false;
				}
				else {
					cprTxt.removeStyleName("gwt-TextBox-invalidEntry");
					cprValid = true;
				}
				checkFormValid();
			}

		});
		
		passTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidPass(passTxt.getText())) {
					passTxt.setStyleName("gwt-TextBox-invalidEntry");
					passValid = false;
				}
				else {
					passTxt.removeStyleName("gwt-TextBox-invalidEntry");
					passValid = true;
				}
				checkFormValid();
			}

		});


		addPanel.add(namePanel);
		addPanel.add(iniPanel);
		addPanel.add(cprPanel);
		addPanel.add(passPanel);
		addPanel.add(save);
	}

	private void checkFormValid() {
		if (nameValid&&iniValid&&cprValid&&passValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);

	}

}
