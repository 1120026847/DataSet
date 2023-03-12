import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
// 创建一个空的json数组
        JsonArray jsonArray = new JsonArray();

// 创建一个变量来记录id属性的值
        int id = 1;

// 创建一个用于读取文本文件的缓冲读取器
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            // 读取文本文件中的每一行
            String line;
            while ((line = br.readLine()) != null) {
                // 创建一个包含id和word属性的json对象
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("id", id);
                jsonObject.add("word", line);
                // 将json对象添加到json数组中
                jsonArray.add(jsonObject);

                // 将id属性的值加一
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

// 将json数组写入一个文件
        try (FileWriter file = new FileWriter("output.json")) {
            file.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}