package de.hdm.itprojekt.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.itprojekt.shared.bo.Group;


public class GroupCell extends AbstractCell<Group> {
	@Override
	public void render(Context context, Group group, SafeHtmlBuilder sb) {
		
		if(group == null) {
			return ;
		}
		
		sb.appendHtmlConstant("<div>");
	    sb.appendHtmlConstant("<div style=\"size:200%;font-weight:bold;\">");
		sb.appendEscaped(group.getName());
		sb.appendHtmlConstant("</div>");
	    sb.appendHtmlConstant("<div style=\"padding-left:100px;\">");
        sb.appendHtmlConstant("<input type=\"button\" />");
        sb.appendHtmlConstant("<input type=\"button\" />");


	}
}
