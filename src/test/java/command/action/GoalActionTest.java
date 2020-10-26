package command.action;

import data.DataDummy;
import data.SingleModule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GoalActionTest {
    DataDummy dataTest = new DataDummy();

    @Test
    public void act_customUserData_calculatedCap() throws Exception {
        GoalAction action = new GoalAction();
        action.modulesWithGrades.put("CS1231", 5.0);
        action.modulesWithGrades.put("MA1511", 4.5);
        action.modulesWithGrades.put("CS2040C", 3.0);
        action.option = "c";
        action.targetCap = 4.8;
        action.mcGraduate = 160;
        String expectedOutput = "Your required average CAP is: 4.8\r\n" + "Jia you! :D\r\n";
        assertEquals(expectedOutput, action.act(dataTest), "Goal normal_custom_data fails");
    }

    @Test
    public void act_normalUserData_calculatedCap() throws Exception {
        int[] indices = {1, 2, 3};
        String[] grades = {"A", "B", "C"};
        for (int index : indices) {
            SingleModule module = (SingleModule) dataTest.mods.get(index);
            module.isTaken = true;
            module.grade = grades[index - 1];
        }
        GoalAction action = new GoalAction();
        action.option = "u";
        action.targetCap = 4.3;
        action.mcGraduate = 160;
        String expectedOutput = "Your required average CAP is: 4.36\r\n" + "Jia you! :D\r\n";
        assertEquals(expectedOutput, action.act(dataTest), "Goal normal_user_data fails");
    }
}