package testScripts;

public class BasicDocument extends Document
{
    public static void main(String[] args) {
        String w = new String("Hello");
        VowelState aVowelState = new VowelState(w);
        System.out.println(aVowelState.getArrayTuples());
    }

}
