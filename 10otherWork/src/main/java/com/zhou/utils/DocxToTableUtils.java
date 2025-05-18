package com.zhou.utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.util.List;

/**
 * @author zhou
 * @since 2023/10/30
 * description:
 */
public class DocxToTableUtils {
    public static void main(String[] args) {
        String path = "C:\\Users\\BJB-314\\Desktop\\附件 保护对象9.22完整版(1).docx";
        try {
            // 读取.docx文件
            FileInputStream fileInputStream = new FileInputStream(path);
            XWPFDocument document = new XWPFDocument(fileInputStream);

//            // 连接到MySQL数据库
//            String url = "jdbc:mysql://localhost:3306/your_database";
//            String user = "your_username";
//            String password = "your_password";
//            Connection connection = DriverManager.getConnection(url, user, password);

            // 创建INSERT语句
//            String insertQuery = "INSERT INTO your_table (field1, field2, field3) VALUES (?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            List<XWPFTable> tables = document.getTables();
            // 遍历表格数据并插入到数据库
            for (int i = 0; i < tables.size(); i++) {
                switch (i) {
                    case 0:
                        XWPFTable table = tables.get(i);
                        List<XWPFTableRow> rows = table.getRows();
                        // 从1开始 忽略表头
                        for (int j = 1; j < rows.size(); j++) {
                            XWPFTableRow row = rows.get(j);
                            // 假设表格中有3列（field1, field2, field3）
                            String field1 = row.getCell(0).getText();
                            String field2 = row.getCell(1).getText();
                            String field3 = row.getCell(2).getText();
                            String insertQuery = "INSERT INTO your_table (field1, field2, field3) VALUES (?, ?, ?)";
//                    preparedStatement.setString(1, field1);
//                    preparedStatement.setString(2, field2);
//                    preparedStatement.setString(3, field3);
//                    preparedStatement.executeUpdate();
                        }
                        break;
                    default:
                        break;
                }

            }

//            // 关闭数据库连接
//            preparedStatement.close();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
