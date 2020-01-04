import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Scanner;

/**
 * this class analyses the performance of the other data structures we made in this ex, plus some of the
 * known collections from java.
 *
 * it uses the Scanner class to have an interface (not like that) with the user that wants to test.
 */
public class SimpleSetPerformanceAnalyzer {

    private String[] data1 = Ex4Utils.file2array("data1.txt");

    private String[] data2 = Ex4Utils.file2array("data2.txt");

    private SimpleSet[] array = new SimpleSet[5];

    /**
     * simple constructor, resets each data structure so it will be empty.
     */
    public SimpleSetPerformanceAnalyzer(){
        this.resetData();
    }

    public static void main(String[] args) {
        int decision = 0;
        SimpleSetPerformanceAnalyzer that = new SimpleSetPerformanceAnalyzer();
        Scanner scan = new Scanner(System.in);
        while (decision != 7) {
            System.out.println("\n\nwhich test do you want to run? (1/2/3/4/5/6/7)\n" +
                    "\n***(important note: if you want to run the contains test you need to first run the " +
                    "insertion tests of corresponding data as they start empty)***\n\n" +
                    "1\t-\tinsertion of all data1 into all the Data structures\n" +
                    "2\t-\tinsertion of all data2 into all the Data structures\n" +
                    "3\t-\tcontains 'hi' for each data structure after it has data1\n" +
                    "4\t-\tcontains '-13170890158' for each data structure after it has data1\n" +
                    "5\t-\tcontains '23' for each data structure after it has data2\n" +
                    "6\t-\tcontains 'hi' for each data structure after it has data2\n" +
                    "7\t-\texit");
            /* i don't check that the input is int because these tests are for us and i know to insert int */
            decision = scan.nextInt();
            switch (decision) {
                case 1:
                    that.test1();
                    break;
                case 2:
                    that.test2();
                    break;
                case 3:
                    that.test3();
                    break;
                case 4:
                    that.test4();
                    break;
                case 5:
                    that.test5();
                    break;
                case 6:
                    that.test6();
                    break;
            }
        }
    }

    /**
     * function that reset each data structure in the array if needed (for tests).
     */
    private void resetData(){
        this.array[0] = new OpenHashSet();
        this.array[1] = new ClosedHashSet();
        this.array[2] = new CollectionFacadeSet(new TreeSet<String>());
        this.array[3] = new CollectionFacadeSet(new LinkedList<String>());
        this.array[4] = new CollectionFacadeSet(new HashSet<String>());
    }

    /**
     * enum for the purpose of giving the test function only valid numbers.
     */
    private enum DataStructure {
        OPEN(0), CLOSED(1), TREE(2), LINKED(3), HASH(4);
        private final int numOfDast;
        DataStructure(int num){
            this.numOfDast = num;
        }
        public int getNumOfDast(){
            return this.numOfDast;
        }
    }

    /**
     * this function tests test1 (insert data1), it has all the tests for each specific Data structure and
     * prints for each one its running time in milliseconds.
     */
    private void test1(){
        this.resetData();
        /* Open Hash Set */
        System.out.println("the open Hash test took this amount of time (in ms) : \t\t" +
                this.insertData(DataStructure.OPEN, this.data1)/1000000);
        /* Closed Hash Set */
        System.out.println("the Closed Hash test took this amount of time (in ms) : \t" +
                this.insertData(DataStructure.CLOSED, this.data1)/1000000);
        /* Tree Set */
        System.out.println("the Tree test took this amount of time (in ms) : \t\t\t" +
                this.insertData(DataStructure.TREE, this.data1)/1000000);
        /* Linked List */
        System.out.println("the Linked list test took this amount of time (in ms) : \t" +
                this.insertData(DataStructure.LINKED, this.data1)/1000000);
        /* Hash Set */
        System.out.println("the Hash test took this amount of time (in ms) : \t\t\t" +
                this.insertData(DataStructure.HASH, this.data1)/1000000);
    }

    /**
     * inserts the data in the supplied String[] into the given data structure reference,
     * returns the amount of time it took.
     *
     * @param whatToRun     -   what test to run.
     * @param data          -   the data we want to insert (data1.txt or data2.txt)
     *
     * @return  the time it took to run the insertion.
     */
    private long insertData(DataStructure whatToRun, String[] data) {
        long timeBefore = System.nanoTime();
        for (String s : data)
            this.array[whatToRun.getNumOfDast()].add(s);
        return System.nanoTime() - timeBefore;
    }

