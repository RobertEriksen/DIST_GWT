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

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.OperatoerDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptKomponentDTO;

public class ReceptKomponent_BrowseView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel browsePanel;
	FlexTable t;
//	Button showInactiveOps;
//	boolean showInactive = false;

	public ReceptKomponent_BrowseView(KartotekServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		HorizontalPanel topPanel = new HorizontalPanel();
//		showInactiveOps = new Button("Vis inaktive operatører");
		Label pageTitleLbl = new Label("Vis Recepter");
		pageTitleLbl.setStyleName("FlexTable-Header");
		pageTitleLbl.setWidth("450px");
		topPanel.add(pageTitleLbl);
//		topPanel.add(showInactiveOps);
		topPanel.addStyleName("spacing-vertical");
		browsePanel.add(topPanel);

		t = new FlexTable();

		t.getFlexCellFormatter().setWidth(0, 0, "8em");
		t.getFlexCellFormatter().setWidth(0, 1, "10em");
		t.getFlexCellFormatter().setWidth(0, 2, "10em");
		t.getFlexCellFormatter().setWidth(0, 3, "20em");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "Recept ID");
		t.setText(0, 1, "Raavare ID");
		t.setText(0, 2, "Nominelle Netto");
		t.setText(0, 3, "Tolerance");

		getReceptKomponenter();
		browsePanel.add(t);
	}
	
	private void getReceptKomponenter() {
		clientImpl.service.getReceptKomponenter(new AsyncCallback<List<ReceptKomponentDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<ReceptKomponentDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t.setText(i+1, 0, ""+result.get(i).getRcpId());
					t.setText(i+1, 1,""+ result.get(i).getRvrId());
					t.setText(i+1, 2,""+ result.get(i).getNomNetto());
					t.setText(i+1, 3,""+ result.get(i).getTolerance());

				}
			}
		});
	}
}
