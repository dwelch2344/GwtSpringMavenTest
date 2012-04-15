package co.davidwelch.test.GwtSpringMavenTest.client.widgets;

import com.google.gwt.user.client.ui.Composite;

public class CheckBox extends Composite{
	
	private ElementPanel panel = new ElementPanel("input");
	
	public CheckBox() {
		panel.getElement().setAttribute("type", "checkbox");
		initWidget(panel);
	}
	
	public void setChecked(boolean checked){
		if(checked){
			panel.getElement().setAttribute("checked", "true");
		}else{
			panel.getElement().removeAttribute("checked");
		}
	}
	
	public boolean isChecked(){
		String val = panel.getElement().getAttribute("checked");
		return !( val == null || val.trim().isEmpty() );
	}

}
