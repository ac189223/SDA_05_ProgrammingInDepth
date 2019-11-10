package Qualified_K;

class Challenge {

    /**
     * Method converts a number into a string with the correct ordinal indicator suffix
     * ordinals are in English
     *
     * @param number           the number to be converted (0 <= number <= 10000)
     * @return                 string ordinal correspondent to the number
     * @author =-_-=
     */
    public String numberToOrdinal( Integer number ) {
        // Initialize string corresponding to the number
        String numberStr = String.valueOf(number);
        // Apply conditions and add suffix for non-zero values
        if (number != 0)
            if (numberStr.length() == 1 || (numberStr.length() > 1 && numberStr.charAt(numberStr.length() - 2) != '1')) {
                switch (numberStr.charAt(numberStr.length() - 1)) {
                    case '1':
                        numberStr += "st";
                        break;
                    case '2':
                        numberStr += "nd";
                        break;
                    case '3':
                        numberStr += "rd";
                        break;
                    default:
                        numberStr += "th";
                        break;
                }
            } else {
                // This line is adding suffix for numbers ending with sth from  scope <10, 19>
                numberStr += "th";
            }
        // Return ordinal indicator with suffix in english
        return numberStr;
    }
}