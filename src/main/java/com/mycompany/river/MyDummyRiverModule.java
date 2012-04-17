package com.mycompany.river;

import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.river.River;

public class MyDummyRiverModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(River.class).to(MyDummyRiver.class).asEagerSingleton();
	}

}
