package de.hdm.itprojekt.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.itprojekt.shared.bo.Group;

// Eine neue Cell erstellen die AbstractCell erweitert
public class GroupCell extends AbstractCell<Group> {

	@Override
	public void render(Context context, Group group, SafeHtmlBuilder sb) {
		// Wenn Group leer ist dann wird methode beendet
		if(group == null) {
			return ;
		}
		// on Browser event
		// Erstellung der Cell mit Gruppenname und zwei Buttons
		sb.appendHtmlConstant("<div>");
	    sb.appendHtmlConstant("<div style=\"size:200%;font-weight:bold;\">");
		sb.appendEscaped(group.getName());
		sb.appendHtmlConstant("</div>");
	    sb.appendHtmlConstant("<div style=\"padding-left:100px;\">");
        sb.appendHtmlConstant("<input type=\"button\" />");
        sb.appendHtmlConstant("<input type=\"button\" />");
	}
}
