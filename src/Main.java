import com.prog2.datastructures.ArrayList;
import com.prog2.datastructures.DoublyLinkedList;
import com.prog2.datastructures.SinglyLinkedList;
import com.prog2.interfaces.List;

public class Main {

    void test2(){

        DoublyLinkedList x = new DoublyLinkedList();
        x.prepend("A").prepend("B").prepend("C").prepend("D");

        x.remove(0);

        printMirroredList(x);

    }

    void test(){
        DoublyLinkedList list = new DoublyLinkedList();
        //SinglyLinkedList list = new SinglyLinkedList();

        list.prepend("Frodo").prepend("Sam").prepend("Merry").prepend("Pippin");

        // expected: Pippin, Merry, Sam, Frodo
        printList(list);

        list.append("Gandalf");

        // expected: Pippin, Merry, Sam, Frodo, Gandalf
        printList(list);

        list.insert(3, "Aragorn");

        // expected: Pippin, Merry, Sam, Aragorn, Frodo, Gandalf
        printList(list);

        list.insert(2, "Legolas");

        // expected: Pippin, Merry, Legolas, Sam, Aragorn, Frodo, Gandalf
        printList(list);

        list.remove(4);

        // expected: Pippin, Merry, Legolas, Sam, Frodo, Gandalf
        printList(list);

        // expected: 3
        System.out.println(list.indexOf("Sam"));

    }

    void printList(List list){

        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("================");
    }

    void printMirroredList(List list){
        System.out.println("================");
        for (int i = list.getLength()-1; i >= 0; i--) {
            System.out.println("("+i+")"+list.get(i));
        }
        System.out.println("================");
    }


    public static void main(String[] args) {
        new Calculator().start();
    }

}
