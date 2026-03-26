package net.adoptopenjdk.test.jlm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillPort {

    public static void killPort(int port) {
        try {
            System.out.println("DEBUG: Checking port " + port);

            Process process = Runtime.getRuntime().exec("lsof -t -i:" + port);

            BufferedReader stdInput = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));

            String pid = stdInput.readLine();

            if (pid != null) {
                System.out.println("DEBUG: Found PID " + pid);

                Process kill = Runtime.getRuntime().exec("kill -9 " + pid);
                kill.waitFor();

                System.out.println("DEBUG: Port " + port + " killed");
            } else {
                System.out.println("DEBUG: No process found on port " + port);
            }

            // Print errors (VERY IMPORTANT)
            String err;
            while ((err = stdError.readLine()) != null) {
                System.out.println("ERROR: " + err);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
