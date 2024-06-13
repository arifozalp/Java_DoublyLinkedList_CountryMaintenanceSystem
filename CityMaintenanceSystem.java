
import java.io.File;
import java.util.Scanner;

//I use the enum here to decide whether I should add it to the beginning or the end of the list when sending it to the add function.
enum pos{first,last};

public class CityMaintenanceSystem {
    //I keep my double linklist in this class as Country Maintenance System class.
    CountryMaintenanceSystem list= new CountryMaintenanceSystem();
    //this constructor reads the input.txt file and adds it to my double linklist
    CityMaintenanceSystem(String pathwayOfınput){
        try{
            //Here I read the file line by line with the scanner. As I read each line, I check whether the line is empty and then add it to my list.
            //Finally, I print out my list.
            File file = new File(pathwayOfınput);
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()){
                String newLine = scanner.nextLine();
                
                if(newLine.trim().isEmpty())
                    continue;
                
                addFunction(spaceCatcherFunction(newLine.trim()).split(" "),pos.last);
                
            }
        }//If the input file is not found, I catch an exception.
        catch(Exception e){
            System.out.println("Your input file not found");
        }
        
        list.printlist();
    }
    
    //Here I am reading the query file line by line as in the input file.
    //When reading each line, I first check whether it is empty or not. Then, after printing the command in the file, 
    //I save the words in the line into a word-by-word array without spaces.
    void readQuery(String pathwayOfQuery){
        try{
            File file = new File(pathwayOfQuery);
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()){
                String newLine = scanner.nextLine();
                
                if(newLine.trim().isEmpty())
                    continue;
                String[] datas = newLine.split(" ");
                
                System.out.println("\n- "+newLine+"\n");
                
                newLine=newLine.replace(datas[0],"");
                
                
                //First, I check the first word of the command because the first word tells me what to do.
                //If the word is query, I send it to the query function.
                if(datas[0].toLowerCase().equals("query")){
                    QueryFunction(spaceCatcherFunction(newLine.trim()).split(" "));
                }
                //If the first word is delete, I delete the country from the list. 
                else if(datas[0].toLowerCase().equals("delete")){
                    list.deleteatCountry(datas[1].toUpperCase());
                }
                //If it is a word add, I call the add function.
                //If the country can be added to the list without any problems, it prints that it has been added.
                else if(datas[0].toLowerCase().equals("add")){
                    if(addFunction(spaceCatcherFunction(newLine.trim()).split(" "),pos.first))
                        System.out.println("A new country has been added.");
                    else
                        System.out.println("The country could not be added to the list.");
                 }else{
                    System.out.println("Your command was entered incorrectly. You can use Query, Add or Delete commands.");
                }
                //When sending, I pay attention to whether there is any space at the beginning or end, delete the first word and send it to the functions.
                
            }
        }//If the query file is not found, I catch an exception.
        catch(Exception e){ 
            System.out.println("Your query file not found");
        }
    }
    
    //In this function, I process the line containing the query command I received in the readquery function, saved in the array.
    void QueryFunction(String[] line){
        String command = line[0].toLowerCase();
        
        comparison_type compare =null;
        
        if(command.equals("print_all")){
            list.printlist();
        }else{
            //If any command other than the print function is entered too much or incompletely, an error is caught.
            try{
                if(line.length>3)
                    throw new Exception();
            //Here, if the query contains comparison, I query the comparison type and save it in the compare variable.
            if (line[1].equals(">")) {
                compare = comparison_type.great;
            } else if (line[1].equals("<")) {
                compare = comparison_type.less;
            } else if (line[1].equals("=")) {
                compare = comparison_type.equal;
            } else {
                System.out.println("Please use comparison operators. ('<', '>', '=')");
                return;
            }
            
            //Here I check the type of the word I want to compare. After determining the type of the word, I send it to the relevant function in the double linkedlist with the compare variable.
            if (command.equals("country")) {
                list.countryFunction(compare, line[2]);
            } else if (command.equals("population")) {
                try {
                    long a = 1 - Long.parseLong(line[2].replace(".", ""));
                    list.populationFunction(compare, line[2]);
                } catch (Exception e) {
                    System.out.println("The population was entered incorrectly.");
                }    
            } else if (command.equals("capital_city")) {
                list.capital_cityFunction(compare, line[2]);
            } else if (command.equals("largest_city")) {
                list.largest_cityFunction(compare, line[2]);
            } else if (command.equals("official_language")) {
                list.official_languageFunction(compare, line[2]);
            } else if (command.equals("currency")) {
                list.currencyFunction(compare, line[2]);
            } else {
                System.out.println("Please enter the correct word type you want to compare or print command. For example,(country, population, capital_city, largest_city, official_language, currency or print_all)");
            }
            
            }catch(Exception e){
                System.out.println("query entered incorrectly");
            }
        }
       
    }
    
    boolean addFunction(String[] line,pos addPos){
        
        try {
            //Here I check whether the arguments of the country to be added in the input or query are entered correctly.
            /*Errors can be:
                The country or currency may be lowercase.
                More arguments than necessary can be entered.
                2nd argument may not be population
            */
            if(line.length!=6){
               System.out.println("Number of arguments error");
               throw new Exception(); 
            }
            if(line[0].compareTo(line[0].toUpperCase())!=0){
                System.out.println("The letters of the country were not written in capital letters.");
                throw new Exception();
            }
            if(line[line.length-1].compareTo(line[line.length-1].toUpperCase())!=0){
                System.out.println("The letters of the currency were not written in capital letters.");
                throw new Exception();
            }
            
            boolean ısSecondParameterPopulation=false;
            try{
                long a= 1 - Long.parseLong(line[1].replace(".", ""));
                ısSecondParameterPopulation=true;
            }catch(Exception e) {
            }
            if(!ısSecondParameterPopulation){
               System.out.println("The population was entered incorrectly.");
               throw new Exception();
            }
            
            //If there is no error, it is added to the list with the addPos variable.
            //If there is a country to be added to the list, it cannot be added to the list.
            if (addPos == pos.first) {
                if(list.addfirst(new Country(line[0], line[1], line[2], line[3], line[4], line[5])))
                 return true;
                else{
                    System.out.println(line[0]+ " is already on the list.\n");
                    return false;
                }
            }else{
                if(list.addlast(new Country(line[0], line[1], line[2], line[3], line[4], line[5])))
                    return true;
                else{
                    System.out.println(line[0] + " is already on the list.\n");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(line[0] + " cannot add in the list\n");
            return false;
        }
    }
    //This function reduces the extra spaces between words on the line of the file we are reading to a single space.
    String spaceCatcherFunction(String word){
        StringBuffer s=new StringBuffer(word);
        char space=' ';
        for(int i=1;i<s.length();i++){
            if(s.charAt(i-1)==space&&s.charAt(i)==space){
                s.replace(i-1, i, "");
                i--;
            }
        }
        return s.toString();
    }
}
