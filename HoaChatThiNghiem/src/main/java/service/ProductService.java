package service;

import db.DbConnection;
import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final String QUERY_PRODUCTS = "SELECT p.id_product, p.name_product, p.description_product, " +
            "p.url_img_product, p.star_review, sp.name_status_product, p.quantity_product," +
            "pp.listed_price, pp.current_price, tp.name_type_product FROM products p " +
            "JOIN price_product pp ON p.id_product = pp.id_product " +
            "JOIN status_product sp on p.id_status_product = sp.id_status_product " +
            "JOIN type_product tp on p.id_type_product = tp.id_type_product";

    public static List<Product> getProducts() {
        List<Product> products;
        try (Statement st = DbConnection.getInstall().getStatement()) {
            ResultSet rs = st.executeQuery(QUERY_PRODUCTS);
            products = getProducts(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public static Product getProductById(String id) {
        Product product;
        try (PreparedStatement ps = DbConnection.getInstall().getPreparedStatement(QUERY_PRODUCTS + " WHERE p.id_product=?")) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            product = getProducts(rs).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    private static List<Product> getProducts(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_product");
            String name = rs.getString("name_product");
            String desc = rs.getString("description_product");
            String imgPath = rs.getString("url_img_product");
            int stars = rs.getInt("star_review");
            String status = rs.getString("name_status_product");
            int quantity = rs.getInt("quantity_product");
            double oldPrice = rs.getInt("listed_price");
            double newPrice = rs.getInt("current_price");
            String type = rs.getString("name_type_product");
            Product product = new Product(id, imgPath, name, stars, status, desc, quantity, type, oldPrice, newPrice);
            products.add(product);
        }
        return products;
    }
}
