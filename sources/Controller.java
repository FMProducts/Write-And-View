package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    private TextArea codeViewer;
    @FXML
    private WebView webViewer;
    @FXML
    private Menu title;


    // standart html
    private static String DEF_HTML_TEMPLATES = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>New Template</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
    @FXML
    public void initialize(){
        codeViewer.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = codeViewer.getText();
                setTitle();
                webViewer.getEngine().loadContent(code==null?"":code);
            }
        });
    }

    private void setTitle(){
        String t = webViewer.getEngine().getTitle();
        title.setText(t);
    }

    // click action
    @FXML
    public void clear(ActionEvent actionEvent) {
        codeViewer.setText(DEF_HTML_TEMPLATES);
        setTitle();
    }

    // click action
    @FXML
    public void clearAll(ActionEvent actionEvent) {
        codeViewer.setText("");
        setTitle();
    }

    // click action
    @FXML
    public void copyFilePath(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
        File file = chooser.showOpenDialog(null);

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString("file:\\".concat(file.getAbsolutePath()));
        clipboard.setContent(content);
    }
}
