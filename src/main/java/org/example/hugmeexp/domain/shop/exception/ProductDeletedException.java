package org.example.hugmeexp.domain.shop.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class ProductDeletedException extends BaseException {
    public ProductDeletedException() {
        super(ErrorStatus.PRODUCT_DELETED);
    }
}
