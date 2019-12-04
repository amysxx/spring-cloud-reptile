package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.dao.ArticleMapper;
import com.example.domain.Article;
import com.example.service.ArticleService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author 侯叶飞
 * @date 2019/12/3
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int add(Article article) {
        return articleMapper.insert(article);
    }
    //遍历起始页数据
    @Override
    public List<Article> list() {
        int page=1;
        List<Article> list = new ArrayList<>();
        while (page<=5) {
            String url = "https://pacaio.match.qq.com/irs/rcd?cid=146&token=49cbb2154853ef1a74ff4e53723372ce&ext=ent&page="+page+"&num=5";
            //向爬取的首页发送请求
            Map<String,Object> map = restTemplate.getForObject(url, Map.class);
            if (map == null) {
                return null;
            }
            Object data = map.get("data");
            if (data == null) {
                break;
            }
            List<Map> maps = JSON.parseArray(JSON.toJSONString(data), Map.class);
            for (Map<String,String> map1 : maps) {
                Object id = map1.get("id");
                //先判断文章的ID是否存在，如果存在，跳过
                ValueOperations<String, String> ops = redisTemplate.opsForValue();
                String value = ops.get("article:"+id);
                if (value != null) {
                    continue;
                }
                //解析详情页数据
                Article article = getArticle(map1);
                System.out.println("正在抓取：****************************************************************");
                System.out.println(article);
                System.out.println("抓取【"+article.getId()+"】完成：****************************************************************");
                //进行爬取到的数据存储
                list.add(article);

                //为了提升效率所以采用Redis存储
                ops.set("article:"+id,JSON.toJSONString(article));
            }
           page++;
        }

        return list;
    }



    //解析详情页内容，下载图片，将数据封装成对应的Java对象
    private Article getArticle(Map<String,String> map){
        Article article = new Article();
        article.setId(map.get("id"));
        article.setIntro(map.get("intro"));
        article.setKeywords(map.get("keywords"));
        article.setTitle(map.get("title"));
        //向文章详情页发送请求，获取文章详情页的内容，文字和图片
        String template = restTemplate.getForObject("https://new.qq.com/rain/a/" + article.getId(), String.class);
        //解析HTML，获取文章内容和图片
        Document document = Jsoup.parse(template);
        //文章内容
        Element content = document.select(".content-article").get(0);
        //获取当前详情页的图片
        List<String> image = getImage(content);

        article.setContent(content.html());
        //以字符串的方式存储
        String str = Arrays.toString(image.toArray());

        article.setImgs(str.substring(1,str.length()-1));

        return article;
    }
    /**
     * 下载图片，同时保存图片名称
     * @param content 文章内容的Element
     * @return 图片名称的集合
     */
    private List<String> getImage(Element content){
        //获取图片
        Elements imgs = content.select(".content-picture");
        //图片的集合
        List<String> imgList = new ArrayList<>();

        for (Element element : imgs) {
            //获取img标签的src属性值
            String img = "http:"+element.attr("src");

            byte[] bytes = restTemplate.getForObject(img, byte[].class);
            //给图片重新命名
            String fileName= UUID.randomUUID() + ".png";

            String path = "D:\\imgs\\" + fileName;

            try {
                FileUtils.writeByteArrayToFile(new File(path),bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //重新设置img标签的src属性值

            element.attr("src", path);

            //只保存图片名称
            imgList.add(fileName);
        }

        return imgList;
    }
}
