package assertion;

import assertion.invariant.InvariantExamples;

public class AssertionExamples {

    static {
        boolean startupAllowed = false;

        // startupAllowed will be true ONLY if assertions are enabled
        // otherwise statement is simply ignored
        assert startupAllowed = true;

        // the following statement prevents the class from loading
        // altoghether...
        if(!startupAllowed)
            throw new RuntimeException("Assertion must be enabled!");
    }
    enum assertFlags {
        BYTE_OUT_OF_RANGE("Value is out of range for a byte");

        private String message;

        assertFlags(String message) {
            this.message = message;
        }
    }

    public static String getAssertMessage(assertFlags aflag) {
        return "Method returns: " + aflag.message;
    }

    public static void main(String[] args) {

        // -disableassertions: assertion.invariant.InvariantExamples
        // --> to disable assertions of this class
        // -da: assertion.invariant...
        // --> to disable assertions for all the class in the package
        InvariantExamples.main(args);

        byte s2;

        short maxValue = Byte.MAX_VALUE + 1;

        // assert that value is within the proper range for byte -->
        // java -enableassertions assertion.AssertionExample
        assert maxValue >= Byte.MIN_VALUE && maxValue <= Byte.MAX_VALUE:
                    getAssertMessage(assertFlags.BYTE_OUT_OF_RANGE);

        s2 = (byte) maxValue;

        System.out.println("Short value (size is 2xByte_size): " + maxValue);
        System.out.println("Byte value: " + s2);

    }
}
