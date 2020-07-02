package com.oneteam.joyread.service;

import com.oneteam.joyread.entity.Book;
import com.oneteam.joyread.entity.Userbook;

import java.util.List;

public interface BookService {

    //添加图书
    public void addOrUpdate(Book book);

    //查询图书(isbn)
    public Book findBookByIsbn(String isbn);

    public List<Book> findBooksByIsbn(List<String> isbns);

    //获取推荐图书
    public List<Book> getRecommendedBooks(int userId);

    //获取用户图书记录
    public Userbook getUserbook(int userId, String isbn);

    //获取用户某状态的所有图书记录
    public List<Userbook> getAllUserbook(int userId, int status);

    //更新用户图书
    public void addOrUpdate(Userbook userbook);

    //搜索
    public List<Book> searchName(String words);

    public List<Book> searchWriter(String words);

    //获取所有用户图书
    public List<Userbook> getAll(int userId);
}
