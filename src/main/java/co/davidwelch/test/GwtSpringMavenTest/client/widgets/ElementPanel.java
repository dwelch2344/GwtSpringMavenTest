package co.davidwelch.test.GwtSpringMavenTest.client.widgets;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class ElementPanel extends ComplexPanel implements HasMouseOverHandlers, HasMouseOutHandlers, HasClickHandlers {

	/**
	 * 
	 * @param elem the base element of your custom panel. .
	 * 
	 * Common ways of getting elements include:
	 *    Document.get().create*Element()
	 *    Document.get().createElement("tag")
	 *    DOM.create*()
	 *    DOM.createElement("tag")
	 */
	public ElementPanel(Element elem) {
		setElement(elem);
	}
	
	@UiConstructor
	public ElementPanel(String tagName) {
		this(Document.get().createElement(tagName));
	}
	
	@Override
	public void add(Widget w) { 
		super.add(w, getElement()); 
	}
	
	/**
	 * ensures that widget is inserted to THIS element maintaining same order in the container as well as on the DOM
	 * @param w
	 * @param beforeIndex
	 */
	public void insert(Widget w, int beforeIndex) { 
		super.insert(w, getElement(), beforeIndex, true); 
	}
	
	/**
	 * set the id of the underlying element
	 * @param id
	 */
	public void setId(String id)
    {
        getElement().setId(id);
    }
	
	 /**
	  * convenience method to set the style/class name on underlying element
	  * this adds support for more familiar attributes in UiBinder
	  * e.g. <x:IxPanel class="myclass" />
	  * @param className
	  */
	public void setClass(String className) {
		setStyleName(className);
	}
	
	/**
	 * this method DOES NOT set the className, but rather the style attribute. 
	 * Do not confuse it with setClass() or setStyleName()
	 * this is mainly used for more familiar attributes in UiBinder
	 * @param value the string to set in the style attribute. e.g. "width:50%;padding:10px;"
	 */
	public void setStyle(String value) {
		this.getElement().setAttribute("style", value);
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
	
	public void setProperty(String property, Object value) {
		getElement().setPropertyObject(property, value);
	}
	
	public Object getProperty(String property) {
		return getElement().getPropertyObject(property);
	}
}
