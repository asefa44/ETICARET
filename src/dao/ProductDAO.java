package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import util.DBConnection;  // DBConnection import ediliyor

public class ProductDAO {

    // Tüm ürünleri listeleyen metot
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";  // Ürünleri getirecek SQL sorgusu

        try (Connection conn = DBConnection.getConnection(); // Burada getConnection kullanılıyor
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Sonuçları işleyerek ürünleri listeye ekliyoruz
            while (rs.next()) {
                Product product = new Product.ProductBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setDescription(rs.getString("description"))
                        .setPrice(rs.getDouble("price"))
                        .setStock(rs.getInt("stock"))
                        .build();
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    // ID'ye göre bir ürün getiren metot
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product.ProductBuilder()
                            .setId(rs.getInt("id"))
                            .setName(rs.getString("name"))
                            .setDescription(rs.getString("description"))
                            .setPrice(rs.getDouble("price"))
                            .setStock(rs.getInt("stock"))
                            .build();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }


    // Yeni ürün ekleyen metot
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, description, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); // Burada da getConnection kullanılıyor
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());

            pstmt.executeUpdate(); // Veritabanına yeni ürün ekliyoruz

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ürün silme işlemi
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); // Burada da getConnection kullanılıyor
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate(); // Ürünü veritabanından siliyoruz

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
