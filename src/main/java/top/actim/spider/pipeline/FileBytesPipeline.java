package top.actim.spider.pipeline;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileBytesPipeline extends FilePipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String type = "file";

    public FileBytesPipeline(String path) {
        super.path = path;
    }

    public FileBytesPipeline(String path, String type) {
        super.path = path;
        this.type = type;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        byte[] fileBytes = resultItems.get("fileBytes");
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        PrintStream printWriter = null;

        String url = resultItems.getRequest().getUrl();
        int i = url.lastIndexOf(".");
        String fileType = type;
        if (i >= 0) {
            fileType = url.substring(i);
        }

        try {
            printWriter = new PrintStream(new FileOutputStream(getFile(path + DigestUtils.md5Hex(url) + fileType)));
            printWriter.write(fileBytes);
        } catch (IOException e) {
            logger.warn("write file error", e);
        } finally {
            printWriter.close();
        }


//        Request request = resultItems.getRequest();
//        String url = request.getUrl();
//        String host = url.split("/")[2];
//        String[] words = host.split("\\.");
//        String filePath = path;
//        for (String word : words) {
//            filePath += "\\" + word;
//        }
//        filePath += "\\" + new Date().getTime() + url.substring(url.lastIndexOf("."));
//        File file = new File(filePath);
//        File fileParent = file.getParentFile();
//        if(!fileParent.exists()){
//            fileParent.mkdirs();
//        }
//
//        try {
//            file.createNewFile();
//            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, true));
//            outputStream.write(fileBytes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
