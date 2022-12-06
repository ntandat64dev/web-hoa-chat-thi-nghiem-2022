package controller.shop;

import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "details", urlPatterns = "/shop/product-details")
public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("product_id");
        if (id != null) {
            Product product = ProductService.getProductById(id);
            if (product != null) {
                List<Product> relatedProducts = ProductService.getProducts().stream()
                        .filter(p -> p.getType().equals(product.getType()))
                        .limit(6).collect(Collectors.toList());
                req.setAttribute("related_products", relatedProducts);
                req.setAttribute("product", product);
                req.getRequestDispatcher("product-details.jsp").forward(req, resp);
            }
        }
    }
}
