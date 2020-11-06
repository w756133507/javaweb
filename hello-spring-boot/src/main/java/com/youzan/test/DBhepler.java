package com.youzan.test;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBhepler {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://172.16.6.5:3306/mytest";
        Connection con = null;
        ResultSet res = null;

        public void DataBase() {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, "用户名", "密码");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                System.err.println("装载 JDBC/ODBC 驱动程序失败。");
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.err.println("无法连接数据库");
                e.printStackTrace();
            }
        }

        // 查询
        public ResultSet Search(String sql, String str[]) {
            DataBase();
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                if (str != null) {
                    for (int i = 0; i < str.length; i++) {
                        pst.setString(i + 1, str[i]);
                    }
                }
                res = pst.executeQuery();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return res;
        }


        //增删修改
        public int AddU(String sql, String str[]) {
            int a = 0;
            DataBase();
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                if (str != null) {
                    for (int i = 0; i < str.length; i++) {
                        pst.setString(i + 1, str[i]);
                    }
                }
                a = pst.executeUpdate();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return a;
        }

    public static void main(String[] args) {
        List<OrderDetailTemp> list = new ArrayList<OrderDetailTemp>();
        String file="C://Users//PC//Desktop//11.xls";
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    String itemCd = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列
                    // 所以这里得j++
                    String price = rs.getCell(j++, i).getContents();
                    list.add(new OrderDetailTemp(itemCd, price));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //得到表格中所有的数据
          /*//得到数据库表中所有的数据
          List<StuEntity> listDb=StuService.getAllByDb();*/
        DBhepler db=new DBhepler();
        for (OrderDetailTemp userInfo : list) {
            String id=userInfo.getItem_cd();
            String price=userInfo.getPrice();
            String sql="insert into order_detail_temp (item_cd,price)";
            String[] str=new String[]{id,price};
            db.AddU(sql, str);
        }
      }
}