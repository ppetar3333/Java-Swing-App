package liste.doublyLinkedList;

public class ListNode<T> {

    public T element;
    public ListNode<T> previous;
    public ListNode<T> next;

    public ListNode() {
    }

    public ListNode(T element) {
        this.element = element;
        previous = null;
        next = null;
    }

    public ListNode(T element,ListNode<T> next){
        this.element = element;
        this.next = next;
    }

    public ListNode(T element, ListNode<T> next, ListNode<T> previous){
        this.previous = previous;
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public ListNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode<T> previous) {
        this.previous = previous;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }


}