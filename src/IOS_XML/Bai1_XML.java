package IOS_XML;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class Bai1_XML {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục: ");
        String directoryPath = scanner.nextLine();

        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            try {
                FileWriter writer = new FileWriter("directory_structure.xml");
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<" + directory.getName() + ">\n");
                listDirectory(directory, writer, 1);
                writer.write("</" + directory.getName() + ">");
                writer.close();
                System.out.println("Xong! Kiểm tra file directory_structure.xml");
            } catch (IOException e) {
                System.err.println("Lỗi khi tạo hoặc ghi vào file!");
                e.printStackTrace();
            }
        } else {
            System.err.println("Đường dẫn không tồn tại hoặc không phải là thư mục!");
        }
    }

    private static void listDirectory(File directory, FileWriter writer, int depth) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    writer.write(getTab(depth) + "<" + file.getName() + ">\n");
                    listDirectory(file, writer, depth + 1);
                    writer.write(getTab(depth) + "</" + file.getName() + ">\n");
                } else {
                    writer.write(getTab(depth) + "<file>" + file.getName() + "</file>\n");
                }
            }
        }
    }

    private static String getTab(int depth) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            tabs.append("\t");
        }
        return tabs.toString();
    }
}