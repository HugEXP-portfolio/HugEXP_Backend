package org.example.hugmeexp.domain.shop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hugmeexp.domain.shop.entity.Product;
import org.example.hugmeexp.domain.shop.entity.ProductImage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String brand;
    private int quantity;
    private int price;
    private String imageUrl;

    // 로그인 사용자가 구매 가능한 상품인지
    private boolean available = false;

    public static ProductResponse from(Product product) {
        ProductResponse response = new ProductResponse();
        response.id = product.getId();
        response.name = product.getName();
        response.brand = product.getBrand();
        response.quantity = product.getQuantity();
        response.price = product.getPrice();
        response.imageUrl = toImageUrl(product.getProductImage());
        return response;
    }

    static String toImageUrl(ProductImage image) {
        if (image == null) return null;
        String fullPath = image.getPath() + "/" + image.getUuid() + "." + image.getExtension();
        if (fullPath.startsWith("/application")) {
            return fullPath.substring("/application".length());
        }
        return fullPath;
    }
}
