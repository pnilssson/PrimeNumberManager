package Assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PrimeNumbers {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Integer> listOfPrimeNumbers = new ArrayList<>();
    private static boolean isPrime = false;

    public static void main(String[] args){
        start();
    }
    //Programmets startmetod, här frågas användaren efter ett val & utifrån detta körs den metod som valts.
    private static void start(){
        Boolean notExit = true;
        int menuChoice;

        while (notExit){
            System.out.print("Would you like to\n(1). Add\n(2). Sort\n(3). Search\n(4). Add x amount of primes\n(0). Exit Program\nYour choice: ");
            menuChoice = in.nextInt();
            switch (menuChoice){
                case 1: add();
                    break;
                case 2: sort();
                    break;
                case 3: search();
                    break;
                case 4: addAmountOfPrimes();
                    break;
                case 0: notExit = false;
                    break;
                default:
                    System.out.println("You can only choose (1). Add (2). Sort (3). Search (0). Exit Program");
                    start();
            }
        }
    }
    //Användaren frågas om ett tal att lägga till, är talet ett primtal & listan är tom läggs talet till i listan.
    //Om listan inte är tom & talet inte redan finns i listan kommer metoden anropa addPrimeNumbers med siffran man vill lägga till i listan om inputen är en int.
    //Metoden kommer även printa listan om den inte är tom.
    private static void add() {
        String input = "";
        Boolean doAdd = true;

        System.out.println("Choose a number to add: ");
        input = in.next();
        if (isAnInt(input)) {
            int num = Integer.parseInt(input);
            if(isPrimeNumber(num)) {
                if (listOfPrimeNumbers.size() <= 0) {
                    System.out.println(num + " is a prime number and was added to the list.");
                    listOfPrimeNumbers.add(num);
                } else {
                    for (int i = 0; i < listOfPrimeNumbers.size(); i++) {
                        if (num == listOfPrimeNumbers.get(i)) {
                            doAdd = false;
                            break;
                        }
                    }
                    if (doAdd) {
                        addPrimeNumbers(num);
                    } else {
                        System.out.println("The given input already exists.");
                    }
                }
            }else{
                System.out.println("Not a prime number.");
            }
        }else{
            System.out.println("Not a number.");
        }
        if (!listOfPrimeNumbers.isEmpty()) {
            System.out.println(listOfPrimeNumbers.toString());
        }
    }
    //Metoden kommer lägga till given parameter om parametern är en int & ett primtal i listan.
    //Metoden kommer kolla så att primtalet som läggs till inte redan finns, om så är fallet läggs det inte till.
    //Efter varje gång ett primtal läggs till adderas samtliga tal i listan, om denna summa blir ett primtal läggs även det till.
    private static void addPrimeNumbers(int num){
        int sumOfPrimes = sumOfList();
        boolean doAdd = true;

        if(isPrimeNumber(num)) {
            for (int i = 0; i < listOfPrimeNumbers.size(); i++) {
                if (num == listOfPrimeNumbers.get(i)) {
                    doAdd = false;
                    break;
                }
            }
            if (doAdd) {
                listOfPrimeNumbers.add(num);
                while(doAdd) {
                    sumOfPrimes = sumOfList();
                    if (isPrimeNumber(sumOfPrimes)) {
                        for (int i = 0; i < listOfPrimeNumbers.size(); i++) {
                            if (sumOfPrimes == listOfPrimeNumbers.get(i)) {
                                doAdd = false;
                                break;
                            }
                        }
                        if (doAdd) {
                            listOfPrimeNumbers.add(sumOfPrimes);
                        }
                    }else{
                        doAdd = false;
                    }
                }
            }
        }
    }
    //Denna metoden ger oss summan utav alla primtal i listan.
    private static int sumOfList(){
        int sum = 0;
        for(int primes : listOfPrimeNumbers){
            sum += primes;
        }
        return sum;
    }
    //Metoden kontrollerar om parametern är en int med hjälp av en try catch
    //Om metoden får en error när man försöker skapa en int utav strängen retuneras false, annars true.
    private static boolean isAnInt(String addInput){
        try{
            int i = Integer.parseInt(addInput);
        }catch (NumberFormatException | NullPointerException nfe){
            return false;
        }
        return true;
    }
    //Metoden kollar om parametern är ett primtal, om så är fallet retuneras true, annars false.
    private static boolean isPrimeNumber(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return isPrime = true;
        } else if (num % 2 == 0) {
            return isPrime = false;
        }
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return isPrime = false;
            }
        }
        return isPrime = true;
    }
    //ArrayListen sorteras med hjälp av en Shell sort.
    private static void sort(){
        if(listOfPrimeNumbers.size() != 0) {
            System.out.println("Before sorting: " + listOfPrimeNumbers.toString());
            int n = listOfPrimeNumbers.size();
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i += 1) {
                    int temp = listOfPrimeNumbers.get(i);
                    int j;
                    for (j = i; j >= gap && listOfPrimeNumbers.get(j - gap) > temp; j -= gap) {
                        listOfPrimeNumbers.set(j, listOfPrimeNumbers.get(j - gap));
                    }
                    listOfPrimeNumbers.set(j, temp);
                }
            }
            System.out.println("After sorting: " + listOfPrimeNumbers.toString());
        }else{
            System.out.println("There is nothing to sort.");
        }
    }
    //Metoden söker igenom listan, siffran användaren söker hittas i listan får man ett meddelande om att angiven siffra finns i listan.
    //Om inte angiven siffra finns i listan får man ett meddelande om detta samt att det närmsta liggande tal i listan skrivs ut.
    private static void search(){
        String numberToLookfor = "";
        Boolean numberFound = false;

        System.out.println("What number are you looking for: ");
        numberToLookfor = in.next();
        if(isAnInt(numberToLookfor)){
            int num = Integer.parseInt(numberToLookfor);
            for(int i = 0; i < listOfPrimeNumbers.size(); i++){
                if(num == listOfPrimeNumbers.get(i)){
                    numberFound = true;
                }
            }
            if(numberFound){
                System.out.println(num + " was found.");
            }else if(listOfPrimeNumbers.size() != 0){
                int distance = Math.abs(listOfPrimeNumbers.get(0) - num);
                int id = 0;
                for(int i = 1; i < listOfPrimeNumbers.size(); i++){
                    int cdistance = Math.abs(listOfPrimeNumbers.get(i) - num);
                    if(cdistance < distance){
                        id = i;
                        distance = cdistance;
                    }
                }
                int closestNumber = listOfPrimeNumbers.get(id);
                System.out.println(num + " was not found, the closest number is " + closestNumber);
            }else{
                System.out.println("There is nothing to search for.");
            }
        }else{
            System.out.println("Not a number.");
            search();
        }
    }
    //I denna metod får man frågan om hur många primtal som ska läggas till efter det högsta primtal som nu redan finns i listan.
    //Antal valda primtal som kommer efter det nuvarande högsta primtalet i listan läggs till.
    //Om listan är tom, börjar programmet med att lägga till det första primtalet(2).
    private static void addAmountOfPrimes(){
        String amountOfPrimesToAdd = "";
        int highestPrime;

        System.out.println("How many prime numbers would you like to add: ");
        amountOfPrimesToAdd = in.next();
        if(isAnInt(amountOfPrimesToAdd)){
            int primesToAdd = Integer.parseInt(amountOfPrimesToAdd);
            for(int i = 0; i < primesToAdd; i++ ) {
                if(listOfPrimeNumbers.size() < 1) {
                    highestPrime = 0;
                }else {
                    highestPrime = Collections.max(listOfPrimeNumbers);
                }
                isPrime = false;
                while (!isPrime) {
                    highestPrime = highestPrime + 1;
                    isPrimeNumber(highestPrime);
                }
                addPrimeNumbers(highestPrime);
            }
        }else{
            System.out.println("Not a number.");
            start();
        }
        System.out.println(listOfPrimeNumbers.toString());
        System.out.println("Totale number of primes in list: " + listOfPrimeNumbers.size());
    }
}
