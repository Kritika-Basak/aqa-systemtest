package net.adoptopenjdk.test.jlm;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillPort {

    public static void killPort(int port) {
        try {
            Process process = Runtime.getRuntime().exec("lsof -t -i:" + port);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String pid = reader.readLine();

            if (pid != null) {
                System.out.println("Port " + port + " running with PID: " + pid);

                Runtime.getRuntime().exec("kill -9 " + pid);
                System.out.println("Port " + port + " killed successfully.");
            } else {
                System.out.println("Port " + port + " is not running.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
