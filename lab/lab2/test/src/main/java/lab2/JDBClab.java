package lab2;

import org.junit.Test;

import java.sql.*;
import java.math.BigDecimal;

public class JDBClab {
    @Test
    public void JDBCCreate() throws Exception {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        try {
            String sql = "CREATE TABLE employee (id INT," +
                         " name VARCHAR(20) NOT NULL, " +
                            " age INT NOT NULL, " +
                            " address VARCHAR(50), " +
                            " salary REAL, " +
                            "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }finally{
            statement.close();
            connection.close();
        }

        System.out.println("Create table successfully");
    }
    @Test
    public void JDBCInsert() throws Exception {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        try {
            String[] insertSQL = {
                "INSERT INTO employee VALUES (1, 'Gong', 48, '2075 Kongjiang Road', 20000.00 );",
                "INSERT INTO employee VALUES (2, 'Luan', 25, '3663 Zhongshan Road(N)', 15000.00 );",
                "INSERT INTO employee VALUES (3, 'Hu', 23, '3663 Zhongshan Road(N)', 15000.00 );",
                "INSERT INTO employee VALUES (4, 'Jin', 24, '3663 Zhongshan Road(N)', 15000.00 );",
                "INSERT INTO employee VALUES (5, 'Yi', 24, '3663 Zhongshan Road(N)', 15000.00 );"
            };
            for (String sql : insertSQL) {
                statement.executeUpdate(sql);
            }
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }finally {
            statement.close();
            connection.close();
        }
        System.out.println("Insert data successfully");
    }
    @Test
    public void JDBCSelect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        //Œ™¡À∑¿÷π◊¢»Îπ•ª˜ π”√‘§±‡“Î”Ôæ‰
        PreparedStatement preparedStatement = connection.prepareStatement("select * from employee");
        ResultSet resultSet = preparedStatement.executeQuery();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double salary = resultSet.getDouble("salary");
                System.out.println(id + " " + name + " " + age + " " + address + " " + salary);
            }
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }finally{
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
    }
    @Test
    public void JDBCUpdate() throws Exception {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        //Œ™¡À∑¿÷π◊¢»Îπ•ª˜ π”√‘§±‡“Î”Ôæ‰

        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE employee set SALARY = 50000.00 where ID=1;");
            PreparedStatement preparedStatement2 = connection.prepareStatement("select * from employee");
            ResultSet resultSet = preparedStatement2.executeQuery();
            int i = preparedStatement1.executeUpdate();
            if(i < 0){
                System.out.println("Update failed");
            }
            else System.out.println("Update successful");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double salary = resultSet.getDouble("salary");
                System.out.println(id + " " + name + " " + age + " " + address + " " + salary);

                resultSet.close();
                preparedStatement1.close();
                preparedStatement2.close();
            }
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }finally {
            connection.close();
        }
    }
    @Test
    public void JDBCDelete() throws Exception {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        //Œ™¡À∑¿÷π◊¢»Îπ•ª˜ π”√‘§±‡“Î”Ôæ‰
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE from employee where ID=2;");
            PreparedStatement preparedStatement2 = connection.prepareStatement("select * from employee");
            int i = preparedStatement1.executeUpdate();
            ResultSet resultSet = preparedStatement2.executeQuery();
            if(i < 0){
                System.out.println("delete failed");
            }
            else System.out.println("delete successful");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                double salary = resultSet.getDouble("salary");
                System.out.println(id + " " + name + " " + age + " " + address + " " + salary);
                // Õ∑≈◊ ‘¥
                resultSet.close();
                preparedStatement1.close();
                preparedStatement2.close();
            }
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }finally {
            connection.close();
        }
    }
    @Test
    public void JDBCPrepare() throws Exception {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement1 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS GPA(grade CHAR(2), grade_point DECIMAL(3,2));");
        preparedStatement1.executeUpdate();
        try {
            String[] strArray = new String[11];
            strArray[0] = "A";
            strArray[1] = "A-";
            strArray[2] = "B+";
            strArray[3] = "B";
            strArray[4] = "B-";
            strArray[5] = "C+";
            strArray[6] = "C";
            strArray[7] = "C-";
            strArray[8] = "D";
            strArray[9] = "D-";
            strArray[10] = "F";
            double[] doubleArray = new double[] {4.0,3.7,3.3,3.0,2.7,2.3,2.0,1.5,1.3,1.0,0};
            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO GPA(grade, grade_point) VALUES (?,?)");
            for(int i = 0;i < 11; i++){
                preparedStatement2.setString(1, strArray[i]);
                preparedStatement2.setBigDecimal(2,BigDecimal.valueOf(doubleArray[i]));
                preparedStatement2.addBatch();
            }
            preparedStatement2.executeBatch();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }finally{
            preparedStatement1.close();
            connection.close();
        }
    }
}
