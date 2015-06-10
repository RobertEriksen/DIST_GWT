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
import dtu.shared.ReceptKomponentDTO;

public class Recept_BrowseView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel browsePanel;
	FlexTable t;
//	Button showInactiveOps;
//	boolean showInactive = false;

	public Recept_BrowseView(KartotekServiceClientImpl clientImpl) {
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
		t.getFlexCellFormatter().setWidth(0, 1, "40em");
		t.getFlexCellFormatter().setWidth(0, 2, "6em");
		t.getFlexCellFormatter().setWidth(0, 3, "20em");
		t.getFlexCellFormatter().setWidth(0, 4, "16em");
//		t.getFlexCellFormatter().setWidth(0, 5, "3em");
//		t.getFlexCellFormatter().setWidth(0, 6, "3em");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "Recept ID");
		t.setText(0, 1, "Raavare ID");
		t.setText(0, 2, "Recept Navn");
		t.setText(0, 3, "Nominelle Netto");
		t.setText(0, 4, "Tolerance");
//		t.setText(0, 5, "Aktiv");
//		t.setText(0, 6, "Niveau");

		getOperators();

		browsePanel.add(t);

//		showInactiveOps.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				for (int i = 1; i < t.getRowCount(); i++) t.removeRow(i); // clear FlexTable (except for first header row)
//				if (!showInactive) {
//					showInactiveOps.setText("Skjul inaktive operatører");
//					showInactive = true;
//					getOperators();
//				}
//				else {
//					showInactiveOps.setText("Vis inaktive operatører");
//					showInactive = false;
//					getOperators();
//				}
//			}
//		});
	}
	
	private void getOperators() {
		clientImpl.service.getRecept(new AsyncCallback<List<ReceptKomponentDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<ReceptKomponentDTO> result) {
				for (int i=0; i < result.size(); i++) {
//					if (!showInactive) {
//						if (Integer.valueOf(result.get(i).getActive()) == 1) {
//							t.setText(i+1, 0, ""+result.get(i).getOprId());
//							t.setText(i+1, 1, result.get(i).getOprNavn());
//							t.setText(i+1, 2, result.get(i).getIni());
//							t.setText(i+1, 3, result.get(i).getCpr());
//							t.setText(i+1, 4, result.get(i).getPassword());
//							t.setText(i+1, 5, result.get(i).getActive());
//							t.setText(i+1, 6, result.get(i).getLevel());
//						}
//					} 
//					else {
						t.setText(i+1, 0, ""+result.get(i).getRcpId());
						t.setText(i+1, 1,""+ result.get(i).getRvrId());
						t.setText(i+1, 2,""+ result.get(i).getNomNetto());
						t.setText(i+1, 3,""+ result.get(i).getTolerance());
//					}
				}
			}
		});
	}
}
