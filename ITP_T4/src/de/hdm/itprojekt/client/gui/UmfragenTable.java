package de.hdm.itprojekt.client.gui;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;

public class UmfragenTable extends VerticalPanel {
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	User user = null;
	
	public UmfragenTable(User user, List <Presentation> Umfrageeintrag) {
		this.user =user;
		this.Umfrageeintrag = Umfrageeintrag;
		
	}
	
	 //erstellen des Data Provider
	   List<Presentation> Umfrageeintrag;
	
	
	public void onLoad() {
		super.onLoad();
		final CellTable <Presentation> surv = new CellTable <Presentation> (); 
		
		 // Create a list data provider.
		ListDataProvider<Presentation> dataProvider = new ListDataProvider<Presentation>();
		
		
		TextColumn<Presentation> nameColumn = new TextColumn<Presentation>() {

			@Override
			public String getValue(Presentation object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		};
		
		TextColumn<Presentation> timeslotColumn = new TextColumn<Presentation>() {

			@Override
			public String getValue(Presentation object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		};
		
		TextColumn<Presentation> movieColumn = new TextColumn<Presentation>() {

			@Override
			public String getValue(Presentation object) {
				// TODO Auto-generated method stub
				return object.getName(); // Jeweils durch ID austauschen
			}
		};
		
		TextColumn<Presentation> cinemaColumn = new TextColumn<Presentation>() {

			@Override
			public String getValue(Presentation object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		};
		
		TextColumn<Presentation> dateColumn = new TextColumn<Presentation>() {

			@Override
			public String getValue(Presentation object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		};
		
		 Cell<String> positiveVoteCell = new ButtonCell();	
			
			Column<Presentation, String> positiveVoteColumn = new Column<Presentation, String>(positiveVoteCell) {

				@Override
				public String getValue(Presentation object) {
					// TODO Auto-generated method stub
					return "X";
				}

			};
			
			 Cell<String> negativeVoteCell = new ButtonCell();	
				
				Column<Presentation, String> negativeVoteColumn = new Column<Presentation, String>(negativeVoteCell) {

					@Override
					public String getValue(Presentation object) {
						// TODO Auto-generated method stub
						return "X";
					}

				};
		
		
		surv.addColumn(nameColumn, "Name");
		surv.addColumn(dateColumn, "Date");
		surv.addColumn(movieColumn, "Movie");
		surv.addColumn(cinemaColumn, "Cinema");
		surv.addColumn(timeslotColumn, "Timeslot");
		surv.addColumn(positiveVoteColumn,"+ Vote");
		surv.addColumn(negativeVoteColumn, "- Vote");

		
		dataProvider.addDataDisplay(surv);
		
		this.add(surv);
		
		final List <Presentation> list = dataProvider.getList();
		for(Presentation group : Umfrageeintrag) {
			list.add(group);
		}
		RootPanel.get().add(surv);

		
		
	}
	
}
