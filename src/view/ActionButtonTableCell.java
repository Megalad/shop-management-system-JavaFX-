package view;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ActionButtonTableCell<S> extends TableCell<S, Void> {
    private final HBox buttonBox;

    public ActionButtonTableCell(String editButtonText, String deleteButtonText, OnActionHandler<S> handler) {
        buttonBox = new HBox();
        buttonBox.setSpacing(5);

        Button editButton = new Button(editButtonText);
        Button deleteButton = new Button(deleteButtonText);

        editButton.setOnAction(event -> {
            if (handler != null) {
                handler.onEdit(getIndex(), getTableRow().getItem());
            }
        });

        deleteButton.setOnAction(event -> {
            if (handler != null) {
                handler.onDelete(getIndex(), getTableRow().getItem());
            }
        });

       
        
        
        buttonBox.getChildren().addAll(editButton, deleteButton);
        setGraphic(buttonBox);
    }

    public interface OnActionHandler<S> {
        void onEdit(int index, S item);
        void onDelete(int index, S item);
    }
    public static <S> Callback<TableColumn<S, Void>, TableCell<S, Void>> forTableColumn(
            String editButtonText, String deleteButtonText, OnActionHandler<S> handler) {
        return col -> new ActionButtonTableCell<>(editButtonText, deleteButtonText, handler);
    }
}
