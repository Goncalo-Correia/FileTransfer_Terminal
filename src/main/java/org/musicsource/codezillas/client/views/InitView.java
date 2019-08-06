package org.musicsource.codezillas.client.views;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InitView implements View {

    private Prompt prompt;
    private MenuInputScanner inputScanner;
    private String message;
    private String[] menuOptions;
    private Integer response;

    public InitView(Prompt prompt) {
        this.prompt = prompt;
    }

    @Override
    public void show() {
        inputScanner = new MenuInputScanner(menuOptions);
        inputScanner.setMessage(message);
        prompt.getUserInput(inputScanner);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMenuOptions(String[] menuOptions) {
        this.menuOptions = menuOptions;
    }

    public Integer getResponse() {
        return response;
    }
}
