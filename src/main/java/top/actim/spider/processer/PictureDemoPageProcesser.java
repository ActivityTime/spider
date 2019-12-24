package top.actim.spider.processer;

import top.actim.spider.config.MyDefaultConfig;
import top.actim.spider.pipeline.FileBytesPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PictureDemoPageProcesser implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("fileBytes", page.getBytes());

        // 部分三：从页面发现后续的url地址来抓取
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new PictureDemoPageProcesser())
                //从"https://"开始抓
                .addUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=447784230,1193748659&fm=26&gp=0.jpg")
                //用json保存到文件
                .addPipeline(new FileBytesPipeline(MyDefaultConfig.cachePath))
                //开启5个线程抓取
                //.thread(5)
                //启动爬虫
                .run();
    }
}
