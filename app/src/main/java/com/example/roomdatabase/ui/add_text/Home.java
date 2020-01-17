package com.example.roomdatabase.ui.add_text;

public class Home {
    private String input;
    public Home(String input) {
        this.input = input;
    }
    public Home() {
    }

    public String getInput() {
        if (input == null) {
            return "";
        }
        return input;
    }



    public boolean isInputValid() {
        return getInput().length()>=5;

    }
}
