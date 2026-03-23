import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillPort {

    public static void main(String[] args) {
        int port = 1234;

        try {
            // Find PID using the port
            Process findProcess = Runtime.getRuntime().exec("lsof -t -i:" + port);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(findProcess.getInputStream()));

            String pid = reader.readLine();

            if (pid != null) {
                System.out.println("Port " + port + " is used by PID: " + pid);

                // Kill the process
                Runtime.getRuntime().exec("kill -9 " + pid);

                System.out.println("Process killed. Port " + port + " closed.");
            } else {
                System.out.println("Port " + port + " is not in use.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
