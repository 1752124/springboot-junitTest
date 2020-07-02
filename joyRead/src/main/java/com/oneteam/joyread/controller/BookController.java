package com.oneteam.joyread.controller;

import com.oneteam.joyread.dto.BasicBookInfoDTO;
import com.oneteam.joyread.dto.BookInfoDTO;
import com.oneteam.joyread.dto.UserListDTO;
import com.oneteam.joyread.entity.Book;
import com.oneteam.joyread.entity.User;
import com.oneteam.joyread.entity.Userbook;
import com.oneteam.joyread.exception.ServiceException;
import com.oneteam.joyread.service.implementation.BookServiceImpl;
import com.oneteam.joyread.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    UserServiceImpl userService;

    @CrossOrigin
    @GetMapping(value="/information/{isbn}")
    ResponseEntity<BasicBookInfoDTO> getBasicBookInfo(@PathVariable("isbn")String isbn) {
        try {
            Book book = bookService.findBookByIsbn(isbn);
            if(book == null) {
                throw new ServiceException("book doesn't exist");
            } else {
                BasicBookInfoDTO basicBookInfoDTO = new BasicBookInfoDTO(book);
                return new ResponseEntity<>(basicBookInfoDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value="/recommended_books/{id}")
    ResponseEntity<List<BasicBookInfoDTO>> getRecommendedBooks(@PathVariable("id")Integer id) {
        try {
            List<Book> books = bookService.getRecommendedBooks(id);
            List<BasicBookInfoDTO> basicBookInfoDTOS = new ArrayList<BasicBookInfoDTO>();
            for(int i=0; i<books.size(); i++) {
                BasicBookInfoDTO basicBookInfoDTO = new BasicBookInfoDTO(books.get(i));
                basicBookInfoDTOS.add(basicBookInfoDTO);
            }
            return new ResponseEntity<>(basicBookInfoDTOS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value="/information/detail")
    ResponseEntity<BookInfoDTO> getDetailBookInfo(@RequestParam("id")Integer id, @RequestParam("isbn")String isbn) {
        try {
            if(id==null){
                System.out.println("UserID is invalid.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            Book book = bookService.findBookByIsbn(isbn);
            if(book == null) {
                throw new ServiceException("book doesn't exist");
            } else {
                BookInfoDTO bookInfoDTO = new BookInfoDTO(book);
                User user = userService.getById(id);
                if (user == null) {
                    bookInfoDTO.setPercentage(0);
                    bookInfoDTO.setStatus(0);
                } else {
                    Userbook userbook = bookService.getUserbook(id, isbn);
                    if (userbook == null) {
                        bookInfoDTO.setPercentage(0);
                        bookInfoDTO.setStatus(1);
                    } else {
                        //该书已与用户关联
                        bookInfoDTO.setPercentage((float) userbook.getCurrentPage() / (float) book.getPages());
                        bookInfoDTO.setStatus(userbook.getStatus());
                    }
                }
                return new ResponseEntity<>(bookInfoDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value="/userbooks")
    ResponseEntity<List<UserListDTO>> getUserBookList(@RequestParam("status")Integer status, @RequestParam("id")Integer id) {
        try {
            if(id==null){
                System.out.println("UserID is invalid.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            User user = userService.getById(id);
            if (user == null) {
                throw new ServiceException("User doesn't exist");
            } else {
                List<Userbook> userbooks = bookService.getAllUserbook(id, status);
                if (userbooks == null) {
                    throw new ServiceException("Book doesn't exist");
                } else {
                    List<UserListDTO> result = new ArrayList<UserListDTO>();
                    List<Book> books = new ArrayList<Book>();
                    for (int i = 0; i < userbooks.size(); i++) {
                        String isbn = userbooks.get(i).getIsbn();
                        Book book = bookService.findBookByIsbn(isbn);
                        UserListDTO userListDTO = new UserListDTO(book);
                        result.add(userListDTO);
                        books.add(book);
                    }
                    if (status == 2) {
                        //想读
                        for (int i = 0; i < userbooks.size(); i++) {
                            result.get(i).setBeginTime(userbooks.get(i).getBeginTime());
                        }
                    } else if (status == 3) {
                        //在读
                        for (int i = 0; i < userbooks.size(); i++) {
                            result.get(i).setPercentage(
                                    (float) userbooks.get(i).getCurrentPage() / (float) books.get(i).getPages()
                            );
                            result.get(i).setLastTime(userbooks.get(i).getLastTime());
                        }
                    } else {
                        for (int i = 0; i < userbooks.size(); i++) {
                            result.get(i).setLastTime(userbooks.get(i).getLastTime());
                            result.get(i).setPoint(userbooks.get(i).getPoint());
                        }
                    }
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PutMapping(value="/change")
    ResponseEntity<Integer> addBookToReadylist(@RequestParam("id")Integer id, @RequestParam("isbn")String isbn) {
        try {
            if(id==null){
                System.out.println("UserID is invalid.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            User user = userService.getById(id);
            if (user == null) {
                throw new ServiceException("user doesn't exist");
            } else {
                Book book = bookService.findBookByIsbn(isbn);
                if (book == null) {
                    throw new ServiceException("Book doesn't exist");
                } else {
                    Userbook userbook = new Userbook();
                    userbook.setUserId(id);
                    userbook.setIsbn(isbn);
                    userbook.setStatus(2);
                    userbook.setBeginTime(LocalDate.now());
                    bookService.addOrUpdate(userbook);
                    return new ResponseEntity<>(userbook.getStatus(), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PutMapping(value="/end")
    ResponseEntity<Integer> finishReadUserbook(
            @RequestParam("id")Integer id, @RequestParam("isbn")String isbn, @RequestParam("point")float point) {
        try {
            if(id==null){
                System.out.println("UserID is invalid.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            User user = userService.getById(id);
            if(user == null) {
                throw new ServiceException("User doesn't exist");
            } else {
                Book book = bookService.findBookByIsbn(isbn);
                Userbook userbook = bookService.getUserbook(id, isbn);
                if(userbook == null) {
                    throw new ServiceException("Book doesn't exist");
                } else {
                    userbook.setStatus(4);
                    userbook.setLastTime(LocalDate.now());
                    if(point<=0){
                        System.out.println("Point is invalid.");
                        return new ResponseEntity<>(null, HttpStatus.OK);
                    }
                    userbook.setPoint(point);
                    userbook.setCurrentPage(book.getPages());
                    bookService.addOrUpdate(userbook);
                    return new ResponseEntity<>(userbook.getStatus(), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PostMapping(value="/add")
    ResponseEntity<Boolean> addBookInfo(@RequestBody Book book) {
        try {
            book.setPic("http://q6vr3uv5e.bkt.clouddn.com/defaultPic.png");
            bookService.addOrUpdate(book);
            Book newbook = bookService.findBookByIsbn(book.getIsbn());
            if (newbook == null) {
                throw new ServiceException("Add book failed");
            } else {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value="/search")
    ResponseEntity<List<BasicBookInfoDTO>> searchBooks(@RequestParam("words")String words) {
        try {
            List<Book> booksName = bookService.searchName("%" + words + "%");
            List<Book> booksWriter = bookService.searchWriter("%" + words + "%");
            List<BasicBookInfoDTO> result = new ArrayList<>();
            for (int i=0; i<booksName.size(); i++) {
                result.add(new BasicBookInfoDTO(booksName.get(i)));
            }
            for (int i=0; i<booksWriter.size(); i++) {
                result.add(new BasicBookInfoDTO(booksWriter.get(i)));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

    }
}