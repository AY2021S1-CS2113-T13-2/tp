package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import exceptions.CommandException;

import java.text.DecimalFormat;

/**
 * Goal planner function
 */
public class GoalAction extends CalculateCapAction {

    String option;
    double targetCAP;
    double mcGraduate;

    @Override
    public String act(Data data) throws Exception {
        double totalScoreGoal = mcGraduate * targetCAP;
        if (option.equals("u")) {
            checkCalculateCap(data);
        }
        double capRequired = (totalScoreGoal - totalScore) / (mcGraduate - totalMC);
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.REQUIRED_CAP + new DecimalFormat("#.##").format(capRequired) + Constants.WIN_NEWLINE);
        if (capRequired <= 1.0) {
            sb.append(Constants.LOW_CAP);
        } else if (capRequired <= 5.0) {
            sb.append(Constants.JIAYOU);
        } else {
            sb.append(Constants.HIGH_CAP);
        }
        return sb.toString();
    }

    @Override
    public void prepare(ParamNode args) throws Exception {

        if (args.thisData == null) {
            return;
        }

        ParamNode currData = args.thisData;
        option = currData.name;

        mcGraduate = Integer.parseInt(currData.thisData.name);
        targetCAP = Double.parseDouble(currData.thisData.thisData.name);

        //input custom mc and cap
        if (option.equals("c")) {
            currData = currData.thisData.thisData;
            totalMC = Double.parseDouble(currData.thisData.name);
            double currentCAP = Double.parseDouble(currData.thisData.thisData.name);
            totalScore = currentCAP * totalMC;
        } else if (option.equals("u")) {
            //do nothing
        } else {
            throw new CommandException();
        }
    }
}