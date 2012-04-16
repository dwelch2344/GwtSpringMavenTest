package co.davidwelch.test.GwtSpringMavenTest.client.widgets;


import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class InlineMultiSelect extends Composite implements HasListValue<String>{
	
	private ElementPanel panel = new ElementPanel("span");
	private TextBox textbox = new TextBox();
	private PopupPanel popup = new ProperPopupPanel();
	private MultiSelect multiSelect = new MultiSelect();
	
	public InlineMultiSelect() {
		popup.setAutoHideEnabled(true);
		popup.addAutoHidePartner(textbox.getElement());
		
		
		multiSelect.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				int size = event.getValue().size();
				textbox.setText( size + " selected" );
			}
		});
		popup.add(multiSelect);
		
		
		
		textbox.setText("Make a selection");
		textbox.setReadOnly(true);
		panel.add(textbox);
		panel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popup.showRelativeTo(panel);
			}
		});
		
		initWidget(panel);
	}
	
	public void addItem(String item){
		multiSelect.addOption(item);
	}

	@Override
	public List<String> getValue() {
		return multiSelect.getValue();
	}

	@Override
	public void setValue(List<String> value) {
		multiSelect.setValue(value);
	}

	@Override
	public void setValue(List<String> value, boolean fireEvents) {
		multiSelect.setValue(value, fireEvents);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<List<String>> handler) {
		return multiSelect.addValueChangeHandler(handler);
	}

	public void setOptions(List<String> list) {
		multiSelect.setOptions( list );
	}
	
	
}
