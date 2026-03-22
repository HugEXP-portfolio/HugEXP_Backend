package org.example.hugmeexp.domain.shop.dto.response;


import lombok.*;
import org.example.hugmeexp.domain.shop.entity.Order;
import org.example.hugmeexp.domain.shop.entity.Product;
import org.example.hugmeexp.domain.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponse {

    // 주문자 정보
    private String purchaserName;
    private int remainingPoint;

    // 상품 정보
    private String productName;
    private int productQuantity;

    // 수령자 번호
    private String phoneNumber;

    // 구매 시각
    private LocalDateTime purchaseTime;

    public static PurchaseResponse from(User purchaser, Product product, Order order) {
        return PurchaseResponse.builder()
                .purchaserName(purchaser.getName())
                .remainingPoint(purchaser.getPoint())
                .productName(product.getName())
                .productQuantity(product.getQuantity())
                .phoneNumber(order.getReceiverPhoneNumber())
                .purchaseTime(order.getCreatedAt())
                .build();
    }
}
