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
import dtu.shared.ProduktBatchDTO;
import dtu.shared.RaavareBatchDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptKomponentDTO;

public class ProductBatchAddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label produktBatchIdLbl;
	Label statusLbl;
	Label receptIdLbl;
	
	TextBox produktBatchIdTxt;
	TextBox statusTxt;
	TextBox receptIdTxt;
	
	Button save = new Button("Tilf\u00F8j");

	// valid fields
	
	boolean produktBatchId = false;
	boolean status = false;
	boolean receptId = false;

	public ProductBatchAddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("tilfoej Produktbatch");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		produktBatchIdLbl = new Label("ProduktBatch ID:");
		produktBatchIdTxt = new TextBox();
		Label nameRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(0, 0, produktBatchIdLbl);
		addTable.setWidget(0, 1, produktBatchIdTxt);
		addTable.setWidget(0, 2, nameRulesLbl);

		statusLbl = new Label("Status:");
		statusTxt = new TextBox();
		Label iniRulesLbl = new Label("(Heltal kun mellem 0 til 2)");
		addTable.setWidget(1, 0, statusLbl);
		addTable.setWidget(1, 1, statusTxt);
		addTable.setWidget(1, 2, iniRulesLbl);

		receptIdLbl = new Label("recept ID:");
		receptIdTxt = new TextBox();
		Label passRulesLbl = new Label("(Decimaltal");
		addTable.setWidget(2, 0, receptIdLbl);
		addTable.setWidget(2, 1, receptIdTxt);
		addTable.setWidget(2, 2, passRulesLbl);
		
		
		produktBatchIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		statusTxt.setStyleName("gwt-TextBox-invalidEntry");
		receptIdTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save.setEnabled(false);
		addTable.setWidget(5, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// create new OperatoerDTO
				
				ProduktBatchDTO newProduktBatch= new ProduktBatchDTO(Integer.valueOf(produktBatchIdTxt.getText()), Integer.valueOf(statusTxt.getText()), Integer.valueOf(receptIdTxt.getText()));
				
				// save on server
				clientImpl.service.createProduktBatch(newProduktBatch, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("ReceptKomponent gemt i kartotek.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});


		// register event handlers

		produktBatchIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(produktBatchIdTxt.getText())) {
					produktBatchIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					produktBatchId = false;
				}
				else {
					produktBatchIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					produktBatchId = true;
				}
				checkFormValid();
			}

		});

		statusTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidStatus(statusTxt.getText())) {
					statusTxt.setStyleName("gwt-TextBox-invalidEntry");
					status = false;
				}
				else {
					statusTxt.removeStyleName("gwt-TextBox-invalidEntry");
					status = true;
				}
				checkFormValid();
			}

		});
		
		receptIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.IsValidMaengde(receptIdTxt.getText())) {
					receptIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					receptId = false;
				}
				else {
					receptIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					receptId = true;
				}
				checkFormValid();
			}

		});


		
		
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (status&&receptId&&produktBatchId)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
