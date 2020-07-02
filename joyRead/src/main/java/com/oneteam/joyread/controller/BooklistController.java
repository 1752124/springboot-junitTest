package com.oneteam.joyread.controller;

import com.oneteam.joyread.dto.BasicBookInfoDTO;
import com.oneteam.joyread.dto.BooklistDTO;
import com.oneteam.joyread.entity.Book;
import com.oneteam.joyread.entity.Booklist;
import com.oneteam.joyread.service.implementation.BookServiceImpl;
import com.oneteam.joyread.service.implementation.BooklistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/booklists")
public class BooklistController {

    @Autowired
    BooklistServiceImpl booklistService;

    @Autowired
    BookServiceImpl bookService;

    @CrossOrigin
    @GetMapping(value="/recommended_booklists/{id}")
    public ResponseEntity<List<Booklist>> getRecommendedBooklists(@PathVariable("id")int id) {
        try {
            List<Booklist> booklists = booklistService.getRecommendedBooklist(id);
            return new ResponseEntity<>(booklists, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value="/information/{id}")
    public ResponseEntity<BooklistDTO> getBooklistInformation(@PathVariable("id")int id) {
        try {
            if(id==1||id==2){
            Booklist booklist = booklistService.getBooklistInfo(id);
            List<String> isbns = booklistService.getBooklistBook(id);
            List<Book> books = bookService.findBooksByIsbn(isbns);
            List<BasicBookInfoDTO> basicBookInfoDTOS = new ArrayList<BasicBookInfoDTO>();
            for (int i=0; i<books.size(); i++) {
                BasicBookInfoDTO basicBookInfoDTO = new BasicBookInfoDTO(books.get(i));
                basicBookInfoDTOS.add(basicBookInfoDTO);
            }
            BooklistDTO booklistDTO = new BooklistDTO(booklist);
            booklistDTO.setBooks(basicBookInfoDTOS);
            return new ResponseEntity<>(booklistDTO, HttpStatus.OK);}
            else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
