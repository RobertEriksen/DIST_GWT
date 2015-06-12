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
import dtu.shared.RaavareBatchDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptKomponentDTO;

public class CommoditiesBatchAddView extends Composite {
	VerticalPanel addPanel;

	// controls
	Label raavareBatchIdLbl;
	Label raavareidLbl;
	Label maengdeLbl;
	
	TextBox raavareBatchIdTxt;
	TextBox raavareidTxt;
	TextBox maengdeTxt;
	
	Button save = new Button("Tilf\u00F8j");

	// valid fields
	
	boolean raavareBatchId = false;
	boolean raavareid = false;
	boolean maengde = false;

	public CommoditiesBatchAddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("tilfoej RaavareBatch");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		raavareBatchIdLbl = new Label("RaavareBatch ID:");
		raavareBatchIdTxt = new TextBox();
		Label nameRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(0, 0, raavareBatchIdLbl);
		addTable.setWidget(0, 1, raavareBatchIdTxt);
		addTable.setWidget(0, 2, nameRulesLbl);

		raavareidLbl = new Label("Raavare ID:");
		raavareidTxt = new TextBox();
		Label iniRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(1, 0, raavareidLbl);
		addTable.setWidget(1, 1, raavareidTxt);
		addTable.setWidget(1, 2, iniRulesLbl);

		maengdeLbl = new Label("Maengde:");
		maengdeTxt = new TextBox();
		Label passRulesLbl = new Label("(Decimaltal");
		addTable.setWidget(2, 0, maengdeLbl);
		addTable.setWidget(2, 1, maengdeTxt);
		addTable.setWidget(2, 2, passRulesLbl);
		
		// Der er forkerte rules p� NOmNeto
		
		
		raavareBatchIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		raavareidLbl.setStyleName("gwt-TextBox-invalidEntry");
		maengdeTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save.setEnabled(false);
		addTable.setWidget(5, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// create new OperatoerDTO
				
				RaavareBatchDTO newRaavareBatch= new RaavareBatchDTO(Integer.valueOf(raavareBatchIdTxt.getText()), Integer.valueOf(raavareidTxt.getText()), Double.valueOf(maengdeTxt.getText()));
				
				// save on server
				clientImpl.service.createRaavareBatch(newRaavareBatch, new AsyncCallback<Void>() {

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

		raavareBatchIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(raavareBatchIdTxt.getText())) {
					raavareBatchIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					raavareBatchId = false;
				}
				else {
					raavareBatchIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					raavareBatchId = true;
				}
				checkFormValid();
			}

		});

		raavareidTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(raavareidTxt.getText())) {
					raavareidTxt.setStyleName("gwt-TextBox-invalidEntry");
					raavareid = false;
				}
				else {
					raavareidTxt.removeStyleName("gwt-TextBox-invalidEntry");
					raavareid = true;
				}
				checkFormValid();
			}

		});
		
		maengdeTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.IsValidMaengde(maengdeTxt.getText())) {
					maengdeTxt.setStyleName("gwt-TextBox-invalidEntry");
					maengde = false;
				}
				else {
					maengdeTxt.removeStyleName("gwt-TextBox-invalidEntry");
					maengde = true;
				}
				checkFormValid();
			}

		});


		
		
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (raavareid&&maengde&&raavareBatchId)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
