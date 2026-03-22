package org.example.hugmeexp.domain.shop.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(Long productId) {
        super(ErrorStatus.PRODUCT_NOT_FOUND, productId);
    }
}
