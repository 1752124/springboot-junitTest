package com.oneteam.joyread.service.implementation;

import com.oneteam.joyread.dao.BooklistBookDAO;
import com.oneteam.joyread.dao.BooklistDAO;
import com.oneteam.joyread.entity.Booklist;
import com.oneteam.joyread.entity.BooklistBook;
import com.oneteam.joyread.service.BooklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooklistServiceImpl implements BooklistService {
    @Autowired
    BooklistDAO booklistDAO;

    @Autowired
    BooklistBookDAO booklistBookDAO;

    @Override
    public List<Booklist> getRecommendedBooklist(int userId) {
        return booklistDAO.getRecommendedBooklists();
    }

    @Override
    public Booklist getBooklistInfo(int id) {
        return booklistDAO.getBooklistById(id);
    }

    @Override
    public List<String> getBooklistBook(int id) {
        List<BooklistBook> booklistBooks = booklistBookDAO.findByBooklistId(id);
        List<String> books = new ArrayList<String>();
        for (int i=0; i<booklistBooks.size(); i++) {
            books.add(booklistBooks.get(i).getIsbn());
        }
        return books;
    }
}
