package co.davidwelch.test.GwtSpringMavenTest.client.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
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

public class MultiSelect extends Composite implements HasListValue<String>{

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
	void doCheckAllAction(ClickEvent e){
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
				if( value != f.checkbox.isChecked() ){
					f.checkbox.toggle();
				}
			}
		}
	}
	
	@UiHandler("filter")
	void doFilter( KeyUpEvent event){
		for(Widget w : panel){
			if( w instanceof Entry){
				Entry f = (Entry) w;
				boolean contains = f.label.getText().toLowerCase().contains( filter.getText().toLowerCase() );
				f.setVisible(contains);
			}
		}
	}	

	
	public void addOption(String item){
		addOption(item, false);
	}
	
	protected void addOption(String item, boolean checked){
		Entry f = new Entry(item);
		if(checked){
			f.checkbox.toggle();
		}
		panel.add(f);
	}
	
	public void setOptions(List<String> value){
		panel.clear();
		if( value != null){
			for(String s : value){
				addOption(s);
			}
		}
	}
	
	@Override
	public List<String> getValue() {
		List<String> results = new ArrayList<String>();
		
		for( Widget w : panel ){
			if( w instanceof Entry ){
				Entry entry = (Entry) w;
				if(entry.checkbox.isChecked()){
					results.add( entry.label.getText() );
				}
			}
		}
		
		return results;
	}

	@Override
	public void setValue(List<String> value) {
		setValue(value, true);
	}

	@Override
	public void setValue(List<String> values, boolean fireEvents) {
		for(String value : values){
			for( Widget w : panel ){
				if( w instanceof Entry ){
					Entry entry = (Entry) w;
					if( entry.label.getText().equals(value) && !entry.checkbox.isChecked() ){
						entry.checkbox.toggle();
						break;
					}
				}
			}
			// we didn't find and break, so we need to add a new option and check it
			addOption(value, true);
		}
		ValueChangeEvent.fire(this, values);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<List<String>> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	
	private class Entry extends Composite{
		
		private ValueCheckBox<String> checkbox;
		private Label label;
		
		public Entry(String name) {
			HTMLPanel p = new HTMLPanel("");
			checkbox = new ValueCheckBox<String>(name);
			checkbox.addValueChangeHandler(new ValueChangeHandler<String>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					List<String> values = MultiSelect.this.getValue();
					ValueChangeEvent.fire( MultiSelect.this, values );
				}
			});
			
			
			label = new InlineLabel(name);
			label.addClickHandler(new ClickHandler() {
				
				public void onClick(ClickEvent arg0) {
					checkbox.toggle();
				}
			});
			
			p.add(checkbox);
			p.add(label);
			initWidget(p);
		}
		
	}
}
