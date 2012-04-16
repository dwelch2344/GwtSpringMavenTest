package co.davidwelch.test.GwtSpringMavenTest.client.widgets;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;

public class CheckBox extends Composite implements HasValue<Boolean>{
	
	private InputElement element = InputElement.as( Document.get().createElement("input") );
	private ElementPanel panel = new ElementPanel(element);
	
	public CheckBox() {
		element.setAttribute("type", "checkbox");
		initWidget(panel);
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType() );
	}

	@Override
	public Boolean getValue() {
		return element.isChecked();
	}

	@Override
	public void setValue(Boolean value) {
		setValue(value, true);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		if (value == null) {
	      value = Boolean.FALSE;
	    }

	    Boolean oldValue = getValue();
	    element.setChecked(value);
	    element.setDefaultChecked(value);
	    if (value.equals(oldValue)) {
	      return;
	    }
	    if (fireEvents) {
	      ValueChangeEvent.fire(this, value);
	    }
	}

}
