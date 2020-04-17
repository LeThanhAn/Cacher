package an.cacherProject.api;

import an.cacherProject.dao.Cache;

public class CacheController {
    private final Cache cache;

    public CacheController() {
        this.cache = new Cache();
    }

    public void processInstruction(String instruction) {
        String[] instructionArray = instruction.split(" ");
        boolean instructionIsValid = validateInstruction(instructionArray);

        if(instructionIsValid) {
            String action = instructionArray[0].toLowerCase();
            switch(action) {
                case "set":
                    processSet(instructionArray);
                    break;
                case "get":
                    processGet(instructionArray);
                    break;
            }
        }
    }

    private void processSet(String[] instructionArray) {
        String key = instructionArray[1];
        String value = instructionArray[2];

        boolean instructionIsSuccess = this.cache.putValue(key, value);

        if (instructionIsSuccess) {
            System.out.println("Sucessfully set key " + key + " with value " + value);
        } else {
            System.out.println("Fail to set key " + key + " with value " + value);
        }
    }

    private void processGet(String[] instructionArray) {
        String key = instructionArray[1];
        String result = this.cache.getValue(key);

        if(result == null) {
            System.out.println("Key " + key + " does not exist in our cache");
        } else {
            System.out.println(result);
        }
    }

    private static boolean validateInstruction(String[] instructionArray) {
        boolean instructionIsValid = true;
        if (instructionArray.length == 0) {
            System.out.println("Please type something...");
            instructionIsValid = false;
        } else if (!(instructionArray[0].toLowerCase().equals("set") || instructionArray[0].toLowerCase().equals("get"))) {
            // instruction is not set or get
            System.out.println("Invalid instruction " + instructionArray[0]);
            instructionIsValid = false;
        } else if (instructionArray[0].toLowerCase().equals("set")){
            if(instructionArray.length != 3){
                System.out.println("Invalid number of parameters for \"set\" action");
                instructionIsValid = false;
            }
        } else if (instructionArray[0].toLowerCase().equals("get")){
            if(instructionArray.length != 2){
                System.out.println("Invalid number of parameters for \"get\" action");
                instructionIsValid = false;
            }
        }

        return instructionIsValid;
    }
}
