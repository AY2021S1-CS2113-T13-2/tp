package data;

import constants.Constants;
import data.jobs.Deadline;
import data.jobs.Event;
import data.jobs.Task;
import data.jobs.ToDo;
import messages.MessageOptions;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Task list.
 */
public class Data {

    private static final Logger LOGGER = Logger.getLogger(Data.class.getName());

    private static ArrayList<Item> tempList;
    public String flag;
    /**
     * The Tasks.
     */
    public ArrayList<Item> tasks;
    /**
     * The default list of modules read in from finalcourselist.txt.
     */
    public ArrayList<Item> mods;
    /**
     * The Index option.
     */
    public MessageOptions indexOption;
    public ArrayList<Item> target;
    /**
     * The Last input.
     */
    public String lastInput;
    /**
     * The Last index option.
     */
    public MessageOptions lastIndexOption;

    /**
     * Instantiates a new Task list.
     */
    public Data() {
        lastInput = "";
        lastIndexOption = MessageOptions.NOT_INDEXED;
        tasks = new ArrayList<>();
        indexOption = MessageOptions.NOT_INDEXED;
        target = tasks;
        mods = new ArrayList<>();
        flag = Constants.TASK;
    }

    public void setFlag(String flag) {
        this.flag = flag;
        target = getTarget();
    }

    public ArrayList<Item> getTarget() {
        return getTarget(flag);
    }

    public ArrayList<Item> getTarget(String flag) {
        refreshTarget(flag);
        return target;
    }

    public void refreshTarget() {
        refreshTarget(flag);
    }

    public void refreshTarget(String flag) {
        switch (flag) {
        case Constants.DEADLINE: // break is unreachable
            target = tasks.stream().filter(x -> x instanceof Deadline).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.EVENT: // break is unreachable
            target = tasks.stream().filter(x -> x instanceof Event).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.TODO: // break is unreachable
            target = tasks.stream().filter(x -> x instanceof ToDo).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.MOD:
            target = mods.stream().filter(x -> x instanceof SingleModule)
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.SU:
            target = mods.stream().filter(
                x -> ((SingleModule) x).hasSU).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.SELECTED:
            target = mods.stream().filter(x -> x.isSelected).collect(Collectors.toCollection(ArrayList::new));
            target.addAll(tasks.stream().filter(x -> x.isSelected).collect(Collectors.toList()));
            break;
        case Constants.TAKEN:
            target = mods.stream().filter(
                x -> ((SingleModule) x).isTaken).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.FOUND: // should not refresh target.
            break;
        default:
            target = tasks.stream().filter(x -> x instanceof Task).collect(Collectors.toCollection(ArrayList::new));
            break;
        }
    }

    private String getTaskType(Task task) {
        if (task instanceof Deadline) {
            return Constants.DEADLINE;
        } else if (task instanceof Event) {
            return Constants.EVENT;
        }
        return "task";
    }


    public void addTask(Task task) {
        // LOGGER.entering(getClass().getName(), "addTask");
        tempList = new ArrayList<>(getTarget(getTaskType(task)));
        Checker cc = new Checker(tempList, task);
        LocalDateTime newDate = cc.checkRecurrenceDate(task);
        if (newDate != null) {
            task.setDateTime(newDate);
        } else {
            LOGGER.log(Level.INFO, "New date was null! Invalid Date");
        }
        if (!cc.checkDuplicates()) {
            // LOGGER.log(Level.INFO, "Task was added to data");
            tasks.add(task);
        } else {
            LOGGER.log(Level.INFO, "Duplicate found! Task was not added to data");
        }
        refreshTarget();
        //LOGGER.exiting(getClass().getName(), "addTask");
    }

    public void removeItem(Item item) {
        target.remove(item);
        if (item instanceof Task) { //modules are not removable from mods list.
            tasks.remove(item);
        }
        refreshTarget();
    }

    public void removeItem(int index) {
        Item currentItem = target.get(index);
        removeItem(currentItem);
    }

    public void updateItem(int index, Item newItem) {
        Item currentItem = target.get(index);
        target.set(target.indexOf(currentItem), newItem);
        if (currentItem instanceof SingleModule) {
            if (mods.contains(currentItem)) {
                mods.set(mods.indexOf(currentItem), newItem);
            }
        } else {
            if (tasks.contains(currentItem)) {
                tasks.set(tasks.indexOf(currentItem), newItem);
            }
        }
        refreshTarget();
    }

    /**
     * Get task.
     *
     * @param index the index
     * @return the task
     */
    public Item get(int index) {
        return target.get(index);
    }


}
