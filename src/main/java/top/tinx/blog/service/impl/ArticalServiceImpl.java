package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Artical;
import top.tinx.blog.maaper.ArticalMapper;
import top.tinx.blog.service.ArticalService;

import java.util.List;

@Service
public class ArticalServiceImpl implements ArticalService {

    @Autowired
    private ArticalMapper articalMapper;

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
        return articalMapper.getAllViewCount();
    }


}
