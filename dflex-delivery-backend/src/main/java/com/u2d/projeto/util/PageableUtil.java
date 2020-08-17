package com.u2d.projeto.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class PageableUtil {

    private static final String PAGE = "page";
    private static final String SIZE = "size";

    public static Pageable getPageableParans(Map<String, String[]> params, Sort.Direction direction, String orderBy) {
        int page = Integer.parseInt(RequestUtil.extrairParametro(params, PAGE));
        int size = Integer.parseInt(RequestUtil.extrairParametro(params, SIZE));
        return PageRequest.of(page, size, direction, orderBy);
    }
}
