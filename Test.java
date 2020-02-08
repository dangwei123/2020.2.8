package com.edu;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * ClassName:Operation
 * Package:com.edu
 * Description:
 *
 * @Date:2020/2/8 9:01
 * @Author:DangWei
 */
public class Operation {
    private static final String URL = "jdbc:mysql://localhost/course";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static DataSource getDataSource() {
        MysqlDataSource ds=new MysqlDataSource();
        ds.setUrl(URL);
        ds.setPassword(PASSWORD);
        ds.setUser(USER);
        return ds;
    }
    public  static void insert(Student student){
        DataSource ds=getDataSource();
        Connection connection=null;
        PreparedStatement stemt=null;

        try {
            connection=ds.getConnection();
            String sql="insert into student values(?,?,？)";
            stemt=connection.prepareStatement(sql);
            stemt.setInt(1,student.getId());
            stemt.setString(2,student.getName());
            stemt.setInt(3,student.getClass_id());

            stemt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stemt!=null){
                stemt.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void select(int id){
        DataSource ds=getDataSource();
        Connection connection=null;
        PreparedStatement stemt=null;
        ResultSet res=null;
        try {
            connection=ds.getConnection();
            String sql="select * from student where id=?";
            stemt=connection.prepareStatement(sql);
            stemt.setInt(1,id);
            res=stemt.executeQuery();
            while(res.next()){
                Student student=new Student();
                student.setId(res.getInt("id"));
                student.setName(res.getString("name"));
                student.setClass_id(res.getInt("class_id"));
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(res!=null){
                res.close();
            }
            if(stemt!=null){
                stemt.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


public class Student {
    private int id;
    private String name;
    private int class_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", class_id=" + class_id +
                '}';
    }
}


public class Test {
    public static void main(String[] args) {
        Student stu1=new Student();
        stu1.setId(4);
        stu1.setName("张三");
        stu1.setClass_id(2);
        Operation op=new Operation();
        //op.insert(stu1);
        select(2);
    }
}
