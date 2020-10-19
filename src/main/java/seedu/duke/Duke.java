package seedu.duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import command.Command;
import constants.Constants;
import data.Data;
import io.Storage;
import lexical.Parser;
import visualize.Cli;
import visualize.FancyCli;

/**
 * The type Duke.
 */
public class Duke {

    private final Storage storage;
    private final FancyCli fui; // fancy ui
    private final Cli pui; //plain ui
    private final Parser parser;
    private Data data;
    private Cli ui;
    private boolean isFancy;

    /**
     * Instantiates a new Duke.
     *
     * @param isFancy        toggle between fancy and normal layout
     * @param stream         the stream
     * @param input          the input
     * @param directory      the directory
     * @param taskFileName   task file name
     * @param courseFileName course file name
     */
    public Duke(boolean isFancy, PrintStream stream, InputStream input, String directory,
                String taskFileName, String courseFileName) {
        fui = new FancyCli(stream, input);
        pui = new Cli(stream, input);
        ui = isFancy ? fui : pui;
        this.isFancy = isFancy;
        ui.showWelcome();
        parser = new Parser();
        storage = new Storage(directory, taskFileName, courseFileName, parser);
        try {
            data = storage.load();
        } catch (Exception e) {
            ui.showText("The save file is corrupted.");
            data = new Data();
        }

        // schedule reminder every 10 minutes
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ui.showReminder(data);
            }
        },Constants.REMINDER_DELAY,Constants.REMINDER_INTERVAL);

    }

    private void reattachUI(boolean isFancy, boolean isPlain) {
        if (this.isFancy && isPlain) {
            ui = pui;
            this.isFancy = false;
            ui.setSwitched();
        } else if (!this.isFancy && isFancy) {
            ui = fui;
            this.isFancy = true;
            ui.setSwitched();
        }
    }

    /**
     * Run.
     */
    public void run() {
        //ui.showReminder(data);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.nextLine();
                ArrayList<Command> commands = parser.parse(fullCommand); //array list of commands
                for (Command c : commands) {
                    c.execute(data);
                    reattachUI(c.isFancy(), c.isPlain());
                    ui.update(c.result, data);
                    isExit = c.isBye();
                    storage.save(data.tasks, data.takenCourses);
                }
            } catch (Exception e) {
                String message = e.getMessage();
                if (message == null) {
                    message = Constants.INDEX_OUT;
                }
                ui.update(message, data);
            }
        }
    }

    /**
     * Execute JUnit test.
     *
     * @param command   Command of user
     * @param isStored  determines if output will be stored in duke.txt
     * @param isPrinted determines if output will be to console
     * @return Duke's response
     */
    public String testSut(String command, boolean isStored, boolean isPrinted) {
        try {
            String fullCommand = command;
            ArrayList<Command> commands = parser.parse(fullCommand); //array list of commands
            for (Command c : commands) {
                c.execute(data);
                if (isPrinted) {
                    reattachUI(c.isFancy(), c.isPlain());
                    ui.update(c.result, data);
                }
                if (isStored) {
                    //check the taken_course path
                    //storage.save(data.tasks, data.takenCourse);
                }
                return c.result;
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = Constants.INDEX_OUT;
            }
            ui.showText(message);
        }
        return "0";
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Starts up using colored CLI on mac or linux, and pure text on windows (for now).
        // This is because ansi sequences needed to be enabled on programs started by cmd in recent windows versions.
        // this is an intended behaviour brought by microsoft developers, so that programs called by cmd
        // do not inherit the appearance of cmd.exe (which by default supports ansi escape sequences).
        // when cmd runs "java -jar xxx", the javac.exe is started by cmd.exe
        // which does not enable the ansi mode by default.
        // if the jar was started by something else such as gitbash or intelliJ shell, there is no such problem
        // thus, we need to find a way to reliable call the windows api to set such mode
        // However, no matter what mode it starts in, I have created switching commands.
        // you can use "fancy" command to switch to fancyCli, and use "plain" command to switch to plain Cli.
        // [AFTER READING THE ABOVE TEXT, PLEASE UNCOMMENT THE FOLLOWING 2 LINES TO RUN THE PROGRAM]
        // boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
        // new Duke(!isWindows, System.out, System.in, Constants.PATH,
        //    Constants.TAKS_FILENAME, Constants.COURSE_FILENAME).run();
    }
}
