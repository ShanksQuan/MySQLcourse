package lab2;
import java.sql.*;
import java.util.Scanner;

public class JDBCProject {
    public static void main(String[] args)  {
        String url = "jdbc:mysql://localhost:3306/college";
        Scanner sc = new Scanner(System.in);
        Connection connection = null;

        while (true) {
            try {
                System.out.println("请输入你的用户名:");
                String user = sc.nextLine();
                System.out.println("请输入你的密码:");
                String password = sc.nextLine();
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("数据库连接成功！");
                break; // 成功连接后退出循环
            } catch (SQLException e) {
                System.err.println("数据库连接失败: " + e.getMessage());
                System.out.println("请重新输入用户名和密码。");
            }
        }//使用循环来实现重新输入的功能

        try {
            //2.2的查询
            while(true) {
                System.out.println("请输入要查询的学生姓名");
                String name = sc.nextLine();
                PreparedStatement prepared = connection.prepareStatement("select ID,name,dept_name,tot_cred from student where name like ? ");
                String input = "%" + name + "%";
                prepared.setString(1, input);//为占位符赋值,同时实现模糊查询
                ResultSet resultSet = prepared.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("无该学生信息");
                    continue;
                }
                //输出学生信息
                do {
                    int sid = resultSet.getInt(1);
                    String sname = resultSet.getString(2);
                    String dname = resultSet.getString(3);
                    int tot = resultSet.getInt(4);
                    System.out.println(sid + "\t" + sname + "\t" + dname + "\t" + tot);
                } while (resultSet.next());
                break;

            }
            //2.3的查询
            Boolean tag = true;
            label1:
            while(tag) {
               System.out.println("请输入学生id");
               int id = sc.nextInt();
               sc.nextLine();
               PreparedStatement prepared2 = connection.prepareStatement("select * from student where id = ? ");
               prepared2.setInt(1, id);
               ResultSet resultSet2 = prepared2.executeQuery();
               if(!resultSet2.next()) {
                System.out.println("无该学生的记录");
                continue;
                }
               else{
                do {
                int sid= resultSet2.getInt(1);
                String sname = resultSet2.getString(2);
                String dname = resultSet2.getString(3);
                int tot = resultSet2.getInt(4);
                System.out.println(sid + "\t" + sname + "\t" + dname + "\t" + tot);
                }  while (resultSet2.next());


                //2.4的查询

                System.out.println("请输入1来查看该学生的所有课程,输入0则停止本次查询");
                int key1 = sc.nextInt();
                sc.nextLine();
                if (key1 == 1) {
                    PreparedStatement prepared3 = connection.prepareStatement(
                            "SELECT t.course_id, t.year, t.semester, c.title, c.dept_name, t.grade, c.credits " +
                                    "FROM takes t " +
                                    "JOIN course c ON t.course_id = c.course_id " +
                                    "WHERE t.id = ?"
                    );
                    prepared3.setInt(1,id);
                    ResultSet resultSet3 = prepared3.executeQuery();
                    while (resultSet3.next()) {
                        int tid= resultSet3.getInt("course_id");
                        int year= resultSet3.getInt("year");
                        String semester= resultSet3.getString("semester");
                        String title= resultSet3.getString("title");
                        String deptName= resultSet3.getString("dept_name");
                        String grade= resultSet3.getString("grade");
                        int credits= resultSet3.getInt("credits");
                        System.out.printf("%d\t%d\t%s\t%s\t%s\t%s\t%d\n", tid, year, semester, title, deptName, grade, credits);
                    }
                    //2.5的查询
                    System.out.println("请输入1来查看该学生的平均成绩，输入0则停止查询");
                    int key2 = sc.nextInt();
                    sc.nextLine();
                    if (key2 == 1) {
                        PreparedStatement prepared4 = connection.prepareStatement("SELECT " + " AVG(g.grade_point) AS average_gpa " + " FROM" + " takes t " +
                                " JOIN " + " gpa g ON t.grade = g.grade " + " WHERE " + " t.id = ?; ");
                        prepared4.setInt(1,id);
                        ResultSet resultSet4 = prepared4.executeQuery();
                        while (resultSet4.next()) {
                            Double gpa= resultSet4.getDouble("average_gpa");
                            System.out.println("average_gpa: "+gpa);
                        }
                        tag = false;

                    }
                    else if (key2 == 0) {
                        tag = false;
                        break label1;
                    }

                }
                else if (key1 == 0) {
                    tag = false;
                    break label1;
                }

            }
            }

            // 关闭连接


        } catch (Exception e) {
            System.err.println("数据库异常: " + e.getMessage());
        } finally {
            // 关闭Scanner对象
            if (sc != null) {
                sc.close();
            }
        }
    }
}
