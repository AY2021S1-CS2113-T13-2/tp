package command.action;

import data.DataDummy;
import data.SingleModule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test for CAP calculator.
 */
class CalculateCapActionTest {

    DataDummy dataTest = new DataDummy();

    @Test
    public void act_customModuleInputs_calculatedCapValue() throws Exception {
        CalculateCapAction action = new CalculateCapAction();
        action.modulesWithGrades.put("CS1231", 5.0);
        action.modulesWithGrades.put("MA1511", 3.5);
        action.modulesWithGrades.put("CS2040C", 3.0);
        action.option = "m";
        String expectedOutput = "Here is your existing CAP: 3.9";
        assertEquals(expectedOutput, action.act(dataTest), "Calculate CAP custom input fails");
    }

    @Test
    public void act_userModule_calculatedCapValue() throws Exception {
        int[] indices = {1, 2, 3};
        String[] grades = {"A", "B", "C"};
        for (int index : indices) {
            SingleModule module = (SingleModule) dataTest.mods.get(index);
            module.isTaken = true;
            module.grade = grades[index - 1];
        }
        CalculateCapAction action = new CalculateCapAction();
        action.option = "u";
        String expectedOutput = "Here is your existing CAP: 3.5";
        assertEquals(expectedOutput, action.act(dataTest), "Calculate CAP user's modules fails");
    }
}

