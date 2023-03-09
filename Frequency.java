import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.isUpperCase;

public class Frequency {

    public static void main(String Args[]) throws IOException {


        // Using Scanner for Getting Input from User
        Scanner input = new Scanner(System.in);

        System.out.println("Enter true to indicate case-sensitive and false to indicate non-case-sensitive: ");
        String boolString = input.next();

        //Check if boolean string is boolean
        if(!(boolString.equals("true") || boolString.equals("false"))){
            System.out.println("Please, type in true or false.");
            return;
        }

        boolean bool = Boolean.parseBoolean(boolString);

        System.out.println("Enter file path:");
        String filePath = input.next();


        // charset for encoding
        Charset encoding = Charset.defaultCharset();

        //Get file path from scanned input and check if file exists
        Path path = Paths.get(filePath);
        if(!Files.exists(path)){
            System.out.println("File path does not exist, please try again.");
            return;
        }
        // reading all lines of file as List of strings
        List<String> lines = Files.readAllLines(path, encoding);

        // converting List<String> to palin string using java 8 api.
        String string = lines.stream().collect(Collectors.joining(""));

        //Remove special characters and spaces
        string = string.replaceAll(" ", "");
        string = string.replaceAll("[^a-zA-Z]+", "");



        // printing content from inputs.
        Frequency frequency = new Frequency();
        frequency.displayData(string, bool);

    }

    public void displayData(String string, boolean sensitive){
        HashMap<String, Integer> display;

        //Sort the HashMap containing the alphabets and their counts
        display = sortByValue(obtainContent(string, sensitive));

        String[] strings = new String[display.size()]; int[] count = new int[display.size()];
        int i = 0; int k = 0;

        //Add map key and value to an array strings and count respectively
        for (Map.Entry<String, Integer> e : display.entrySet()) {
            strings[i] = e.getKey();
            count[i] = e.getValue();
            i++;
        }

        System.out.println("");
        System.out.println("Top 10 most frequently occurring characters along with the number of occurrences:");
        System.out.println("");

        //Display the alphabets and their corresponding strings
        for(int j = strings.length - 1; j >=0; j--){

            if(k < 10){
                System.out.println(strings[j]+" ("+count[j]+")");
                k++;
            }else{
                break;
            }

        }

    }

    public HashMap<String, Integer> obtainContent(String string, boolean sensitive){

        //HashMap to contain the alphabets;
        HashMap<String, Integer> alphaMap = new HashMap<String, Integer>();

        //Convert the string to array of strings
        String[] stringArray = string.split("");

        //Loop through the string items and put in a hash map
        for(int i = 0; i < stringArray.length; i++){
            char cha = stringArray[i].charAt(0);
            String newString = stringArray[i].toLowerCase();

            if(sensitive){
                newString = stringArray[i];
            }

            if(alphaMap.containsKey(newString)){
                alphaMap.put(newString, alphaMap.get(newString) + 1);
            }else{
                alphaMap.put(newString, 1);
            }

        }

        System.out.println("");
        System.out.println("Total characters: "+ stringArray.length);

        return alphaMap;

    }

    // function to sort hashmap by values
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortedMap){
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list
                = new LinkedList<Map.Entry<String, Integer> >(
                unsortedMap.entrySet());

        // Sort the list using lambda expression
        Collections.sort(list, (i1, i2) -> i1.getValue().compareTo(i2.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
