import java.util.*;


public class SimpleSetPerformanceAnalyzer {
    private static final String PATH_DATA1 = "data1.txt";
    private static final String PATH_DATA2 = "data2.txt";
    private static final int ITERATIONS =70000;
    private static final int ITERATIONS_OF_LINKED_LIST=7000;
    private static final long TIME_UNIT_DIVIDE = 1000000;//million

    /**
     * Compares the performance of different collection types
     *
     * @param args Program arguments (not relevant)
     */
    public static void main(String[] args) {
        String[] data1_content = Ex3Utils.file2array(PATH_DATA1);
        String[] data2_content = Ex3Utils.file2array(PATH_DATA2);
        Test1(data1_content);
        Test2(data2_content);
        Test3(data1_content);
        Test4(data1_content);
        Test5(data2_content);
        Test6(data2_content);
    }

    /**
     * Adding all the words in data1.txt, one by one, to each of the data structures (with default
     * initialization) in separate. This time should be measured in milliseconds (1ms = 10−3
     * s)
     *
     * @param content-the word from the data
     */
    private static void Test1(String[] content) {
        System.out.println("Test 1 for the 'data1.txt':");
        Test1and2(content);
        System.out.println("Finished test 1 for the 'data1.txt':");
    }

    /**
     * The same as Test 1 for data2.txt. Again - in millisecond
     *
     * @param content-the word from the data
     */
    private static void Test2(String[] content) {
        System.out.println("Test 2 for the 'data2.txt':");
        Test1and2(content);
        System.out.println("Finished test 2 for the 'data2.txt':");
    }

    /**
     * mutual test for test 1 and test 2
     *
     * @param content-the word from the data
     */
    private static void Test1and2(String[] content) {
        SimpleSet closedHashSet = new ClosedHashSet();
        SimpleSet classicHashSet = new CollectionFacadeSet(new HashSet<>());
        SimpleSet linkedList = new CollectionFacadeSet(new LinkedList<>());
        SimpleSet openHashSet = new OpenHashSet();
        SimpleSet treeSet = new CollectionFacadeSet(new TreeSet<>());
        long timeBefore;
        long runTime;
        /*
         * test the closedHashSet
         */
        timeBefore = System.nanoTime();
        for (String str : content) {
            closedHashSet.add(str);
        }
        runTime = (System.nanoTime() - timeBefore) / TIME_UNIT_DIVIDE;
        System.out.println("in closed Hash Set the add took : " + runTime + " milliseconds");
        /*
         * test the classicHashSet
         */
        timeBefore = System.nanoTime();
        for (String str : content) {
            classicHashSet.add(str);
        }
        runTime = (System.nanoTime() - timeBefore) / TIME_UNIT_DIVIDE;
        System.out.println("in classic Hash Set the add took : " + runTime + " milliseconds");
        /*
         * test the linkedList
         */
        timeBefore = System.nanoTime();
        for (String str : content) {
            linkedList.add(str);
        }
        runTime = (System.nanoTime() - timeBefore) / TIME_UNIT_DIVIDE;
        System.out.println("in linked List the add took : " + runTime + " milliseconds");
        /*
         * test the openHashSet
         */
        timeBefore = System.nanoTime();
        for (String str : content) {
            openHashSet.add(str);
        }
        runTime = (System.nanoTime() - timeBefore) / TIME_UNIT_DIVIDE;
        System.out.println("in open Hash Set the add took :" + runTime + "milliseconds");
        /*
         * test the treeSet
         */
        timeBefore = System.nanoTime();
        for (String str : content) {
            treeSet.add(str);
        }
        runTime = (System.nanoTime() - timeBefore) / TIME_UNIT_DIVIDE;
        System.out.println("in tree Set the add took :" + runTime + "milliseconds");
    }

    /**
     * For each data structure, perform contains("hi") when it’s initialized with data1.txt. Note
     * that "hi" has a different hashCode than the words in data1.txt. All ’contains’ operations
     * should be measured in nanoseconds (1ns = 10−9
     * s)
     *
     * @param content-the word from the data
     */
    private static void Test3(String[] content) {
        System.out.println("Test 3 for the 'data1.txt' -contains('hi'):");
        TestOfContainers(content,"hi");
        System.out.println("Finished test 3 for the 'data1.txt':");
    }

