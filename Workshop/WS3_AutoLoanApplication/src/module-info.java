module Workshop3 {

    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
	requires jdk.compiler;
    

    opens application to javafx.graphics, javafx.fxml;
    opens application.controller to javafx.fxml;
    exports application;
}