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
import dtu.shared.CommoditiesBatchDTO;
import dtu.shared.RecipeDTO;
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
	
	Button save = new Button("Tilf\u00F8j råvarebatch");

	// valid fields
	boolean raav_BatIDValid = false;
	boolean raavIDValid = false;
	boolean maengdeValid = false;

	public CommoditiesBatchAddView(final DatabaseServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("Tilføj råvarebatch");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		raavareBatchIdLbl = new Label("Råvarebatch ID:");
		raavareBatchIdTxt = new TextBox();
		Label rbIDRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(0, 0, raavareBatchIdLbl);
		addTable.setWidget(0, 1, raavareBatchIdTxt);
		addTable.setWidget(0, 2, rbIDRulesLbl);

		raavareidLbl = new Label("Råvare ID:");
		raavareidTxt = new TextBox();
		Label rvIDRulesLbl = new Label("(Heltal kun)");
		addTable.setWidget(1, 0, raavareidLbl);
		addTable.setWidget(1, 1, raavareidTxt);
		addTable.setWidget(1, 2, rvIDRulesLbl);

		maengdeLbl = new Label("Mængde:");
		maengdeTxt = new TextBox();
		Label maengdeRulesLbl = new Label("(Decimaltal)");
		addTable.setWidget(2, 0, maengdeLbl);
		addTable.setWidget(2, 1, maengdeTxt);
		addTable.setWidget(2, 2, maengdeRulesLbl);
		
		raavareBatchIdTxt.setStyleName("gwt-TextBox-invalidEntry");
		raavareidLbl.setStyleName("gwt-TextBox-invalidEntry");
		maengdeTxt.setStyleName("gwt-TextBox-invalidEntry");

		// use unicode escape sequence \u00F8 for '�'
		save.setEnabled(false);
		addTable.setWidget(5, 1, save);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				CommoditiesBatchDTO newRaavareBatch = new CommoditiesBatchDTO(Integer.valueOf(raavareBatchIdTxt.getText()), Integer.valueOf(raavareidTxt.getText()), Double.valueOf(maengdeTxt.getText()));
				
				// save on server
				clientImpl.service.createCommodityBatch(newRaavareBatch, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Råvarebatch gemt i database.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});

		raavareBatchIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(raavareBatchIdTxt.getText())) {
					raavareBatchIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					raav_BatIDValid = false;
				}
				else {
					raavareBatchIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					raav_BatIDValid = true;
				}
				checkFormValid();
			}

		});

		raavareidTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(raavareidTxt.getText())) {
					raavareidTxt.setStyleName("gwt-TextBox-invalidEntry");
					raavIDValid = false;
				}
				else {
					raavareidTxt.removeStyleName("gwt-TextBox-invalidEntry");
					raavIDValid = true;
				}
				checkFormValid();
			}

		});
		
		maengdeTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.IsValidMaengde(maengdeTxt.getText())) {
					maengdeTxt.setStyleName("gwt-TextBox-invalidEntry");
					maengdeValid = false;
				}
				else {
					maengdeTxt.removeStyleName("gwt-TextBox-invalidEntry");
					maengdeValid = true;
				}
				checkFormValid();
			}

		});
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (raavIDValid&&maengdeValid&&raav_BatIDValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
