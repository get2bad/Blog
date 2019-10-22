package top.tinx.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Artical;
import top.tinx.blog.maaper.ArticalMapper;
import top.tinx.blog.service.ArticalService;
import top.tinx.blog.service.CategoryService;
import top.tinx.blog.service.CommentService;
import top.tinx.blog.service.UserService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticalServiceImpl implements ArticalService {

    @Autowired
    private ArticalMapper articalMapper;
    @Resource
    private RestHighLevelClient client;
    @Autowired
    private RestClient restClient;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;

    private final int FROM = 0;
    private final int SIZE = 100;


    private final String ARTICAL_DOC = "artical_doc";

    @Override
    public void insertArtical(Artical artical) {
        articalMapper.insertArtical(artical);
    }

    @Override
    public List<Artical> getAllJudgeArtical() {
        return articalMapper.getAllJudgeArtical();
    }

    @Override
    public List<Artical> getAllPassArtical(int start,int end) {
        return articalMapper.getAllPassArtical(start,end);
    }

    @Override
    public void backJudgeArtical(String id) {
        articalMapper.backJudgeArtical(id);
    }

    @Override
    public void passArtical(String id) {
        articalMapper.passArtical(id);
    }

    @Override
    public Artical findArticalById(int id) {
        return articalMapper.findArticalById(id);
    }

    @Override
    public int getAllArticalCount() {
        return articalMapper.getAllArticalCount();
    }

    @Override
    public List<Artical> getArticalByConditions(String id, String s, String a) {
        return articalMapper.getArticalByConditions(id,s,a);
    }

    @Override
    public int getAllViewCount() {
        Integer num =  articalMapper.getAllViewCount();
        if(num == null){
            return 0;
        }else{
            return num;
        }
    }

    @Override
    public boolean addArticalIntoES(Artical artical,String id) throws IOException {
        try{
            IndexRequest req = new IndexRequest(ARTICAL_DOC).id(id).source(beanToMap(artical));
            IndexResponse resp = client.index(req, RequestOptions.DEFAULT);
            System.out.println(JSONObject.toJSON(resp));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteArticalByES(String id) throws IOException {
        DeleteByQueryRequest req = new DeleteByQueryRequest(ARTICAL_DOC);
        BulkByScrollResponse resp = client.deleteByQuery(req,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(resp));
        return false;
    }

    @Override
    public boolean updateArticalByEs(Artical artical, String id) throws IOException {
        UpdateRequest req = new UpdateRequest(ARTICAL_DOC,id).doc(beanToMap(artical));
        UpdateResponse update = client.update(req, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(update));
        return false;
    }

    @Override
    public boolean deleteAllArticalByES() throws IOException {
        try{
            Request req = new Request("DELETE",ARTICAL_DOC);
            Response resp =restClient.performRequest(req);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Artical> searchMatch(String key, String value) throws IOException {
        SearchRequest req = new SearchRequest(ARTICAL_DOC);
        SearchSourceBuilder sb = new SearchSourceBuilder();
        sb.query(QueryBuilders.matchQuery(key,value));
        sb.from(FROM);
        sb.size(SIZE);
        HighlightBuilder hb = new HighlightBuilder();
        hb.preTags("<b style='color:red;'>");
        hb.postTags("</b>");
        sb.highlighter(hb);
        req.source(sb);
        SearchResponse resp = client.search(req,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(resp));
        SearchHit[] hits = resp.getHits().getHits();
        List<Artical> all = new ArrayList<Artical>();
        all = getAllArtical(hits);
        return all;
    }

    @Override
    public List<Artical> searchTerm(String key, String value) throws IOException {
        SearchRequest req = new SearchRequest(ARTICAL_DOC);
        SearchSourceBuilder sb = new SearchSourceBuilder();
        sb.query(QueryBuilders.termsQuery(key,value));
        sb.from(FROM);
        sb.size(SIZE);
        HighlightBuilder hb = new HighlightBuilder();
        hb.preTags("<b style='color:red;'>");
        hb.postTags("</b>");
        sb.highlighter(hb);
        req.source(sb);
        SearchResponse resp = client.search(req,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(resp));
        SearchHit[] hits = resp.getHits().getHits();
        List<Artical> all = new ArrayList<Artical>();
        all = getAllArtical(hits);
        return all;
    }

    @Override
    public List<Artical> searchPrefix(String key, String value) throws IOException {
        SearchRequest req = new SearchRequest(ARTICAL_DOC);
        SearchSourceBuilder sb = new SearchSourceBuilder();
        sb.query(QueryBuilders.prefixQuery(key,value));
        sb.from(FROM);
        sb.size(SIZE);
        HighlightBuilder hb = new HighlightBuilder();
        hb.preTags("<b style='color:red;'>");
        hb.postTags("</b>");
        sb.highlighter(hb);
        req.source(sb);
        SearchResponse resp = client.search(req,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(resp));
        SearchHit[] hits = resp.getHits().getHits();
        List<Artical> all = new ArrayList<Artical>();
        all = getAllArtical(hits);
        return all;
    }

    public List<Artical> getAllArtical(SearchHit[] hits){
        List<Artical> all = new ArrayList<Artical>();
        for (SearchHit hit : hits) {
            Artical artical = JSONObject.parseObject(hit.getSourceAsString(), Artical.class);
            getInfos(artical);
            all.add(artical);
            System.out.println("ID为："+artical.getArticalId());
        }
        return all;
    }

    public Artical getInfos(Artical artical){
        artical.setUserName(userService.findUserByUserId(Integer.parseInt(artical.getUserId())).getUserName());
        artical.setCategoryName(categoryService.getCategoryById(artical.getCategoryId()).getCategoryName());
        artical.setArticalCommentCount(commentService.getCommentCountByArticalId(artical.getArticalId()+""));
        return artical;
    }

    @Override
    public String getArticalCache() throws IOException {
        /*
        String method = "GET";
        String endPoints = "/artical_doc/_search";
        Request request = new Request(method,endPoints);
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\":{\n" +
                "    \"match\": {\n" +
                "      \"articalId\": \"12\"\n" +
                "    }\n" +
                "  }\n" +
                "}");
        request.setEntity(entity);
        Response resp = restClient.performRequest(request);
         */
        String json = "";
        List<Artical> list = articalMapper.getAllPassArtical(0, 1000);
        for (Artical artical : list) {
            GetRequest getRequest = new GetRequest(ARTICAL_DOC).id(artical.getArticalId()+"");
            GetResponse get = client.get(getRequest, RequestOptions.DEFAULT);
            json+=JSONObject.toJSONString(get);
        }
        return json;
    }

    @Override
    public void deleteArticalById(String id) {
        articalMapper.deleteArticalById(id);
        commentService.deleteCommentByArticalId(id);
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if(beanMap.get(key) != null)
                    map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
}
