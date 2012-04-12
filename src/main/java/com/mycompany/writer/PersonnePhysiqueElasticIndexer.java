package com.mycompany.writer;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.mycompany.PersonnePhysique;

@Component
public class PersonnePhysiqueElasticIndexer implements ItemWriter<PersonnePhysique> {

    private Client client;
    
    private int nbWrite = 0;


    public void write(List<? extends PersonnePhysique> personnePhysiques) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (PersonnePhysique personnePhysique : personnePhysiques) {
            nbWrite++;
            bulkRequest.add(client.prepareIndex("personne", "personnephysique", Integer.toString(nbWrite))
                    .setSource(jsonBuilder().startObject()
                            .field("id", nbWrite)
                            .field("nom", personnePhysique.getNom())
                            .field("prenom", personnePhysique.getPrenom())
                            .field("codePostal", personnePhysique.getCodePostal())
                            .field("pays", personnePhysique.getPays())
                            .field("dateNaissance", personnePhysique.getDateNaissance()).endObject()
                    ));

        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            throw new RuntimeException(bulkResponse.buildFailureMessage());
        }
    }


    public void setClient(Client client) {
        this.client = client;
    }
}

