package com.mycompany.writer;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.mycompany.Geoname;

@Component
public class GeonameElasticIndexer implements ItemWriter<Geoname>,InitializingBean, DisposableBean {

	private Client client;

	private int nbWrite = 0;
	

	public void write(List<? extends Geoname> geonames)
			throws Exception {
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (Geoname geoname : geonames) {
			nbWrite++;
			bulkRequest.add(client.prepareIndex("geonames", "geoname",
					Integer.toString(nbWrite)).setSource(
					jsonBuilder()
							.startObject()
							.field("id", nbWrite)
							.field("geonameId", geoname.getGeonameId())
							.field("name", geoname.getName())
							.field("asciiname", geoname.getAsciiname())
							.field("alternatenames", geoname.getAlternatenames())
							.field("latitude", geoname.getLatitude())
							.field("longitude", geoname.getLongitude())
							.field("featureClass", geoname.getFeatureClass())
							.field("featureCode", geoname.getFeatureCode())
							.field("countryCode", geoname.getCountryCode())
							.field("cc2", geoname.getCc2())
							.field("admin1Code", geoname.getAdmin1Code())
							.field("admin2Code", geoname.getAdmin2Code())
							.field("admin3Code", geoname.getAdmin3Code())
							.field("admin4Code", geoname.getAdmin4Code())
							.field("population", geoname.getPopulation())
							.field("elevation", geoname.getElevation())
							.field("dem", geoname.getDem())
							.field("timezone", geoname.getTimezone())
							.field("modificationDate", geoname.getModificationDate())
							.endObject()));
		}
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			throw new RuntimeException(bulkResponse.buildFailureMessage());
		}
	}


	public void destroy() throws Exception {
		client.close();
	}

	public void afterPropertiesSet() throws Exception {
		client = new TransportClient()
		.addTransportAddress(new InetSocketTransportAddress("localhost",
				9300));
	}

}
