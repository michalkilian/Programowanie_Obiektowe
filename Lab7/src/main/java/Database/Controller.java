package Database;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private ListView<Book> valueList;

    @FXML
    private Button connectionButton;

    @FXML
    private Circle connectionStatus;

    @FXML
    private TextField isbn;

    @FXML
    private TextField author;

    @FXML
    private TextField year;

    @FXML
    private TextField title;

    @FXML
    private Pane window;


    private ObservableList<Book> books = FXCollections.observableArrayList();
    private ChangeListener<Book> picked = new ChangeListener<Book>() {
        public void changed(ObservableValue<? extends Book> ov,
                            Book old_, Book new_) {

            isbn.setText(new_.getIsbn());
            title.setText(new_.getTitle());
            author.setText(new_.getAuthor());
            year.setText(new_.getYear());
        }
    };


    @FXML
    public void testConnection(){
        DB db = new DB();
        try {
            db.connect();
            connectionStatus.setFill(Color.GREEN);
        }
        catch (Exception e){
            connectionStatus.setFill(Color.RED);
        }
    }

    @FXML
    public void initialize() {
        valueList.setItems(books);
        valueList.setCellFactory(param -> new ListCell<Book>() {
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });
        valueList.getSelectionModel().selectedItemProperty().addListener(picked);
    }


    @FXML
    public void addBook() {
        DB db = new DB();
        Book toAdd = new Book(isbn.getText(), title.getText(), author.getText(), year.getText());
        db.addBook(toAdd);
        books.add(toAdd);


        isbn.setText("");
        title.setText("");
        author.setText("");
        year.setText("");

    }



}

