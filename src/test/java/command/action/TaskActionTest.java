package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.Duke;

/**
 * Tests for various task actions.
 */
class TaskActionTest {

    private Duke duke = new Duke(false, System.out, System.in, Constants.PATH, Constants.TEST_FILENAME);

    @Test
    public void actClear_normalCase_clearResponseMessage() {
        String expectedOutput = "Nice! I've cleared everything in the list.";
        String testCustomInputsCommand = "clear";

        assertAll("McActionTest",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, true, true),
                "Clear fails")
        );
    }

    @Test
    public void actDeadline_randomByTime_customTimeDeadline() {
        duke.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[D][X] watch powerRangers episode 2 (by: TODAY!!)\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "deadline watch powerRangers episode 2 -by TODAY!!";
        assertAll("Deadline random time",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "Deadline custom time inputs fails")
        );
    }

    @Test
    public void actDeadline_dateTimeFormat_formattedTimeDeadline() {
        duke.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[D][X] watch powerRangers episode 3 (by: Feb 23 2022 09:00)\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "deadline watch powerRangers episode 3 /by 2022-02-23 09:00";
        assertAll("Deadline date time",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "Deadline format time inputs fails"));
    }

    @Test
    public void actTodo_normalTodo_todoRespondMessage() {
        duke.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[T][X] finish CS2113 Project!\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "todo finish CS2113 Project!";
        assertAll("Todo",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "Todo normal inputs fails"));
    }

    @Test
    public void actEvent_randomAtTime_customTimeDeadline() {
        duke.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[E][X] watch Dora The Explorer episode 2 (at: your Free Time :))\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "event watch Dora The Explorer episode 2 -at your Free Time :)";
        assertAll("Event random time",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "Event custom time inputs fails")
        );
    }

    @Test
    public void actEvent_dateTimeFormat_formattedTimeDeadline() {
        duke.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[E][X] watch Dora The Explorer episode 3 (at: Feb 23 2022 00:00)\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "event watch Dora The Explorer episode 3 /at 2022-02-23";
        assertAll("Event custom time",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "Event format time inputs fails")
        );
    }

    @Test
    public void actDone_indexInList_doneResponseMessage() {
        duke.testSut("clear", true, true);
        duke.testSut("deadline watch Spongebob roundpants s3 /by 22-01-2001 12:00", false, false);
        duke.testSut("todo eat banana while watching Minions s4", false, false);
        duke.testSut("event Tinder meetingg!! /at THE END OF TIME TT", false, false);

        String expectedOutput = "Nice! I've marked this task as done:\r\n"
            + "[D][V] watch Spongebob roundpants s3 (by: Jan 22 2001 12:00)";
        String testCustomInputsCommand = "done 1";
        assertAll("Mark done",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "Mark done normal fails")
        );
    }

    @Test
    public void actList_filledList_listResponseMessage() {
        duke.testSut("clear", true, true);
        duke.testSut("deadline watch Spongebob roundpants s3 /by 22-01-2001 12:00", false, false);
        duke.testSut("todo eat banana while watching Minions s4", false, false);
        duke.testSut("event Tinder meetingg!! /at THE END OF TIME TT", false, false);

        String expectedOutput = "Here is the list of items:\r\n"
            + "[D][X] watch Spongebob roundpants s3 (by: Jan 22 2001 12:00)\r\n"
            + "[T][X] eat banana while watching Minions s4\r\n"
            + "[E][X] Tinder meetingg!! (at: THE END OF TIME TT)\r\n";
        String testCustomInputsCommand = "list";
        assertAll("List normal tasks",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "List normal tasks fails")
        );
    }

    @Test
    public void actUndone_indexInList_undoneResponseMessage() {
        duke.testSut("clear", true, true);
        duke.testSut("deadline watch Spongebob roundpants s3 /by 22-01-2001 12:00", false, false);
        duke.testSut("todo eat banana while watching Minions s4", false, false);
        duke.testSut("event Tinder meetingg!! /at THE END OF TIME TT", false, false);
        duke.testSut("done 1", false, false);//mark first item as done

        String expectedOutput = "Nice! I've marked this task as undone:\r\n"
            + "[D][X] watch Spongebob roundpants s3 (by: Jan 22 2001 12:00)";
        String testCustomInputsCommand = "undone 1";
        assertAll("Mark undone",
            () -> assertEquals(expectedOutput, duke.testSut(testCustomInputsCommand, false, true),
                "Mark undone normal fails")
        );
    }
}
