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
                System.out.println("����������û���:");
                String user = sc.nextLine();
                System.out.println("�������������:");
                String password = sc.nextLine();
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("���ݿ����ӳɹ���");
                break; // �ɹ����Ӻ��˳�ѭ��
            } catch (SQLException e) {
                System.err.println("���ݿ�����ʧ��: " + e.getMessage());
                System.out.println("�����������û��������롣");
            }
        }//ʹ��ѭ����ʵ����������Ĺ���

        try {
            //2.2�Ĳ�ѯ
            while(true) {
                System.out.println("������Ҫ��ѯ��ѧ������");
                String name = sc.nextLine();
                PreparedStatement prepared = connection.prepareStatement("select ID,name,dept_name,tot_cred from student where name like ? ");
                String input = "%" + name + "%";
                prepared.setString(1, input);//Ϊռλ����ֵ,ͬʱʵ��ģ����ѯ
                ResultSet resultSet = prepared.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("�޸�ѧ����Ϣ");
                    continue;
                }
                //���ѧ����Ϣ
                do {
                    int sid = resultSet.getInt(1);
                    String sname = resultSet.getString(2);
                    String dname = resultSet.getString(3);
                    int tot = resultSet.getInt(4);
                    System.out.println(sid + "\t" + sname + "\t" + dname + "\t" + tot);
                } while (resultSet.next());
                break;

            }
            //2.3�Ĳ�ѯ
            Boolean tag = true;
            label1:
            while(tag) {
               System.out.println("������ѧ��id");
               int id = sc.nextInt();
               sc.nextLine();
               PreparedStatement prepared2 = connection.prepareStatement("select * from student where id = ? ");
               prepared2.setInt(1, id);
               ResultSet resultSet2 = prepared2.executeQuery();
               if(!resultSet2.next()) {
                System.out.println("�޸�ѧ���ļ�¼");
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


                //2.4�Ĳ�ѯ

                System.out.println("������1���鿴��ѧ�������пγ�,����0��ֹͣ���β�ѯ");
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
                    //2.5�Ĳ�ѯ
                    System.out.println("������1���鿴��ѧ����ƽ���ɼ�������0��ֹͣ��ѯ");
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

            // �ر�����


        } catch (Exception e) {
            System.err.println("���ݿ��쳣: " + e.getMessage());
        } finally {
            // �ر�Scanner����
            if (sc != null) {
                sc.close();
            }
        }
    }
}
