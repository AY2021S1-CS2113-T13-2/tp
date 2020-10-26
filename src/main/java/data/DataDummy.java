package data;

import constants.Constants;
import io.ModuleParser;
import io.Storage;
import messages.MessageOptions;

import java.io.IOException;
import java.util.ArrayList;

public class DataDummy extends Data {

    ModuleParser moduleloader = new ModuleParser();

    public DataDummy() {
        super();
        lastInput = "";
        lastIndexOption = MessageOptions.NOT_INDEXED;
        tasks = new ArrayList<>();
        try {
            mods = moduleloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = Constants.TASK;
    }
}
