/*
 * The MIT License (MIT)
 *
 * Copyright © 2020 xrv <xrg@live.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.ehlxr.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class TestJdbc26 {
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://111.235.158.26:3306/wins-dsp-new?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
        String username = "root";
        String password = "pxene";
        Connection conn = null;
        try {
            Class.forName(driver); // classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        insertData();
        //		updateData();
    }

    private static void insertData() {
        Connection conn = getConn();
        System.out.println(new Date());
        for (int i = 0; i > -1; i++) {
            String cid = UUID.randomUUID().toString();
            String sql = "INSERT INTO `dsp_t_statis_by_day` (`time`, `creativeid`, `category`, `imprs`, `clks`, `cost`, `downloads`, `regists`, `flag`, `createtime`) VALUES ('2014-12-06 00:00:00', '" + cid + "', '2', '961', '9', '201860.7000', '0', '0', '0', '2015-09-14 15:07:42');";
            PreparedStatement pstmt;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                if (i % 200 == 0) {
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(new Date());
    }

    private static void updateData() {
        Connection conn = getConn();
        System.out.println(new Date());
        for (int i = 0; i < 800; i++) {
            String sql = "UPDATE `dsp_t_statis_by_day` SET `clks`='" + i + "' WHERE `creativeid`='068860ba-2df2-42cb-bf66-f0515c5a83aa'";
            PreparedStatement pstmt;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new Date());
    }
}
