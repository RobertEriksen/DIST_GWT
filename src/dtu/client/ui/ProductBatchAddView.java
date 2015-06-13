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
import dtu.shared.ProductBatchDTO;
import dtu.shared.CommoditiesBatchDTO;
import dtu.shared.RecipeDTO;
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
	
	Button save = new Button("Tilf\u00F8j produktbatch");

	// valid fields
	
	boolean prodBatchValid = false;
	boolean statusValid = false;
	boolean receptIdValid = false;

	public ProductBatchAddView(final DatabaseServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();
		initWidget(this.addPanel);
		
		FlexTable addTable = new FlexTable();
		
		Label pageTitleLbl = new Label("Tilføj produktbatch");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.addStyleName("spacing-vertical");
		addPanel.add(pageTitleLbl);

		produktBatchIdLbl = new Label("Produktbatch ID:");
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

		receptIdLbl = new Label("Recept ID:");
		receptIdTxt = new TextBox();
		Label passRulesLbl = new Label("(Heltal kun)");
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
				
				ProductBatchDTO newProduktBatch= new ProductBatchDTO(Integer.valueOf(produktBatchIdTxt.getText()), Integer.valueOf(statusTxt.getText()), Integer.valueOf(receptIdTxt.getText()));
				
				// save on server
				clientImpl.service.createProductBatch(newProduktBatch, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Produktbatch gemt i database.");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});

		produktBatchIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidID(produktBatchIdTxt.getText())) {
					produktBatchIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					prodBatchValid = false;
				}
				else {
					produktBatchIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					prodBatchValid = true;
				}
				checkFormValid();
			}

		});

		statusTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidStatus(statusTxt.getText())) {
					statusTxt.setStyleName("gwt-TextBox-invalidEntry");
					statusValid = false;
				}
				else {
					statusTxt.removeStyleName("gwt-TextBox-invalidEntry");
					statusValid = true;
				}
				checkFormValid();
			}

		});
		
		receptIdTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.IsValidMaengde(receptIdTxt.getText())) {
					receptIdTxt.setStyleName("gwt-TextBox-invalidEntry");
					receptIdValid = false;
				}
				else {
					receptIdTxt.removeStyleName("gwt-TextBox-invalidEntry");
					receptIdValid = true;
				}
				checkFormValid();
			}

		});


		
		
		addPanel.add(addTable);
	}

	private void checkFormValid() {
		if (statusValid&&receptIdValid&&prodBatchValid)
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
