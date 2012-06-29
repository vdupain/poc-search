package com.mycompany.writer;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.util.List;

import org.elasticsearch.ExceptionsHelper;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.mycompany.Geoname;

@Component
public class GeonameElasticIndexer implements ItemWriter<Geoname>,InitializingBean, DisposableBean {

	private Client client;

	private int nbWrite = 0;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GeonameElasticIndexer.class);
 
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
							.startObject("location").field("lat", geoname.getLatitude()).field("lon", geoname.getLongitude()).endObject()
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
//			throw new RuntimeException(bulkResponse.buildFailureMessage());
		    LOGGER.error(bulkResponse.buildFailureMessage());
		}
	}


	public void destroy() throws Exception {
		client.close();
	}

	public void afterPropertiesSet() throws Exception {
		Settings settings = ImmutableSettings.settingsBuilder()
                //.put("cluster.name", "pocprod")
//                .put("discovery.zen.ping.multicast.group", " 225.66.26.77")
//                .put("discovery.zen.ping.multicast.port:", "54328")
//                .put("discovery.zen.ping.multicast.address", "10.220.181.57")
                .build();
		client = new TransportClient(settings)
		.addTransportAddress(new InetSocketTransportAddress("localhost",
				9300));
		try {
			String mapping = XContentFactory.jsonBuilder().startObject().startObject("geoname")
	                .startObject("properties").startObject("location").field("type", "geo_point").field("lat_lon", true).endObject().endObject()
	                .endObject().endObject().string();
			client.admin().indices().prepareCreate("geonames").addMapping("geoname", mapping).execute().actionGet();
	        client.admin().cluster().prepareHealth().setWaitForGreenStatus().execute().actionGet();
		} catch (Exception ex) {
			 if (ExceptionsHelper.unwrapCause(ex) instanceof IndexAlreadyExistsException) {
	                // that's fine
	            } else if (ExceptionsHelper.unwrapCause(ex) instanceof ClusterBlockException) {
	                // ok, not recovered yet..., lets start indexing and hope we recover by the first bulk
	                // TODO: a smarter logic can be to register for cluster event listener here, and only start sampling when the block is removed...
	            } else {
	                throw ex;
	            }
		}

	}
}
