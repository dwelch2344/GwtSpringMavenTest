package co.davidwelch.test.GwtSpringMavenTest.client.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * A {@link PopupPanel} implementation that *ACTUALLY* hides when actions occur outside of it. 
 * GWT's implementation is old and full of bugs. 
 * @author dwelch
 *
 */
public class ProperPopupPanel extends PopupPanel {

    private HandlerRegistration handler;
    protected List<Element> autoHidePartners = new ArrayList<Element>();

    public ProperPopupPanel() {
		super();
		setStyleName( GX.PREFIX + "popup");
	}
    
    @Override
    public void hide() {
        super.hide();
        removeHandlers();
    }

    @Override
    public void show() {
        super.show();
        addHandlers();
    }
    
    @Override
    public void addAutoHidePartner(Element partner) {
    	super.addAutoHidePartner(partner);
    	autoHidePartners.add(partner);
    }
    
    @Override
    public void removeAutoHidePartner(Element partner) {
    	super.removeAutoHidePartner(partner);
    	autoHidePartners.remove(partner);
    }
    
    private void addHandlers() {
		handler = Event.addNativePreviewHandler(new NativePreviewHandler() {
			
			@Override
			public void onPreviewNativeEvent(NativePreviewEvent npEvent) {
				NativeEvent nativeEvent = npEvent.getNativeEvent();
				if(nativeEvent instanceof Event) {
					Event event = (Event) nativeEvent;
					EventTarget target = event.getEventTarget();
					
					switch(event.getTypeInt()) {
					
					case(Event.ONMOUSEUP):
						if (!Element.is(target) || !getElement().isOrHasChild(Element.as(target))) {
							Element targetElement = target.cast();
							// make sure that the element clicked is NOT the element (or a partner)
							if(targetElement != getElement() && !autoHidePartners.contains(targetElement)) {
					            if (isModal()) { // Stop event if the popup is modal
					            	nativeEvent.preventDefault();
					            }
					            autohidePopup();
							}
				        }
						break;
						
					case(Event.ONKEYUP):
						if( event.getKeyCode() == KeyCodes.KEY_TAB ){
							autohidePopup();
						}
						break;
					}
				}
			}
		});
    }
    
    private void autohidePopup() {
        if (isAutoHideEnabled()) {
            hide(true);
            removeHandlers();
        }
    }
    
    private void removeHandlers() {
    	if(handler != null) {
    		handler.removeHandler();
    		handler = null;
    	}
    }
    
}
