package top.tinx.blog.service;

import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Artical;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

public interface ArticalService {

    public void insertArtical(Artical artical);

    public List<Artical> getAllJudgeArtical();

    public List<Artical> getAllPassArtical(int start,int end);

    public void backJudgeArtical(String id);

    public void passArtical(String id);

    public Artical findArticalById(int id);

    public int getAllArticalCount();

    List<Artical> getArticalByConditions(String id,String s,String a);

    public int getAllViewCount();

    public boolean addArticalIntoES(Artical artical,String id) throws IOException;

    public boolean deleteArticalByES(String id)throws IOException;

    public boolean updateArticalByEs(Artical artical,String id)throws IOException;

    public boolean deleteAllArticalByES()throws IOException;

    public List<Artical> searchMatch(String key,String value) throws IOException;

    public List<Artical> searchTerm(String key,String value) throws IOException;

    public List<Artical> searchPrefix(String key,String value) throws IOException;

    public String getArticalCache() throws IOException;

    public void deleteArticalById(String id);

}
