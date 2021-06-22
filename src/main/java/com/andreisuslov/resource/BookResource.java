package com.andreisuslov.resource;

import com.andreisuslov.model.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Path("/books")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,})
@Api(value = "Books")
public class BookResource {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookResource(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GET
    @ApiOperation(value = "Get List of all books", notes = "Returns all the books in the DB", response = Book.class, responseContainer = "List")
    public List<Book> listBooks() {

        String sql = "SELECT * FROM BOOK";

        return namedParameterJdbcTemplate.query(sql, new BookMapper());
    }

    @GET
    @Path("/{bookId}")
    @ApiOperation(value = "Get a single book by ID", notes = "Returns the details of a single book by ID", response = Book.class)
    public Book getBook(
            @ApiParam(value = "The book ID", required = true) @PathParam("bookId") Integer bookId) {
        String sql = "SELECT * FROM BOOK WHERE id=:bookId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("bookId", bookId);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new BookMapper()).get(0);
    }

    @POST
    @ApiOperation(value = "Add a new book", notes = "Add a new book to the DB")
    public String createBook(final Book book) {
        String sql = "INSERT INTO BOOK VALUES(:id, :name, :releaseDate, :reviewScore, :category, :rating)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(book);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);
        return "{\"status\": \"Record Added Successfully\"}";
    }

    @PUT
    @Path("/{bookId}")
    @ApiOperation(value = "Update a book", notes = "Update an existing book in the DB by specifying a new body ", response = Book.class)
    public Book editBook(final Book book, @PathParam("bookId") Integer bookId) {
        String sql = "UPDATE BOOK SET id=:id, name=:name, released_on=:releaseDate, review_score=:reviewScore, category=:category, rating=:rating WHERE id=:id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(book);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);

        sql = "SELECT * FROM BOOK WHERE id=:bookId";
        SqlParameterSource namedParameters2 = new MapSqlParameterSource("bookId", bookId);
        return namedParameterJdbcTemplate.query(sql, namedParameters2, new BookMapper()).get(0);
    }

    @DELETE
    @Path("/{bookId}")
    @ApiOperation(value = "Delete a book", notes = "Deletes a book from the DB by ID")
    public String deleteBook(
            @ApiParam(value = "The book ID", required = true) @PathParam("bookId") Integer bookId) {
        String sql = "DELETE FROM BOOK WHERE id =:bookId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("bookId", bookId);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);
        return "{\"status\": \"Record deleted successfully\"}";
    }


    private static final class BookMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setReleaseDate(rs.getDate("released_on"));
            book.setReviewScore(rs.getInt("review_score"));
            book.setCategory(rs.getString("category"));
            book.setRating(rs.getString("rating"));
            return book;

        }
    }
}
