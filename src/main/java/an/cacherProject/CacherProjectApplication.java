package an.cacherProject;

import an.cacherProject.api.CacheController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CacherProjectApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CacherProjectApplication.class, args);

		boolean stopped = false;
		CacheController cacheController = new CacheController();
		printInstructions();

		while(!stopped) {
			Scanner sc = new Scanner(System.in);

			String instruction = sc.nextLine();
			if(instruction.equals("stop")) {
				stopped = true;
			} else {
				cacheController.processInstruction(instruction);
				System.out.println();
			}
		}
	}

	public static void printInstructions() {
		System.out.println("An's Cacher is running");
		System.out.println("When you want to stop, type stop");
		System.out.println("To set a key and a value, type \"set [key] [value]\"");
		System.out.println("To get a key and a value, type \"get [key] [value]\"");
	}

}
