package com.ahmed.khanfir.varobf;

import spoon.processing.AbstractProcessor;
import spoon.refactoring.Refactoring;
import spoon.reflect.code.CtCatchVariable;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtParameter;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class VarObfProcessor extends AbstractProcessor<CtElement> {

    private Set<String> usedVarNames = new HashSet<String>();
    private Random random = new Random(10);

    public String getRandomVarName() {
        // a list of characters to choose from in form of a string

        int varNameLength = random.nextInt(40);

        String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        // creating a StringBuffer size of AlphaNumericStr

        StringBuilder s = new StringBuilder(varNameLength);

        int i;

        for (i = 0; i < varNameLength; i++) {

            //generating a random number using math.random()

            int ch = (int) (alphaNumericStr.length() * Math.random());

            //adding Random character one by one at the end of s

            s.append(alphaNumericStr.charAt(ch));

        }

        String varName = 'v' + s.toString();
        usedVarNames.add(varName);
        System.out.println("obf " + varName);
        return varName;
    }

    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return (candidate instanceof CtLocalVariable && !usedVarNames.contains(((CtLocalVariable<?>) candidate).getSimpleName()))
                || (candidate instanceof CtParameter && !usedVarNames.contains(((CtParameter<?>) candidate).getSimpleName()));
    }

    public void process(CtElement element) {
        if (element instanceof CtLocalVariable) {
            Refactoring.changeLocalVariableName((CtLocalVariable<?>) element, getRandomVarName());
        } else if (element instanceof CtParameter) {
            new CtRenameParameterRefactoring().setTarget((CtParameter<?>) element).setNewName(getRandomVarName()).refactor();
        } else if (element instanceof CtCatchVariable) {
            new CtRenameCatchParameterRefactoring().setTarget((CtCatchVariable<?>) element).setNewName(getRandomVarName()).refactor();
        }
    }
}
