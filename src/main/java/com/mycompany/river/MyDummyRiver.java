package com.mycompany.river;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.river.AbstractRiverComponent;
import org.elasticsearch.river.River;
import org.elasticsearch.river.RiverName;
import org.elasticsearch.river.RiverSettings;

public class MyDummyRiver extends AbstractRiverComponent implements River {

	@Inject
	public MyDummyRiver(RiverName riverName, RiverSettings settings) {
		super(riverName, settings);
		logger.info("create");
	}

	public void start() {
		logger.info("start");
	}

	public void close() {
		logger.info("close");
	}
}
