package co.davidwelch.test.GwtSpringMavenTest.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MultiSelect extends Composite{

	private static MultiSelectUiBinder uiBinder = GWT.create(MultiSelectUiBinder.class);

	interface MultiSelectUiBinder extends UiBinder<Widget, MultiSelect> {
	}

	@UiField HTMLPanel panel;
	@UiField Button noneButton, allButton;
	@UiField TextBox filter;
	
	public MultiSelect() {
		Widget w = uiBinder.createAndBindUi(this);
		initWidget(w);
	}
	
	@UiHandler({"allButton", "noneButton"})
	void checkAll(ClickEvent e){
		boolean value;
		if(e.getSource() == noneButton){
			value = false;
		}else if(e.getSource() == allButton ){
			value = true;
		}else{
			throw new IllegalStateException("Unreachable Code");
		}
		
		for(Widget w : panel){
			if( w instanceof Entry){
				Entry f = (Entry) w;
				f.checkbox.setChecked( value );
			}
		}
	}
	
	@UiHandler("filter")
	void something( KeyUpEvent event){
		for(Widget w : panel){
			if( w instanceof Entry){
				Entry f = (Entry) w;
				boolean contains = f.label.getText().toLowerCase().contains( filter.getText().toLowerCase() );
				f.setVisible(contains);
			}
		}
	}	

	
	public void addItem(String item){
		Entry f = new Entry(item);
		panel.add(f);
	}
	
	public static class Entry extends Composite{
		
		private CheckBox checkbox;
		private Label label;
		
		public Entry(String name) {
			HTMLPanel p = new HTMLPanel("");
			checkbox = new CheckBox();
			label = new InlineLabel(name);
			label.addClickHandler(new ClickHandler() {
				
				public void onClick(ClickEvent arg0) {
					checkbox.setChecked( !checkbox.isChecked() );
				}
			});
			
			p.add(checkbox);
			p.add(label);
			initWidget(p);
		}
		
	}

}
