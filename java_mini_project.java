// Author: J Manikanth Reddy
// roll no. 100523733024
// cse 3rd sem
import java.util.Scanner;

class JavaMiniproject {

    static int[] getUserInput() {
        int[] dob = new int[4];
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------");
        System.out.println("Enter 1 to select DOB= YYdlcMMdlcDD (International format)");
        System.out.println("Enter 2 to select DOB= DDdlcMMdlcYY (Indian format)");
        System.out.println("Enter 3 to select DOB= MMdlcDDdlcYY (US format)");
        System.out.println("Enter 4 to select AGE= YYdlcMMdlcDD");
        System.out.println("Allowed dlc are '-' or '/' ");
        System.out.println("----------------------------------------------------------");
        System.out.print("Enter selection: ");
        String inputStr = sc.nextLine();
        int selection = 0;
        try {
            selection = Integer.parseInt(inputStr);
        } catch (NumberFormatException err) {
            System.out.println("Enter a valid selection!");
            return getUserInput();
        }

        if (selection != 4) {
            System.out.print("Enter DOB: ");
        } else {
            System.out.print("Enter AGE: ");
        }
        String dateStr = sc.nextLine();
        System.out.println("----------------------------------------------------------");
        String[] input = new String[3];
        if (dateStr.contains("-") || dateStr.contains("/")) {
            input = dateStr.trim().split("-|\\/");
        } else {
            System.out.println("Enter a valid delimiter!");
            return getUserInput();
        }

        try {
            switch (selection) {
                case 1:
                    dob[0] = Integer.parseInt(input[0]);
                    dob[1] = Integer.parseInt(input[1]);
                    dob[2] = Integer.parseInt(input[2]);
                    break;
                case 2:
                    dob[0] = Integer.parseInt(input[2]);
                    dob[1] = Integer.parseInt(input[1]);
                    dob[2] = Integer.parseInt(input[0]);
                    break;
                case 3:
                    dob[0] = Integer.parseInt(input[2]);
                    dob[1] = Integer.parseInt(input[0]);
                    dob[2] = Integer.parseInt(input[1]);
                    break;
                case 4:
                    dob[0] = Integer.parseInt(input[0]);
                    dob[1] = Integer.parseInt(input[1]);
                    dob[2] = Integer.parseInt(input[2]);
                    break;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
            System.out.println("Enter a valid input!");
            return getUserInput();
        }
        dob[3] = (selection == 4) ? 1 : 0;

        return dob;
    }

    static int[] getValidInput() {
        int valid = 0;
        int[] input = new int[4];
        boolean leapYear;
        while (valid == 0) {
            input = getUserInput();
            leapYear = ((input[0] % 4 == 0) && (input[0] % 100 != 0 || input[0] % 400 == 0));
            if (input[3] == 0) {
                if ((input[2] == 29 && !leapYear) || ((input[1] == 9 || input[1] == 11 || input[1] % 2 == 0) && input[2] > 30 && input[1] != 2)
                        || ((input[1] == 8 || input[1] == 10 || input[1] == 12 || input[1] % 2 != 0) && input[2] > 31 && input[1] != 2)
                        || (input[0] < 0 || input[1] < 0 || input[2] < 0)) {
                    System.out.println("Enter a valid DOB!");
                } else {
                    valid = 1;
                }
            } else {
                if (input[0] < 0 || input[1] < 0 || input[2] < 0) {
                    System.out.println("Enter a valid AGE!");
                } else {
                    valid = 1;
                }
            }
        }
        return input;
    }

    public static void main(String[] args) {
        int[] input = getValidInput();
        int year = input[0];
        int month = input[1];
        int day = input[2];
        int currentYear = 2024;
        int currentDay = 20;
        int currentMonth = 10;
        int years = 0;
        int months = 0;
        int days = 0;
        int leapDays = 0;
        for (int i = year; i <= currentYear; i++) {
            if ((i % 4 == 0) && (i % 100 != 0 || i % 400 == 0)) {
                leapDays++;
            }
        }
        if (year >= currentYear) {
            years = 0;
            months = 0;
            days = 0;
        } else {
            if (month < currentMonth) {
                years = currentYear - year;
            } else {
                if (currentYear != year && currentMonth != month) {
                    years = currentYear - year - 1;
                    currentMonth = currentMonth + 12;
                } else {
                    years = currentYear - year;
                }
            }

            if (day <= currentDay) {
                days = currentDay - day;
                months = currentMonth - month;
            } else {
                if (month == 9 || month == 11 || month % 2 == 0) {
                    days = (30 - day) + currentDay;
                    months = currentMonth - month - 1;
                } else if (month == 8 || month == 10 || month == 12 || month % 2 == 1) {
                    days = (31 - day) + currentDay;
                    months = currentMonth - month - 1;
                } else if (month == 2) {
                    days = (28 - day) + currentDay + leapDays;
                    months = currentMonth - month - 1;
                }
            }
        }
        if (input[3] == 0) {
            System.out.println("Your age is: " + years + " years " + months + " months " + days + " days");
        } else {
            System.out.println("Your DOB is: " + days + "/" + months + "/" + years);
        }
        System.out.println("----------------------------------------------------------");
    }
}
