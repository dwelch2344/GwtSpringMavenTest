package co.davidwelch.test.GwtSpringMavenTest.client.widgets;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;

public class ValueCheckBox<T> extends Composite implements HasValue<T>{

	private CheckBox checkbox = new CheckBox();
	private T value;
	
	public ValueCheckBox(T value) {
		super();
		initWidget(checkbox);
		checkbox.addValueChangeHandler(handler);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
	
	// TODO feels dirty
	public boolean isChecked(){
		return checkbox.getValue();
	}
	// TODO feels really dirty
	public void toggle(){
		checkbox.setValue( !checkbox.getValue() );
	}
	

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		setValue(value, true);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		this.value = value;
		if(fireEvents){
			ValueChangeEvent.fire(this, value);
		}
	}
	
	private ValueChangeHandler<Boolean> handler = new ValueChangeHandler<Boolean>() {
		private T value;
		
		@Override
		public void onValueChange(ValueChangeEvent<Boolean> event) {
			if( event.getValue() ){
				ValueCheckBox.this.setValue(value, true);
				this.value = null;
			}else{
				this.value = ValueCheckBox.this.getValue();
				ValueCheckBox.this.setValue(null, true);
			}
		}
	};
}
