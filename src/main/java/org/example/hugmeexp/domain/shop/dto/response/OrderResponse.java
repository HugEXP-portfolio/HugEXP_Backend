package org.example.hugmeexp.domain.shop.dto.response;

import lombok.*;
import org.example.hugmeexp.domain.shop.entity.Order;
import org.example.hugmeexp.domain.shop.entity.Product;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private String imageUrl;
    private String brand;
    private String name;
    private String price;
    private LocalDateTime orderTime;
    private String receiverPhoneNumber;

    public static OrderResponse from(Order order) {
        Product product = order.getProduct();
        return OrderResponse.builder()
                .imageUrl(ProductResponse.toImageUrl(product.getProductImage()))
                .brand(product.getBrand())
                .name(product.getName())
                .price(String.format("%d 포인트", product.getPrice()))
                .orderTime(order.getCreatedAt())
                .receiverPhoneNumber(order.getReceiverPhoneNumber())
                .build();
    }
}
