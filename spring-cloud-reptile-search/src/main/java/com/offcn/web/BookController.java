package com.offcn.web;

import com.offcn.domain.Book;
import com.offcn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@RestController
@RequestMapping(value = "/api/search")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/findAll")
    public List<Book> findAll(){

        return bookService.list();
    }
}
