package test;

import main.Luhn;

public class LuhnTest {

    public static void main(String[] args) {
        Luhn luhn = new Luhn();

        //Some invalid card Numbers
        validIsValidLuhn(null, luhn.isValidLuhn(null));
        validIsValidLuhn("5", luhn.isValidLuhn("5"));
        validIsValidLuhn("0", luhn.isValidLuhn("0"));
        validIsValidLuhn("", luhn.isValidLuhn(""));
        validIsValidLuhn("assdfsdfsd", luhn.isValidLuhn("assdfsdfsd"));
        validIsValidLuhn("111113235234444444444444444", luhn.isValidLuhn("111113235234444444444444444")); // > 19

        //Check generateCheckDigit
        validIsValidLuhn("", luhn.isValidLuhn(luhn.generateCheckDigit("")));
        validIsValidLuhn(null, luhn.isValidLuhn(luhn.generateCheckDigit(null)));
        validIsValidLuhn("2", luhn.isValidLuhn(luhn.generateCheckDigit("2")));
        validIsValidLuhn("assdfsdfsd", luhn.isValidLuhn(luhn.generateCheckDigit("assdfsdfsd")));
        validIsValidLuhn("111113235234444444444444434", luhn.isValidLuhn(luhn.generateCheckDigit("111113235234444444444444434"))); // > 19

        //Generate some check Digits and then validate the numbers with checkdigits
        //These should generate some valid card numbers
        String cardNumber = "4100450129598" ;
        String validCardNumber =cardNumber +  luhn.generateCheckDigit(cardNumber);
        validIsValidLuhn(validCardNumber , luhn.isValidLuhn(validCardNumber));
        cardNumber = "41004501295924444";
        validCardNumber =cardNumber +  luhn.generateCheckDigit(cardNumber);
        validIsValidLuhn(validCardNumber  , luhn.isValidLuhn(validCardNumber));
        cardNumber = "41004501295924445";
        validCardNumber =cardNumber +  luhn.generateCheckDigit(cardNumber);
        validIsValidLuhn(validCardNumber  , luhn.isValidLuhn(validCardNumber));
        cardNumber = "41003332222124444";
        validCardNumber =cardNumber +  luhn.generateCheckDigit(cardNumber);
        validIsValidLuhn(validCardNumber  , luhn.isValidLuhn(validCardNumber));

        //we know two numbers are valid
        validateRange(luhn.countRange("41004501295987", "41004501296000"), 2);
        validateRange(luhn.countRange("410045012959244446", "410045012959244453"), 2);
    }

    private static void validIsValidLuhn(String cardNumber, boolean isValid) {
        System.out.println(" The card Number -> " + cardNumber + " is " + (isValid ? "valid" : "invalid"));
    }

    private static void validateRange(int range, int expectedRange) {
        if (range == expectedRange) {
            System.out.println(" Actual range matches expectedRange");
        } else {
            //Error , need to check the code
            System.out.println(" Actual Range " + range + "  doesnt match expected range " + expectedRange);
        }
    }
}
