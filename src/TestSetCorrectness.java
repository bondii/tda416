import java.util.TreeSet;

/**
 * Created by Bondi on 2016-02-09.
 */
public class TestSetCorrectness {

    public static void main(String[] args) {//int n1, int n2, int n3, int n4) {
        int n1 = 0;
        int n2 = 100000; //tests
        int n3 = 100;
        int n4 = 20;

        boolean testing = false;

        System.out.println("Starting tests");
        for (int i = n2; i != 0; i--) {
            //System.out.println("New round woop");
            TreeSet referenceSet = new TreeSet();
            SimpleSet testSet = (n1 == 1 ? new SortedLinkedListSet() : new SplayTreeSet());

            for (int j = n3; j != 0; j--) {

                    /*
                    // slumpa fram n4 st tal
                    int[] numbers = new int[n4];
                    for (int k = 0; k < n4; k++) {
                        numbers[k] = k;
                    }
                    */

                // slumpa vilket test
                if (testing) {
                    //System.out.println("New single test, number " + j);
                }

                int rnd = (int) (Math.random() * 4);
                if (rnd == 0) {
                    int refSize = referenceSet.size();
                    int testSize = testSet.size();
                    if (testing || refSize != testSize) {
                        System.out.println("Testing size");
                        error("RefSize: " + refSize + " TestSize: " + testSize);
                    }
                } else if (rnd == 1) {
                    int index = (int) (Math.random() * n4);
                    boolean refAdd = referenceSet.add(index);
                    boolean testAdd = testSet.add(index);
                    if (testing || refAdd != testAdd) {
                        System.out.println("testing add");
                        error("Number: " + index + " RefAdded: " + refAdd + " TestAdded: " + testAdd);
                    }
                } else if (rnd == 2) {
                    int index = (int) (Math.random() * n4);
                    boolean refRem = referenceSet.remove(index);
                    boolean testRem = testSet.remove(index);
                    if (testing || refRem != testRem) {
                        System.out.println("testing remove");
                        error("Number: " + index + " RefRemoved: " + refRem + " TestRemoved: " + testRem);
                    }
                } else {
                    int index = (int) (Math.random() * n4);
                    boolean refCon = referenceSet.contains(index);
                    boolean testCon = testSet.contains(index);
                    if (testing || refCon != testCon) {
                        System.out.println("testing contains");
                        error("Number: " + index + " RefContains: " + refCon + " TestContains: " + testCon);
                    }
                }
            }
        }

        System.out.println("Done with test!");

        return;
    }

    public static void error(String error) {
        System.out.println(error);
    }

}
