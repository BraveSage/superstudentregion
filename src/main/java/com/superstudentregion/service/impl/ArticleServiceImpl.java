package com.superstudentregion.service.impl;

import com.superstudentregion.bean.ArticleInfo;
import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.constant.Constants;
import com.superstudentregion.constant.FilePath;
import com.superstudentregion.exception.ArticleException;
import com.superstudentregion.exception.UserException;
import com.superstudentregion.mapper.ArticleMapper;
import com.superstudentregion.service.ArticleService;
import com.superstudentregion.service.UserInfoService;
import com.superstudentregion.stuenum.StateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private static Logger log = LoggerFactory.getLogger(ArticleService.class);
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserInfoService userInfoService;

    public ArticleServiceImpl() {
    }

    @Transactional
    public int insertArticle(ArticleInfo articleInfo, MultipartFile articleByHtml, MultipartFile articleByXml) {
        //判断用户的权限
        this.userAuthority(articleInfo.getUserId());
        //获取文件名称
//        String htmlFileName = articleByHtml.getOriginalFilename();
//        String xmlFileName = articleByXml.getOriginalFilename();
//        //设置文章存储路径
//        String htmlFilePath = FilePath.ARTICLE_FILE_PATH_PREFIX + articleInfo.getUserId() + "/" + "html/" + htmlFileName;
//        String xmlFilePath = FilePath.ARTICLE_FILE_PATH_PREFIX + articleInfo.getUserId() + "/" + "xml/" + xmlFileName;
//        File uploadHtmlFile = new File(htmlFilePath);
//        File uploadXmlFile = new File(xmlFilePath);
//        uploadFile(articleByHtml, uploadHtmlFile);
//        uploadFile(articleByXml, uploadXmlFile);
//        //设置存入文件服务器的映射路径
//        String htmlArticlePath =FilePath.ARTICLE_PATH_PREFIX + articleInfo.getUserId() + "/" + "html/" + htmlFileName;
//        String xmlArticlePath =FilePath.ARTICLE_PATH_PREFIX + articleInfo.getUserId() + "/" + "xml/" + xmlFileName;

        String htmlArticlePath = this.optionFile(articleByHtml, "html", articleInfo);
        String xmlArticlePath = this.optionFile(articleByXml, "md", articleInfo);
        articleInfo.setArticleHtmlPath(htmlArticlePath);
        articleInfo.setArticleMdPath(xmlArticlePath);
        int i = this.articleMapper.insertArticle(articleInfo);
        return i;
    }

    //操作文件的同时返回文章映射路径
    public String optionFile(MultipartFile file, String type, ArticleInfo articleInfo){
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //设置文章存储路径
        String filePath = FilePath.ARTICLE_FILE_PATH_PREFIX + articleInfo.getUserId() + "/" + type + "/" + fileName;
        //上传文件
        File uploadFile = new File(filePath);
        uploadFile(file, uploadFile);
        //返回文章映射路径
        String articlePath = FilePath.ARTICLE_PATH_PREFIX + articleInfo.getUserId() + "/" + type + "/" + fileName;
        return articlePath;
    }

    @Transactional
    public int deleteArticle(ArticleInfo articleInfo) {
        return 0;
    }

    @Transactional
    public int updateArticle(ArticleInfo articleInfo,MultipartFile articleByHtml, MultipartFile articleByXml) {
        try {
            //判断用户权限
            this.userAuthority(articleInfo.getUserId());

            String fileNameByHtml = articleByHtml.getOriginalFilename();
            String filePathByHtml =FilePath.ARTICLE_PATH_PREFIX + articleInfo.getUserId() + "/" + "html/" + fileNameByHtml;
            File uploadFile = new File(filePathByHtml);
            articleByHtml.transferTo(uploadFile);
            int i = this.updateArticle(articleInfo);
            return i;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Transactional
    public int updateArticle(ArticleInfo articleInfo) {
        articleInfo.setSubmitTime(new Date());
        int i = this.articleMapper.updateArticle(articleInfo);
        return i;
    }

    public void likeArticle(Integer articleId) {
    }

    public ArticleInfo browseArticle(Integer articleId) {
        ArticleInfo articleInfo = this.articleMapper.selectArticleById(articleId);
        if (!articleInfo.getStateFlag().equals(StateEnum.NORMAL.getValue())) {
            throw new ArticleException("该文章处于冻结状态，无法进行查看");
        } else {
            return articleInfo;
        }
    }

    public List<ArticleInfo> allArticleByUser(Integer userId) {
        List<ArticleInfo> allArticleByUser = this.articleMapper.selectAllArticleByUser(userId);
        return allArticleByUser;
    }

    public List<String> uploadArticlePic(MultipartFile[] pictures, Integer userId) {
        this.userAuthority(userId);
        List<String> list = new ArrayList();
        MultipartFile[] var4 = pictures;
        int var5 = pictures.length;

        for(int i = 0; i < var5; ++i) {
            MultipartFile picture = var4[i];
            String picturePath = uploadPic(picture, userId);
            list.add(picturePath);
        }

        return list;
    }

    private static String uploadPic(MultipartFile picture, Integer userId) {
        try {
            String fileName = picture.getOriginalFilename();
            String filePath = FilePath.ARTICLE_PATH_PREFIX + userId + "/" + System.currentTimeMillis() + fileName;
            String articlePath =FilePath.ARTICLE_PATH_PREFIX + userId + "/" + System.currentTimeMillis() + fileName;
            File uploadFile = new File(filePath);
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }

            picture.transferTo(uploadFile);
            uploadFile.createNewFile();
            return articlePath;
        } catch (IOException e) {
            log.error("上传文章图片失败失败", e);
            throw new UserException(Constants.RESP_STATUS_INTERNAL_ERROR, "上传文章图片失败失败");
        }
    }

    void userAuthority(Integer userId) {
        UserInfo userInfo = this.userInfoService.selectInfoById(userId);
        if (!userInfo.getStateFlag().equals(StateEnum.NORMAL.getValue())) {
            throw new UserException(Constants.RESP_STATUS_INTERNAL_ERROR, "该用户处于未激活状态或冻结状态，因此无法进行操作");
        }
    }

    static void uploadFile(MultipartFile article, File uploadFile) {
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }

        try {
            article.transferTo(uploadFile);
            uploadFile.createNewFile();
        } catch (IOException e) {
            log.error("上传文章失败", e);
            throw new ArticleException("上传文章失败");
        }
    }
}
