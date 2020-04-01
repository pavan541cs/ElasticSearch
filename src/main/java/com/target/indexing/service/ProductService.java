package com.target.indexing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.indexing.document.ProductDocument;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.target.indexing.utils.Constant.INDEX;
import static com.target.indexing.utils.Constant.TYPE;

@Service
public class ProductService {
    private RestHighLevelClient client;
    private ObjectMapper mapper;

    @Autowired
    public ProductService(RestHighLevelClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public String createProductDocument(ProductDocument document) throws Exception {
        IndexRequest indexRequest = new IndexRequest(INDEX,TYPE, document.getProduct_id())
                .source(convertProductDocumentToMap(document));

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getResult().name();
    }

    public ProductDocument findById(String id) throws Exception {
        GetRequest getRequest = new GetRequest(INDEX,TYPE,id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();
        return convertMapToProductDocument(resultMap);
    }

    public String updateProduct(ProductDocument productDocument) throws Exception {
        ProductDocument resultDocument = findById(productDocument.getProduct_id());
        UpdateRequest updateRequest = new UpdateRequest(INDEX,TYPE,resultDocument.getProduct_id());
        updateRequest.doc(resultDocument);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        return updateResponse.getResult().name();
    }

    public List<ProductDocument> findAll() throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.types(TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return getSearchResult(searchResponse);
    }

    public List<ProductDocument> findProductBySellerId(String sellerId) throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.types(TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("sellerId",sellerId).operator(Operator.AND));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return getSearchResult(searchResponse);
    }

    public String deleteProductDocument(String id) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX,TYPE,id);
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse.getResult().name();
    }

    private Map<String, Object> convertProductDocumentToMap(ProductDocument productDocument) {
        return mapper.convertValue(productDocument, Map.class);
    }

    private ProductDocument convertMapToProductDocument(Map<String, Object> map) {
        return mapper.convertValue(map, ProductDocument.class);
    }

    private List<ProductDocument> getSearchResult(SearchResponse searchResponse) {
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<ProductDocument> productDocuments = new ArrayList<>();
        Arrays.stream(searchHits).forEach(hit -> productDocuments
                        .add(mapper.convertValue(
                                hit.getSourceAsMap(), ProductDocument.class
                        ))
        );
        return productDocuments;
    }

    private SearchRequest buildSearchRequest(String index, String type) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.searchType(type);
        return searchRequest;
    }
}
