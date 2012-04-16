package co.davidwelch.test.GwtSpringMavenTest.client;

import java.util.ArrayList;
import java.util.List;

import co.davidwelch.test.GwtSpringMavenTest.client.widgets.InlineMultiSelect;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloWorldEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		
		List<String> list = new ArrayList<String>();
		
		list.add("Blah1");
		list.add("blah2");
		list.add("blah3");
		list.add("blah4");
		list.add("blah5");
		list.add("blah6");
		list.add("blah7");
		list.add("blah8");
		list.add("blah9");
		
		

		final InlineMultiSelect m = new InlineMultiSelect();
		m.setOptions(list);
		
		
		RootPanel.get().add(m);
	}

}
