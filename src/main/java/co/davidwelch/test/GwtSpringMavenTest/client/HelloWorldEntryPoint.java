package co.davidwelch.test.GwtSpringMavenTest.client;

import co.davidwelch.test.GwtSpringMavenTest.client.widgets.MultiSelect;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloWorldEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		final MultiSelect m = new MultiSelect();
		
		m.addItem("Blah1");
		m.addItem("blah2");
		m.addItem("blah3");
		m.addItem("blah4");
		m.addItem("blah5");
		m.addItem("blah6");
		m.addItem("blah7");
		m.addItem("blah8");
		m.addItem("blah9");

		
		RootPanel.get().add(m);
	}

}
