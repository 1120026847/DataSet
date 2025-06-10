import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextToJsonConverter {
    public static void main(String[] args) {
        try {
            // 1. 读取input.txt文件内容
            List<String> words = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) { // 跳过空行
                        words.add(line.trim());
                    }
                }
            }

            // 2. 构建JSON结构
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rootNode = mapper.createObjectNode();
            rootNode.put("code", 0);
            rootNode.put("msg", "ok");
            
            ArrayNode dataArray = mapper.createArrayNode();
            for (int i = 0; i < words.size(); i++) {
                ObjectNode itemNode = mapper.createObjectNode();
                itemNode.put("id", i + 1);  // ID从1开始递增
                itemNode.put("word", words.get(i));
                dataArray.add(itemNode);
            }
            rootNode.set("data", dataArray);

            // 3. 写入output.json文件
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File("output.json"), rootNode);
            
            System.out.println("转换成功！文件已保存为 output.json");

        } catch (Exception e) {
            System.err.println("转换失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
