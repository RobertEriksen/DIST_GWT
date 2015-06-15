package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.DatabaseServiceClientImpl;
import dtu.shared.UserDTO;
import dtu.shared.ProductBatchDTO;
import dtu.shared.RecipeDTO;
import dtu.shared.ReceptKomponentDTO;

public class ProductBatchBrowseView extends Composite {
	DatabaseServiceClientImpl clientImpl;
	VerticalPanel browsePanel;
	FlexTable t;

	public ProductBatchBrowseView(DatabaseServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		HorizontalPanel topPanel = new HorizontalPanel();
		Label pageTitleLbl = new Label("Vis produktbatches");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.setWidth("450px");
		topPanel.add(pageTitleLbl);
		topPanel.addStyleName("spacing-vertical");
		browsePanel.add(topPanel);

		t = new FlexTable();

		t.getFlexCellFormatter().setWidth(0, 0, "8em");
		t.getFlexCellFormatter().setWidth(0, 1, "10em");
		t.getFlexCellFormatter().setWidth(0, 2, "6em");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "Produktbatch ID");
		t.setText(0, 1, "Status");
		t.setText(0, 2, "Recept ID");

		getProduktBatches();

		browsePanel.add(t);
	}
	
	private void getProduktBatches() {
		clientImpl.service.getProductBatch(new AsyncCallback<List<ProductBatchDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<ProductBatchDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t.setText(i+1, 0, ""+result.get(i).getPb_ID());
					t.setText(i+1, 1,""+ result.get(i).getStatus());
					t.setText(i+1, 2,""+ result.get(i).getRecept_id());
				}
			}
		});
	}
}
