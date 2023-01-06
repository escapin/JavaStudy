public class EffectivelyFinal {

    public static void main(String[] args) {

        new EffectivelyFinal().method();
    }

    private void method() {
        int efinal = 42+1;

         class LocalClassInsideAMehod {
            public void printValue() {
                System.out.println("Print " + efinal);
            }
        }

        new LocalClassInsideAMehod().printValue();
    }
}
