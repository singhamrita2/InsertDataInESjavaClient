import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IndexingInsertdata {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));

/*		
        //Creating New Index into ElasticSearch
	    CreateIndexRequest request = new CreateIndexRequest("sampleindex1");
        request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 2));
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("response id: " + createIndexResponse.index());
        
        
          
        //  At a time Indexing /Inserting data to ElasticSearch
        //1. Way 1 –> Inserting a Sample String Value     
        IndexRequest indexRequest = new IndexRequest("sampleindex2");
        indexRequest.id("001");
        indexRequest.source("SampleKey","SampleValue");
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id: "+indexResponse.getId());
        System.out.println("response name: "+indexResponse.getResult().name());


        
        //  At a time Indexing /Inserting data to ElasticSearch
        //2. Way 2 –> Inserting a Map data
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("keyOne", 10); 
        map.put("keyTwo", 30);
        map.put("KeyThree", 20); 
        //here indexing new index
        IndexRequest indexRequest2 = new IndexRequest("amrita");
        indexRequest2.id("001");
        indexRequest2.source(map);
        IndexResponse indexResponse2 = client.index(indexRequest2, RequestOptions.DEFAULT);
        System.out.println("response id: "+indexResponse2.getId());
        System.out.println("response name: "+indexResponse2.getResult().name());
        
*/        
        
        //  At a time Indexing /Inserting data to ElasticSearch
        //3. Way 3 –> Inserting POJO mappings data
        EmployeePojo emp = new EmployeePojo("Amy", "Sinh",  LocalDate.now());
        
        IndexRequest indexRequest3 = new IndexRequest("lucky");
        indexRequest3.id("500");
        //indexRequest3.id("400");// only the last one data insert at a time
        indexRequest3.source(new ObjectMapper().writeValueAsString(emp), XContentType.JSON);
        IndexResponse indexResponse3 = client.index(indexRequest3, RequestOptions.DEFAULT);
        System.out.println("response id: "+indexResponse3.getId());
        System.out.println("response name: "+indexResponse3.getResult().name());

	}

}
