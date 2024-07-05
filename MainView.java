package com.example.demo;

import java.awt.*;
import java.util.*;

/*
    Steps to run this program: Run DemoApplication.main() then navigate to a web browser and type http://localhost:8080/
*/

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
//@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout {
    private VerticalLayout vl;
    private VerticalLayout vl2;
    private StreamResource imgResource;
    private TextField textField;
    public MainView() {

        // 'first' represents the Directions
        var first = createText();
        // Creating the UI and putting Directions and a Button
        vl = new VerticalLayout(first, createButton());

        // Adjusting the items on webpage
        vl.setSpacing(false);
        vl.getThemeList().add("spacing-s");
        vl.setWidthFull();
        vl.setHeight("200px");
        vl.expand(first);
        vl.setFlexShrink(0.5, first);
        vl.setJustifyContentMode(JustifyContentMode.AROUND);
        vl.setAlignItems(Alignment.STRETCH);
        vl.setAlignItems(Alignment.CENTER);

        add(vl);

    }

    public TextField createText() {
        // Define the text to display as Directions
        textField = new TextField();
        textField.setLabel("Enter the animal you want converted into ASCII art.");
        textField.setSizeFull();

        // Instructions within text field and display options below
        textField.setPlaceholder("Type here");
        textField.setHelperText("Your options include: Dog, Cat, Rabbit, Elephant, Deer, Camel, Rhino, Bison, Wolf, Scorpion, or Frog");

        // Input text Rules
        textField.setRequired(true);
        textField.setClearButtonVisible(true);
        textField.setMinLength(3);
        textField.setMaxLength(9);
        // Regex Expression to allow one or more times of '[a--zA--Z\s]'
        textField.setPattern("^[a-zA-Z\\s]+");

        return textField;
    }

    public Button createButton() {
        var submitButton = new Button("Submit");

        // Style Button
        submitButton.getStyle().set("color", "white");
        submitButton.getStyle().set("background-color", "black");

        // Create an listener to display art if Enter key pressed
        submitButton.addClickShortcut(Key.ENTER);
        // Note: We REPLACE the current displayed image with new image returned by printAnimal()
        submitButton.addClickListener(e -> {
            replace(vl2, printAnimal(textField.getValue()));
        });

        // Returns Button to public constructor to display
        return submitButton;
    }

    public VerticalLayout printAnimal(String request) {

        // Clean the text so that it is usable in switch-case
        String rq = request.toLowerCase();

        // vl2 - Local Scope - will hold the animal art image selected from switch case
        vl2 = new VerticalLayout();

        switch(rq) {
            // If user typed "dog" then get the Doberman.png from images folder and add to local variable, vl2
            case "dog":
                imgResource =  new StreamResource("Doberman.png",
                        () -> getClass().getResourceAsStream("/images/Doberman.png"));
                vl2.add(new Image(imgResource, "Doberman"));
                break;
            case "cat":
                imgResource = new StreamResource("Cat.png",
                        () -> getClass().getResourceAsStream("/images/Cat.png"));
                vl2.add(new Image(imgResource, "Cat"));
                break;
            case "rabbit":
                imgResource = new StreamResource("Rabbit.png",
                        () -> getClass().getResourceAsStream("/images/Rabbit.png"));
                vl2.add(new Image(imgResource, "Rabbit"));
                break;
            case "bison":
                imgResource = new StreamResource("Bison.png",
                        () -> getClass().getResourceAsStream("/images/Bison.png"));
                vl2.add(new Image(imgResource, "Bison"));
                break;
            case "camel":
                imgResource = new StreamResource("Camel.png",
                        () -> getClass().getResourceAsStream("/images/Camel.png"));
                vl2.add(new Image(imgResource, "Camel"));
                break;
            case "deer":
                imgResource = new StreamResource("Deer.png",
                        () -> getClass().getResourceAsStream("/images/Deer.png"));
                vl2.add(new Image(imgResource, "Deer"));
                break;
            case "elephant":
                imgResource = new StreamResource("Elephant.png",
                        () -> getClass().getResourceAsStream("/images/Elephant.png"));
                vl2.add(new Image(imgResource, "Elephant"));
                break;
            case "frog":
                imgResource = new StreamResource("Frog.png",
                        () -> getClass().getResourceAsStream("/images/Frog.png"));
                vl2.add(new Image(imgResource, "Frog"));
                break;
            case "rhino":
                imgResource = new StreamResource("Rhino.png",
                        () -> getClass().getResourceAsStream("/images/Rhino.png"));
                vl2.add(new Image(imgResource, "Rhino"));
                break;
            case "scorpion":
                imgResource = new StreamResource("Scorpion.png",
                        () -> getClass().getResourceAsStream("/images/Scorpion.png"));
                vl2.add(new Image(imgResource, "Scorpion"));
                break;
            case "wolf":
                imgResource = new StreamResource("Wolf.png",
                        () -> getClass().getResourceAsStream("/images/Wolf.png"));
                vl2.add(new Image(imgResource, "Wolf"));
                break;
            // if mistyped or error, display Error.png
            default:
                imgResource = new StreamResource("Error.png",
                        () -> getClass().getResourceAsStream("/images/Error.png"));
                vl2.add(new Image(imgResource, "Error"));
                break;
        }

        // vl2 holds the user requested animal art image
        return vl2;
    }
}
