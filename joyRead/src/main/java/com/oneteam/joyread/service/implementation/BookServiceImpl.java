package com.oneteam.joyread.service.implementation;

import com.oneteam.joyread.dao.BookDAO;
import com.oneteam.joyread.dao.UserbookDAO;
import com.oneteam.joyread.entity.Book;
import com.oneteam.joyread.entity.Userbook;
import com.oneteam.joyread.entity.User;
import com.oneteam.joyread.exception.ServiceException;
import com.oneteam.joyread.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDAO bookDAO;

    @Autowired
    UserbookDAO userbookDAO;

    @Autowired
    UserServiceImpl userService;

    @Override
    public void addOrUpdate(Book book) {
        bookDAO.save(book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookDAO.findByIsbn(isbn);
    }

    @Override
    public List<Book> getRecommendedBooks(int userId) {
        return bookDAO.getRecommendedBooks();
    }

    @Override
    public List<Book> findBooksByIsbn(List<String> isbns) {
        List<Book> result = new ArrayList<Book>();
        for (int i=0; i<isbns.size(); i++) {
            Book book = bookDAO.findByIsbn(isbns.get(i));
            result.add(book);
        }
        return result;
    }

    @Override
    public Userbook getUserbook(int userId, String isbn) {
        User user = userService.getById(userId);
        if (user == null) {
            System.out.println("User doesn't exist");
            return null;
        }
        if(isbn==null){
            System.out.println("Isbn is invalid");
            return null;
        }
        System.out.println("ServiceImpl Success!");
        return userbookDAO.findAllByUserIdAndIsbn(userId, isbn);
    }

    @Override
    public List<Userbook> getAllUserbook(int userId, int status) {
        User user = userService.getById(userId);
        if (user == null) {
            System.out.println("User doesn't exist");
            return null;
        }
        if(status==2||status==3||status==4){
            List<Userbook> userbooks =userbookDAO.findAllByUserIdAndStatus(userId, status);
            System.out.println("ServiceImpl Success!");
            return userbooks;
        }
        else {
            System.out.println("Status is invalid");
            return null;
        }
    }

    @Override
    public void addOrUpdate(Userbook userbook) {
        userbookDAO.save(userbook);
    }

    @Override
    public List<Book> searchName(String words) {
        return bookDAO.findAllByNameLike(words);
    }

    @Override
    public List<Book> searchWriter(String words) {
        return bookDAO.findAllByWriterLike(words);
    }

    @Override
    public List<Userbook> getAll(int userId) {
        User user = userService.getById(userId);
        if (user == null) {
            System.out.println("User doesn't exist");
            return null;
        }
        System.out.println("BookServiceImpl Success!");
        return userbookDAO.findAllByUserId(userId);
    }
}
