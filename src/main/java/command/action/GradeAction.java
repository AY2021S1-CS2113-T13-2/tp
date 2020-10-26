package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;
import exceptions.CommandException;
import exceptions.ModuleNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Grade input action.
 */
public class GradeAction extends TakeAction {

    protected HashMap<String, String> modulesWithGrades = new HashMap<>();
    protected String option;

    @Override
    public String act(Data data) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;

        boolean isEmpty = true;
        stringBuilder.append(Constants.GRADE_HEAD);
        if (option.equals("a") || option.equals("d") || option.equals("t")) {
            for (Map.Entry<String, String> m : modulesWithGrades.entrySet()) {
                SingleModule module = matchModule(m.getKey(), data.mods);
                if (module == null) {
                    throw new ModuleNotFoundException();
                }
                if (module.isTaken || option.equals("t")) {
                    isEmpty = false;
                    modifyObject(module, m.getValue());
                    String message = m.getValue().equals("T") ? Constants.MOD_NO_GRADE : m.getValue();
                    addGradeList(stringBuilder, index, m.getKey(), message);
                } else {
                    addGradeList(stringBuilder, index, m.getKey(), Constants.MOD_NOT_TAKEN);
                }
                index++;
            }
        } else if (option.equals("s")) {
            for (Item item : data.getTarget(Constants.TAKEN)) {
                SingleModule module = (SingleModule) item;
                if (module.grade != null && !module.grade.isBlank()) {
                    isEmpty = false;
                    String moduleCode = module.moduleCode;
                    String grade = module.grade;
                    String message = grade.equals("T") ? Constants.MOD_NO_GRADE : grade;
                    addGradeList(stringBuilder, index, moduleCode, message);
                    index++;
                }
            }
        }
        if (isEmpty) {
            return Constants.NO_MOD_GRADED;
        }
        return stringBuilder.toString();
    }

    private void addGradeList(StringBuilder stringBuilder, int index, String moduleCode, String message) {
        stringBuilder.append(index).append(".").append(Constants.SPACE).append(moduleCode)
            .append(Constants.TAB).append(message).append(Constants.WIN_NEWLINE);
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        option = "s";//set default option
        if (args.thisData == null) {
            return;
        }

        ParamNode currData = flattenedArgs[0];

        //if user input custom option
        if (flattenedArgs[0].name.length() == 1) {
            option = flattenedArgs[0].name.trim();
        }

        currData = currData.thisData;

        //input custom modules
        if (option.equals("a") || option.equals("t")) {
            //match grades to modules
            while (currData != null) {
                String moduleCode = currData.name.toUpperCase().trim();
                String grade = currData.thisData.name.toUpperCase().trim();
                if (!grade.equals("NULL")) {
                    modulesWithGrades.put(moduleCode, grade);
                }
                currData = currData.thisData.thisData;
            }
        } else if (option.equals("s")) {
            //should be do nothing
        } else if (option.equals("d")) {
            //match grades to modules
            while (currData != null) {
                String moduleCode = currData.name.toUpperCase().trim();
                modulesWithGrades.put(moduleCode, "T");
                currData = currData.thisData;
            }
        } else {
            //unidentified option
            throw new CommandException();
        }
    }

    protected void modifyObject(Item item, String grade) {
        ((SingleModule) item).grade = grade;
        ((SingleModule) item).isTaken = true;
    }

    /**
     * Match module code to module.
     *
     * @param moduleCode input module code
     * @param mods       module list
     * @return module selected
     */

    private SingleModule matchModule(String moduleCode, ArrayList<Item> mods) {
        for (Item item : mods) {
            SingleModule module = (SingleModule) item;
            if (moduleCode.equals(module.getName())) {
                return module;
            }
        }
        return null;
    }

}
