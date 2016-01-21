package testScripts;

public class BasicDocument extends Document
{
    public static void main(String[] args) {
        String w = new String("Sleepy");
        VowelState aVowelState = new VowelState(w);
        aVowelState.getArrayTuples();
    }

}
