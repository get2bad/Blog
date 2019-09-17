package top.tinx.blog.service;

import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Artical;

import java.util.List;

public interface ArticalService {

    public void insertArtical(Artical artical);

    public List<Artical> getAllJudgeArtical();

    public List<Artical> getAllPassArtical();

    public void backJudgeArtical(String id);

    public void passArtical(String id);
}
