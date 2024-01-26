package com.ahmed.khanfir.varobf;

import com.ahmed.khanfir.utils.TextUtils;
import spoon.Launcher;

/**
 * Starting point of the app.
 * It takes the input and the output paths as arguments.
 *
 * <p>
 * Created by ahmed on 2/3/19.
 */
public class Runner {

    private static final String INPUT_PARAM = "-i";
    private static final String OUTPUT_PARAM = "-o";
    private static final String PROCESSOR_PARAM = "-p";
    private static final String COMPILE_PARAM = "--compile";


    public static void main(String[] args) {
        if (areArgsValid(args)) {
            System.out.println("started if-reverser with args: ");
            for (String arg : args) {
                System.out.println(arg);
            }
            final String[] processorArgs = {
                    INPUT_PARAM, args[1],
                    OUTPUT_PARAM, args[3],
                    PROCESSOR_PARAM, VarObfProcessor.class.getName(),
                    COMPILE_PARAM
            };
            final Launcher launcher = new Launcher();
            launcher.setArgs(processorArgs);
            launcher.run();
        } else {
            System.out.println("Started if-reverser with args. Please pass:");
            System.out.println(INPUT_PARAM + " followed by the files to process path");
            System.out.println(OUTPUT_PARAM + " followed by the output files destination path");
        }
    }

    /**
     * Check that the passed arguments are valid.
     *
     * @param args the app starting arguments.
     * @return {@code true} if the arguments are correct, otherwise {@code false}.
     */
    private static boolean areArgsValid(String[] args) {
        return (args != null)
                && (args.length == 4)
                && (INPUT_PARAM.equals(args[0]))
                && (!TextUtils.isEmpty(args[1]))
                && (OUTPUT_PARAM.equals(args[2]))
                && (!TextUtils.isEmpty(args[3]));
    }
}
