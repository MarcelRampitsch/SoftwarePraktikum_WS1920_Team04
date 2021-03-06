package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Vote;

public class UmfragenTable extends VerticalPanel {
	
	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>EditorAdministration</code>.
	   */
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

	User user = null;
	List<SurveyEntry> eintrag;
	
	
	List<SafeHtml> CellData = new Vector<SafeHtml>();
	Survey s = null;
	
	Button back = new Button("back");
	Button secondVote = new Button("Start the second vote round");
	Label selectedSurveyLabel = new Label("Selected survey:");
	Label name = new Label();
	Button deleteSurvey = new Button("delete");
	HorizontalPanel surveyPanel = new HorizontalPanel();
	UmfragenCell2 cell = new UmfragenCell2();
	CellList<SafeHtml> list = new CellList<SafeHtml>(cell);
	VerticalPanel main = new VerticalPanel();
	Vote v = new Vote();
	
	Vector <SurveyEntry> entrys = new Vector <SurveyEntry>();
	
	public UmfragenTable() {
	}
	
	public UmfragenTable(User user, List<SurveyEntry> eintrag, Survey s) {
		this.user = user;
		this.eintrag = eintrag;	
//		Window.alert(""+eintrag.size());
		this.s = s;
		for(SurveyEntry se : this.eintrag) {
			new PrepareSurveyEntry(se, CellData, this, user);
			
		}
	}
	
	 //erstellen des Data Provider
	   ListDataProvider<SurveyEntry> Umfrageeintrag = new ListDataProvider<SurveyEntry>();
	
	
	   /*
		 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
		 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
		 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
		 */
	public void onLoad() {
		super.onLoad();
		name.setText(s.getName());
		main.add(back);
		main.add(selectedSurveyLabel);
		selectedSurveyLabel.addStyleName("text");
		back.addStyleName("backButtonStyle");
		surveyPanel.add(name);
		if(user.getId()==s.getUserID()) {
			surveyPanel.add(deleteSurvey);
		}
		deleteSurvey.addStyleName("deleteButtonStyle");
		deleteSurvey.addClickHandler(new deleteSurveyHandler());
		main.add(surveyPanel);
		secondVote.addClickHandler(new secondVoteHandler());
		if(s.getRound()==1 && user.getId()==s.getUserID()) {
		main.add(secondVote);
		secondVote.addStyleName("newButtonStyle");
		}
		main.add(list);
		back.addClickHandler(new backHandler());
		this.add(main);
//		Window.alert("" + eintrag.get(0).getId());
		list.setRowCount(CellData.size(), true);
		list.setRowData(0, CellData);
	}
	
	public ListDataProvider<SurveyEntry> getDataProvider() {
 		return this.Umfrageeintrag;
 	}
	
	
	//ClickHandler mit dem man auf die EditorForm zurückgelangt 
	private class backHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm form = new EditorForm(user);
			RootPanel.get().add(form);
		}
	}
	
	private class deleteSurveyHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			editorAdministration.deleteSurveyBySurveyID(s, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Void result) {
					RootPanel.get().clear();
					EditorForm form = new EditorForm(user);
					RootPanel.get().add(form);
				}
			});
		}
	}
	
	private class secondVoteHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			for (int i = 0; i < eintrag.size(); i++) {
			entrys.add(eintrag.get(i));
			}
			editorAdministration.secondVoteRound(entrys, new AsyncCallback<Vector<SurveyEntry>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<SurveyEntry> result) {
					main.clear();
					s.setRound(2);
					UmfragenTable form = new UmfragenTable(user, result, s);
					main.add(form);
				}
			});
		}
	}
	
	public void updateTable() {		
			list.setRowCount(CellData.size(), true);
			list.setRowData(0, CellData);
	}
	
	
	class UmfragenCell2 extends AbstractCell<SafeHtml> {

		public UmfragenCell2() {
		      /*
		       * Sink the click and keydown events. We handle click events in this
		       * class. AbstractCell will handle the keydown event and call
		       * onEnterKeyDown() if the user presses the enter key while the cell is
		       * selected.
		       */
		      super("click");
		    }
		
		@Override
		public void render(Context context, SafeHtml value, SafeHtmlBuilder sb) {
			// TODO Auto-generated method stub
			sb.append(value);
		}

		@Override
		public void onBrowserEvent(Context context, Element parent, SafeHtml value, NativeEvent event,
				ValueUpdater<SafeHtml> valueUpdater) {
			super.onBrowserEvent(context, parent, value, event, valueUpdater);
			// Handle the click event.
			if ("click".equals(event.getType())) {
				// Ignore clicks that occur outside of the outermost element.
				EventTarget eventTarget = event.getEventTarget();
				consoleLog(Element.as(eventTarget).toString());
				if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget)) && ButtonElement.is(eventTarget)) {
					NodeList<Element> nodes = parent.getFirstChildElement().getElementsByTagName("input");
					int surveyEntryID = Integer.parseInt(parent.getFirstChildElement().getAttribute("data-seid"));
					int userID = Integer.parseInt(parent.getFirstChildElement().getAttribute("data-userid"));
//					consoleLog(nodes.getItem(0).getAttribute("checked"));
//					if(nodes.getItem(0).hasAttribute("checked")) {
					consoleLog(nodes.getItem(0).toString());
					if(InputElement.as(nodes.getItem(0)).isChecked()) {
						// +1
						Vote v = new Vote(surveyEntryID,userID,1);
						doAction(v);
					}
					if(InputElement.as(nodes.getItem(1)).isChecked()) {	
						// -1
						Vote v = new Vote(surveyEntryID,userID,-1);
						doAction(v);
					}
						
					
					
				}
				//
			}
		}

		private void doAction(Vote v) {
			consoleLog("Vote "+v.getVoteResult());
			
			editorAdministration.createVote(v, new AsyncCallback<Vote>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vote result) {
					main.clear();
					UmfragenTable form = new UmfragenTable(user,eintrag,s);
					main.add(form);
				}
				
			});
			
		}
		
		native void consoleLog( String message) /*-{
	      console.log( "me:" + message );
	  	}-*/;
	}
}
