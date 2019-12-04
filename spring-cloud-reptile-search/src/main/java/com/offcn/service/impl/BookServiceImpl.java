package com.offcn.service.impl;

import com.offcn.dao.BookMapper;
import com.offcn.domain.Book;
import com.offcn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> list() {
        return bookMapper.selectAll();
    }
}
