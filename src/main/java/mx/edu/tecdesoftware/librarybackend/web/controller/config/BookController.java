package mx.edu.tecdesoftware.librarybackend.web.controller.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.librarybackend.domain.Book;
import mx.edu.tecdesoftware.librarybackend.domain.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book", description = "Manage books in the library catalog")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    @Operation(summary = "Get all books", description = "Return a list of all books in the catalog")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of books")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Book>> getAll(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Return a book by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "book found")
    @ApiResponse(responseCode = "404", description = "book not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Book> getBook(
            @Parameter(description = "ID of the book", example = "1", required = true)
            @PathVariable("id") int bookId){
        return bookService.getBook(bookId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get books by category", description = "Return all books in a specific category")
    @ApiResponse(responseCode = "200", description = "books found in the category")
    @ApiResponse(responseCode = "404", description = "no books found in the category")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Book>> getByCategory(
            @Parameter(description = "Category ID", example = "1", required = true)
            @PathVariable int categoryId){
        return bookService.getByCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Save a new book",
            description = "Register a new book and return the created book",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example book", value = """
                                    {
                                    "title" : "Cien años de soledad",
                                    "author" : "Gabriel García Márquez",
                                    "isbn" : "978-0307474728",
                                    "categoryId" : 1,
                                    "availableQuantity" : 5,
                                    "available" : true
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "book created")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Book> save(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID", description = "Delete a book if it exists")
    @ApiResponse(responseCode = "200", description = "book deleted")
    @ApiResponse(responseCode = "404", description = "book not found")
    @ApiResponse(responseCode = "409", description = "book is referenced by an existing loan")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the book to be deleted", example = "1", required = true)
            @PathVariable("id") int bookId){
        if(bookService.delete(bookId)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}