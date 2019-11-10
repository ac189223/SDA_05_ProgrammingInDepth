package Qualified_K;

class CreditCard {

    /**
     * Method to mask the input (String) according to dynamic mask, which will:
     * not mask FIRST and LAST FOUR characters,
     * not mask anything IF input is shorter than six characters,
     * MASK all other than above DIGITS with "#",
     * never mask NON-DIGITS.
     *
     * @param creditCardNumber    string to be masked
     * @return                    masked string
     * @author =-_-=
     */
    public String maskify(String creditCardNumber) {
        // Initialize masked string
        String masked = "";
        if (creditCardNumber.length() < 6)
            // Never mask short strings
            masked = creditCardNumber;
        else {
            // Initialize mask to wear on
            String mask = createMask(creditCardNumber);
            // Initialize bulding of masked string
            StringBuilder maskedBuilder = new StringBuilder();
            // Loop over the mask
            for (int index = 0; index < mask.length(); index++) {
                // Read characters from the mask and the string to be masked
                char cc = creditCardNumber.charAt(index);
                char cm = mask.charAt(index);
                // Apply the rules
                if (cm == 'x' || !(cc >= '0' && cc <= '9'))
                    maskedBuilder.append(cc);
                else
                    maskedBuilder.append(cm);
                // Assign to the masked string
                masked = maskedBuilder.toString();
            }
        }
        // Return the masked string
        return masked;
    }

    /**
     * Private method to prepare mask for masking
     *
     * @param s                   string to be masked
     * @return                    mask
     * @author =-_-=
     */
    public String createMask(String s) {
        // Initialize the mask
        String mask = "";
        // Build the mask only for the long strings (over 5 characters)
        if (s.length() > 5) {
            // Amount of characters to be (potentially) masked
            int toBeMasked = s.length() - 5;
            // Initialize builder of the mask
            StringBuilder maskBuilder = new StringBuilder();
            // Do not mask the first character
            maskBuilder.append("x");
            // (Potentially) mask those that need to be masked
            for (int index = 0; index < toBeMasked; index++)
                maskBuilder.append("#");
            // Do not mask last four characters
            maskBuilder.append("xxxx");
            // Assign to the mask
            mask = maskBuilder.toString();
        }
        // Return the mask
        return mask;
    }
}