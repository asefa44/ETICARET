package util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHelper {
    public static void testConnection() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Veritabanına başarıyla bağlanıldı.");
            } else {
                System.out.println("❌ Veritabanı bağlantısı başarısız.");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Hata: " + e.getMessage());
        }
    }
}
