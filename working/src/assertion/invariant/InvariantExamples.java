package assertion.invariant;

import java.util.ArrayList;
import java.util.Arrays;

public class InvariantExamples {

    enum PetType { DOG, CAT, HAMSTER, GERBIL, PARROT }

    public static void main(String[] args) {
        InvariantExamples ex = new InvariantExamples();
        for(PetType pt: PetType.values()) {
            // Check animal description...
            int petAge = Math.random()< 0.5? 0 : (int) (Math.random()*9)+1;
            System.out.print("A " + pt + " of age " + petAge + " is called " +
                    ex.getPetTypeAgeDescription_v2(pt, petAge) + ".\t");
            System.out.println(ex.getPetTypeDietaryNeeds(pt));
        }
    }

    public String getPetTypeAgeDescription_v2(PetType petType, int petAge) {
        if (petAge < 0)
            throw new IllegalArgumentException("Age cannot be negative");

        // **Bad pratice: PRECONDITION assertion to check validity of public
        // method parameters
        assert petAge >= 0;

        String description = "ADULT";
        if(petType == PetType.CAT || petType == PetType.DOG) {
            if (petAge > 7) description = "SENIOR";
            else if (petAge < 1)
                description = (petType == PetType.CAT) ? "KITTEN" : "PUPPY";
        } else if(petType == PetType.PARROT) {
            if(petAge > 45) description = "SENIOR";
            else if (petAge <1)
                description = "CHICK";
        } else{
            // **Internal INVARIANT Assertion
            // Assumes if pet not CAT/DOG, must be GEBRIL/HAMSTER
            assert (petType == PetType.GERBIL || petType == PetType.HAMSTER):
                    "This pet is not handled for age description.";
            // if this assertion is not valid, the following assertions are
            // never checked

            if(petAge > 3) description = "SENIOR";
            else if (petAge < 1)
                description = "PUP";

        }

        // **Bad Practice: an object created ONLY for an 'asseert' statement
        // Performance overhead when assertions are disabled
        ArrayList assertArrayList = new ArrayList(
                Arrays.asList(new String[]{"ADULT", "SENIOR", "KITTEN", "PUPPY", "PUP"}));

        // **God Practice: POSTCONDITION assertion to check the result
        assert (assertArrayList.indexOf(description)> -1 && petAge++ < 50) :
                "No description exists for " + petType + " of age " + petAge;

        System.out.print("petAge+1 (" + petAge + "): ");
        // the assertion above created a SIDE effect: the output differs whether
        // assertions are enabled or not

        return description;
    }

    public String getPetTypeAgeDescription(PetType petType, int petAge) {
        if (petAge < 0)
            throw new IllegalArgumentException("Age cannot be negative");

        // **Bad pratice: PRECONDITION assertion to check validity of public
        // method parameters
        assert petAge >= 0;

        String description = "ADULT";
        if(petType == PetType.CAT || petType == PetType.DOG) {
            if(petAge > 7) description = "SENIOR";
            else if(petAge < 1)
                description = (petType == PetType.CAT)? "KITTEN" : "PUPPY";
        } else{
            // **Internal INVARIANT Assertion
            // Assumes if pet not CAT/DOG, must be GEBRIL/HAMSTER
            assert (petType == PetType.GERBIL || petType == PetType.HAMSTER):
                        "This pet is not handled for age description.";
            // if this assertion is not valid, the following assertions are
            // never checked

            if(petAge > 3) description = "SENIOR";
            else if (petAge < 1)
                description = "PUP";

        }

        // **God Practice: POSTCONDITION assertion to check the result
        assert (new ArrayList(Arrays.asList(new String[]{"ADULT", "SENIOR",
                "KITTEN", "PUPPY", "PUP"})).indexOf(description)> -1) :
                "No description exists for " + petType + " age " + petAge;

        return description;
    }

    private String getPetTypeDietaryNeeds(PetType petType) {
        switch (petType) {
            case DOG -> {
                return "Some formulary for Dog";
            }
            case CAT -> {
                return "Some formulary for Cat";
            }
            case HAMSTER -> {
                return "Some formulary for Hamster";
            }
            case GERBIL ->
            {
                return "Some formulary for Gerbil";
            }
            default -> {
                // --- Control-Flow invariant assertion ---
                // Assumption: code not reachable, all types not references
                // above
                assert false: "Formulary does not exist for " + petType;
            }
        }
        return null;
    }
}
