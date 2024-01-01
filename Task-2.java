public class Task {
    public static class DoublyLinkedList<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        private static class Node<T> {
            Node<T> previous;
            Node<T> next;
            T data;

            Node(T data) {
                this.data = data;
            }
        }

        public boolean isEmpty() {
            return (head == null);
        }

        public void displayList() {
            for (Node<T> start = head; start != null; start = start.next)
                System.out.println(start.data + " ");
            System.out.println();
        }

        public void displayReverseList() {
            for (Node<T> end = tail; end != null; end = end.previous)
                System.out.println(end.data + " ");
            System.out.println();
        }

        public void rangeCheck(int index) {
            if (index < 0 || index >= this.size)
                throw new IndexOutOfBoundsException();
        }

        public boolean add(T element) {
            Node<T> toAdd = new Node<>(element);
            size++;

            if (head == null) {
                head = toAdd;
                tail = toAdd;
                return true;
            }

            tail.next = toAdd;
            toAdd.previous = tail;
            tail = toAdd;

            return true;
        }

        public void add(int index, T element) {
            if (index == size) {
                add(element);
                return;
            }

            rangeCheck(index);
            Node<T> toAdd = new Node<>(element);
            size++;

            if (index == 0) {
                toAdd.next = head;
                toAdd.next.previous = toAdd;
                head = toAdd;
                return;
            }

            Node<T> insert;
            if (index < size / 2) {
                insert = head;
                for (int i = 0; i < index; i++)
                    insert = insert.next;
            } else {
                insert = tail;
                for (int i = size - 1; i > index; i--)
                    insert = insert.previous;
            }
        }

        public T remove() {
            return remove(size - 1);
        }

        public T remove(int index) {
            rangeCheck(index);

            T toReturn;
            if (index == 0) {
                toReturn = head.data;
                if (head.next != null) {
                    head.next.previous = null;
                }
                head = head.next;
                if (head == null) {
                    tail = null;
                }
            } else {
                Node<T> newNode = head;
                for (int i = 0; i < index - 1; i++) {
                    newNode = newNode.next;
                }
                toReturn = newNode.next.data;
                newNode.next = newNode.next.next;

                if (newNode.next != null) {
                    newNode.next.previous = newNode;
                } else {
                    tail = newNode;
                }
            }
            size--;
            return toReturn;
        }
    }

    String description;
    String dueDate;

    public Task(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getDescprition() {
        return this.description;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String toString() {
        return ("Task Description: " + this.description + "\nDue date: " + this.dueDate);
    }

    public static class TaskManager {
        private DoublyLinkedList<Task> taskList = new DoublyLinkedList<>();

        public void addTask(Task task) {
            taskList.add(task);
        }

        public void removeTask(Task task) {
            DoublyLinkedList.Node<Task> current = taskList.head;

            int index = 0;
            while (current != null) {
                if (current.data.equals(task)) {
                    taskList.rangeCheck(index);
                    taskList.remove(index);
                    break;
                }

                current = current.next;
                index++;
            }
        }

        public void displayTasks() {
            taskList.displayList();
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Study for Midterm", "10/05/2023");
        Task task2 = new Task("Data Structure Lab 6", "09/24/2023");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        taskManager.displayTasks();

        taskManager.removeTask(task1);
        taskManager.displayTasks();
    }
}

