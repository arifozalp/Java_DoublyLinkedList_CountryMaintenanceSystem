
//I used enum to determine the comparison type.
enum comparison_type{great,less,equal};  

public class CountryMaintenanceSystem {
    Node head;
    Node tail;
    
    static class Node{
        Country element;
        Node next;
        Node prev;
        Node(Country s){
            element=s;
        }
    }
    //These two add functions also check whether the same country is in the list or not.
    //This function adds country to the beginning of the double linked list.
    boolean addfirst(Country element1){
        Node new_node=new Node(element1);
        Node currNode=head;
        while(currNode!=null){
            if(convertToEnglish(element1.CountryName).equals(convertToEnglish(currNode.element.CountryName)))
                return false;
            currNode=currNode.next;
        }
        
        if(head==null||tail==null){
            head=new_node;
            tail=new_node;
        }else{
            head.prev=new_node;
            new_node.next=head;
            head=new_node;
        }
        return true;
    }
    //This function adds country to the end of the double linked list.
    boolean addlast(Country element1){
        Node new_node=new Node(element1);
        Node currNode=head;
        while(currNode!=null){
            if(convertToEnglish(element1.CountryName).equals(convertToEnglish(currNode.element.CountryName)))
                return false;
            currNode=currNode.next;
        }
        
        if(head==null||tail==null){
            head=new_node;
            tail=new_node;
        }else{
            tail.next=new_node;
            new_node.prev=tail;
            tail=new_node;
        }
        return true;
    }
    //This function prints all countries in the linked list.
    void printlist(){
        Node currNode=head;
        while(currNode!=null){
            System.out.println(currNode.element.toString());
            currNode=currNode.next;
        }
    }
    //This function searches for the name of the country it received from the query in the list. If it finds it, it deletes it, if not, it prints country is not found.
    boolean deleteatCountry(String countryName){
        Node currNode=head;
        if(currNode==null){
            System.out.println("This list is empty");
            return false;
        }else if(head==tail&& convertToEnglish(currNode.element.CountryName.toUpperCase()).equals(convertToEnglish(countryName))){
            head=null;
            System.out.println("Country deleted");
            return true;
        }else{
            while(currNode!=null){
            if(convertToEnglish(currNode.element.CountryName.toUpperCase()).equals(convertToEnglish(countryName))){
                if(currNode==tail){
                    tail=currNode.prev;
                    tail.next=null;
                }else if(currNode==head){
                    head=currNode.next;
                    head.prev=null;
                }else{  
                    currNode.prev.next = currNode.next;
                    currNode.next.prev = currNode.prev;
                }
                System.out.println("Country deleted");
                return true;
               }
            currNode=currNode.next;
            }
            System.out.println("Country is not found");
            return false;
        }
    }
    //This function is called by the query and is called if the word to be compared is country.
    //The argument is compared with the elements of the countries in the list with the compareto function. When comparing, all letters in two words are capitalized and translated into English.
    void countryFunction(comparison_type compare,String argument){
        Node currNode=head;
        while(currNode!=null){
            int comparisonResult = convertToEnglish(argument.toUpperCase()).compareTo(convertToEnglish(currNode.element.CountryName.toUpperCase()));
            if ((compare == comparison_type.great && comparisonResult < 0)
                    || (compare == comparison_type.equal && comparisonResult == 0)
                    || (compare == comparison_type.less && comparisonResult > 0)) {
                System.out.println(currNode.element.toString());
            }
            currNode=currNode.next;
        }
        
    }
    //This function is called by the query and is called if the word to be compared is population.
    //The points in both strings are deleted and converted to long. Here, the difference between two longs is taken and the comparison is made according to this difference.
    void populationFunction(comparison_type compare,String argument){
        Node currNode=head;
        while(currNode!=null){
            Long comparisonResult = Long.parseLong(currNode.element.population.replace(".", ""))-Long.parseLong(argument.replace(".", ""));
            if ((compare == comparison_type.great && comparisonResult > 0)
                    || (compare == comparison_type.equal && comparisonResult == 0)
                    || (compare == comparison_type.less && comparisonResult < 0)) {
                System.out.println(currNode.element.toString());
            }
            currNode=currNode.next;
        }
    }
    //This function is called by the query and is called if the word to be compared is capital_city.
    //The argument is compared with the elements of the countries in the list with the compareto function. When comparing, all letters of two words are converted to lowercase and translated into English.
    void capital_cityFunction(comparison_type compare,String argument){
        Node currNode=head;
        while(currNode!=null){
            int comparisonResult = convertToEnglish(argument.toLowerCase()).compareTo(convertToEnglish(currNode.element.capital_city.toLowerCase()));
            if ((compare == comparison_type.great && comparisonResult < 0)
                    || (compare == comparison_type.equal && comparisonResult == 0)
                    || (compare == comparison_type.less && comparisonResult > 0)) {
                System.out.println(currNode.element.toString());
            }
            currNode=currNode.next;
        }
    }
    //This function is called by the query and is called if the word to be compared is largest_city.
    //The argument is compared with the elements of the countries in the list with the compareto function. When comparing, all letters of two words are converted to lowercase and translated into English.
    void largest_cityFunction(comparison_type compare,String argument){
        Node currNode=head;
        while(currNode!=null){
            int comparisonResult = convertToEnglish(argument.toLowerCase()).compareTo(convertToEnglish(currNode.element.largest_city.toLowerCase()));
            if ((compare == comparison_type.great && comparisonResult < 0)
                    || (compare == comparison_type.equal && comparisonResult == 0)
                    || (compare == comparison_type.less && comparisonResult > 0)) {
                System.out.println(currNode.element.toString());
            }
            currNode=currNode.next;
        }
    }
    //This function is called by the query and is called if the word to be compared is official_language.
    //The argument is compared with the elements of the countries in the list with the compareto function. When comparing, all letters of two words are converted to lowercase and translated into English.
    void official_languageFunction(comparison_type compare,String argument){
        Node currNode=head;
        while(currNode!=null){
            int comparisonResult = convertToEnglish(argument.toLowerCase()).compareTo(convertToEnglish(currNode.element.official_language.toLowerCase()));
            if ((compare == comparison_type.great && comparisonResult < 0)
                    || (compare == comparison_type.equal && comparisonResult == 0)
                    || (compare == comparison_type.less && comparisonResult > 0)) {
                System.out.println(currNode.element.toString());
            }
            currNode=currNode.next;
        }
    }
    //This function is called by the query and is called if the word to be compared is currency.
    //The argument is compared with the elements of the countries in the list with the compareto function. When comparing, all letters of two words are converted to lowercase and translated into English.
    void currencyFunction(comparison_type compare,String argument){
        Node currNode=head;
        while(currNode!=null){
            int comparisonResult = convertToEnglish(argument.toUpperCase()).compareTo(convertToEnglish(currNode.element.currency.toUpperCase()));
            if ((compare == comparison_type.great && comparisonResult < 0)
                    || (compare == comparison_type.equal && comparisonResult == 0)
                    || (compare == comparison_type.less && comparisonResult > 0)) {
                System.out.println(currNode.element.toString());
            }
            currNode=currNode.next;
        }
    }
    
    //This function converts the received word into English word.
    String convertToEnglish(String word){
        return word.replace("İ", "I")
                   .replace("Ğ", "G")
                   .replace("Ü", "U")
                   .replace("Ş", "S")
                   .replace("Ö", "O")
                   .replace("Ç", "C")
                   .replace("ı", "i")  
                   .replace("ğ", "g") 
                   .replace("ü", "u")
                   .replace("ş", "s")
                   .replace("ö", "o")
                   .replace("ç", "c");
    }
    
}