    /**
     * this function tests test2 (insert data2), it has all the tests for each specific Data structure and
     * prints for each one its running time in milliseconds.
     */
    private void test2(){
        this.resetData();
        /* Open Hash Set */
        System.out.println("the open Hash test took this amount of time (in ms) : \t\t" +
                this.insertData(DataStructure.OPEN, this.data2)/1000000);
        /* Closed Hash Set */
        System.out.println("the Closed Hash test took this amount of time (in ms) : \t" +
                this.insertData(DataStructure.CLOSED, this.data2)/1000000);
        /* Tree Set */
        System.out.println("the Tree test took this amount of time (in ms) : \t\t\t" +
                this.insertData(DataStructure.TREE, this.data2)/1000000);
        /* Linked List */
        System.out.println("the Linked list test took this amount of time (in ms) : \t" +
                this.insertData(DataStructure.LINKED, this.data2)/1000000);
        /* Hash Set */
        System.out.println("the Hash test took this amount of time (in ms) : \t\t\t" +
                this.insertData(DataStructure.HASH, this.data2)/1000000);
    }

    /**
     * this function tests test3 (contain hi in data1), it has all the tests for each specific Data structure
     * and prints for each one its running time in nano seconds.
     */
    private void test3(){
        /* Open Hash Set */
        System.out.println("the Open Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.OPEN, "hi"));
        /* Closed Hash Set */
        System.out.println("the Closed Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.CLOSED, "hi"));
        /* Tree Set */
        System.out.println("the Tree set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.TREE, "hi"));
        /* Linked List */
        System.out.println("the Linked list test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.LINKED, "hi"));
        /* Hash Set */
        System.out.println("the Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.HASH, "hi"));
    }

    /**
     * this function tests test4 (contain weird number in data1), it has all the tests for each specific Data
     * structure and prints for each one its running time in nano seconds.
     */
    private void test4(){
        /* Open Hash Set */
        System.out.println("the Open Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.OPEN, "-13170890158"));
        /* Closed Hash Set */
        System.out.println("the Closed Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.CLOSED, "-13170890158"));
        /* Tree Set */
        System.out.println("the Tree set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.TREE, "-13170890158"));
        /* Linked List */
        System.out.println("the Linked list test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.LINKED, "-13170890158"));
        /* Hash Set */
        System.out.println("the Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.HASH, "-13170890158"));
    }

    /**
     * this function tests test5 (contain 23 in data2), it has all the tests for each specific Data
     * structure and prints for each one its running time in nano seconds.
     */
    private void test5(){
        /* Open Hash Set */
        System.out.println("the Open Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.OPEN, "23"));
        /* Closed Hash Set */
        System.out.println("the Closed Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.CLOSED, "23"));
        /* Tree Set */
        System.out.println("the Tree set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.TREE, "23"));
        /* Linked List */
        System.out.println("the Linked list test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.LINKED, "23"));
        /* Hash Set */
        System.out.println("the Hash set test took this amount of time (in nano sec) : \t" +
                this.checkContains(DataStructure.HASH, "23"));
    }

    /**
     * test 6 is exactly the same as test 3 only on a different data set but as i say in the menu of main,
     * to put data set 2 in the data structure you have to run the test that inserts all the data of it,
     * so there is no reason to make different tests, especially since this class is mostly for our use.
     *
     * so why do i eben have this method? so if someone want to choose test 6 it will do test 6 and not that
     * 3 will do 6.
     */
    private void test6(){
        this.test3();
    }

    /**
     * checks the contain method on the asked data structure on the searchVal String.
     * uses a warm up of 70000 iteration before each try.
     *
     * @param whatToRun -   the data structure we check its contain method.
     * @param searchVal -   the Strin value we search for.
     *
     * @return  the time it took in average to find the word (or admit defeat) in nano seconds.
     */
    private long checkContains(DataStructure whatToRun, String searchVal){
        long sum = 0;
        long timeBefore;
        if (whatToRun != DataStructure.LINKED) {
            for (int i = 0; i < 70000; i++)
                this.array[whatToRun.getNumOfDast()].contains(searchVal);
        }
        for (int i = 0; i < 70000; i++){
            timeBefore = System.nanoTime();
            this.array[whatToRun.getNumOfDast()].contains(searchVal);
            sum += System.nanoTime() - timeBefore;
        }
        return sum / 70000;
    }
}