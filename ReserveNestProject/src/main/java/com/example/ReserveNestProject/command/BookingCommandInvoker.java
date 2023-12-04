package com.example.ReserveNestProject.command;

import org.springframework.stereotype.Component;

@Component
public class BookingCommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}