    /**
     * For each data structure, perform contains("-13170890158") when it is initialized with the
     * words in data1.txt. Note that "-13170890158" has the same hashCode as all the words in
     * data1.txt
     *
     * @param content-the word from the data
     */
    private static void Test4(String[] content) {
        System.out.println("Test 4 for the 'data1.txt' -contains('-13170890158'):");
        TestOfContainers(content,"-13170890158");
        System.out.println("Finished test 4 for the 'data1.txt'.");
    }

    /**
     * For each data structure, perform contains("23") when it’s initialized with data2.txt. Note
     * that "23" appears in data2.tx
     *
     * @param content-the word from the data
     */
    private static void Test5(String[] content) {
        System.out.println("Test 5 for the 'data1.txt' -contains('23'):");
        TestOfContainers(content,"23");
        System.out.println("Finished test 5 for the 'data1.txt'.");
    }

    /**
     * For each data structure, perform contains("hi") when it’s initialized with data2.txt. "hi"
     * does not appear in data2.txt
     *
     * @param content-the word from the data
     */
    private static void Test6(String[] content) {
        System.out.println("Test 6 for the 'data2.txt' -contains('hi'):");
        TestOfContainers(content,"hi");
        System.out.println("Finished test 6 for the 'data2.txt'.");
    }

    /**
     * run warm up iterations and test the running time
     * @param content- the values of the data
     * @param searchVal -the word to seacrh
     */
    private static void TestOfContainers(String[] content, String searchVal){
        SimpleSet closedHashSet = new ClosedHashSet();
        SimpleSet classicHashSet = new CollectionFacadeSet(new HashSet<>());
        SimpleSet linkedList = new CollectionFacadeSet(new LinkedList<>());
        SimpleSet openHashSet = new OpenHashSet();
        SimpleSet treeSet = new CollectionFacadeSet(new TreeSet<>());
        long timeBefore;
        long runTime;
        /*
         * test the closedHashSet
         */
        for(String word:content)
        {
            closedHashSet.add(word);
        }
        for(int i=0;i<ITERATIONS;i++)
        {
            closedHashSet.contains(searchVal);
        }
        timeBefore = System.nanoTime();
        for(int i=0;i<ITERATIONS;i++)
        {
            closedHashSet.contains(searchVal);
        }
        runTime = (System.nanoTime() - timeBefore) / (ITERATIONS);
        System.out.println("in closed Hash Set the contain took : " + runTime + " Nanoseconds");
        /*
         * test the classicHashSet
         */
        for(String word:content)
        {
            classicHashSet.add(word);
        }
        for(int i=0;i<ITERATIONS;i++)
        {
            classicHashSet.contains(searchVal);
        }
        timeBefore = System.nanoTime();
        for(int i=0;i<ITERATIONS;i++)
        {
            closedHashSet.contains(searchVal);
        }
        runTime = (System.nanoTime() - timeBefore) / (ITERATIONS);
        System.out.println("in classic Hash Set the contain took : " + runTime + " Nanoseconds");
        /*
         * test the linkedList
         */
        for(String word:content)
        {
            linkedList.add(word);
        }
        for(int i=0;i<ITERATIONS_OF_LINKED_LIST;i++)
        {
            linkedList.contains(searchVal);
        }
        timeBefore = System.nanoTime();
        for(int i=0;i<ITERATIONS_OF_LINKED_LIST;i++)
        {
            linkedList.contains(searchVal);
        }
        runTime = (System.nanoTime() - timeBefore) / ((ITERATIONS_OF_LINKED_LIST));
        System.out.println("in linked List the contain took : " + runTime + " Nanoseconds");
        /*
         * test the openHashSet
         */
        for(String word:content)
        {
            openHashSet.add(word);
        }
        for(int i=0;i<ITERATIONS;i++)
        {
            openHashSet.contains(searchVal);
        }
        timeBefore = System.nanoTime();
        for(int i=0;i<ITERATIONS;i++)
        {
            openHashSet.contains(searchVal);
        }
        runTime = (System.nanoTime() - timeBefore) / (ITERATIONS);
        System.out.println("in open Hash Set the contain took : " + runTime + " Nanoseconds");
        /*
         * test the treeSet
         */
        for(String word:content)
        {
            treeSet.add(word);
        }
        for(int i=0;i<ITERATIONS;i++)
        {
            treeSet.contains(searchVal);
        }
        timeBefore = System.nanoTime();
        for(int i=0;i<ITERATIONS;i++)
        {
            treeSet.contains(searchVal);
        }
        runTime = (System.nanoTime() - timeBefore) / (ITERATIONS);
        System.out.println("in tree Set the contain took : " + runTime + " Nanoseconds");
    }
}
