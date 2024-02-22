package com.example.stackcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    //اوﻟﻮﯾﺖ ات ﻋﺒﺎر ﻪ ﺑ ﺐ ، ﺗﺮﺗﯿ ﻓﺎﮐﺘﻮرﻞ ان، ﺗﻮ ب ، ﺿﺮ و ﻘﺴﯿﻢ ﻊ ﺟﻤ و ﻖ ﺗﻔﺮ. ﯽ ﺑﺎﺷﺪ در ﻦ ﺑﻦ اد، ﻣﻮار ﻋﻤﻠﯿﺎت ون در ﺰ ﺮاﻧﺖ اوﻟﻮﯾ ی ﺑﯿﺸﺘﺮ ﺪ ﺧﻮاﻫﻨ. داﺷﺖ

    @FXML
    private AnchorPane anch_calculator;

    @FXML
    private AnchorPane anch_calculator1;

    @FXML
    private Button btn_Division;

    @FXML
    private Button btn_E;

    @FXML
    private Button btn_Eight;

    @FXML
    private Button btn_Equal;

    @FXML
    private Button btn_Fact;

    @FXML
    private Button btn_Five;

    @FXML
    private Button btn_Four;

    @FXML
    private Button btn_Minus;

    @FXML
    private Button btn_Multiple;

    @FXML
    private Button btn_Nine;

    @FXML
    private Button btn_One;

    @FXML
    private Button btn_PI;

    @FXML
    private Button btn_Point;

    @FXML
    private Button btn_Pow;

    @FXML
    private Button btn_Seven;

    @FXML
    private Button btn_Six;

    @FXML
    private Button btn_Sum;

    @FXML
    private Button btn_Three;

    @FXML
    private Button btn_Two;

    @FXML
    private Button btn_Zero;

    @FXML
    private Button btn_leftParenthesis;

    @FXML
    private Button btn_rightParenthesis;

    @FXML
    private Button btn_turnZero;

    @FXML
    private TextField txt_consol;

    private String result="";

    @FXML
    void division(ActionEvent event) {

        result=txt_consol.getText();
        result+="÷";
        txt_consol.setText(result);

    }

    @FXML
    void equal(ActionEvent event) {

            try {
                String result=MathOperations.infixToPostfix(txt_consol.getText());
                String result2=MathOperations.postfixEvaluator(result);
                txt_consol.setText(result2);
            }
            catch (InvalidExpression e)
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid expression!!");
                alert.setContentText(e.getMessage());
                alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                alert.setHeight(300);
                alert.setWidth(300);
                alert.initStyle(StageStyle.TRANSPARENT);
                alert.show();
            }
    }

    @FXML
    void leftParenthesis(ActionEvent event) {

        result=txt_consol.getText();
        result+="(";
        txt_consol.setText(result);

    }

    @FXML
    void minus(ActionEvent event) {

        result=txt_consol.getText();
        result+="-";
        txt_consol.setText(result);

    }

    @FXML
    void multiple(ActionEvent event) {

        result=txt_consol.getText();
        result+="×";
        txt_consol.setText(result);

    }

    @FXML
    void rightParenthesis(ActionEvent event) {

        result=txt_consol.getText();
        result+=")";
        txt_consol.setText(result);

    }

    @FXML
    void sum(ActionEvent event) {

        result=txt_consol.getText();
        result+="+";
        txt_consol.setText(result);

    }

    @FXML
    void PI(ActionEvent event) {

        result=txt_consol.getText();
        result+="π";
        txt_consol.setText(result);

    }

    @FXML
    void e(ActionEvent event) {

        result=txt_consol.getText();
        result+="e";
        txt_consol.setText(result);

    }

    @FXML
    void point(ActionEvent event) {

        result=txt_consol.getText();
        result+=".";
        txt_consol.setText(result);

    }

    @FXML
    void pow(ActionEvent event) {

        result=txt_consol.getText();
        result+="^";
        txt_consol.setText(result);

    }

    @FXML
    void fact(ActionEvent event) {

        result=txt_consol.getText();
        result+="!";
        txt_consol.setText(result);

    }

    @FXML
    void nine(ActionEvent event) {

        result=txt_consol.getText();
        result+="9";
        txt_consol.setText(result);

    }

    @FXML
    void eight(ActionEvent event) {

        result=txt_consol.getText();
        result+="8";
        txt_consol.setText(result);

    }

    @FXML
    void seven(ActionEvent event) {

        result=txt_consol.getText();
        result+="7";
        txt_consol.setText(result);

    }

    @FXML
    void six(ActionEvent event) {

        result=txt_consol.getText();
        result+="6";
        txt_consol.setText(result);

    }

    @FXML
    void five(ActionEvent event) {

        result=txt_consol.getText();
        result+="5";
        txt_consol.setText(result);

    }

    @FXML
    void four(ActionEvent event) {

        result=txt_consol.getText();
        result+="4";
        txt_consol.setText(result);
    }

    @FXML
    void three(ActionEvent event) {

        result=txt_consol.getText();
        result+="3";
        txt_consol.setText(result);
    }

    @FXML
    void two(ActionEvent event) {

        result=txt_consol.getText();
        result+="2";
        txt_consol.setText(result);
    }

    @FXML
    void one(ActionEvent event) {

        result=txt_consol.getText();
        result+="1";
        txt_consol.setText(result);

    }

    @FXML
    void zero(ActionEvent event) {

        result=txt_consol.getText();
        result+="0";
        txt_consol.setText(result);
    }

    @FXML
    void turnZero(ActionEvent event) {

        result="";
        txt_consol.setText(result);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_consol.getStylesheets().add(getClass().getResource("textFieldStyle.css").toExternalForm());
        btn_One.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Two.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Zero.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_turnZero.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Three.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Four.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Five.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Six.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Seven.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Eight.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Nine.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Point.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Minus.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Multiple.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Sum.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Fact.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_leftParenthesis.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_rightParenthesis.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_PI.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Pow.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_E.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Equal.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Division.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Equal.setDefaultButton(true);
    }
}
