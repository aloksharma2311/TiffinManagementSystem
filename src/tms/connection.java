    package tms;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.Statement;

    public class connection {
        Connection c;
        Statement s;
        connection(){
            String username = "root";
            String password = "alok2311";
            String url = "jdbc:mysql://localhost:3306/tms";
            try{
                c = DriverManager.getConnection(url, username,password);
                s = c.createStatement();
                System.out.println("Connection successfull!!");

            }catch (Exception exception){
                System.out.println("Connection failed!!");
            }
        }

    }

