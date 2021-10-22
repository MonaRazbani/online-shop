package controlValidValue;

import exceptions.ExceptionInput;

import java.util.Scanner;

public class ControlValidValue {
    static private Scanner scanner = new Scanner(System.in);

    public char getValidChar() {
        boolean isContinue = true;
        while (isContinue) {
            char select = scanner.next().charAt(0);
            try {
                if (isValidChar(select)) {
                    isContinue = false;
                    return select;
                } else {
                    throw new ExceptionInput("wrong model car   \n try again ");
                }

            }
            catch (ExceptionInput e) {
                System.out.println(e.getMessage());
            }
        }
        return 'e';
    }

    public boolean isValidPostCode (String postCode){
        boolean isValid = false;
        if (postCode.matches("[0-9]*") )
            if ( postCode.length() == 10)
                isValid=true;
        return isValid;
    }

    public boolean isValidChar(char select) {

        if (select == 'y'||select == 'n'){
            return true;
        }
        else return false;

    }

    public boolean isValidGender(String gender) {
        boolean isvalid = false;
        if (gender.toUpperCase().equals("MALE"))
            isvalid = true ;
        else if (gender.toUpperCase().equals("FEMALE"))
            isvalid = true ;
        return isvalid;

    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        boolean isValid=false;
        if (phoneNumber.matches("[0-9]*"))
            if ( phoneNumber.startsWith("09"))
                if (phoneNumber.length()==11)
                    isValid= true;
        return isValid;
    }


    public boolean isValidUserNationalCode(String nationalCode) {
        boolean isVAlid = false;
        if (nationalCode.matches("[0-9]*") )
            if ( nationalCode.length() == 10)
                isVAlid=true;
        return isVAlid;
    }


    public boolean isValidPassword(String name) {
        if (name.matches("[0-9]*") && name.length() > 3)
            return true;
        else
            return false;
    }

    public boolean isValidUserName(String userName) {
        if (userName.length() > 3)
            return true;
        else
            return false;
    }


    public boolean isValidName(String name) {

        if (name.matches("[a-zA-Z]*") && name.length() > 2)
            return true;
        else
            return false;
    }

}
