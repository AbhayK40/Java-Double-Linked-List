import java.util.Arrays;
    public class TestMyDoubleLinkedList {
        public static void main(String[] args) {
        String[] name1 = { "Tom", "George", "Peter", "Jean", "Jane" };
        String[] name2 = { "George", "Michael", "Michelle", "Daniel" };
        String[] name3 = { "Tom", "Peter", "Jane", "Adam", "Peter", "Mary","David" };
        Integer[] list = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19 };
        MyDoubleLinkedList<String> list1 = new MyDoubleLinkedList<String>();
        MyDoubleLinkedList<String> temp = new MyDoubleLinkedList<>();
        
        for (int i = 0; i < name1.length; ++i){
            temp.addLast(name1[i]);
        }
        
        MyDoubleLinkedList<String> c = new
        MyDoubleLinkedList<>(Arrays.asList(name1));
        MyDoubleLinkedList<Integer> l = new MyDoubleLinkedList<>(list);
        System.out.println("Testing revrese()");
        System.out.println(l);
        l.reverse();
        System.out.println(l);
        System.out.println(temp);
        temp.reverse();
        System.out.println(temp);
        System.out.println("\nTesting addInOrder()");
        
        for (int i = 0; i < name1.length; ++i){            
            list1.addInOrder(name1[i]);
        }
        System.out.println("list1: " + list1);
        System.out.println("\nTesting overloaded constructor");
        System.out.println("name1; " + Arrays.asList(name1));
        MyDoubleLinkedList<String> list2 = new
        MyDoubleLinkedList<String>(name1);
        System.out.println("list2: " + list2);
        System.out.println("\nTesting equlas()");
        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        temp.reverse();
        System.out.println("temp: " + temp);
        System.out.println("list1 equals temp: " + (list1.equals(temp) ?
        "PASSED" : "FAILED"));
        System.out.println("list2 equals temp: " + (list2.equals(temp) ?
        "PASSED" : "FAILED"));
        MyDoubleLinkedList<String> list3 = new
        MyDoubleLinkedList<String>(name3);
        System.out.println("\nTesting list1.set(1,\"John\")");
        System.out.println("list1: " + list1);
        list1.set(1, "John");
        System.out.println("list1: " + list1);
        System.out.println("\nTesting list1.set(10,\"John\")");
        System.out.println("list1: " + list1);
        System.out.println("Indedx out of bound: " + (list1.set(10, "John") ==
        null ? "PASSESD" : "FAILED"));
        /* System.out.println("\nTesting printList() & printReverse()");
        list2.printList();
        list2.printReverse();
        */
        System.out.println("\nTesting indexOf(\"Peter\")");
        System.out.println("First index of Peter is 1: " +
        (list3.indexOf("Peter") == 1 ? "PASSED" : "FAILED"));
        System.out.println("\nTesting lastIndexOf(\"Peter\")");
        System.out.println("Last index of Peter is 4 " +
        (list3.lastIndexOf("Peter") == 4 ? "PASSED" : "FAILED"));
        System.out.println("\nTesting contains()");
        System.out.println("list1 contains Bahram is " +
        list1.contains("Bahram"));
        System.out.println("list3 contains Peter is " +
        list3.contains("Peter"));
        /* System.out.println("\nTesting getLength()");
        System.out.println("Length of list1 is 5 " + (list1.getLength() == 5 ?
        "PASSED" : "FAILED"));
        System.out.println("Length of list3 is 7 " + (list3.getLength() == 7 ?
        "PASSED" : "FAILED"));
        */
        System.out.println("\nTesting middleElemt()");
        Integer mid1 = l.middleElement();
        System.out.println(mid1+":"+(mid1.equals(10)?"PASSES":"FAILED"));
        MyDoubleLinkedList<String> m = new MyDoubleLinkedList<>(name2);
        String mid2 = m.middleElement();
        System.out.println(mid2+":"+
        (mid2.equals("Michael")?"PASSES":"FAILED"));
        System.out.println("\nTesting removeElement(\"Peter\")");
        System.out.println("list2: " + list2);
        list2.remove("Peter");
        System.out.println("list2: " + list2);
        System.out.println("\nTesting remove(1)");
        System.out.println("list3: " + list3);
        list3.remove(1);
        System.out.println("list3: " + list3);
        System.out.println("\nTesting get(2) and get(10)");
        System.out.println(list1);
        System.out.println(list1.get(2));
        System.out.println(list1.get(10));
        System.out.println("\nTesting split()");
        System.out.println(list1);
        MyDoubleLinkedList<String> list4 = list1.split();
        System.out.println(list1);
        System.out.println(list4);
        
        MyDoubleLinkedList<String> s1 = new
        MyDoubleLinkedList<>(name1);
        MyDoubleLinkedList<String> s2 = new
        MyDoubleLinkedList<>(name2);
        MyDoubleLinkedList<String> s3 = new
        MyDoubleLinkedList<>(name3);
        // Testing set operations
        System.out.println("\nTesting containAll()");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1.containsAll(s1));
        System.out.println(s1.containsAll(s2));
        System.out.println("\nTesting addAll()");
        s1.addAll(s2);
        System.out.println(s1);
        System.out.println("\nTesting retainAll()");
        s1.retainAll(s2);
        System.out.println(s1);
        System.out.println("\nTesting toArray()");
        System.out.println(Arrays.asList(s1.toArray()));
        String[] array = new String[s1.size()];
        s1.toArray(array);
        System.out.println(Arrays.asList(array));
        System.out.println("\nTesting clone()");
        MyDoubleLinkedList<String> clone =
        (MyDoubleLinkedList<String>)list1.clone();
        System.out.println(list1);
        System.out.println(clone);
    }
}