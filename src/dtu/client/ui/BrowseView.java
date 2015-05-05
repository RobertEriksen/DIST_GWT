package dtu.client.ui;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.OperatoerDTO;

public class BrowseView extends Composite {
	VerticalPanel browsePanel;

	public BrowseView(KartotekServiceClientImpl clientImpl) {

		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		Label pageTitleLbl = new Label("Vis operatører");
		pageTitleLbl.setStyleName("FlexTable-Header");
		browsePanel.add(pageTitleLbl);

		final FlexTable t = new FlexTable();

		t.getFlexCellFormatter().setWidth(0, 0, "4em");
		t.getFlexCellFormatter().setWidth(0, 1, "20em");
		t.getFlexCellFormatter().setWidth(0, 2, "3em");
		t.getFlexCellFormatter().setWidth(0, 3, "10em");
		t.getFlexCellFormatter().setWidth(0, 4, "8em");
		t.getFlexCellFormatter().setWidth(0, 5, "3em");
		t.getFlexCellFormatter().setWidth(0, 6, "3em");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Password");
		t.setText(0, 5, "Aktiv");
		t.setText(0, 6, "Niveau");

		// V.2
		clientImpl.service.getOperators(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				for (int i=0; i < result.size(); i++) {
					if (Integer.valueOf(result.get(i).getActive()) == 1) {
						t.setText(i+1, 0, ""+result.get(i).getOprId());
						t.setText(i+1, 1, result.get(i).getOprNavn());
						t.setText(i+1, 2, result.get(i).getIni());
						t.setText(i+1, 3, result.get(i).getCpr());
						t.setText(i+1, 4, result.get(i).getPassword());
						t.setText(i+1, 5, result.get(i).getActive());
						t.setText(i+1, 6, result.get(i).getLevel());
					}
				}

			}

		});

		browsePanel.add(t);
	}
}
