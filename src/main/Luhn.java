package main;

public class Luhn {

    /**
     * Making some assumptions
     * 1) Max card Number is 19
     * 2) We dont have single digit card numbers
     */

    /**
     * Accepts a card number and determines if the card number is a valid number with
     * respect to the
     * Luhn algorithm.
     *
     * @param cardNumber the card number
     * @return true if the card number is valid according to the Luhn algorithm, false
     * if not
     */

    public boolean isValidLuhn(String cardNumber) {
        if (!validCardNumber(cardNumber, 19)) {
            return false;
        }
        // If the sum is mod of 10 then its valid , else its invalid

        return (getLuhnSum(cardNumber) % 10 == 0);
    }

    /**
     * Accepts a partial card number (excluding the last digit) and generates the
     * appropriate Luhn
     * check digit for the number.
     *
     * @param cardNumber the card number (not including a check digit)
     * @return the check digit
     */

    public String generateCheckDigit(String cardNumber) {
        if (!validCardNumber(cardNumber, 18)) { // since one digit is missing
            return "";
        }
        cardNumber = cardNumber + '0'; // '0' is check digit
        /* From wikipedia 1) Compute the sum of the sum digits (128).
          2) Take the units digit (8).
          3) Subtract the units digit from 10.
          4) The result (2) is the check digit. In case the sum of digits ends in 0 then 0 is the check digit. */
        int checkDigit = getLuhnSum(cardNumber) % 10; // reminder is usually the unit digit
        //If 0 return 0;
        if (checkDigit > 0) {
            checkDigit = 10 - checkDigit;
        }
        return "" + checkDigit;
    }

    /**
     * Accepts two card numbers representing the starting and ending numbers of a
     * range of card numbers
     * and counts the number of valid Luhn card numbers that exist in the range,
     * inclusive.
     *
     * @param startRange the starting card number of the range
     * @param endRange   the ending card number of the range
     * @return the number of valid Luhn card numbers in the range, inclusive
     */

    public int countRange(String startRange, String endRange) {
        int numberOfValid = 0;
        if (!validCardNumber(startRange, 19) || !validCardNumber(endRange, 19)) {
            return 0;
        }
        // Assuming Long as  cards are at the max 19 digits
        try {
            long startRangeLong = Long.parseLong(startRange);
            long endRangeLong = Long.parseLong(endRange);

            while (startRangeLong <= endRangeLong) {
                if (isValidLuhn(String.valueOf(startRangeLong))) {
                    numberOfValid++;
                }
                startRangeLong++;
            }
        } catch (NumberFormatException e) {
            // Number Format - Dont do anything
            //May be log
        }

        return numberOfValid;
    }

    private boolean validCardNumber(String cardNumber, int maxNumber) {
        // Assuming that single digit card numbers are invalid
        // As luhn alogrithm require atleast two digits
        // And maximum cardNumber is 19
        if (cardNumber == null || cardNumber.length() <= 1 || cardNumber.length() > maxNumber) {
            return false;
        }

        return true;
    }

    /**
     * Found this information on Wikipedia
     * Sum is calculated in following way:
     * Double every second digit, from the rightmost: (1×2) = 2, (8×2) = 16, (3×2) = 6, (2×2) = 4, (9×2) = 18
     * Sum all the individual digits
     * (digits in parentheses are the products from Step 1): x + (2) + 7 + (1+6) + 9 + (6) + 7 + (4) + 9 + (1+8) + 7 = x + 67.
     *
     * @param cardNumber
     * @return sum
     */

    private int getLuhnSum(String cardNumber) {
        boolean secondDigit = false; // set it to false
        int sum = 0; // sum
        try {
            //Double every digit from the rightmost
            for (int i = cardNumber.length() - 1; i >= 0; i--) {
                int digit = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
                if (secondDigit) {
                    //mutiply by 2; Double the digit
                    digit = digit * 2;
                    if (digit > 9) { //two digits
                        // split it and sum - 17 / 10 = 1 quotient and 7 reminder -> 1+ 7 = 8
                        digit = digit / 10 + digit % 10;
                    }
                    //Set to false , so that the next one will be skipped
                    secondDigit = false;
                } else {
                    // every other iteration set to true.
                    secondDigit = true;
                }
                sum += digit;
            }
            return sum;
        } catch (NumberFormatException e) {
            //Number format exception;
            return 1;
        }
    }
}
