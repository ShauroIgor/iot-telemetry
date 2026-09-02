import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class CheckDB {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/telemetry", "telemetry_admin", "local_dev_password");
            ResultSet rs = conn.getMetaData().getColumns(null, null, "device", null);
            while (rs.next()) {
                System.out.println("Column: " + rs.getString("COLUMN_NAME"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
