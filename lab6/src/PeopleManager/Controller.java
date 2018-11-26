package PeopleManager;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private ListView<Person> valueList;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private Spinner<Integer> age;

    @FXML
    private TextField town;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private Pane window;


    private SpinnerValueFactory<Integer> ageSpinValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 18);
    private ObservableList<Person> people = FXCollections.observableArrayList();
    private Integer indexToRemove;
    private ChangeListener<Person> picked = new ChangeListener<Person>() {
        public void changed(ObservableValue<? extends Person> ov,
                            Person old_, Person new_) {

            name.setText(new_.getName());
            surname.setText(new_.getSurname());
            age.getValueFactory().setValue(new_.getAge());
            town.setText(new_.getTown());
            address.setText(new_.getSurname());
            phone.setText(new_.getPhone());
            indexToRemove = people.indexOf(new_);
        }
    };


    @FXML
    public void initialize() {
        age.setValueFactory(ageSpinValue);
        valueList.setItems(people);
        valueList.setCellFactory(param -> new ListCell<Person>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + ' ' + item.getSurname());
                }
            }
        });
        valueList.getSelectionModel().selectedItemProperty().addListener(picked);
    }


    @FXML
    public void addPerson() {
        Person toAdd = new Person(name.getText(), surname.getText(), age.getValue(), town.getText(), address.getText(), phone.getText());
        people.add(toAdd);


        name.setText("");
        surname.setText("");
        age.getValueFactory().setValue(18);
        town.setText("");
        address.setText("");
        phone.setText("");

    }


    @FXML
    public void removePerson(){
        Person to_Remove = valueList.getSelectionModel().getSelectedItem();
        valueList.getItems().remove(to_Remove);
        people.remove(to_Remove);
        name.setText("");
        surname.setText("");
        town.setText("");
        address.setText("");
        address.setText("");
        age.getValueFactory().setValue(18);
        name.requestFocus();
    }

}

